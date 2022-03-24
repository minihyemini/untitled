package jisungsoft.com.mes.sym.code.validation;

import jisungsoft.com.persistence.dto.sym.code.CmmnCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class CodeEditFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CmmnCode.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CmmnCode form = (CmmnCode) target;
        //한글 포함여부
        Pattern eachkoreanWord = Pattern.compile(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");

        if (!StringUtils.hasText(form.getCodeId())) {
            errors.rejectValue("codeId", "required", "required");
        } else {
            if (eachkoreanWord.matcher(form.getCodeId()).find()) {
                errors.rejectValue("codeId", "invalid.language", "invalid");
            }
            if (form.getClCode().length() > 10) {
                errors.rejectValue("codeId", "maxlength", new Object[]{10}, "maxlength");
            }
        }
        if (!StringUtils.hasText(form.getClCode())) {
            errors.rejectValue("clCode", "required", "required");
        } else {
            if (eachkoreanWord.matcher(form.getClCode()).find()) {
                errors.rejectValue("clCode", "invalid.language", "invalid");
            }
            if (form.getClCode().length() > 10) {
                errors.rejectValue("clCode", "maxlength", new Object[]{10}, "maxlength");
            }
        }
        if (!StringUtils.hasText(form.getCodeIdNm())) {
            errors.rejectValue("codeIdNm", "required", "required");
        } else {
            if (form.getCodeIdNm().length() > 50) {
                errors.rejectValue("codeIdNm", "maxlength", new Object[]{50}, "maxlength");
            }
        }
        if (!StringUtils.hasText(form.getUseAt())) {
            errors.rejectValue("useAt", "required", "required");
        }
        if (StringUtils.hasText(form.getCodeIdDc())) {
            if (form.getClCode().length() > 50) {
                errors.rejectValue("codeIdDc", "maxlength", new Object[]{200}, "maxlength");
            }
        }
    }
}
