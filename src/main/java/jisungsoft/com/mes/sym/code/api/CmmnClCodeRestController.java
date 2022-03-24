package jisungsoft.com.mes.sym.code.api;

import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;
import jisungsoft.com.service.CmmnClCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mes/sym/code/clcode")
public class CmmnClCodeRestController {

    @Resource(name = "cmmnClCodeService")
    private CmmnClCodeService cmmnClCodeService;

    @PostMapping("/detail.json")
    public ModelAndView detail(CmmnClCode formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        CmmnClCode result = cmmnClCodeService.getCmmnClCodeDetail(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }

    @PostMapping("/list.json")
    public ModelAndView list(CmmnClCode formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<CmmnClCode> result = cmmnClCodeService.getCmmnClCodeAllList(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
