package jisungsoft.com.sec.ram.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.model.sec.AuthorInfoVO;
import jisungsoft.com.sec.ram.service.AuthorRoleManageVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/cms/sec/ram")
public class AuthorManageController {
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

//    @Resource(name = "authorManageService")
//    AuthorInfoService authorManageService;

//    @Resource(name = "authorRoleManageService")
//    AuthorRoleManageService authorRoleManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 권한제한 화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/accessDenied.do")
    public String accessDenied()
            throws Exception {
        return "egovframework/com/cms/sec/accessDenied.cms";
    }

    /**
     * 권한 목록 조회
     * @param authorInfoVO AuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/authorManageList.do")
    public String authorList(@ModelAttribute("authorManageVO") AuthorInfoVO authorInfoVO,
                             @ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO,
                                   ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        /** EgovPropertyService.sample */
        //authorManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
        //authorManageVO.setPageSize(propertiesService.getInt("pageSize"));
        
        /*권한 관리 DATA */
        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
//        paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
//        paginationInfo.setPageSize(authorManageVO.getPageSize());

//        authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//        authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
//        authorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

//        List<?> resultList = authorManageService.getAuthorInfoList(authorInfoVO);
//        int totCnt = authorManageService.getAuthorListTotCnt(authorInfoVO);
//        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("authorManageVO", authorInfoVO);
//        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/sec/ram/author/authorManageList.cms";
    }

    /**
     * 권한 관리 폼
     * @param authorCode
     * @param authorInfoVOVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/authorManageForm.do")
    public String authorManage(@RequestParam("authorCode") String authorCode, @ModelAttribute("authorManage") AuthorInfoVO authorInfoVOVO,
                             ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        authorInfoVOVO.setAuthorCode(authorCode);
//        AuthorInfoVO authorInfoVO = authorManageService.getAuthorInfoDetail(authorInfoVOVO);

//        if (authorInfoVO == null) {
//            authorInfoVO = new AuthorInfoVO();
//        }

//        authorManage.setPageIndex(authorManageVO.getPageIndex());
//        authorManage.setSearchKeyword(authorManageVO.getSearchKeyword());
//        authorManage.setSearchCondition(authorManageVO.getSearchCondition());
//        model.addAttribute("authorManage", authorInfoVO);

        return "jisungsoft/com/cms/sec/ram/author/authorManageForm.cms";
    }

    /**
     * 권한 관리 등록
     * @param authorInfoVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/authorManageInsert.do")
    public String authorManageInsert(@ModelAttribute("authorManage") AuthorInfoVO authorInfoVO,
                               ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        authorManageService.insertAuthor(authorManageVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/sec/ram/authorManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 권한 관리 수정
     * @param authorInfoVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/authorManageUpdate.do")
    public String authorManageUpdate(@ModelAttribute("authorManage") AuthorInfoVO authorInfoVO,
                                     ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        authorManageService.updateAuthor(authorManageVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/sec/ram/authorManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 권한 관리 삭제
     * @param authorInfoVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/authorManageDelete.do")
    public String authorManageDelete(@ModelAttribute("authorManage") AuthorInfoVO authorInfoVO,
                                     ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        authorManageService.deleteAuthor(authorManageVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/sec/ram/authorManageList.do");

        return "jisungsoft/com/script/alertHref";
    }
}
