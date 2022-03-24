package jisungsoft.com.mes.base.group.validation;

import jisungsoft.com.persistence.dto.sec.Group;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GroupAddFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Group.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Group form = (Group) target;

        if (!StringUtils.hasText(form.getGroupNm())) {
            errors.rejectValue("groupNm", "required");
        } else {
            if (form.getGroupNm().length() > 60) {
                errors.rejectValue("groupNm", "maxlength", new Object[]{50}, "maxlength");
            }
        }

        if (!StringUtils.hasText(form.getOrgnztId())) {
            errors.rejectValue("orgnztId", "required");
        }

        if (StringUtils.hasText(form.getGroupDc())) {
            if (form.getGroupDc().length() > 50) {
                errors.rejectValue("groupDc", "maxlength", new Object[]{50}, "maxlength");
            }
        }
    }
}
