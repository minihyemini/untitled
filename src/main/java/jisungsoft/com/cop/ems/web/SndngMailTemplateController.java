package jisungsoft.com.cop.ems.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.SndngMailTemplateService;
import jisungsoft.com.cop.ems.service.SndngMailTemplateVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 메일 템플릿 등록/수정/삭제/목록
 */
@Controller
@RequestMapping("/cms/cop/ems")
public class SndngMailTemplateController {

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "sndngMailTemplateService")
    private SndngMailTemplateService sndngMailTemplateService;

    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;
    /**
     * FileMngUtil
     */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    @RequestMapping(value = "/mailTemplateList.do")
    public String sndngMailTemplateList(@ModelAttribute("sndngMailTemplateVO") SndngMailTemplateVO vo, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        /** EgovPropertyService.sample */
        vo.setPageUnit(propertiesService.getInt("pageUnit"));
        vo.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(vo.getPageIndex());
        paginationInfo.setRecordCountPerPage(vo.getPageUnit());
        paginationInfo.setPageSize(vo.getPageSize());

        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        vo.setLastIndex(paginationInfo.getLastRecordIndex());
        vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        DefaultCodeVO defaultCodeVO = new DefaultCodeVO();
        defaultCodeVO.setCodeId("MAIL01");
        List<?> cmmCode = cmmUseService.selectCmmCodeDetail(defaultCodeVO);

        List<?> resultList = sndngMailTemplateService.selectSndngMailTemplateList(vo);
        int totCnt = sndngMailTemplateService.selectSndngMailTemplateListCnt(vo);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("cmmCode", cmmCode);
        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/cop/ems/tpl/mailTemplateList.cms";
    }

    @RequestMapping(value = "/mailTemplateForm.do")
    public String sndngMailTemplateForm(@ModelAttribute("sndngMailTemplateVO") SndngMailTemplateVO sndngMailTemplateVO,
                                        ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        SndngMailTemplateVO sndngMailTemplate = sndngMailTemplateService.selectSndngMailTemplateDetail(sndngMailTemplateVO);

        if (sndngMailTemplate == null) {
            sndngMailTemplate = new SndngMailTemplateVO();
        }

        DefaultCodeVO defaultCodeVO = new DefaultCodeVO();
        defaultCodeVO.setCodeId("MAIL01");
        List<?> cmmCode = cmmUseService.selectCmmCodeDetail(defaultCodeVO);

        sndngMailTemplate.setPageIndex(sndngMailTemplateVO.getPageIndex());
        sndngMailTemplate.setSearchKeyword(sndngMailTemplate.getSearchKeyword());
        sndngMailTemplate.setSearchCondition(sndngMailTemplate.getSearchCondition());

        model.addAttribute("cmmCode", cmmCode);
        model.addAttribute("sndngMailTemplate", sndngMailTemplate);

        return "jisungsoft/com/cms/cop/ems/tpl/mailTemplateForm.cms";
    }

    @RequestMapping(value = "/mailTemplateInsert.do", method = RequestMethod.POST)
    public String sndngMailTempalteInsert(@ModelAttribute("sndngMailTemplateVO") SndngMailTemplateVO sndngMailTemplateVO,
                                          HttpSession session,
                                          ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        sndngMailTemplateVO.setFrstRegisterId(authenticatedUser.getUniqId());
        sndngMailTemplateService.insertSndngMailTemplate(sndngMailTemplateVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/cop/ems/mailTemplateList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/mailTemplateUpdate.do", method = RequestMethod.POST)
    public String sndngMailTemplateUpdate(@ModelAttribute("sndngMailTemplateVO") SndngMailTemplateVO sndngMailTemplateVO,
                                          HttpSession session,
                                          ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        sndngMailTemplateVO.setLastUpdusrId(authenticatedUser.getUniqId());
        sndngMailTemplateService.updateSndngMailTemplate(sndngMailTemplateVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/cop/ems/mailTemplateList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/mailTemplateDelete.do", method = RequestMethod.POST)
    public String sndngMailTemplateDelete(@ModelAttribute("sndngMailTemplateVO") SndngMailTemplateVO sndngMailTemplateVO,
                                          ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        sndngMailTemplateVO.setLastUpdusrId(authenticatedUser.getUniqId());
        sndngMailTemplateService.deleteSndngMailTemplate(sndngMailTemplateVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/cop/ems/mailTemplateList.do");

        return "jisungsoft/com/script/alertHref";
    }
}
