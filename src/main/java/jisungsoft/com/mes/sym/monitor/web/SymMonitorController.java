package jisungsoft.com.mes.sym.monitor.web;

import egovframework.com.cmm.EgovMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/mes/sym/monitor")
public class SymMonitorController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    private final String VIEW_PATH = "jisungsoft/com/mes/sales/spp";
    private final String REDIRECT_PATH = "/mes/sales/spp";


    @RequestMapping(value = "/monitor01.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String view01(final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/view.mes";
    }

    @RequestMapping(value = "/monitor02.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String view02(final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/view.mes";
    }


}
