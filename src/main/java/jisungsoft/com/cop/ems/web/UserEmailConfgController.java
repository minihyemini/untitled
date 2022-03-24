package jisungsoft.com.cop.ems.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.UserEmailConfgService;
import jisungsoft.com.cop.ems.service.UserEmailConfgVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/{path}/cop/ems")
public class UserEmailConfgController {

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
     * 회원메일설정 서비스
     */
    @Resource(name = "userEmailConfgService")
    private UserEmailConfgService userEmailConfgService;

    @RequestMapping("/userEmailConfgForm.do")
    public String userEmailConfgForm(@ModelAttribute("userEmailConfgVO")UserEmailConfgVO userEmailConfgVO, ModelMap model) throws Exception {

        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/cms/cop/ems/userEmailConfgForm.do");

            return "jisungsoft/com/script/alertHref";
        }
        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        userEmailConfgVO.setEmplyrId(authenticatedUser.getUniqId());

        UserEmailConfgVO result = userEmailConfgService.selectUserEmailConfgDetail(userEmailConfgVO);
        if (result == null) {
            result = new UserEmailConfgVO();
            result.setEmplyrId(userEmailConfgVO.getEmplyrId());
            result.setEmEmailAddress(authenticatedUser.getEmail());
            
            //Gmail 기준
            result.setEmPort("465");
            result.setEmProtocol("smtp");
            result.setEmHost("smtp.gmail.com");
        }

        model.addAttribute("userEmailConfgVO", result);

        return "jisungsoft/com/cms/cop/ems/cfg/userEmailConfgForm.cms";
    }

    @RequestMapping("/userEmailConfgInsert.do")
    public String insertUserEmailConfg(@ModelAttribute("userEmailConfgVO") UserEmailConfgVO userEmailConfgVO, ModelMap model) throws Exception {
        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/cms/cop/ems/userEmailConfgForm.do");

            return "jisungsoft/com/script/alertHref";
        }
        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        userEmailConfgVO.setEmplyrId(authenticatedUser.getUniqId());

        int cnt = userEmailConfgService.selectUserEmailConfgCnt(userEmailConfgVO);
        if (cnt > 0) {
            userEmailConfgService.updateUserEmailConfg(userEmailConfgVO);
        } else {
            userEmailConfgService.insertUserEmailConfg(userEmailConfgVO);
        }

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/cop/ems/userEmailConfgForm.do");

        return "jisungsoft/com/script/alertHref";
    }
}
