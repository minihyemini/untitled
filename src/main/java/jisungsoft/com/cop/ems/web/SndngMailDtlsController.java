package jisungsoft.com.cop.ems.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.SndngMailDtlsService;
import jisungsoft.com.cop.ems.service.SndngMailVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

//@Controller
//@RequestMapping("/cms/cop/ems")
public class SndngMailDtlsController {

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
//    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

//    @Resource(name = "sndngMailDtlsService")
    private SndngMailDtlsService sndngMailDtlsService;


    @RequestMapping(value = "/sndngMailList.do")
    public String sndngMailList(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        // 발송메일 내역 조회
        /** EgovPropertyService.sample */
        sndngMailVO.setPageUnit(propertiesService.getInt("pageUnit"));
        sndngMailVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(sndngMailVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(sndngMailVO.getPageUnit());
        paginationInfo.setPageSize(sndngMailVO.getPageSize());

        sndngMailVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        sndngMailVO.setLastIndex(paginationInfo.getLastRecordIndex());
        sndngMailVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sndngMailList = sndngMailDtlsService.selectSndngMailList(sndngMailVO);
        int totCnt = sndngMailDtlsService.selectSndngMailListTotCnt(sndngMailVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultList", sndngMailList);
        model.addAttribute("sndngMailVO", sndngMailVO);

        return "jisungsoft/com/cms/cop/ems/dtl/sndngMailList.cms";
    }

    @RequestMapping(value = "/sndngMailDetail.do")
    public String sndngMailDetail(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        SndngMailVO result = sndngMailDtlsService.selectSndngMailDetail(sndngMailVO);

        model.addAttribute("sndngMailVO", result);

        return "jisungsoft/com/cms/cop/ems/dtl/sndngMailDetail.cms";
    }

    @RequestMapping(value = "/deleteSndngMail.do")
    public String deleteSndngMail(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        sndngMailDtlsService.deleteSndngMail(sndngMailVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/cop/ems/sndngMailList.do");

        return "jisungsoft/com/script/alertHref";
    }
}
