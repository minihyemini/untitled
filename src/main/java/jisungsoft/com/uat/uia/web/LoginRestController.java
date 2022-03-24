package jisungsoft.com.uat.uia.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cop.ems.service.*;
import jisungsoft.com.cop.sms.service.SmsService;
import jisungsoft.com.cop.sms.service.SmsVO;
import jisungsoft.com.uss.umt.service.MberManageService;
import jisungsoft.com.uss.umt.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/{path}/uat/uia")
public class LoginRestController {

    /** log */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestController.class);

    /**
     * 업무회원 서비스
     */
    @Resource(name = "UserManageService")
    private UserManageService userManageService;
    /**
     * 일반회원 서비스
     */
    @Resource(name = "mberManageService")
    private MberManageService mberManageService;
    /**
     * 사용자 조회 공통
     */
    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    /**
     * 메일 템플릿 서비스
     */
    @Resource(name = "sndngMailTemplateService")
    private SndngMailTemplateService sndngMailTemplateService;
    /**
     * 메일 발송 서비스
     */
    @Resource(name = "sndngMailService")
    private SndngMailService sndngMailService;
    /**
     * 인증키 서비스
     */
    @Resource(name = "regMberAuthService")
    private RegMberAuthService regMberAuthService;
    /**
     * SMS 발송 서비스
     */
    @Resource(name = "smsService")
    private SmsService smsService;

    private final String SESSION_AUTH_KEY = "S_KEY_";

    /**
     * 인증키 문자발송
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "/sndngMbtlConfirm.json", method = RequestMethod.POST)
    public ModelAndView sndngMbtlConfirm(HttpServletRequest request, @RequestParam Map<String, Object> commandMap) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        boolean result = false;
        String mbtlnum = (String) commandMap.get("mbtlnum");
        if (mbtlnum == null || mbtlnum.equals("")) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.phone2"));
            modelAndView.addObject("isSuccess", result);
            return modelAndView;
        }

        CmmDetailUser cmmDetailUser = new CmmDetailUser();
        cmmDetailUser.setMbtlnum(mbtlnum);
        cmmDetailUser.setUserSe("GNR");
        List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);

        if (userList.size() == 0) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.sendMbtl.phone1"));
            modelAndView.addObject("isSuccess", result);

            return modelAndView;
        }

        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_AUTH_KEY+mbtlnum);

        String uuid = CommonUtil.getAuthCode(6);
        SmsVO smsVO = new SmsVO();
        smsVO.setAuthKey(uuid);
        smsVO.setAuthAt("N");
        smsVO.setTrnsmisCn("[동북권ICT이노베이션스퀘어]\n인증번호 [" + uuid + "]를 입력해주세요.");
        smsVO.setRecptnTelno(mbtlnum);
        smsService.insertSmsAuth(smsVO);

        result = true;

        modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.sendMbtl.confirm"));
        modelAndView.addObject("isSuccess", result);

        return modelAndView;
    }

    /**
     * 아이디 확인
     * @param request
     * @param commandMap
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = "/idConfrim.json", method = RequestMethod.POST)
    public ModelAndView idConfrim(HttpServletRequest request, @RequestParam Map<String, Object> commandMap) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        CmmDetailUser cmmDetailUser = new CmmDetailUser();
        cmmDetailUser.setUserSe("GNR");
        cmmDetailUser.setSearchCondition("2");
        cmmDetailUser.setSearchKeyword(commandMap.get("id").toString());
        int checkCnt = cmmUseService.selectUserViewTotCnt(cmmDetailUser);

        if(checkCnt > 0){
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.search.id"));
        }else{
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.search.id"));
        }
        return modelAndView;
    }
}
