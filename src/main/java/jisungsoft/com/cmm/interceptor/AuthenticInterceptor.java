package jisungsoft.com.cmm.interceptor;

import egovframework.com.cmm.service.Globals;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.model.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 인증여부 체크 인터셉터
 */
@Slf4j
public class AuthenticInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Environment environment;

    /**
     * CMS 접근 권한 패턴 목록
     */
    private List<String> adminAuthPatternList;
    /**
     * 로그인 접근 권한 패턴 목록
     */
    private List<String> loginAuthPatternList;
    /**
     * CMS인증여부
     */
    boolean adminAuthUrlPatternMatcher;
    /**
     * 로그인 패턴
     */
    boolean loginAuthUrlPatternMatcher;
    /**
     * 로그인 사용자 정보 및 권한
     */
    private LoginVO authenticatedUser;

    /**
     * 인증된 사용자 여부로 인증 여부를 체크한다.
     * 관리자 권한에 따라 접근 페이지 권한을 체크한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> inputFlashMap = (Map<String,Object>) RequestContextUtils.getInputFlashMap(request);
        HashMap<String, Object> data = new HashMap<String, Object>();

        //redirect시 data input
        if(inputFlashMap!=null && inputFlashMap.isEmpty()==false) {
            Map.Entry<String,Object> entry = null;
            Iterator<Map.Entry<String,Object>> iterator = inputFlashMap.entrySet().iterator();
            while(iterator.hasNext()){
                entry = iterator.next();
                data.put(entry.getKey(), entry.getValue());
            }
        }

        Enumeration params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String names = (String) params.nextElement();
            data.put(names, request.getParameter(names.replaceAll("<", "&lt;").replaceAll(">", "&gt;")));
        }

        /* 프로토콜 */
        data.put("requestURL", new String(request.getRequestURL()));
        /* 서버명 */
        data.put("serverName", request.getServerName());
        /* 웹요청포트 */
        data.put("serverPort", Integer.toString(request.getServerPort()));
        /* 실제경로 */
        data.put("realPath", request.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/"));
        /* 요청경로 */
        data.put("servletPath", request.getServletPath());
        /* 요청경로 */
        data.put("strePath", request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/")+1));
        /* 파라메터 */
        data.put("queryString", request.getQueryString());
        /* 파라메터 없을 경우 공백처리 */
        if (data.get("queryString") == null) {
            data.put("queryString", "");
        }

        /* 접속통계 */
        String ua = request.getHeader("User-Agent").toLowerCase();
        if (ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")
                || ua.substring(0, 4).matches(
                "(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
            data.put("platform", "M");
        } else {
            data.put("platform", "P");
        }

        //인증된사용자 여부
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        //AntPathRequestMatcher
        AntPathRequestMatcher antPathRequestMatcher = null;
        loginAuthUrlPatternMatcher = false;
        for(String authPattern : loginAuthPatternList) {
            antPathRequestMatcher = new AntPathRequestMatcher(authPattern);
            if(antPathRequestMatcher.matches(request)) {
                loginAuthUrlPatternMatcher = true;
            }
        }
        //권한 없을 경우 로그인 페이지로 이동처리
        if (!loginAuthUrlPatternMatcher) {
            if (!isAuthenticated) {
                ModelAndView modelAndView = new ModelAndView("redirect:"+Globals.USR_LOGIN_PAGE);
                throw new ModelAndViewDefiningException(modelAndView);
            }
        }

        //        List<String> authorities = UserDetailsHelper.getAuthorities();
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        //로그아웃시에 사용할 데이터
        if (authenticatedUser != null) {
            data.put("userSe", authenticatedUser.getUserSe());
        }

        if (data.get("category") == null) {
            data.put("category", "MES");
        }

        request.setAttribute("data", data);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HashMap<String, Object> dataMap = (HashMap) request.getAttribute("data");

        //인증된사용자 여부
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        String auth = AuthorityCode.ROLE_ANONYMOUS.name();
        if (isAuthenticated) {
            auth = authenticatedUser.getAuthority();
        }

        String requestURI = dataMap.get("servletPath").toString();
//        MenuManageVO menuManageVO = new MenuManageVO();
//        menuManageVO.setAuthorCode(auth);
//        menuManageVO.setMenuCategory((String) dataMap.get("category"));

        if (!adminAuthUrlPatternMatcher) {
            //메인
            if (requestURI.equals("/page/index.do")) {
//                List<?> popupList = cmmUseService.selectPopupList(new PopupVO());
//                int popupCnt      = cmmUseService.selectPopupListCnt(new PopupVO());
//                modelAndView.addObject("popupDepth", popupList);
//                modelAndView.addObject("popupCnt", popupCnt);
            }
        }
        if (isAuthenticated) {
//            NoteVO noteVO = new NoteVO();
//            noteVO.setRcverId(authenticatedUser.getUniqId());
//            List<?> noteList = cmmUseService.selectNoteList(noteVO);
//            int noteCnt = cmmUseService.selectNoteListCnt(noteVO);
//            modelAndView.addObject("noteList", noteList);
//            modelAndView.addObject("noteCnt", noteCnt);
        }

//        modelAndView.addObject("data", dataMap);
        modelAndView.addObject("authenticatedUser", authenticatedUser);

        super.postHandle(request, response, handler, modelAndView);
    }

    public void setAdminAuthPatternList(List<String> adminAuthPatternList) {
        this.adminAuthPatternList = adminAuthPatternList;
    }

    public void setLoginAuthPatternList(List<String> loginAuthPatternList) {
        this.loginAuthPatternList = loginAuthPatternList;
    }
}
