package jisungsoft.com.cop.ems.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cop.ems.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/{path}/cop/ems")
public class SndngMailRestController {

    /**
     * 메일 템플릿 서비스
     */
//    @Resource(name = "sndngMailTemplateService")
    private SndngMailTemplateService sndngMailTemplateService;
    /**
     * 메일 발송 서비스
     */
//    @Resource(name = "sndngMailService")
    private SndngMailService sndngMailService;
    /**
     * 인증키 서비스
     */
//    @Resource(name = "regMberAuthService")
    private RegMberAuthService regMberAuthService;
    /**
     * Common Service
     */
//    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;

    private final String SESSION_AUTH_KEY = "S_KEY_";
    /**
     * 파일구분자
     */
    static final char FILE_SEPARATOR = File.separatorChar;
    /**
     * EgovMessageSource
     */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;
    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    /**
     * 템플릿 ID
     */
    private final String TEMPLATE_ID = "MAILTMP_000000000261";

    /**
     * 인증코드 메일 발송
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sndngMailConfirm.json", method = RequestMethod.POST)
    public ModelAndView sndngMailConfirm(HttpServletRequest request,
                                         @RequestParam Map<String, Object> commandMap) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String mailAdres = (String) commandMap.get("email");
        if (mailAdres.equals("")) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.email2"));

            return modelAndView;
        } else if (!CommonUtil.isValidEmail(mailAdres)) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessageArgs("errors.email", new Object[] {mailAdres}));

            return modelAndView;
        }

        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_AUTH_KEY+mailAdres);

        //메일 템플릿 생성
        SndngMailTemplateVO sndngMailTemplateVO = new SndngMailTemplateVO();
        sndngMailTemplateVO.setEtId(TEMPLATE_ID);
        SndngMailTemplateVO resultSndng = sndngMailTemplateService.selectSndngMailTemplateDetail(sndngMailTemplateVO);
        //인증키 생성
        resultSndng.convertKey();


        if (resultSndng == null) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.confirm"));
            return modelAndView;
        }

        //메일 발송 생성
        SndngMailVO sndngMailVO = new SndngMailVO();
        sndngMailVO.setRecptnPerson(mailAdres);
        sndngMailVO.setSj(resultSndng.getEtSj());
        sndngMailVO.setEmailCn(resultSndng.getEtCn());
        sndngMailVO.setHtml(true);
        boolean isSndngMail = sndngMailService.sndngMail(sndngMailVO);

        if (isSndngMail) {
            //인증키 데이터 저장
            RegMberAuthVO regMberAuthVO = new RegMberAuthVO();
            regMberAuthVO.setEmailAdres(mailAdres);
            regMberAuthVO.setAuthAt("N");
            regMberAuthVO.setAuthKey(resultSndng.getAuthKey());
            regMberAuthService.insertRegAuthKey(regMberAuthVO);

            //이메일 발송 후 인증키 세션 저장
            int time = 60*3;
            session.setAttribute(SESSION_AUTH_KEY+mailAdres, resultSndng.getAuthKey());
            session.setMaxInactiveInterval(time);   //3분

            modelAndView.addObject("count", time);
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.sendEmail.confirm"));
        } else {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.confirm"));
        }

        modelAndView.addObject("isSndngMail", isSndngMail);

        return modelAndView;
    }

    /**
     * 발송된 인증코드로 인증확인
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mailConfirmAuth.json", method = RequestMethod.POST)
    public ModelAndView mailConfirmAuth(HttpServletRequest request,
                                        @RequestParam Map<String, Object> commandMap) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String mailAdres = (String) commandMap.get("email");
        String authNum = (String) commandMap.get("authKey");

        HttpSession session = request.getSession();
        String authKey = (String) session.getAttribute(SESSION_AUTH_KEY+mailAdres);
        boolean result = false;

        if (authNum.isEmpty()) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.authkey"));
        } else if (mailAdres.isEmpty()) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.email"));
        } else {
            if (authKey == null) {
                modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.confirm.auth2"));
            } else {
                //인증키 데이터 업데이트
                RegMberAuthVO regMberAuthVO = new RegMberAuthVO();
                regMberAuthVO.setEmailAdres(mailAdres);
                regMberAuthVO.setAuthKey(authNum);
                RegMberAuthVO resultAUth = regMberAuthService.selectRegAuthKeyDetail(regMberAuthVO);

                if (resultAUth == null) {
                    modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendEmail.confirm.auth"));
                    return modelAndView;
                }

                //인증 성공시
                resultAUth.setAuthAt("Y");
                regMberAuthService.updateRegAuthKey(resultAUth);

                result = true;
                session.removeAttribute(SESSION_AUTH_KEY+mailAdres);
                modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.sendEmail.confirm.auth"));
                modelAndView.addObject("isConfirm", result);
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/user/list.json")
    public ModelAndView userList(@RequestParam Map<String, Object> commandMap, CmmDetailUser cmmDetailUser) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //일반 사용자
        cmmDetailUser.setUserSe("GNR");
        List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);

        modelAndView.addObject("resultList", userList);
        return modelAndView;
    }
}
