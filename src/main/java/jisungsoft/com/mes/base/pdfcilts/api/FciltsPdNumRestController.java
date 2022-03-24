package jisungsoft.com.mes.base.pdfcilts.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.mes.FciltsPdNum;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.service.FciltsPdNumService;
import jisungsoft.com.service.PdNumService;
import jisungsoft.com.service.PdfciltsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 설비별 품번관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/fciltsbypdnum")
public class FciltsPdNumRestController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdfciltsService")
    private PdfciltsService pdfciltsService;

    @Resource(name = "fciltsPdNumService")
    private FciltsPdNumService fciltsPdNumService;

    @Resource(name = "pdNumService")
    private PdNumService pdNumService;

    @PostMapping("/list.json")
    public ModelAndView detail(FciltsPdNum formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<FciltsPdNum> result = fciltsPdNumService.getFciltsPdNumAllList(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
