package jisungsoft.com.mes.base.client.pdnum.validation;

import jisungsoft.com.persistence.dto.mes.PdnumClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientPdnumFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PdnumClient.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PdnumClient form = (PdnumClient) target;

        if (form.getCltId() == null || form.getCltId() == 0) {
            errors.rejectValue("cltId", "required", "required");
        }
    }
}
