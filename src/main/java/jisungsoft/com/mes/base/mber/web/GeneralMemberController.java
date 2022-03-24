package jisungsoft.com.mes.base.mber.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.code.MemberStatusCode;
import jisungsoft.com.mes.base.mber.form.GeneralMemberAddForm;
import jisungsoft.com.mes.base.mber.form.GeneralMemberEditForm;
import jisungsoft.com.mes.base.mber.validation.GeneralMemberAddFormValidator;
import jisungsoft.com.mes.base.mber.validation.GeneralMemberEditFormValidator;
import jisungsoft.com.persistence.dto.member.GeneralMember;
import jisungsoft.com.service.GeneralMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * 일반회원 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/member/general")
public class GeneralMemberController {

    @Resource(name = "generalMemberService")
    private GeneralMemberService generalMemberService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    private final String viewPath = "jisungsoft/com/mes/base/member/general";

    /**
     * 회원 목록
     * @return
     */
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Model model, GeneralMember generalMember) {

        List<GeneralMember> resultList = generalMemberService.getGeneralMemberAllList(generalMember);

        model.addAttribute("resultList", resultList);
        model.addAttribute("generalMember", generalMember);
        model.addAttribute("generalMemberEditForm", new GeneralMemberEditForm());

        return viewPath + "/list.mes";
    }

    /**
     * 회원등록 폼
     * @return
     */
    @PostMapping(value = "/addForm.do")
    public String addForm(Model model) {

        GeneralMemberAddForm form = new GeneralMemberAddForm();
        model.addAttribute("generalMemberAddForm", form);

        return viewPath + "/addForm.mes";
    }

    /**
     * 회원 등록처리
     * @param form
     * @return
     */
    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("generalMemberAddForm") GeneralMemberAddForm form,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes) throws Exception {

        new GeneralMemberAddFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return viewPath + "/addForm.mes";
        }

        try {
            GeneralMember generalMember = new GeneralMember();
            generalMember.setMberId(form.getId());
            generalMember.setMberEmailAdres(form.getEmail());
            generalMember.setPassword(form.getPassword());
            generalMember.setMberNm(form.getName());
            generalMember.setAdres(form.getAddress());
            generalMember.setAreaNo(form.getAreaNo());
            generalMember.setDetailAdres(form.getDetailAddress());
            generalMember.setBrthdy(form.getBirthday());
            generalMember.setMberSttusCode(MemberStatusCode.A.name());
            generalMemberService.addGeneralMember(generalMember);

            redirectAttributes.addAttribute("menuId", form.getMenuId());
            return "redirect:/mes/base/member/general/list.do";
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("failMessage", egovMessageSource.getMessage("fail.common.sql"));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("failMessage", egovMessageSource.getMessage("fail.common.insert"));
        }

        return viewPath + "/addForm.mes";
    }

    /**
     * 회원 수정처리
     * @param form
     * @return
     */
    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("generalMemberEditForm") GeneralMemberEditForm form,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        new GeneralMemberEditFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            return viewPath + "/list.mes";
        }

        return "redirect:/mes/base/member/general/list.do";
    }

}
