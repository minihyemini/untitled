package jisungsoft.com.mes.base.process.pdnRouting.api;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import jisungsoft.com.mes.base.process.ProcessCode;
import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;
import jisungsoft.com.service.CmmnDetailCodeService;
import jisungsoft.com.service.PdnumByRoutgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import java.util.List;

/**
 * 품번별라우팅관리
 */
@Slf4j
@RestController
@RequestMapping("/mes/base/process/pdnumRouting")
public class PdnumRoutingRestController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdnumByRoutgService")
    private PdnumByRoutgService pdnumByRoutgService;

    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    private final String MAIN_CODE = Globals.MAIN_CODE;
    private final String PROCSSMSTR = ProcessCode.PROCSSMSTR.name();
    private final String ROUTING001 = ProcessCode.ROUTING001.name();

    @PostMapping("/list.json")
    public ModelAndView list(Pdnumbyroutg formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        List<Pdnumbyroutg> result = pdnumByRoutgService.getRoutgByPdnumAllList(formData);
        modelAndView.addObject("data", result);

        return modelAndView;
    }
}
