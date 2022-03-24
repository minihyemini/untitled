package jisungsoft.com.mes.base.process.validation;

import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ProcessCoderValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CmmnDetailCode.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CmmnDetailCode form = (CmmnDetailCode) target;

        //한글 포함여부
        Pattern eachkoreanWord = Pattern.compile(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");

        if (!StringUtils.hasText(form.getCodeId())) {
            errors.rejectValue("codeId", "required", "required");
        } else {
            if (eachkoreanWord.matcher(form.getCodeId()).find()) {
                errors.rejectValue("codeId", "invalid.language", "invalid");
            }
            if (form.getCodeId().length() > 10) {
                errors.rejectValue("codeId", "maxlength", new Object[]{10}, "maxlength");
            }
        }
        if (!StringUtils.hasText(form.getCodeNm())) {
            errors.rejectValue("codeNm", "required", "required");
        } else {
            if (form.getCodeNm().length() > 50) {
                errors.rejectValue("codeNm", "maxlength", new Object[]{50}, "maxlength");
            }
        }
        if (!StringUtils.hasText(form.getUseAt())) {
            errors.rejectValue("useAt", "required", "required");
        }
        if (StringUtils.hasText(form.getCodeDc())) {
            if (form.getClCode().length() > 50) {
                errors.rejectValue("codeDc", "maxlength", new Object[]{200}, "maxlength");
            }
        }
    }
}
