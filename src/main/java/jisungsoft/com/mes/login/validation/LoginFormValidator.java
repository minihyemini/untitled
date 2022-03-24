package jisungsoft.com.mes.login.validation;

import jisungsoft.com.mes.login.form.LoginForm;
import jisungsoft.com.persistence.model.LoginVO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return LoginVO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginVO form = (LoginVO) target;

        if (!StringUtils.hasText(form.getId())) {
            errors.rejectValue("id", "required", "required");
        }
        if (!StringUtils.hasText(form.getPassword())) {
            errors.rejectValue("password", "required", "required");
        }
    }
}
