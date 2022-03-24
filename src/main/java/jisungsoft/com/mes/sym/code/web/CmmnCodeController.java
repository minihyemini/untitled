package jisungsoft.com.mes.sym.code.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.sym.code.validation.CodeAddFormValidator;
import jisungsoft.com.mes.sym.code.validation.CodeEditFormValidator;
import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;
import jisungsoft.com.persistence.dto.sym.code.CmmnCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnClCodeService;
import jisungsoft.com.service.CmmnCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/mes/sym/code/code")
public class CmmnCodeController {

    @Resource(name = "cmmnClCodeService")
    private CmmnClCodeService cmmnClCodeService;

    @Resource(name = "cmmnCodeService")
    private CmmnCodeService cmmnCodeService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    SessionLocaleResolver localeResolver;

    private final String VIEW_PATH = "jisungsoft/com/mes/sym/code/code";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("codeForm")
    public CmmnCode newCodeForm() {
        return new CmmnCode();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute CmmnCode cmmnCode,
                       final Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<CmmnCode> resultList = cmmnCodeService.getCmmnCodeAllList(cmmnCode);

        CmmnClCode cmmnClCode = new CmmnClCode();
        cmmnClCode.setUseAt("Y");
        List<CmmnClCode> cmmnClCodeList = cmmnClCodeService.getCmmnClCodeAllList(cmmnClCode);

        model.addAttribute("cmmnClCodeList", cmmnClCodeList);
        model.addAttribute("resultList", resultList);
        model.addAttribute("cmmnCode", cmmnCode);

        return VIEW_PATH + "/list.mes";
    }

    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        CmmnClCode cmmnClCode = new CmmnClCode();
        cmmnClCode.setUseAt("Y");
        List<CmmnClCode> cmmnClCodeList = cmmnClCodeService.getCmmnClCodeAllList(cmmnClCode);

        model.addAttribute("cmmnClCodeList", cmmnClCodeList);

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("codeForm") CmmnCode form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new CodeAddFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        CmmnCode result = cmmnCodeService.getCmmnCodeDetail(form);

        if (result != null) {
            bindingResult.rejectValue("codeId", "duplicated", "duplicated");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "codeForm", bindingResult);
            attr.addFlashAttribute("codeForm", form);


            return "redirect:/mes/sym/code/code/addForm.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setFrstRegisterId(user.getUniqId());
            cmmnCodeService.addCmmnCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
            return "redirect:/mes/sym/code/code/list.do";
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.insert", locale));
        }

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("codeForm") CmmnCode form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new CodeEditFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "codeForm", bindingResult);
            attr.addFlashAttribute("codeForm", form);


            return "redirect:/mes/sym/code/code/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            cmmnCodeService.editCmmnCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
            return "redirect:/mes/sym/code/code/list.do";
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/code/code/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("codeForm") CmmnCode form,
                               Locale locale, final RedirectAttributes attr) {
        attr.addFlashAttribute("menuId", form.getMenuId());

        try {
            cmmnCodeService.removeCmmnCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
            return "redirect:/mes/sym/code/clcode/list.do";
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/code/code/list.do";
    }
}
