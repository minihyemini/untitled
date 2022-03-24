package jisungsoft.com.mes.base.pdfcilts.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.service.PdfciltsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/mes/base/pdfilts")
public class PdfiltsDataRestController {
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdfciltsService")
    private PdfciltsService pdfciltsService;

    @PostMapping("/detail.json")
    public ModelAndView detail(Pdfcilts formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        Pdfcilts result = pdfciltsService.getPdfciltsDetail(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
