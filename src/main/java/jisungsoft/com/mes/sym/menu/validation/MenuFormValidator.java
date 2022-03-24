package jisungsoft.com.mes.sym.menu.validation;

import jisungsoft.com.persistence.dto.sym.menu.Menu;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MenuFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Menu.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Menu form = (Menu) target;

        if (!StringUtils.hasText(form.getMenuNm())) {
            errors.rejectValue("menuNm", "required", "required");
        }
        if (form.getProgrmId() == null) {
            errors.rejectValue("progrmFileNm", "required", "required");
        }
    }
}
