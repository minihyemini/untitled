package jisungsoft.com.mes.base.mber.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.mes.base.mber.MberSttusCode;
import jisungsoft.com.mes.base.mber.SexdstnCode;
import jisungsoft.com.mes.base.mber.form.EmployeeMemberForm;
import jisungsoft.com.mes.base.mber.form.MemberForm;
import jisungsoft.com.mes.base.mber.validation.MemberPasswordChangeFormValidator;
import jisungsoft.com.persistence.dto.member.EmployeeMember;
import jisungsoft.com.persistence.dto.sec.AuthorInfo;
import jisungsoft.com.service.AuthorInfoService;
import jisungsoft.com.service.EmployeeMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.*;

/**
 * 업무회원 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/mes/base/member/employee")
public class EmployeeMemberRestController {

    /**
     * 업무회원 Service
     */
    @Resource(name = "employeeMemberService")
    private EmployeeMemberService employeeMemberService;

    @Resource(name = "authorInfoService")
    private AuthorInfoService authorInfoService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Autowired
    SessionLocaleResolver localeResolver;

    /**
     * 회원상세조회
     */
    @PostMapping("/detail.json")
    public ModelAndView detail(EmployeeMemberForm formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {

            return modelAndView;
        }

        EmployeeMember employeeMember = new EmployeeMember();
        employeeMember.setUniqId(formData.getUniqId());
        EmployeeMember result = employeeMemberService.getEmployeeMemberDetail(employeeMember);
        if (StringUtils.hasText(result.getEmplyrSttusCode())) {
            if (MberSttusCode.A.name().equals(result.getEmplyrSttusCode())) {
                result.setEmplyrSttusCodeNm(egovMessageSource.getMessage("member.waiting"));
            } else if (MberSttusCode.P.name().equals(result.getEmplyrSttusCode())) {
                result.setEmplyrSttusCodeNm(egovMessageSource.getMessage("member.entering"));
            } else if (MberSttusCode.D.name().equals(result.getEmplyrSttusCode())) {
                result.setEmplyrSttusCodeNm(egovMessageSource.getMessage("member.quitting"));
            }
        }

        if (StringUtils.hasText(result.getSexdstnCode())) {
            if (SexdstnCode.F.name().equals(result.getSexdstnCode())) {
                result.setSexdstnCodeNm(egovMessageSource.getMessage("member.male"));
            } else if (SexdstnCode.M.name().equals(result.getSexdstnCode())) {
                result.setSexdstnCodeNm(egovMessageSource.getMessage("member.female"));
            }
        }

        List<AuthorInfo> authorInfoList = authorInfoService.getAuthorInfoAllList(new AuthorInfo());

        modelAndView.addObject("employee", result);
        modelAndView.addObject("authorInfoList", authorInfoList);

        return modelAndView;
    }

    /**
     * 비밀번호 변경처리
     */
    @PostMapping(value = "/pwdEdit.json")
    public ModelAndView editPasswordAction(@Validated MemberForm form,
                                     final BindingResult bindingResult, Model model, Locale locale,
                                     final RedirectAttributes attr) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");

        new MemberPasswordChangeFormValidator().validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            List<Map> errors = new ArrayList<>();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError err : allErrors) {
                Map<String, Object> data = new LinkedHashMap<>();
                String message = egovMessageSource.getMessage(err.getCode(), locale);
                data.put("error", err);
                data.put("errorMessage", message);
                errors.add(data);
            }

            mav.addObject("errors", errors);
            return mav;
        }

        try {
            EmployeeMember employeeMember = new EmployeeMember();
            employeeMember.setEmplyrId(form.getId());
            employeeMember.setUniqId(form.getUniqId());
            employeeMember.setPassword(form.getPassword());
            employeeMemberService.editEmployeeMemberPassword(employeeMember);

            mav.addObject("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.debug(e.getMessage());
            mav.addObject("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.debug(e.getMessage());
            mav.addObject("resultMessage", egovMessageSource.getMessage("fail.common.update", locale));
        }

        return mav;
    }

    /**
     * 인증제한 해제
     */
    @RequestMapping(value = "/lockIncorrect.json", method = RequestMethod.POST)
    public ModelAndView lockIncorrect(@Validated MemberForm form, final BindingResult bindingResult, Locale locale) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");
        if (!StringUtils.hasText(form.getUniqId())) {
            bindingResult.rejectValue("uniqId", "required", "required");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            List<Map> errors = new ArrayList<>();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError err : allErrors) {
                Map<String, Object> data = new LinkedHashMap<>();
                String message = egovMessageSource.getMessage(err.getCode(), locale);

                data.put("error", err);
                data.put("errorMessage", message);
                errors.add(data);
            }

            mav.addObject("errors", errors);
            return mav;
        }

        EmployeeMember employeeMember = new EmployeeMember();
        employeeMember.setUniqId(form.getUniqId());

        employeeMemberService.editLockIncorrect(employeeMember);

        mav.addObject("resultMsg", egovMessageSource.getMessage("success.request.msg"));
        return mav;
    }
}
