package jisungsoft.com.mes.sym.menu.program.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.sym.menu.program.validation.MenuProframFormValidator;
import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.MenuProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/mes/sym/menu/program")
public class ProgramManageController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "menuProgramService")
    protected MenuProgramService menuProgramService;

    private final String VIEW_PATH = "jisungsoft/com/mes/sym/menu/program";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("menuProgramForm")
    public MenuProgram newMenuProgramForm() {
        return new MenuProgram();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String programManageList(@ModelAttribute MenuProgram menuProgram,
                                    ModelMap model, Locale locale, HttpServletRequest request) throws Exception{

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<MenuProgram> resultList = menuProgramService.getProgramAllList(menuProgram);

        //프로그램 분류코드
//        DefaultCodeVO defaultCodeVO = new DefaultCodeVO();
//        defaultCodeVO.setCodeId("PRM001");
//        List<?> codeList = cmmUseService.selectCmmCodeDetail(defaultCodeVO);

//        model.addAttribute("codeList", codeList);
        model.addAttribute("menuProgram", menuProgram);
        model.addAttribute("resultList", resultList);

        return VIEW_PATH + "/list.mes";
    }

    @RequestMapping(value = "/addForm.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String addForm(Locale locale, HttpServletRequest request) throws Exception {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        return VIEW_PATH + "/addForm.mes";
    }

    @PostMapping(value = "/add.do")
    public String add(@Validated @ModelAttribute("menuProgramForm") MenuProgram form,
                      final BindingResult bindingResult,
                      final RedirectAttributes attr, Locale locale) {

        new MenuProframFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {

            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "menuProgramForm", bindingResult);
            attr.addFlashAttribute("menuProgramForm", form);

            return "redirect:/mes/sym/menu/program/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            menuProgramService.addProgram(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/menu/program/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String programManageUpdate(@Validated @ModelAttribute("menuProgramForm") MenuProgram form,
                                      final BindingResult bindingResult,
                                      final RedirectAttributes attr, Locale locale) {

        new MenuProframFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {

            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "menuProgramForm", bindingResult);
            attr.addFlashAttribute("menuProgramForm", form);

            return "redirect:/mes/sym/menu/program/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            menuProgramService.editProgram(form);
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/menu/program/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String programManageDelete(@ModelAttribute("menuProgramForm") MenuProgram menuProgram, Locale locale,
                                      final RedirectAttributes attr) {

        try {
            menuProgramService.removeProgram(menuProgram);
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/menu/program/list.do";
    }
}
