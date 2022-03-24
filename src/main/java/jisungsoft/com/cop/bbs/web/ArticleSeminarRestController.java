package jisungsoft.com.cop.bbs.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.bbs.service.ArticleSeminarService;
import jisungsoft.com.cop.bbs.service.ArticleSeminarVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("/cms/cop/bbs")
public class ArticleSeminarRestController {

    /**
     * Service
     */
//    @Resource(name = "articleSeminarService")
    private ArticleSeminarService articleSeminarService;

    /**
     * 신청자 조회
     * @param commandMap 파라메터전달용 commandMap
     * @param AticleSeminarVO
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/seminarMberList.json")
    public ModelAndView seminarMberList(@RequestParam Map<String, Object> commandMap,
                                        ArticleSeminarVO mainVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        String seId = (String) commandMap.get("seId");

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(mainVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(mainVO.getPageUnit());
        paginationInfo.setPageSize(mainVO.getPageSize());

        mainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        mainVO.setLastIndex(paginationInfo.getLastRecordIndex());
        mainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        mainVO.setSeId(seId);

        List<?> resultList = articleSeminarService.selectSeminarMberList(mainVO);

        int totCnt = articleSeminarService.selectSeminarMberCnt(mainVO);
        paginationInfo.setTotalRecordCount(totCnt);

        modelAndView.addObject("result", resultList);
        modelAndView.addObject("paginationInfoJs", paginationInfo);

        return modelAndView;
    }
}
