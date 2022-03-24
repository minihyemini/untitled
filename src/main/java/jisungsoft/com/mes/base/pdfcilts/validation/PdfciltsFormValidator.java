package jisungsoft.com.mes.base.pdfcilts.validation;

import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PdfciltsFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Pdfcilts.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Pdfcilts form = (Pdfcilts) target;

        if (!StringUtils.hasText(form.getPdfciltsCode())) {
            errors.rejectValue("pdfciltsCode", "required", "required");
        }
        if (!StringUtils.hasText(form.getPdfciltsNm())) {
            errors.rejectValue("pdfciltsNm", "required", "required");
        }
        if (!StringUtils.hasText(form.getUseAt())) {
            errors.rejectValue("useAt", "required", "required");
        }
        if (StringUtils.hasText(form.getPdfciltsDc())) {
            if (form.getPdfciltsDc().length() > 50) {
                errors.rejectValue("pdfciltsDc", "maxlength", new Object[]{200}, "maxlength");
            }
        }
    }
}
