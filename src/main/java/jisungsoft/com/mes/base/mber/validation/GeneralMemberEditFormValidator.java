package jisungsoft.com.mes.base.mber.validation;

import jisungsoft.com.mes.base.mber.form.GeneralMemberEditForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 일반회원 수정 Validation
 */
@Component
public class GeneralMemberEditFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return GeneralMemberEditForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GeneralMemberEditForm member = (GeneralMemberEditForm) target;

        if (!StringUtils.hasText(member.getUniqId())) {
            errors.rejectValue("uniqId", "required", "required");
        }
        if (!StringUtils.hasText(member.getId())) {
            errors.rejectValue("id", "required", "required");
        }
        if (!StringUtils.hasText(member.getName())) {
            errors.rejectValue("name", "required", "required");
        }
    }
}
