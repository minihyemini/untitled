package jisungsoft.com.cop.sms.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.property.EgovPropertyService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.CommonLibrary;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.sms.service.SmsService;
import jisungsoft.com.cop.sms.service.SmsVO;
import jisungsoft.com.service.LoginService;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

//@RestController
//@RequestMapping("/{path}/cop/sms")
public class SendSmsRestController {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SendSmsRestController.class);

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    /**
     * Common Use Service
     */
//    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;
    /**
     * Login Service
     */
//    @Resource(name = "loginService")
    private LoginService loginService;

    /**
     * SMS Service
     */
//    @Resource(name = "smsService")
    private SmsService smsService;
    /**
     * 메세지 서비스
     */
//    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    /**
     * 공통 라이브러리
     */
//    @Resource(name = "commonLibrary")
    private CommonLibrary commonLibrary;

    private final String SESSION_AUTH_KEY = "S_KEY_";

    /**
     * 사용자 주소록 목록
     * @param commandMap
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/list.json")
    public ModelAndView userList(@RequestParam Map<String, Object> commandMap, CmmDetailUser cmmDetailUser) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        //일반 사용자
        cmmDetailUser.setUserSe("GNR");
        //모바일 번호 조회
        cmmDetailUser.setOpenMbtlnum(true);
        List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);

        modelAndView.addObject("resultList", userList);
        return modelAndView;
    }

    /**
     * SMS 발송
     * @param smsVO
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sndngSmsInsert.json")
    public ModelAndView sndngSmsInsert(SmsVO smsVO, @RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sms.send"));
            return modelAndView;
        }

        LOGGER.debug(smsVO.toString());
        smsService.insertSms(smsVO);
        modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.sms.send"));

        return modelAndView;
    }

    /**
     * 엑셀 주소록 불러오기
     * @param commandMap
     * @param multipartRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fileRead.json")
    public ModelAndView fileRead(@RequestParam Map<String, Object> commandMap, MultipartHttpServletRequest multipartRequest) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String path = "";
        String fullPath = "";

        path = EgovProperties.getProperty("Globals.fileStorePath") + EgovProperties.getProperty("Globals.filePath.fileRead");
        commonLibrary.mkdirs(path, false);

        MultipartFile attach = multipartRequest.getFile("attach");
        String originalFileName = attach.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        fullPath = path + originalFileName;

        String content = "";
        if (!attach.isEmpty()) {
            try {
                byte[] bytes = attach.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
                stream.write(bytes);
                stream.close();

                if (".xlsx".equals(extension.toLowerCase())) {
                    JSONArray jsonArray = commonLibrary.excelRead(fullPath);
                    modelAndView.addObject("jsonArray", jsonArray);

                } else {
                    modelAndView.addObject("resultMsg", "파일 형식이 잘못되었습니다.(" + extension + ")");
                    return modelAndView;
                }

                return modelAndView;
            } catch (Exception e) {
                modelAndView.addObject("resultMsg", "파일 업로드에 실패했습니다.");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("resultMsg", "파일 업로드가 되지 않았습니다.");
            return modelAndView;
        }
    }

    /**
     * SMS 인증코드 발송
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sndngSmsConfirm.json")
    public ModelAndView sndngSmsConfirm(HttpServletRequest request, @RequestParam Map<String, Object> commandMap) throws Exception {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("jsonView");

            String mbtlnum = (String) commandMap.get("mbtlnum");
            String id = (String) commandMap.get("id");
            String name = (String) commandMap.get("name");

            if(id != null) {
                if (!id.isEmpty() && !name.isEmpty()) {
                    LoginVO loginVO = new LoginVO();
                    loginVO.setUserSe("GNR");
                    loginVO.setId(id);
                    loginVO.setName(name);
                    LoginVO result = loginService.searchId(loginVO);
                    if (result == null) {
                        modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.search.id"));
                        return modelAndView;
                    }
                }
            }

            if (mbtlnum.isEmpty()) {
                modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.phone6"));
                return modelAndView;
            } else if (!CommonUtil.isValidPhone(mbtlnum)) {
                modelAndView.addObject("resultMsg", egovMessageSource.getMessageArgs("errors.phone", new Object[]{mbtlnum}));
                return modelAndView;
            }

            HttpSession session = request.getSession();
            session.removeAttribute(SESSION_AUTH_KEY + mbtlnum);

            Random random = new Random();
            String authKey = "";
            for (int i = 0; i < 6; i++) {
                String randomStr = Integer.toString(random.nextInt(10));
                authKey += randomStr;
            }

            SmsVO smsVO = new SmsVO();
            smsVO.setTrnsmisCn("[동북권ICT이노베이션스퀘어]\n인증번호 [" + authKey + "]를 입력해주세요.");
            smsVO.setRecptnTelno(mbtlnum);
            smsService.senderSms(smsVO);

            int time = 60 * 3;
            session.setAttribute(SESSION_AUTH_KEY + mbtlnum, authKey);
            session.setMaxInactiveInterval(time);   //3분

            modelAndView.addObject("count", time);
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.sendMbtl.confirm"));

            return modelAndView;
    }

    /**
     * 발송된 인증코드로 인증확인
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mbtlConfirmAuth.json", method = RequestMethod.POST)
    public ModelAndView mbtlConfirmAuth(HttpServletRequest request,
                                        @RequestParam Map<String, Object> commandMap) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String mbtlnum = (String) commandMap.get("mbtlnum");
        String authNum = (String) commandMap.get("authKey");

        HttpSession session = request.getSession();
        String authKey = (String) session.getAttribute(SESSION_AUTH_KEY+mbtlnum);
        boolean result = false;

        if (authNum.isEmpty()) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.auth1"));
        } else if (mbtlnum.isEmpty()) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.phone6"));
        } else {
            if (authKey == null) {
                modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.confirm.auth2"));
            } else {
                if(!authKey.equals(authNum)){
                    modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.confirm.auth"));
                    return modelAndView;
                }

                result = true;
                session.removeAttribute(SESSION_AUTH_KEY+mbtlnum);
                modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.sendMbtl.confirm.auth"));
                modelAndView.addObject("isConfirm", result);
            }
        }

        return modelAndView;
    }
}
