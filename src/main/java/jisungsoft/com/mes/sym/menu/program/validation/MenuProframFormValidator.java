package jisungsoft.com.mes.sym.menu.program.validation;

import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 메뉴 프로그램 등록 Validation
 */
@Component
public class MenuProframFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MenuProgram.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MenuProgram form = (MenuProgram) target;

        if (!StringUtils.hasText(form.getProgrmFileNm())) {
            errors.rejectValue("progrmFileNm", "required", "required");
        }
        if (!StringUtils.hasText(form.getProgrmKoreanNm())) {
            errors.rejectValue("progrmKoreanNm", "required", "required");
        }
        if (!StringUtils.hasText(form.getUrl())) {
            errors.rejectValue("url", "required", "required");
        }
    }
}
