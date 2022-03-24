package jisungsoft.com.sec.rmt.web;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.sec.rmt.service.RoleManage;
import jisungsoft.com.sec.rmt.service.RoleManageService;
import jisungsoft.com.sec.rmt.service.RoleManageVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/cms/sec/rmt")
public class RoleManageController {

    @Resource(name = "roleManageService")
    private RoleManageService roleManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Message ID Generation */
    @Resource(name="egovRoleIdGnrService")
    private EgovIdGnrService egovRoleIdGnrService;

    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/roleManageList.do")
    public String roleList(@ModelAttribute("roleManageVO") RoleManageVO roleManageVO,
                                 ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(roleManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(roleManageVO.getPageUnit());
        paginationInfo.setPageSize(roleManageVO.getPageSize());

        roleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        roleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        roleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = roleManageService.selectRoleList(roleManageVO);
        roleManageVO.setRoleManageList((List<RoleManageVO>) resultList);
        int totCnt = roleManageService.selectRoleListTotCnt(roleManageVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("roleManageVO", roleManageVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/sec/rmt/role/roleManageList.cms";
    }

    /**
     * 롤 정보 폼
     * @param roleManageVO
     * @param roleCode
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/roleManageForm.do")
    public String roleManage(@ModelAttribute("roleManage") RoleManageVO roleManageVO,
                           @RequestParam("roleCode") String roleCode,
                           ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        DefaultCodeVO defaultCodeVO = new DefaultCodeVO();
        defaultCodeVO.setCodeId("COM003");
        List<?> cmmDetailCodes = cmmUseService.selectCmmCodeDetail(defaultCodeVO);

        roleManageVO.setRoleCode(roleCode);
        RoleManageVO roleManage = roleManageService.selectRole(roleManageVO);

        if (roleManage == null) {
            roleManage = new RoleManageVO();
        }

        roleManage.setPageIndex(roleManageVO.getPageIndex());
        roleManage.setSearchKeyword(roleManageVO.getSearchKeyword());
        roleManage.setSearchCondition(roleManageVO.getSearchCondition());

        model.addAttribute("cmmDetailCodes", cmmDetailCodes);
        model.addAttribute("roleManage", roleManage);

        return "jisungsoft/com/cms/sec/rmt/role/roleManageForm.cms";
    }

    /**
     * 롤 정보 등록 처리
     * @param roleManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/roleManageInsert.do")
    public String roleManageInsert(@ModelAttribute("roleManage") RoleManageVO roleManageVO,
                                   RoleManage roleManage, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        String id = egovRoleIdGnrService.getNextStringId();
        roleManage.setRoleCode(id);
        roleManage.setRoleNm(roleManageVO.getRoleNm());
        roleManage.setRolePttrn(roleManageVO.getRolePttrn());
        roleManage.setRoleDc(roleManageVO.getRoleDc());
        roleManage.setRoleTy(roleManageVO.getRoleTy());
        roleManage.setRoleSort(roleManageVO.getRoleSort());

        RoleManageVO result = roleManageService.insertRole(roleManage, roleManageVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/sec/rmt/roleManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 롤 정보 수정 처리
     * @param roleManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/roleManageUpdate.do")
    public String roleManageUpdate(@ModelAttribute("roleManage") RoleManageVO roleManageVO,
                                   ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        roleManageService.updateRole(roleManageVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/sec/rmt/roleManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 롤 정보 삭제 처리
     * @param roleManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/roleManageDelete.do")
    public String roleManageDelete(@ModelAttribute("roleManage") RoleManageVO roleManageVO,
                                   ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        roleManageService.deleteRole(roleManageVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/sec/rmt/roleManageList.do");

        return "jisungsoft/com/script/alertHref";
    }
}
