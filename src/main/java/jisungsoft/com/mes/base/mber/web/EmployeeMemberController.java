package jisungsoft.com.mes.base.mber.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.mes.base.mber.MberSttusCode;
import jisungsoft.com.mes.base.mber.form.EmployeeMemberForm;
import jisungsoft.com.mes.base.mber.form.MemberForm;
import jisungsoft.com.mes.base.mber.validation.EmployeeMemberAddFormValidator;
import jisungsoft.com.mes.base.mber.validation.EmployeeMemberEditFormValidator;
import jisungsoft.com.mes.base.mber.validation.MemberPasswordChangeFormValidator;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.persistence.dto.member.EmployeeMember;
import jisungsoft.com.persistence.dto.sec.AuthorInfo;
import jisungsoft.com.persistence.dto.sec.Group;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.service.*;
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
 * 업무회원 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/member/employee")
public class EmployeeMemberController {

    /**
     * 업무회원 Service
     */
    @Resource(name = "employeeMemberService")
    private EmployeeMemberService employeeMemberService;
    /**
     * 권한서비스
     */
    @Resource(name = "authorInfoService")
    private AuthorInfoService authorInfoService;
    /**
     * 부서 서비스
     */
    @Resource(name = "deptService")
    private DeptService deptService;
    /**
     * 그룹(팀)서비스
     */
    @Resource(name = "groupService")
    private GroupService groupService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    /**
     * 상세코드 서비스
     */
    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    @Autowired
    SessionLocaleResolver localeResolver;

    private final String VIEW_PATH = "jisungsoft/com/mes/base/member/employee";
    private final String REDIRECT_PATH = "redirect:/mes/base/member/employee";
    /**
     * code
     */
    private final String MBER_CODE = "MBERSTTS";
    private final String POSITION_CODE = "POSITION";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("memberForm")
    public MemberForm newMemberForm() {
        return new MemberForm();
    }

    /**
     * 회원 목록
     * @return
     */
    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute("searchForm") EmployeeMember employeeMember,
                       final Model model, Locale locale, HttpServletRequest request) {

        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<AuthorInfo> authorInfoList = authorInfoService.getAuthorInfoAllList(new AuthorInfo());
        List<Dept> deptList = deptService.getDeptAllList(new Dept());
        List<Group> groupList = groupService.getGroupAllList(new Group());
        CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
        /*직위코드*/
        cmmnDetailCode.setCodeId(POSITION_CODE);
        List<CmmnDetailCode> positionList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);
        /*회원상태코드*/
        cmmnDetailCode.setCodeId(MBER_CODE);
        List<CmmnDetailCode> mberStatusCodes = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);

        List<EmployeeMember> resultList = employeeMemberService.getEmployeeMemberAllList(employeeMember);

        model.addAttribute("positionList", positionList);
        model.addAttribute("deptList", deptList);
        model.addAttribute("groupList", groupList);
        model.addAttribute("resultList", resultList);
        model.addAttribute("authorInfoList", authorInfoList);
        model.addAttribute("searchForm", employeeMember);
        model.addAttribute("mberStatusCodes", mberStatusCodes);

        return VIEW_PATH + "/list.mes";
    }

    /**
     * 회원등록 처리
     */
    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("memberForm") MemberForm form,
                            final BindingResult bindingResult, Model model, Locale locale,
                            final RedirectAttributes attr) {

        attr.addFlashAttribute("menuId", form.getMenuId());
        EmployeeMember member = setMemberParam(form);

        int idCnt = employeeMemberService.checkIdDplct(member);
        int emailCnt = employeeMemberService.checkEmailDplct(member);
        if (emailCnt > 0) {
            bindingResult.rejectValue("email", "duplicated", "duplicated");
        }
        if (idCnt > 0) {
            bindingResult.rejectValue("id", "duplicated", "duplicated");
        }

        new EmployeeMemberAddFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "memberForm", bindingResult);
            attr.addFlashAttribute("memberForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            employeeMemberService.addEmployeeMember(member);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
            return REDIRECT_PATH + "/list.do";
        } catch (DataAccessException e) {
            e.printStackTrace();
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            e.printStackTrace();
            attr.addFlashAttribute("resultMessage", "fail.common.insert");
        }

        return VIEW_PATH + "/addForm.mes";
    }

    /**
     * 회원 수정 처리
     */
    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("memberForm") MemberForm form,
                             final BindingResult bindingResult, Model model, Locale locale,
                             final RedirectAttributes attr) {

        new EmployeeMemberEditFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "memberForm", bindingResult);
            attr.addFlashAttribute("memberForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            EmployeeMember member = setMemberParam(form);
            employeeMemberService.editEmployeeMember(member);

            attr.addFlashAttribute("menuId", form.getMenuId());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.debug(e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.debug(e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.update", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }

    /**
     * 비밀번호 변경처리
     */
    @PostMapping(value = "/pwdEdit.do")
    public String editPasswordAction(@Validated @ModelAttribute("memberForm") MemberForm form,
                                     final BindingResult bindingResult, Model model, Locale locale,
                                     final RedirectAttributes attr) {

        new MemberPasswordChangeFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "memberForm", bindingResult);
            attr.addFlashAttribute("memberForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            EmployeeMember employeeMember = new EmployeeMember();
            employeeMember.setUniqId(form.getUniqId());
            employeeMember.setPassword(form.getPassword());
            employeeMemberService.editEmployeeMemberPassword(employeeMember);

            attr.addFlashAttribute("menuId", form.getMenuId());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.debug(e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.debug(e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.update", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }

    /**
     * 삭제처리
     */
    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("memberForm") MemberForm form, Locale locale,
                               final RedirectAttributes attr) {

        try {
            EmployeeMember employeeMember = new EmployeeMember();
            employeeMember.setUniqId(form.getUniqId());
            employeeMemberService.removeEmployeeMember(employeeMember);

            attr.addFlashAttribute("menuId", form.getMenuId());
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

    public EmployeeMember setMemberParam(MemberForm form) {
        EmployeeMember member = new EmployeeMember();

        member.setUniqId(form.getUniqId());
        member.setEmplyrId(form.getId());
        member.setEmplyrNum(form.getId());
        member.setGroupId(form.getGroupId());
        member.setAuthorCode(form.getAuthorCode());
        member.setEmailAdres(StringUtils.hasText(form.getEmail()) ? form.getEmail() : null);
        member.setPassword(form.getPassword());
        member.setEmplyrNm(form.getName());
        member.setAdres(form.getAddress());
        member.setZip(form.getZip());
        member.setAreaNo(form.getAreaNo());
        member.setDetailAdres(form.getDetailAddress());
        member.setBrthdy(form.getBirthday());
        member.setJoiningDate(form.getJoiningDate());
        member.setQuittingDate(form.getQuittingDate());
        member.setMbtlnum(form.getMobileNum());
        member.setOffmTelno(form.getOffmTelno());
        member.setOfcpsNm(form.getOfcpsNm());
        member.setSexdstnCode(form.getSexdstnCode());
        member.setAttendanceAt(form.getAttendanceAt());
        member.setForeignerAt(form.getForeignerAt());
        member.setEmplyrSttusCode(form.getEmplyrSttusCode());
        member.setSbscrbDe(form.getSbscrbDe());

        return member;
    }
}
