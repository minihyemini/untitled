package jisungsoft.com.mes.base.group.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.dept.validation.DeptAddFormValidator;
import jisungsoft.com.mes.base.group.validation.GroupAddFormValidator;
import jisungsoft.com.mes.base.group.validation.GroupEditFormValidator;
import jisungsoft.com.persistence.dto.sec.Group;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.GroupService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Class Name  : GroupController.java
 * Description : 그룹관리 등록/수정/삭제 처리하는 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/group")
public class GroupController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "groupService")
    private GroupService groupService;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/group";
    private final String REDIRECT_PATH = "redirect:/mes/base/group";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("groupForm")
    public Group newGroupForm() {
        return new Group();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") Group searchForm,
                       final Model model,
                       Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<Group> resultList = groupService.getGroupAllList(searchForm);

        model.addAttribute("resultList", resultList);
        model.addAttribute("searchForm", searchForm);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("groupForm") Group form,
                            final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {
        new GroupAddFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "groupForm", bindingResult);
            attr.addFlashAttribute("groupForm", form);

            return REDIRECT_PATH + "/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

            groupService.addGroup(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.insert", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("groupForm") Group form, final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new GroupEditFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "groupForm", bindingResult);
            attr.addFlashAttribute("groupForm", form);

            return REDIRECT_PATH + "/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

            groupService.editGroup(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.update", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("groupForm") Group form,
                               final BindingResult bindingResult, Model model, Locale locale,
                               final RedirectAttributes attr) {

        try {

            groupService.removeGroup(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.delete", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }
}
