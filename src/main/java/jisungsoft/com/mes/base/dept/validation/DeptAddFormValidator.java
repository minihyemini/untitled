package jisungsoft.com.mes.base.dept.validation;

import jisungsoft.com.persistence.dto.member.Dept;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 부서 등록 Validation
 */
@Component
public class DeptAddFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Dept.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Dept deptAddForm = (Dept) target;

        if (!StringUtils.hasText(deptAddForm.getOrgnztNm())) {
            errors.rejectValue("orgnztNm", "required");
        } else {
            if (deptAddForm.getOrgnztNm().length() > 40) {
                errors.rejectValue("orgnztNm", "maxlength", new Object[]{50}, "maxlength");
            }
        }

        if (StringUtils.hasText(deptAddForm.getOrgnztDc())) {
            if (deptAddForm.getOrgnztDc().length() > 50) {
                errors.rejectValue("orgnztDc", "maxlength", new Object[]{50}, "maxlength");
            }
        }
    }
}
