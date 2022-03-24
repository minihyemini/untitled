package jisungsoft.com.mes.base.dept.validation;

import jisungsoft.com.persistence.dto.member.Dept;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 부서 수정 Validation
 */
@Component
public class DeptEditormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Dept.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Dept form = (Dept) target;

        if (!StringUtils.hasText(form.getOrgnztId())) {
            errors.rejectValue("orgnztId", "required");
        }

        if (!StringUtils.hasText(form.getOrgnztNm())) {
            errors.rejectValue("orgnztNm", "required");
        } else {
            if (form.getOrgnztNm().length() > 40) {
                errors.rejectValue("orgnztNm", "maxlength", new Object[]{50}, "maxlength");
            }
        }

        if (StringUtils.hasText(form.getOrgnztDc())) {
            if (form.getOrgnztDc().length() > 50) {
                errors.rejectValue("orgnztDc", "maxlength", new Object[]{50}, "maxlength");
            }
        }
    }
}
