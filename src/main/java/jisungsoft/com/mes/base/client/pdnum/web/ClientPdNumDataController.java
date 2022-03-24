package jisungsoft.com.mes.base.client.pdnum.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.client.pdnum.validation.ClientPdnumFormValidator;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.dto.mes.PdnumClient;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.ClientService;
import jisungsoft.com.service.PdnumClientService;
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
 * 주거래 품번
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/client/pdnum")
public class ClientPdNumDataController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "clientService")
    private ClientService clientService;

    @Resource(name = "pdnumClientService")
    private PdnumClientService pdnumClientService;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/client/pdnum";
    private final String REDIRECT_PATH = "redirect:/mes/base/client/pdnum";

    @ModelAttribute("clientPdNumForm")
    public PdnumClient clientPdNumForm() {
        return new PdnumClient();
    }

    /**
     * 목록
     */
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") Client searchForm,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<PdnumClient> pdnumClientList = pdnumClientService.getPdnumClientAllList(new PdnumClient());
        List<Client> resultList = clientService.getClientAllList(searchForm);

        model.addAttribute("resultList", resultList);
        model.addAttribute("pdnumClientList", pdnumClientList);
        model.addAttribute("searchForm", searchForm);

        return VIEW_PATH + "/list.mes";
    }

    /**
     * 미사용
     */
    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/addForm.mes";
    }

    /**
     * 등록 처리
     */
    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("clientPdNumForm") PdnumClient form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new ClientPdnumFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "clientPdNumForm", bindingResult);
            attr.addFlashAttribute("clientPdNumForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

            pdnumClientService.addPdnumClientData(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return REDIRECT_PATH+"/list.do";
    }

    /**
     * 미사용 
     */
    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("clientPdNumForm") PdnumClient form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new ClientPdnumFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "clientPdNumForm", bindingResult);
            attr.addFlashAttribute("clientPdNumForm", form);

            return REDIRECT_PATH+"/list.do";
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

        return REDIRECT_PATH+"/list.do";
    }

    /**
     * 미사용
     */
    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("clientPdNumForm") PdnumClient form, Locale locale,
                               final RedirectAttributes attr) {

        try {


            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.delete");
        }

        return REDIRECT_PATH+"/list.do";
    }
}
