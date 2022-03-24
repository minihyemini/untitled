package jisungsoft.com.mes.sym.code.api;

import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.code.CmmnCode;
import jisungsoft.com.service.CmmnCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mes/sym/code/code")
public class CmmnCodeRestController {

    @Resource(name = "cmmnCodeService")
    private CmmnCodeService cmmnCodeService;

    @PostMapping("/detail.json")
    public ModelAndView detail(CmmnCode formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        CmmnCode result = cmmnCodeService.getCmmnCodeDetail(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }

    @PostMapping("/list.json")
    public ModelAndView list(CmmnCode formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<CmmnCode> result = cmmnCodeService.getCmmnCodeAllList(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
