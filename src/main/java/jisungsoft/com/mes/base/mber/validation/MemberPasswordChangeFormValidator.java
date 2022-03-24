package jisungsoft.com.mes.base.mber.validation;

import jisungsoft.com.mes.base.mber.form.EmployeeMemberForm;
import jisungsoft.com.mes.base.mber.form.MemberForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 *
 */
@Component
public class MemberPasswordChangeFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MemberForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm form = (MemberForm) target;

        //'숫자', '문자' 무조건 1개 이상, '최소 8자에서 최대 20자' 허용
        // (특수문자는 정의된 특수문자만 사용 가능)
        Pattern passwdPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$");
/*
        if (!StringUtils.hasText(form.getId())) {
            errors.rejectValue("id", "required", "required");
        }
        */
        if (!StringUtils.hasText(form.getUniqId())) {
            errors.rejectValue("uniqId", "required", "required");
        }
        if (!StringUtils.hasText(form.getPassword())) {
            errors.rejectValue("password", "required.password", "required");
        }
        if (!StringUtils.hasText(form.getRePassword())) {
            errors.rejectValue("rePassword", "required.password", "required");
        }

        if (StringUtils.hasText(form.getPassword())) {
            if (StringUtils.hasText(form.getRePassword())) {
                //비밀번호 패턴 체크
                if (!passwdPattern.matcher(form.getPassword()).find()) {
                    errors.rejectValue("password", "invalid.password", new Object[]{1, 8, 20}, null);
                }
                //비밀번호 일치여부
                if (!form.getPassword().equals(form.getRePassword())) {
                    errors.rejectValue("rePassword", "range.rePassword", null);
                }
            }
        }
    }
}
