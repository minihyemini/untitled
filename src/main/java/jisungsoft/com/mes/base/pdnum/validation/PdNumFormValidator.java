package jisungsoft.com.mes.base.pdnum.validation;

import jisungsoft.com.persistence.dto.mes.Pdnum;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PdNumFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Pdnum.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Pdnum form = (Pdnum) target;
        //품번
        if (!StringUtils.hasText(form.getPdnumNum())) {
            errors.rejectValue("pdnumNum", "required", "required");
        }
        //품명
        if (!StringUtils.hasText(form.getPdnumNm())) {
            errors.rejectValue("pdnumNm", "required", "required");
        }
        //단위중량
        if (form.getPdnumWght() != null) {

        }
        //품목규격
        if (StringUtils.hasText(form.getPdnumStnd())) {
            if (form.getPdnumStnd().length() > 49) {
                errors.rejectValue("pdnumStnd", "maxlength", new Object[]{49}, "maxlength");
            }
        }
        //품목안전재고
        if (form.getPdnumSfstck() != null) {

        }
        //품목코드
        if (StringUtils.hasText(form.getPdnumCode())) {
            if (form.getPdnumCode().length() > 9) {
                errors.rejectValue("pdnumCode", "maxlength", new Object[]{9}, "maxlength");
            }
        }
        //품목자재코드
        if (StringUtils.hasText(form.getPdnumMtrscode())) {
            if (form.getPdnumMtrscode().length() > 9) {
                errors.rejectValue("pdnumMtrscode", "maxlength", new Object[]{9}, "maxlength");
            }
        }
        //등록도면수량
        if (form.getPdnumDrwqnty() != null) {

        }
        //품목단위
        if (StringUtils.hasText(form.getPdnumUnit())) {
            if (form.getPdnumUnit().length() > 9) {
                errors.rejectValue("pdnumUnit", "maxlength", new Object[]{9}, "maxlength");
            }
        }
    }
}
