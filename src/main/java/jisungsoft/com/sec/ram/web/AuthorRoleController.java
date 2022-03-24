package jisungsoft.com.sec.ram.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.sec.ram.service.AuthorRoleManage;
import jisungsoft.com.sec.ram.service.AuthorRoleManageService;
import jisungsoft.com.sec.ram.service.AuthorRoleManageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/cms/sec/ram")
public class AuthorRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRoleController.class);

    @Resource(name = "authorRoleManageService")
    AuthorRoleManageService authorRoleManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 권한별 할당된 롤 목록 조회
     *
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/authorRoleList.do")
    public String selectAuthorRoleList(@ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO,
                                       @RequestParam("authorCode") String authorCode,
                                       ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        authorRoleManageVO.setAuthorCode(authorCode);
        authorRoleManageVO.setSearchKeyword(authorCode);

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorRoleManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorRoleManageVO.getPageUnit());
        paginationInfo.setPageSize(authorRoleManageVO.getPageSize());

        authorRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        authorRoleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = authorRoleManageService.selectAuthorRoleList(authorRoleManageVO);
        authorRoleManageVO.setAuthorRoleList((List<AuthorRoleManageVO>) resultList);
        int totCnt = authorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO);

        paginationInfo.setTotalRecordCount(totCnt);

//        model.addAttribute("resultList", resultList);
        model.addAttribute("authorRoleManageVO", authorRoleManageVO);
        model.addAttribute("paginationInfo", paginationInfo);

        /*새창*/
        return "jisungsoft/com/cms/sec/ram/authorRole/authorRoleList";
    }

    /**
     * 권한정보에 롤을 할당하여 데이터베이스에 등록
     * @param authorRoleManage AuthorRoleManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/authorRoleInsert.do")
    public String insertAuthorRole(@ModelAttribute("authorRoleManageVO") AuthorRoleManage authorRoleManage,
                                   ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        authorRoleManage.setRoleCode(authorCode);

        if (authorRoleManage.getRegYn().equals("Y")) {
            authorRoleManageService.deleteAuthorRole(authorRoleManage);
            authorRoleManageService.insertAuthorRole(authorRoleManage);
        } else {
            authorRoleManageService.deleteAuthorRole(authorRoleManage);
        }

        return "redirect:/cms/sec/ram/authorRoleList.do";
    }
}
