package jisungsoft.com.mes.sym.code.validation;

import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ClCodeEditFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CmmnClCode.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CmmnClCode form = (CmmnClCode) target;
        //한글 포함여부
        Pattern eachkoreanWord = Pattern.compile(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");

        if (!StringUtils.hasText(form.getClCodeNm())) {
            errors.rejectValue("name", "required", "required");
        } else {
            if (form.getClCodeNm().length() > 50) {
                errors.rejectValue("id", "maxlength", new Object[]{50}, "maxlength");
            }
        }
        if (!StringUtils.hasText(form.getUseAt())) {
            errors.rejectValue("useAt", "required", "required");
        }
        if (StringUtils.hasText(form.getClCodeDc())) {
            if (form.getClCode().length() > 50) {
                errors.rejectValue("desc", "maxlength", new Object[]{200}, "maxlength");
            }
        }
    }
}
