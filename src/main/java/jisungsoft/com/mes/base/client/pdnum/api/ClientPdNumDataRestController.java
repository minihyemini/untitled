package jisungsoft.com.mes.base.client.pdnum.api;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.mes.base.client.validation.ClientFormValidator;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.persistence.dto.mes.PdnumClient;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.ClientService;
import jisungsoft.com.service.PdNumService;
import jisungsoft.com.service.PdnumClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 주거래 품번
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/client/pdnum")
public class ClientPdNumDataRestController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "clientService")
    private ClientService clientService;

    @Resource(name = "pdNumService")
    private PdNumService pdNumService;

    @Resource(name = "pdnumClientService")
    private PdnumClientService pdnumClientService;

    @PostMapping("/list.json")
    public ModelAndView list(PdnumClient formData) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<PdnumClient> resultList = pdnumClientService.getPdnumClientAllList(formData);

        modelAndView.addObject("dataList", resultList);

        return modelAndView;
    }
}
