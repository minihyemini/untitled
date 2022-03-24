package jisungsoft.com.mes.base.dept.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.mes.base.dept.validation.DeptAddFormValidator;
import jisungsoft.com.mes.base.dept.validation.DeptEditormValidator;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * Class Name  : DeptManageController.java
 * Description : 부서관리 등록/수정/삭제 처리하는 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/dept")
public class DeptManageController {


    @Resource(name = "deptService")
    private DeptService deptService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/dept";
    private final String REDIRECT_PATH = "redirect:/mes/base/dept";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("deptForm")
    public Dept newDeptForm() {
        return new Dept();
    }

    /**
     * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
     */
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") Dept searchForm, ModelMap model) throws Exception {

        List<Dept> resultList = deptService.getDeptAllList(searchForm);

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("resultList", resultList);

        return VIEW_PATH + "/list.mes";
    }

    /**
     * 부서정보를 신규로 등록한다.
     */
    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("deptForm") Dept form, Locale locale,
                             BindingResult bindingResult,
                             RedirectAttributes attr) throws Exception {

        if (StringUtils.hasText(form.getOrgnztId())) {
            int cnt = deptService.getDeptListTotCnt(form);
            if (cnt > 0) {
                bindingResult.rejectValue("orgnztId", "duplicated", "duplicated");
            }
        }

        new DeptAddFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "deptForm", bindingResult);
            attr.addFlashAttribute("deptForm", form);

            return REDIRECT_PATH + "/list.do";
        }

        try {
            deptService.addDept(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return REDIRECT_PATH + "/list.do";
    }

    /**
     * 기 등록된 부서정보를 수정한다.
     */
    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("deptForm") Dept form, Locale locale,
                             BindingResult bindingResult,
                             RedirectAttributes attr) throws Exception {

        new DeptEditormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "deptForm", bindingResult);
            attr.addFlashAttribute("deptForm", form);

            return REDIRECT_PATH + "/list.do";
        }

        try {
            deptService.editDept(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.update");
        }

        return REDIRECT_PATH + "/list.do";
    }

    /**
     * 기 등록된 부서정보를 삭제한다.
     */
    @PostMapping(value = "/remove.do")
    public String removeAction(@ModelAttribute("deptForm") Dept form, Locale locale,
                             RedirectAttributes attr) throws Exception {

        try {
            deptService.removeDept(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.delete");
        }

        return REDIRECT_PATH + "/list.do";
    }
}
