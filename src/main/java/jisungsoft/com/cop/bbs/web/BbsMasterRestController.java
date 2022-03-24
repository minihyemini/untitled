package jisungsoft.com.cop.bbs.web;

import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.service.CmmnDetailCodeService;
import jisungsoft.com.persistence.model.sym.code.CmmnDetailCodeVO;
import org.springframework.web.servlet.ModelAndView;

//@RestController
//@RequestMapping("/cms/cop/bbs")
class BbsMasterRestController {
    /**
     * Service
     */
//    @Resource(name = "cmmnDetailCodeManageService")
    private CmmnDetailCodeService cmmnDetailCodeManageService;

    /**
     * 게시판유형 조회
     *
     * @param cmmnDetailCodeVO
     * @throws Exception
     */
//    @RequestMapping(value = "/bbsTyCodeList.json")
    public ModelAndView bbsTyCodeList(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        /** paging */
        /*
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(cmmnDetailCodeVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(cmmnDetailCodeVO.getPageUnit());
        paginationInfo.setPageSize(cmmnDetailCodeVO.getPageSize());

        cmmnDetailCodeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        cmmnDetailCodeVO.setLastIndex(paginationInfo.getLastRecordIndex());
        cmmnDetailCodeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        cmmnDetailCodeVO.setCodeId("COM002");
        List<?> resultList = cmmnDetailCodeManageService.getCmmnDetailCodeAllList(cmmnDetailCodeVO);
        paginationInfo.setTotalRecordCount(cmmnDetailCodeManageService.getCmmnDetailCodeListCnt(cmmnDetailCodeVO));

        modelAndView.addObject("result", resultList);
        modelAndView.addObject("paginationInfoJs", paginationInfo);
        */
        return modelAndView;
    }
}
