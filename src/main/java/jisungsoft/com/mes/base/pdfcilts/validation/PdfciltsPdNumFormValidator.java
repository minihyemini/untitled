package jisungsoft.com.mes.base.pdfcilts.validation;

import jisungsoft.com.persistence.dto.mes.FciltsPdNum;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PdfciltsPdNumFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FciltsPdNum.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FciltsPdNum form = (FciltsPdNum) target;
        Integer[] i = form.getPdnumIdArr();
        if (i == null) {
            errors.rejectValue("pdnumIdArr", "invalid", "invalid");
        }
        if (form.getPdfciltsId() == null) {
            errors.rejectValue("pdfciltsId", "required", "required");
        }
    }
}
