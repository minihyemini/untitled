package jisungsoft.com.mes.base.process.pdnRouting.validation;

import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class PdnumbyroutgFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Pdnumbyroutg.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Pdnumbyroutg form = (Pdnumbyroutg) target;

        if (form.getPdnumId() == null) {
            errors.rejectValue("pdnumId", "required.pdnumbyroutgForm.pdnumId", "required");
        }

        boolean hasData = false;
        List<Pdnumbyroutg> getFormList = form.getPdnumroutgList();

        for (Pdnumbyroutg data : getFormList) {
            if (StringUtils.hasText(data.getPbrCheck())) {
                hasData = true;
            }
        }

        if (hasData == false) {
            errors.rejectValue("pdnumroutgList", "invalid");
        }

        if (getFormList.size() == 0) {
            errors.rejectValue("pdnumroutgList", "invalid");
        }
    }
}
