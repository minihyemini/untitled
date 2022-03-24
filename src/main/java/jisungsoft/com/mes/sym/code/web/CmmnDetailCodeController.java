package jisungsoft.com.mes.sym.code.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.sym.code.validation.DetailCodeAddFormValidator;
import jisungsoft.com.mes.sym.code.validation.DetailCodeEditFormValidator;
import jisungsoft.com.persistence.dto.sym.code.CmmnCode;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnClCodeService;
import jisungsoft.com.service.CmmnCodeService;
import jisungsoft.com.service.CmmnDetailCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/mes/sym/code/detail")
public class CmmnDetailCodeController {

    @Resource(name = "cmmnClCodeService")
    private CmmnClCodeService cmmnClCodeService;

    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    @Resource(name = "cmmnCodeService")
    private CmmnCodeService cmmnCodeService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    SessionLocaleResolver localeResolver;

    private final String VIEW_PATH = "jisungsoft/com/mes/sym/code/detail";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("detailCodeForm")
    public CmmnDetailCode newDetailCodeForm() {
        return new CmmnDetailCode();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute CmmnDetailCode cmmnDetailCode,
                       final Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<CmmnDetailCode> resultList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);

        CmmnCode cmmnCode = new CmmnCode();
        cmmnCode.setUseAt("Y");
        List<CmmnCode> cmmnCodeList = cmmnCodeService.getCmmnCodeAllList(cmmnCode);

        model.addAttribute("resultList", resultList);
        model.addAttribute("cmmnCodeList", cmmnCodeList);
        model.addAttribute("cmmnDetailCode", cmmnDetailCode);

        return VIEW_PATH + "/list.mes";
    }

    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        CmmnCode cmmnCode = new CmmnCode();
        cmmnCode.setUseAt("Y");
        List<CmmnCode> cmmnCodeList = cmmnCodeService.getCmmnCodeAllList(cmmnCode);

        model.addAttribute("cmmnCodeList",cmmnCodeList);

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("detailCodeForm") CmmnDetailCode form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new DetailCodeAddFormValidator().validate(form, bindingResult);

        CmmnDetailCode result = cmmnDetailCodeService.getCmmnDetailCodeDetail(form);

        if (result != null) {
            bindingResult.rejectValue("code", "duplicated", "duplicated");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "detailCodeForm", bindingResult);
            attr.addFlashAttribute("detailCodeForm", form);
            attr.addFlashAttribute("menuId", form.getMenuId());

            return "redirect:/mes/sym/code/detail/addForm.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setFrstRegisterId(user.getUniqId());
            cmmnDetailCodeService.addCmmnDetailCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
            return "redirect:/mes/sym/code/detail/list.do";
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("detailCodeForm") CmmnDetailCode form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new DetailCodeEditFormValidator().validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "detailCodeForm", bindingResult);
            attr.addFlashAttribute("detailCodeForm", form);
            attr.addFlashAttribute("menuId", form.getMenuId());

            return "redirect:/mes/sym/code/detail/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            cmmnDetailCodeService.editCmmnDetailCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/code/detail/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("detailCodeForm") CmmnDetailCode form,
                               Locale locale, final RedirectAttributes attr) {

        try {
            cmmnDetailCodeService.removeCmmnDetailCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/code/detail/list.do";
    }
}
