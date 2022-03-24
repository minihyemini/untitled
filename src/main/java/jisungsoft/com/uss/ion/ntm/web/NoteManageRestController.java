package jisungsoft.com.uss.ion.ntm.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.umt.service.UserManageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/{path}/uss/ion/ntm")
public class NoteManageRestController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * Service
     */
    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;

    /**
     * UserManageService
     */
    @Resource(name = "UserManageService")
    private UserManageService userManageService;

    /**
     * 수신/참조자 목록
     *
     * @param commandMap
     * @param cmmDetailUser
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/note/list.json")
    public ModelAndView userList(@RequestParam Map<String, Object> commandMap,
                                 @PathVariable(value="path") String path,
                                 CmmDetailUser cmmDetailUser) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<String> list = new ArrayList<>();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        String searchKeyword   = (String) commandMap.get("searchKeyword");
        String searchCondition = (String) commandMap.get("searchCondition");

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(cmmDetailUser.getPageIndex());
        paginationInfo.setRecordCountPerPage(cmmDetailUser.getPageUnit());
        paginationInfo.setPageSize(cmmDetailUser.getPageSize());

        cmmDetailUser.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cmmDetailUser.setLastIndex(paginationInfo.getLastRecordIndex());
        cmmDetailUser.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        cmmDetailUser.setSearchKeyword(searchKeyword);
        cmmDetailUser.setSearchCondition(searchCondition);

        if (path.equals("lms")){
            LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
            if (authenticatedUser.getAuthority().equals(AuthorityCode.ROLE_USER.name())) {
                cmmDetailUser.setGroupId("GROUP_00000000000064");
            }
        }

        List<?> userList = cmmUseService.selectUserViewList(cmmDetailUser);
        int totCnt = cmmUseService.selectUserViewTotCnt(cmmDetailUser);
        paginationInfo.setTotalRecordCount(totCnt);

        modelAndView.addObject("resultList", userList);
        modelAndView.addObject("paginationInfoJs", paginationInfo);

        return modelAndView;
    }
}
