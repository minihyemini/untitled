package jisungsoft.com.mes.sym.menu.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.sym.menu.validation.MenuFormValidator;
import jisungsoft.com.persistence.dto.sym.menu.Menu;
import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import jisungsoft.com.persistence.dto.sec.AuthorInfo;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.AuthorInfoService;
import jisungsoft.com.service.MenuProgramService;
import jisungsoft.com.service.MenuService;
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
@RequestMapping("/mes/sym/menu")
public class MenuManageController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "menuService")
    protected MenuService menuService;

    @Resource(name = "menuProgramService")
    private MenuProgramService menuProgramService;

    @Resource(name = "authorInfoService")
    private AuthorInfoService authorInfoService;

    private final String VIEW_PATH = "jisungsoft/com/mes/sym/menu";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("menuForm")
    public Menu newMenuForm() {
        return new Menu();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(@ModelAttribute Menu menu, ModelMap model, Locale locale, HttpServletRequest request) throws Exception {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<AuthorInfo> authList = authorInfoService.getAuthorInfoAllList(new AuthorInfo());
        List<MenuProgram> programList = menuProgramService.getProgramAllList(new MenuProgram());

        model.addAttribute("authList", authList);
        model.addAttribute("menu", menu);
        model.addAttribute("programList", programList);
        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String add(@Validated @ModelAttribute ("menuForm") Menu form,
                      final BindingResult bindingResult, Locale locale,
                      final RedirectAttributes attr) throws Exception {

        new MenuFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "menuForm", bindingResult);
            attr.addFlashAttribute("menuForm", form);

            return "redirect:/mes/sym/menu/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            menuService.addMenuManage(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return "redirect:/mes/sym/menu/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String edit(@Validated @ModelAttribute ("menuForm") Menu form,
                       final BindingResult bindingResult, Locale locale,
                       final RedirectAttributes attr) throws Exception {

        new MenuFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "menuForm", bindingResult);
            attr.addFlashAttribute("menuForm", form);

            return "redirect:/mes/sym/menu/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            menuService.editMenuManage(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.update");
        }

        return "redirect:/mes/sym/menu/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String remove(@Validated @ModelAttribute ("menuForm") Menu form,
                         final BindingResult bindingResult,
                         Locale locale,
                         final RedirectAttributes attr) throws Exception {

        attr.addFlashAttribute("menuId", form.getMenuId());

        try {
            menuService.removeMenuManage(form);
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.delete");
        }

        return "redirect:/mes/sym/menu/list.do";
    }
}
