package jisungsoft.com.mes.prdc.wrk.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.persistence.dto.mes.Pdexcdrection;
import jisungsoft.com.persistence.dto.mes.Sles;
import jisungsoft.com.persistence.model.LoginVO;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 생산작업지시
 */
@Slf4j
@Controller
@RequestMapping(value = "/mes/prdc/wrk")
public class PrdcWorkOrderController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    private final String VIEW_PATH = "jisungsoft/com/mes/prdc/wrk";
    private final String REDIRECT_PATH = "/mes/prdc/wrk";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("slesForm")
    public Pdexcdrection newPdexcdrectionForm() {
        return new Pdexcdrection();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Pdexcdrection pdexcdrection, final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        model.addAttribute("searchForm", pdexcdrection);

        return VIEW_PATH + "/list.mes";
    }

    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();


            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.insert", locale));
        }

        return "redirect:"+REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated final BindingResult bindingResult, Locale locale,
                             final MultipartHttpServletRequest multiRequest,
                             final RedirectAttributes attr) {

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();


            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.update", locale));
        }

        return "redirect:"+REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated
                               final BindingResult bindingResult, Model model, Locale locale,
                               final RedirectAttributes attr) {

        try {


            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.delete", locale));
        }

        return "redirect:"+REDIRECT_PATH+"/list.do";
    }
}