package jisungsoft.com.mes.sym.code.api;

import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.service.CmmnDetailCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/mes/sym/code/detail")
public class CmmnDetailCodeRestController {

    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    @PostMapping("/detail.json")
    public ModelAndView detail(CmmnDetailCode formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        CmmnDetailCode result = cmmnDetailCodeService.getCmmnDetailCodeDetail(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
