package jisungsoft.com.sec.gmt.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.service.GroupService;
import jisungsoft.com.sec.gmt.service.GroupManageVO;
import jisungsoft.com.sec.gmt.service.GroupUserManageVO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cms/sec/gmt")
public class GroupManageRestController {

//    @Resource(name = "groupManageService")
    GroupService groupService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 그룹 사용자 목록
     * @param groupUserManageVO
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/groupUserList.json")
    public ModelAndView groupUserList(GroupUserManageVO groupUserManageVO, ModelMap model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(groupUserManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(groupUserManageVO.getPageUnit());
        paginationInfo.setPageSize(groupUserManageVO.getPageSize());

        groupUserManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        groupUserManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        groupUserManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

//        List<?> resultList = groupService.getGroupUserList(groupUserManageVO);
//        groupUserManageVO.setGroupUserManageList((List<GroupUserManageVO>) resultList);
//        int totCnt = groupService.getGroupUserListTotCnt(groupUserManageVO);
//        paginationInfo.setTotalRecordCount(totCnt);

        //그룹명 조회/추가
        GroupManageVO groupManageVO = new GroupManageVO();
        groupManageVO.setGroupId(groupUserManageVO.getGroupId());
//        groupManageVO = groupService.getGroupDetail(groupManageVO);
        groupUserManageVO.setGroupNm(groupManageVO.getGroupNm());

        modelAndView.addObject("result", groupUserManageVO);
        modelAndView.addObject("paginationInfoJs", paginationInfo);

        return modelAndView;
    }
}
