package jisungsoft.com.mes.mat.nvc.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.persistence.dto.mes.InventrChckMonthly;
import jisungsoft.com.persistence.dto.mes.Material;
import jisungsoft.com.service.CmmnDetailCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 월별실사재고관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/mat/nvc")
public class InventrChckMonthlyController {

    @Resource
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

    private final String VIEW_PATH = "jisungsoft/com/mes/mat/nvc";
    private final String REDIRECT_PATH = "redirect:/mes/mat/nvc";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("inventrChckMonthlyForm")
    public InventrChckMonthly newInventrChckMonthlyForm() {
        return new InventrChckMonthly();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") InventrChckMonthly searchForm,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        model.addAttribute("searchForm", searchForm);

        return VIEW_PATH + "/list.mes";
    }
}
