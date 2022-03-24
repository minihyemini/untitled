package jisungsoft.com.uss.umt.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.RegMberAuthService;
import jisungsoft.com.cop.ems.service.RegMberAuthVO;
import jisungsoft.com.uss.umt.service.MberManageService;
import jisungsoft.com.uss.umt.model.MberManageVO;
import jisungsoft.com.uss.umt.service.UserManageService;
import jisungsoft.com.uss.umt.model.UserManageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Class Name  : UserMberRestController.java
 * Description : 업무회원, 일반회원 RestAPI
 * Menu : CMS, LMS, PAGE
 */
@RestController
@RequestMapping("/{path}/uss/umt")
public class UserMberRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMberRestController.class);

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
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 인증키 서비스
     */
    @Resource(name = "regMberAuthService")
    private RegMberAuthService regMberAuthService;

    /**
     * 일반회원, 업무회원 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param commandMap 파라메터전달용 commandMap
     * @return uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value = "/idDplctCnfirm.json", method = RequestMethod.POST)
    public ModelAndView checkIdDplctAjax(@RequestParam Map<String, Object> commandMap, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        String resultMsg = "";
        String checkId = (String) commandMap.get("idCheck");
        boolean result = false;

        if (checkId.equals("")) {
            resultMsg = egovMessageSource.getMessage("fail.common.idInput");
        } else {
            checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

            int usedCnt = userManageService.checkIdDplct(checkId);

            if (usedCnt > 0) {
                resultMsg = egovMessageSource.getMessage("fail.common.idDuplicate");
            } else {
                result = true;
                resultMsg = egovMessageSource.getMessage("success.common.idSearch");
            }
        }

        modelAndView.addObject("resultMsg", resultMsg);
        modelAndView.addObject("checkId", checkId);
        modelAndView.addObject("isDuplicate", result);

        return modelAndView;
    }

    /**
     * 업무회원 비밀번호 변경처리
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userPasswordUpdt.json", method = RequestMethod.POST)
    public ModelAndView userPasswordUpdate(@RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        boolean isCorrectPassword = false;
        String resultMsg = "";

        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        //보안 취약점
        if (!isAuthenticated) {
            resultMsg = egovMessageSource.getMessage("fail.user.connectFail");
            return modelAndView;
        }

        String uniqId = (String) commandMap.get("uniqId");
        String password = (String) commandMap.get("password");
        String password2 = (String) commandMap.get("password2");
        String id = (String) commandMap.get("emplyrId");

        if (password.equals(password2)) {
            if (CommonUtil.validationPassword(password)) {
                isCorrectPassword = true;
            } else {
                resultMsg = egovMessageSource.getMessage("errors.password1");
            }
        } else {
            resultMsg = egovMessageSource.getMessage("errors.password5");
        }

        if (isCorrectPassword) {
            UserManageVO userManageVO = new UserManageVO();
            userManageVO.setUniqId(uniqId);
            userManageVO.setPassword(password);
            userManageVO.setEmplyrId(id);
            userManageService.updatePassword(userManageVO);

            resultMsg = "success.common.update";
        }

        modelAndView.addObject("resultMsg", resultMsg);

        return modelAndView;
    }

    /**
     * 업무회원 로그인인증제한 해제
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userLockIncorrect.json", method = RequestMethod.POST)
    public ModelAndView userLockIncorrect(@RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg = "";
        String uniqId = (String) commandMap.get("uniqId");

        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        //보안 취약점
        if (!isAuthenticated) {
            resultMsg = egovMessageSource.getMessage("fail.user.connectFail");
            return modelAndView;
        }

        if (!uniqId.equals("")) {
            UserManageVO userManageVO = new UserManageVO();
            userManageVO.setUniqId(uniqId);
            userManageService.updateLockIncorrect(userManageVO);

            resultMsg = egovMessageSource.getMessage("success.request.msg");
        } else {
            resultMsg = egovMessageSource.getMessage("fail.request.msg");
        }

        modelAndView.addObject("resultMsg", resultMsg);

        return modelAndView;
    }

    /**
     * 일반회원 로그인인증제한 해제
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberLockIncorrect.json", method = RequestMethod.POST)
    public ModelAndView mberLockIncorrect(@PathVariable(value="path") String path,
                                          @RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg = "";
        String uniqId = (String) commandMap.get("uniqId");

        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        //보안 취약점
        if (!isAuthenticated) {
            resultMsg = egovMessageSource.getMessage("fail.user.connectFail");
            return modelAndView;
        }

        if (!uniqId.equals("")) {
            MberManageVO mberManageVO = new MberManageVO();
            mberManageVO.setUniqId(uniqId);
            mberManageService.updateLockIncorrect(mberManageVO);

            resultMsg = egovMessageSource.getMessage("success.request.msg");
        } else {
            resultMsg = egovMessageSource.getMessage("fail.request.msg");
        }

        modelAndView.addObject("resultMsg", resultMsg);

        return modelAndView;
    }

    /**
     * 일반회원 비밀번호 변경 처리
     *
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberPasswordUpdt.json", method = RequestMethod.POST)
    public ModelAndView mberPasswordUpdate(@PathVariable(value="path") String path,
                                           @RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        boolean isCorrectPassword = false;
        String resultMsg = "";

        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        //보안 취약점
        if (!isAuthenticated) {
            resultMsg = egovMessageSource.getMessage("fail.user.connectFail");
            return modelAndView;
        }

        String uniqId = (String) commandMap.get("uniqId");
        String password = (String) commandMap.get("password");
        String password2 = (String) commandMap.get("password2");
        String id = (String) commandMap.get("mberId");

        if (password.equals(password2)) {
            if (CommonUtil.validationPassword(password)) {
                isCorrectPassword = true;
            } else {
                resultMsg = egovMessageSource.getMessage("errors.password1");
            }
        } else {
            resultMsg = egovMessageSource.getMessage("errors.password5");
        }

        if (isCorrectPassword) {
            MberManageVO mberManageVO = new MberManageVO();
            mberManageVO.setUniqId(uniqId);
            mberManageVO.setPassword(password);
            mberManageVO.setMberId(id);
            mberManageService.updatePassword(mberManageVO);

            resultMsg = "success.common.update";
        }

        modelAndView.addObject("resultMsg", resultMsg);

        return modelAndView;
    }

    /**
     * 일반회원 이메일 변경
     * @param path
     * @param commandMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberEmailUpdt.json", method = RequestMethod.POST)
    public ModelAndView mberEmailUpdate(@PathVariable(value="path") String path,
                                           @RequestParam Map<String, Object> commandMap) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        String resultMsg = "";

        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        //보안 취약점
        if (!isAuthenticated) {
            resultMsg = egovMessageSource.getMessage("fail.user.connectFail");
            return modelAndView;
        }

        String email = (String) commandMap.get("newMberEmailAdres");
        String authKey = (String) commandMap.get("authKey");
        String uniqId = (String) commandMap.get("uniqId");

        RegMberAuthVO regMberAuthVO = new RegMberAuthVO();
        regMberAuthVO.setEmailAdres(email);
        regMberAuthVO.setAuthKey(authKey);

        int resultCnt = regMberAuthService.selectRegAuthKeyCheck(regMberAuthVO);
        boolean result = false;

        if (resultCnt == 0) {
            resultMsg = egovMessageSource.getMessage("fail.sendEmail.confirm.auth3");
        } else {
            MberManageVO mberManageVO = new MberManageVO();
            mberManageVO.setMberEmailAdres(email);
            mberManageVO.setMberId(email);
            mberManageVO.setUniqId(uniqId);

            mberManageService.updateEmail(mberManageVO);

            resultMsg = egovMessageSource.getMessage("success.common.update");
            result = true;
            modelAndView.addObject("resultEmailId", email);
        }

        modelAndView.addObject("resultMsg", resultMsg);
        modelAndView.addObject("result", result);

        return modelAndView;
    }

}