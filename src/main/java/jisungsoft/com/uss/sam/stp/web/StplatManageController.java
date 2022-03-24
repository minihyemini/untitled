package jisungsoft.com.uss.sam.stp.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.sam.stp.service.StplatManageService;
import jisungsoft.com.uss.sam.stp.service.StplatVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 *  Class Name  : StplatManageController.java
 *  Description : 약관관리  Business class
 *  Menu : CMS > 사이트관리 > 약관관리
 */
@Controller
@RequestMapping("/cms/uss/sam/stp")
public class StplatManageController {

    /** Service */
    @Resource(name = "stplatManageService")
    private StplatManageService stplatManageService;

    @RequestMapping("/stplatList.do")
    public String stplatList(@ModelAttribute StplatVO mainVO,
                           ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(mainVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(mainVO.getPageUnit());
        paginationInfo.setPageSize(mainVO.getPageSize());

        mainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        mainVO.setLastIndex(paginationInfo.getLastRecordIndex());
        mainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = stplatManageService.selectStplatList(mainVO);
        int totCnt = stplatManageService.selectStplatListCnt(mainVO);

        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", resultList);
        model.addAttribute("resultCnt", totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/uss/sam/stp/stplatList.cms";
    }

    @RequestMapping("/stplatForm.do")
    public String stplatForm(@ModelAttribute StplatVO mainVO,
                             ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        if(!mainVO.getUseStplatId().isEmpty()){
            StplatVO resultData = stplatManageService.selectStplatDetail(mainVO);
            model.addAttribute("stplatVO", resultData);
        }else{
            model.addAttribute("stplatVO", new StplatVO());
        }
        return "jisungsoft/com/cms/uss/sam/stp/stplatForm.cms";
    }

    @RequestMapping(value = "/stplatInsert.do", method = RequestMethod.POST)
    public String stplatInsert(ModelMap model,
                             @ModelAttribute StplatVO mainVO) throws Exception {
        stplatManageService.insertStplat(mainVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/uss/sam/stp/stplatList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value="/stplatUpdate.do", method = RequestMethod.POST)
    public String stplatUpdate(@ModelAttribute StplatVO mainVO,
                               ModelMap model) throws Exception {
        stplatManageService.updateStplat(mainVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/uss/sam/stp/stplatList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value="/stplatDelete.do", method = RequestMethod.POST)
    public String stplatDlete(@ModelAttribute StplatVO mainVO,
                               ModelMap model) throws Exception {
        stplatManageService.deleteStplat(mainVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/uss/sam/stp/stplatList.do");

        return "jisungsoft/com/script/alertHref";
    }
}