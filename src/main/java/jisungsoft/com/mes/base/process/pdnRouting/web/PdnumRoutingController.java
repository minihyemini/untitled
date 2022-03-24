package jisungsoft.com.mes.base.process.pdnRouting.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.process.ProcessCode;
import jisungsoft.com.mes.base.process.pdnRouting.validation.PdnumbyroutgFormValidator;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnDetailCodeService;
import jisungsoft.com.service.PdnumByRoutgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 품번별라우팅관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/process/pdnumRouting")
public class PdnumRoutingController {

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

    private final String VIEW_PATH = "jisungsoft/com/mes/base/process/pdnRouting";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("pdnumbyroutgForm")
    public Pdnumbyroutg newForm() {
        return new Pdnumbyroutg();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") Pdnumbyroutg pdnumbyroutg,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
        cmmnDetailCode.setClCode(MAIN_CODE);
        cmmnDetailCode.setCodeId(PROCSSMSTR);
        List<CmmnDetailCode> processCodeList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);
        cmmnDetailCode.setCodeId(ROUTING001);
        List<CmmnDetailCode> routingCodeList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);
        List<Pdnumbyroutg> resultList = pdnumByRoutgService.getPdnumWithCountAllList(pdnumbyroutg);

        model.addAttribute("processCodeList", processCodeList);
        model.addAttribute("routingCodeList", routingCodeList);
        model.addAttribute("resultList", resultList);
        model.addAttribute("searchForm", pdnumbyroutg);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("pdnumbyroutgForm") Pdnumbyroutg form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new PdnumbyroutgFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "pdnumbyroutgForm", bindingResult);
            attr.addFlashAttribute("pdnumbyroutgForm", form);
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.insert", locale));

            return "redirect:/mes/base/process/pdnumRouting/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            log.info(form.toString());

            pdnumByRoutgService.addPdnumbyroutg(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/process/pdnumRouting/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("pdnumbyroutgForm") Pdnumbyroutg form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

//        new PdfciltsFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "pdnumbyroutgForm", bindingResult);
            attr.addFlashAttribute("pdnumbyroutgForm", form);

            return "redirect:/mes/base/process/pdnumRouting/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();


            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/process/pdnumRouting/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("pdnumbyroutgForm") Pdnumbyroutg form,
                               final BindingResult bindingResult, Model model, Locale locale,
                               final RedirectAttributes attr) {

        try {


            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/pdfilts/list.do";
    }
}
