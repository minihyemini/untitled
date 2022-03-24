package jisungsoft.com.mes.base.process.routing.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.mes.base.process.ProcessCode;
import jisungsoft.com.mes.base.process.validation.ProcessCoderValidator;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnDetailCodeService;
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

@Slf4j
@Controller
@RequestMapping("/mes/base/process/routing")
public class RoutingController {

    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    SessionLocaleResolver localeResolver;

    /** routingCodeIdGnrService */
    @Resource(name="routingCodeIdGnrService")
    private EgovIdGnrService idgenService;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/process/routing";

    private final String MAIN_CODE = Globals.MAIN_CODE;
    private final String PROCESS_CODE = ProcessCode.ROUTING001.name();

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("detailCodeForm")
    public CmmnDetailCode newDetailCodeForm() {
        CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
        cmmnDetailCode.setClCode(MAIN_CODE);
        cmmnDetailCode.setCodeId(PROCESS_CODE);
        return cmmnDetailCode;
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") CmmnDetailCode cmmnDetailCode,
                       final Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        cmmnDetailCode.setClCode(MAIN_CODE);
        cmmnDetailCode.setCodeId(PROCESS_CODE);
        List<CmmnDetailCode> resultList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);

        model.addAttribute("resultList", resultList);
        model.addAttribute("searchForm", cmmnDetailCode);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("detailCodeForm") CmmnDetailCode form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new ProcessCoderValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());
        CmmnDetailCode result = cmmnDetailCodeService.getCmmnDetailCodeDetail(form);

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "detailCodeForm", bindingResult);
            attr.addFlashAttribute("detailCodeForm", form);

            return "redirect:/mes/base/process/routing/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

            if (result != null) {
                form.setLastUpdusrId(user.getUniqId());
                cmmnDetailCodeService.editCmmnDetailCode(form);
                attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
            } else {
                String id = idgenService.getNextStringId();
                form.setCode(id);
                form.setFrstRegisterId(user.getUniqId());
                cmmnDetailCodeService.addCmmnDetailCode(form);
                attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
            }
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/process/routing/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("detailCodeForm") CmmnDetailCode form,
                               Locale locale, final RedirectAttributes attr) {
        attr.addFlashAttribute("menuId", form.getMenuId());

        try {
            cmmnDetailCodeService.removeCmmnDetailCode(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/process/routing/list.do";
    }
}
