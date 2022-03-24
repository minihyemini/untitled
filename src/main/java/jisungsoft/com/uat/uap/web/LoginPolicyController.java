package jisungsoft.com.uat.uap.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uat.uap.service.LoginPolicy;
import jisungsoft.com.uat.uap.service.LoginPolicyService;
import jisungsoft.com.uat.uap.service.LoginPolicyVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/cms/uat/uap")
public class LoginPolicyController {
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name="loginPolicyService")
    LoginPolicyService loginPolicyService;

    @Resource(name="cmmUseService")
    CmmUseService cmmUseService;

    /**
     * 로그인정책 목록 조회
     * @param loginPolicyVO - 로그인정책 VO
     * @return String - 리턴 Url
     */
    @RequestMapping("/loginPolicyList.do")
    public String selectLoginPolicyList(@ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO,
                                        ModelMap model) throws Exception {

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(loginPolicyVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(loginPolicyVO.getPageUnit());
        paginationInfo.setPageSize(loginPolicyVO.getPageSize());

        loginPolicyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        loginPolicyVO.setLastIndex(paginationInfo.getLastRecordIndex());
        loginPolicyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<LoginPolicyVO> resultList = loginPolicyService.selectLoginPolicyList(loginPolicyVO);
        model.addAttribute("resultList", resultList);

        int totCnt = loginPolicyService.selectLoginPolicyListTotCnt(loginPolicyVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/uat/uap/loginPolicyList.cms";
    }

    /**
     * 로그인정책 등록 화면
     * @param loginPolicyVO - 로그인정책 VO
     * @return String - 리턴 Url
     */
    @RequestMapping("/loginPolicyForm.do")
    public String selectLoginPolicy(@ModelAttribute("loginPolicyVO") LoginPolicyVO loginPolicyVO,
                                    ModelMap model) throws Exception {

        LoginPolicyVO vo = loginPolicyService.selectLoginPolicy(loginPolicyVO);

        //업무사용자 목록
        CmmDetailUser cmmDetailUser = new CmmDetailUser();
        cmmDetailUser.setUserSe("USR");
        List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);

        if (vo == null) {
            vo = new LoginPolicyVO();
        }

        model.addAttribute("userList", userList);
        model.addAttribute("loginPolicy", vo);

        return "jisungsoft/com/cms/uat/uap/loginPolicyForm.cms";
    }

    /**
     * 로그인정책 정보를 신규 등록
     * @param loginPolicy - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/loginPolicyInsert.do")
    public String insertLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy,
                                    ModelMap model) throws Exception {

        LoginVO user = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        loginPolicy.setUserId(user.getId());

        boolean result = loginPolicyService.insertLoginPolicy(loginPolicy);
        if (!result) {
            model.addAttribute("resultMsg", "fail.common.insert");

            return "jisungsoft/com/script/alertBack";
        }

        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));

        return "forward:/cms/uat/uap/loginPolicyList.do";
    }

    /**
     * 기 등록된 로그인정책 정보를 수정한다.
     * @param loginPolicy - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/loginPolicyUpdate.do")
    public String updateLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy,
                                    ModelMap model) throws Exception {

        LoginVO user = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
        loginPolicy.setUserId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

        boolean result = loginPolicyService.updateLoginPolicy(loginPolicy);
        if (!result) {
            model.addAttribute("resultMsg", "fail.common.update");

            return "jisungsoft/com/script/alertBack";
        }

        model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

        return "forward:/cms/uat/uap/loginPolicyList.do";
    }

    /**
     * 기 등록된 로그인정책 정보를 삭제한다.
     * @param loginPolicy - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/loginPolicyDelete.do")
    public String deleteLoginPolicy(@ModelAttribute("loginPolicy") LoginPolicy loginPolicy,
                                    ModelMap model) throws Exception {

        loginPolicyService.deleteLoginPolicy(loginPolicy);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/uat/uap/loginPolicyList.do";
    }
}
