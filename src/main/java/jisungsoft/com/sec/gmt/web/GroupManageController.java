package jisungsoft.com.sec.gmt.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.sec.gmt.service.GroupManage;
import jisungsoft.com.service.GroupService;
import jisungsoft.com.sec.gmt.service.GroupManageVO;
import jisungsoft.com.sec.gmt.service.GroupUserManageVO;
import jisungsoft.com.service.DeptService;
import jisungsoft.com.uss.umt.model.DeptManageVO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

//@Controller
//@RequestMapping("/cms/sec/gmt")
public class GroupManageController {

//    @Resource(name = "groupManageService")
//    GroupService groupService;

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

//    @Resource(name = "deptService")
//    private DeptService deptService;

//    @Resource(name = "deptManageService")
//    private DeptManageService deptManageService;

    /**
     * 그룹 목록, 그룹 사용자 목록(모달)
     * @param groupManageVO
     * @param groupUserManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/groupList.do")
    public String groupList(@ModelAttribute("groupManageVO") GroupManageVO groupManageVO,
                            @ModelAttribute("groupUserManageVO") GroupUserManageVO groupUserManageVO,
                                  ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(groupManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(groupManageVO.getPageUnit());
        paginationInfo.setPageSize(groupManageVO.getPageSize());

        groupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        groupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        groupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

//        List<?> resultList = groupService.getGroupList(groupManageVO);
//        int totCnt = groupService.getGroupListTotCnt(groupManageVO);
//        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("groupManageVO", groupManageVO);
//        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/sec/gmt/group/groupList.cms";
    }

    /**
     * 그룹 관리 폼
     * @param groupId
     * @param groupManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/groupForm.do")
    public String groupForm(@RequestParam("groupId") String groupId,
                            @ModelAttribute("groupManageVO") GroupManageVO groupManageVO,
                            ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        groupManageVO.setGroupId(groupId);
//        GroupManageVO groupManage = groupService.getGroupDetail(groupManageVO);

        DeptManageVO deptManageVO = new DeptManageVO();
        Dept dept = new Dept();
//        List<Dept> deptList = deptService.getDeptAllList(dept);

//        if (groupManage == null) {
//            groupManage = new GroupManageVO();
//        }

//        groupManage.setPageIndex(groupManageVO.getPageIndex());
//        groupManage.setSearchCondition(groupManageVO.getSearchCondition());
//        groupManage.setSearchKeyword(groupManageVO.getSearchKeyword());

//        model.addAttribute("groupManage", groupManage);
//        model.addAttribute("deptList", deptList);

        return "jisungsoft/com/cms/sec/gmt/group/groupForm.cms";
    }

    /**
     * 그룹 관리 등록 처리
     * @param groupManage
     * @param groupManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/groupInsert.do")
    public String groupInsert(@ModelAttribute("groupManage") GroupManage groupManage,
                              @ModelAttribute("groupManageVO") GroupManageVO groupManageVO, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        groupManageVO = groupService.addGroup(groupManage);

        model.addAttribute("groupManage", groupManageVO);
        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/sec/gmt/groupList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 그룹 관리 수정 처리
     * @param groupManage
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/groupUpdate.do")
    public String groupUpdate(@ModelAttribute("groupManage") GroupManage groupManage, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        groupService.editGroup(groupManage);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/sec/gmt/groupList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 그룹 관리 삭제 처리
     * @param groupManage
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/groupDelete.do")
    public String groupDelete(@ModelAttribute("groupManage") GroupManage groupManage, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

//        groupService.removeGroup(groupManage);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/sec/gmt/groupList.do");

        return "jisungsoft/com/script/alertHref";
    }
}
