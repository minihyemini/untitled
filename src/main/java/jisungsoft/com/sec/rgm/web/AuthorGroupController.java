package jisungsoft.com.sec.rgm.web;

//@Controller
//@RequestMapping("/cms/sec/rgm")
public class AuthorGroupController {

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
//    protected EgovPropertyService propertiesService;

//    @Resource(name = "authorGroupService")
//    AuthorGroupService authorGroupService;
/*
    @RequestMapping(value = "/authorGroupList.do")
    public String authorGroupList(@ModelAttribute("authorGroupVO")AuthorGroupVO authorGroupVO, ModelMap model) throws Exception {
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        *//** paging *//*
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorGroupVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorGroupVO.getPageUnit());
        paginationInfo.setPageSize(authorGroupVO.getPageSize());

        authorGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
        authorGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> authorList = authorGroupService.selectAuthorList();
        List<AuthorGroupVO> groupList = authorGroupService.selectGroupList(authorGroupVO);

        if (authorGroupVO.getGroupId() == null) {
            authorGroupVO.setGroupId(groupList.get(0).getGroupId());
        }

        authorGroupVO.setAuthorGroupList(authorGroupService.selectAuthorGroupUserList(authorGroupVO));
        int totCnt = authorGroupService.selectAuthorGroupUserListTotCnt(authorGroupVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", authorGroupVO.getAuthorGroupList());
        model.addAttribute("authorGroup", authorGroupVO);
        model.addAttribute("authorList", authorList);
        model.addAttribute("groupList", groupList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/sec/rgm/authorGroupList.cms";
    }
    */
/*
    @RequestMapping(value = "/authorGroupForm.do")
    public String authorGroupForm(@ModelAttribute("authorGroupVO") AuthorGroupVO authorGroupVO, ModelMap model) throws Exception {
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        AuthorGroupVO authorGroup = authorGroupService.selectAuthorGroup(authorGroupVO);
        List<?> authorList = authorGroupService.selectAuthorList();

        authorGroup.setPageIndex(authorGroupVO.getPageIndex());
        authorGroup.setSearchKeyword(authorGroupVO.getSearchKeyword());
        authorGroup.setSearchCondition(authorGroupVO.getSearchCondition());

        model.addAttribute("authorList", authorList);
        model.addAttribute("authorGroup", authorGroup);

        return "jisungsoft/com/cms/sec/rgm/authorGroupForm.cms";
    }
    */
/*
    @RequestMapping(value = "/authorGroupInsert.do", method = RequestMethod.POST)
    public String authorGroupInsert(@ModelAttribute("authorGroup") AuthorGroup authorGroup, RedirectAttributes redirectAttributes) throws Exception {
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        AuthorGroupVO authorGroupVO = new AuthorGroupVO();
        authorGroupVO.setAuthorCode(authorGroup.getAuthorCode());
        authorGroupVO.setEsntlId(authorGroup.getEsntlId());
        authorGroupVO.setMberTyCode(authorGroup.getMberTyCode());

        authorGroupService.insertAuthorGroupUser(authorGroupVO);
        redirectAttributes.addAttribute("pageIndex", authorGroup.getPageIndex());
        redirectAttributes.addAttribute("groupId", authorGroup.getGroupId());

        return "redirect:/cms/sec/rgm/authorGroupList.do";
    }
    */
}
