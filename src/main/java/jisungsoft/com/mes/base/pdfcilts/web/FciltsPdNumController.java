package jisungsoft.com.mes.base.pdfcilts.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.pdfcilts.validation.PdfciltsPdNumFormValidator;
import jisungsoft.com.persistence.dto.mes.FciltsPdNum;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.FciltsPdNumService;
import jisungsoft.com.service.PdNumService;
import jisungsoft.com.service.PdfciltsService;
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
 * 설비별 품번관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/fciltsByPdnum")
public class FciltsPdNumController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdfciltsService")
    private PdfciltsService pdfciltsService;

    @Resource(name = "fciltsPdNumService")
    private FciltsPdNumService fciltsPdNumService;

    @Resource(name = "pdNumService")
    private PdNumService pdNumService;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/fcPdnum";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("fciltsPdNumForm")
    public FciltsPdNum newFciltsPdNumForm() {
        return new FciltsPdNum();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute FciltsPdNum fciltsPdNum,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        //설비별 품번관리 리스트
        List<FciltsPdNum> resultList = fciltsPdNumService.getFciltsAndPdNumAllList(fciltsPdNum);
        model.addAttribute("resultList", resultList);
        model.addAttribute("fciltsPdNumForm", fciltsPdNum);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("fciltsPdNumForm") FciltsPdNum form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new PdfciltsPdNumFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        //설비코드 중복여부

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "fciltsPdNumForm", bindingResult);
            attr.addFlashAttribute("fciltsPdNumForm", form);

            return "redirect:/mes/base/fciltsByPdnum/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            log.info(form.toString());

            fciltsPdNumService.addFciltsPdNumList(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/base/fciltsByPdnum/list.do";
    }


}
