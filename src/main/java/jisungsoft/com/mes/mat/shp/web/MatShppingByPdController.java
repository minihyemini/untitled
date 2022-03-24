package jisungsoft.com.mes.mat.shp.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.persistence.dto.mes.Material;
import jisungsoft.com.persistence.dto.mes.Order;
import jisungsoft.com.persistence.model.LoginVO;
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
import java.util.Locale;

/**
 * 자재출고관리(품목별)
 */
@Slf4j
@Controller
@RequestMapping(value = "/mes/mat/shp/pd")
public class MatShppingByPdController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
    /**
     * 상세코드 서비스
     */
    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    private final String VIEW_PATH = "jisungsoft/com/mes/mat/shp/pd";
    private final String REDIRECT_PATH = "redirect:/mes/mat/shp/pd";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("matShppingForm")
    public Material newMatShppingForm() {
        Material material = new Material();
        return material;
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") Material searchForm, final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        model.addAttribute("searchForm", searchForm);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("matShppingForm") Material form,
                            final BindingResult bindingResult,
                            Locale locale, final RedirectAttributes attr) {

        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "matShppingForm", bindingResult);
            attr.addFlashAttribute("matShppingForm", form);

            return REDIRECT_PATH+"/list.do";
        }

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

        return REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("matShppingForm") Material form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "matShppingForm", bindingResult);
            attr.addFlashAttribute("matShppingForm", form);

            return REDIRECT_PATH+"/list.do";
        }

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

        return REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute Order order,
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

        return REDIRECT_PATH+"/list.do";
    }
}
