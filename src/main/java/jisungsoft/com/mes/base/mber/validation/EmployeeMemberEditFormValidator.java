package jisungsoft.com.mes.base.mber.validation;

import jisungsoft.com.mes.base.mber.form.EmployeeMemberForm;
import jisungsoft.com.mes.base.mber.form.MemberForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * 업무회원 수정 Validation
 */
@Component
public class EmployeeMemberEditFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return MemberForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm member = (MemberForm) target;

        //이메일 형식
        Pattern emailPattern = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
        //숫자 형식
        Pattern integerPattern = Pattern.compile("^[0-9]*$");

        if (!StringUtils.hasText(member.getUniqId())) {
            errors.rejectValue("uniqId", "required", "required");
        }
        if (StringUtils.hasText(member.getEmail())) {
            //이메일 패턴 체크
            if (!emailPattern.matcher(member.getEmail()).find()) {
                errors.rejectValue("email", "invalid", "invalid");
            }
        }
        if (!StringUtils.hasText(member.getName())) {
            errors.rejectValue("name", "required", "required");
        }
    }
}
