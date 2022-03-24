package jisungsoft.com.mes.base.client.validation;

import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.persistence.dto.mes.Client;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ClientFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client form = (Client) target;
        //숫자 형식
        Pattern integerPattern = Pattern.compile("^[0-9]*$");

        if (!StringUtils.hasText(form.getCltNm())) {
            errors.rejectValue("cltNm", "required", "required");
        } else {
            if (form.getCltNm().length() > 100) {
                errors.rejectValue("cltNm", "maxlength", new Object[]{100}, "maxlength");
            }
        }
        if (StringUtils.hasText(form.getCltTelno())) {
            if (!integerPattern.matcher(form.getCltTelno()).find()) {
                errors.rejectValue("cltTelno", "integer", "integer");
            }
            if (form.getCltNm().length() > 100) {
                errors.rejectValue("cltTelno", "maxlength", new Object[]{15}, "maxlength");
            }
        }
        if (StringUtils.hasText(form.getCltFaxnum())) {
            if (!integerPattern.matcher(form.getCltFaxnum()).find()) {
                errors.rejectValue("cltFaxnum", "integer", "integer");
            }
            if (form.getCltNm().length() > 100) {
                errors.rejectValue("cltFaxnum", "maxlength", new Object[]{15}, "maxlength");
            }
        }

        if (StringUtils.hasText(form.getCltEmail())) {
            if (!CommonUtil.isValidEmail(form.getCltEmail())) {
                errors.rejectValue("cltEmail", "invalid.email");
            }
            if (form.getCltNm().length() > 100) {
                errors.rejectValue("cltEmail", "maxlength", new Object[]{30}, "maxlength");
            }
        }
    }
}
