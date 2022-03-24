package jisungsoft.com.mes.base.pdfcilts.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.pdfcilts.validation.PdfciltsFormValidator;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.PdfciltsService;
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

/**
 * 설비관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/pdfilts")
public class PdfiltsDataController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdfciltsService")
    private PdfciltsService pdfciltsService;
    
    private final String VIEW_PATH = "jisungsoft/com/mes/base/pdfilts";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("pdfciltsForm")
    public Pdfcilts newPdfciltsForm() {
        return new Pdfcilts();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") Pdfcilts pdfcilts,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<Pdfcilts> resultList = pdfciltsService.getPdfciltsAllList(pdfcilts);

        model.addAttribute("resultList", resultList);
        model.addAttribute("searchForm", pdfcilts);

        return VIEW_PATH + "/list.mes";
    }

    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("pdfciltsForm") Pdfcilts form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new PdfciltsFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        //설비코드 중복여부
        int codeCnt = pdfciltsService.getPdfciltsCntByCode(form);
        if (codeCnt > 0) {
            bindingResult.rejectValue("pdfciltsCode", "duplicate", "duplicate");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "pdfciltsForm", bindingResult);
            attr.addFlashAttribute("pdfciltsForm", form);

            return "redirect:/mes/base/pdfilts/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setFrstRegisterId(user.getUniqId());

            pdfciltsService.addPdfcilts(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/pdfilts/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("pdfciltsForm") Pdfcilts form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new PdfciltsFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        //설비코드 중복여부
//        int codeCnt = pdfciltsService.getPdfciltsCntByCode(form);
//        if (codeCnt > 0) {
//            bindingResult.rejectValue("pdfciltsCode", "duplicate", "duplicate");
//        }

        if (form.getPdfciltsId() == null || form.getPdfciltsId() == 0) {
            bindingResult.rejectValue("pdfciltsId", "required", "required");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "pdfciltsForm", bindingResult);
            attr.addFlashAttribute("pdfciltsForm", form);

            return "redirect:/mes/base/pdfilts/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            pdfciltsService.editPdfcilts(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/pdfilts/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("pdfciltsForm") Pdfcilts form,
                             final BindingResult bindingResult, Model model, Locale locale,
                             final RedirectAttributes attr) {

        try {
            pdfciltsService.removePdfcilts(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/pdfilts/list.do";
    }
}
