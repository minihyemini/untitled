package jisungsoft.com.mes.base.process.master.api;

import egovframework.com.cmm.service.Globals;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.mes.base.process.ProcessCode;
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
@RequestMapping("/mes/base/process/master")
public class ProcessMasterRestController {

    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    private final String MAIN_CODE = Globals.MAIN_CODE;
    private final String PROCESS_CODE = ProcessCode.PROCSSMSTR.name();

    @PostMapping("/detail.json")
    public ModelAndView detail(CmmnDetailCode formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        formData.setClCode(MAIN_CODE);
        formData.setCodeId(PROCESS_CODE);
        CmmnDetailCode result = cmmnDetailCodeService.getCmmnDetailCodeDetail(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
