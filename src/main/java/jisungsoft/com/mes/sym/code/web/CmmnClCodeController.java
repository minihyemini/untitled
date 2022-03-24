package jisungsoft.com.mes.sym.code.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.sym.code.validation.ClCodeAddFormValidator;
import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnClCodeService;
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
@RequestMapping("/mes/sym/code/clcode")
public class CmmnClCodeController {

    @Resource(name = "cmmnClCodeService")
    private CmmnClCodeService cmmnClCodeService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    SessionLocaleResolver localeResolver;

    private final String VIEW_PATH = "jisungsoft/com/mes/sym/code/clcode";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("clCodeForm")
    public CmmnClCode newClCodeForm() {
        return new CmmnClCode();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute CmmnClCode cmmnClCode,
                       final Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<CmmnClCode> resultList = cmmnClCodeService.getCmmnClCodeAllList(cmmnClCode);

        model.addAttribute("resultList", resultList);
        model.addAttribute("cmmnClCode", cmmnClCode);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/addForm.do")
    public String addForm(Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        model.addAttribute("clCodeForm", new CmmnClCode());

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("clCodeForm") CmmnClCode form,
                            final BindingResult bindingResult, Model model, Locale locale,
                            final RedirectAttributes attr) {

        new ClCodeAddFormValidator().validate(form, bindingResult);

        CmmnClCode result = cmmnClCodeService.getCmmnClCodeDetail(form);
        if (result != null) {
            bindingResult.rejectValue("clCode", "duplicated", "duplicated");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return VIEW_PATH + "/addForm.mes";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setFrstRegisterId(user.getUniqId());
            cmmnClCodeService.addCmmnClCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
            return "redirect:/mes/sym/code/clcode/list.do";
        } catch (DataAccessException e) {
            e.printStackTrace();
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            e.printStackTrace();
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("clCodeForm") CmmnClCode form,
                             final BindingResult bindingResult, Model model, Locale locale,
                             final RedirectAttributes attr) {
        new ClCodeAddFormValidator().validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "clCodeForm", bindingResult);
            attr.addFlashAttribute("clCodeForm", form);

            return "redirect:/mes/sym/code/clcode/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            cmmnClCodeService.editCmmnClCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
            return "redirect:/mes/sym/code/clcode/list.do";
        } catch (DataAccessException e) {
            e.printStackTrace();
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            e.printStackTrace();
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/code/clcode/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("clCodeForm") CmmnClCode form,
                             final BindingResult bindingResult, Model model, Locale locale,
                             final RedirectAttributes attr) {

        try {
            cmmnClCodeService.removeCmmnClCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
            return "redirect:/mes/sym/code/clcode/list.do";
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/code/clcode/list.do";
    }
}
