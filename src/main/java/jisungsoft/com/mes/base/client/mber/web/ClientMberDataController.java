package jisungsoft.com.mes.base.client.mber.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.client.validation.ClientFormValidator;
import jisungsoft.com.persistence.dto.member.EnterpriseMember;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.ClientService;
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
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 거래처별 담당자 관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/client/mber")
public class ClientMberDataController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "clientService")
    private ClientService clientService;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/client/mber";
    private final String REDIRECT_PATH = "redirect:/mes/base/client/mber";

    @ModelAttribute("clientMberForm")
    public EnterpriseMember newClientMberForm() {
        return new EnterpriseMember();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") EnterpriseMember searchForm,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        Client client = new Client();
        List<Client> clientList = clientService.getClientAllList(client);

        model.addAttribute("clientList", clientList);
        model.addAttribute("resultList", "");
        model.addAttribute("searchForm", searchForm);

        return VIEW_PATH + "/list.mes";
    }
    
    /**
     * 거래처 담당자 등록 폼
     */
    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/addForm.mes";
    }

    /**
     * 거래처 등록처리
     */
    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("clientForm") Client form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new ClientFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "clientForm", bindingResult);
            attr.addFlashAttribute("clientForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setFrstRegisterId(user.getUniqId());

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
     * 거래처 수정처리
     */
    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("clientForm") Client form,
                             final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new ClientFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (form.getCltId() == null || form.getCltId() == 0) {
            bindingResult.rejectValue("cltId", "required", "required");
        }
        if (!StringUtils.hasText(form.getCltCode())) {
            bindingResult.rejectValue("cltCode", "required", "required");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "clientForm", bindingResult);
            attr.addFlashAttribute("clientForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            

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
     * 거래처 삭제처리
     */
    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("clientForm") Client form, Locale locale,
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
