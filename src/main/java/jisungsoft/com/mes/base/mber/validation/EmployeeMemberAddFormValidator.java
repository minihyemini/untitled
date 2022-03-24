package jisungsoft.com.mes.base.mber.validation;

import jisungsoft.com.mes.base.mber.form.EmployeeMemberForm;
import jisungsoft.com.mes.base.mber.form.MemberForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * 업무회원 등록 Validation
 */
@Component
public class EmployeeMemberAddFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MemberForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm member = (MemberForm) target;

        //이메일 형식
        Pattern emailPattern = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
        //숫자 형식
        Pattern integerPattern = Pattern.compile("^[0-9]*$");
        //'숫자', '문자' 무조건 1개 이상, '최소 8자에서 최대 20자' 허용
        // (특수문자는 정의된 특수문자만 사용 가능)
        Pattern passwdPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$");
        //한글 포함여부
        Pattern eachkoreanWord = Pattern.compile(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
        /*  사원번호 자동생성으로 주석처리
        if (!StringUtils.hasText(member.getId())) {
            errors.rejectValue("id", "required", "required");
        }
        */
        if (StringUtils.hasText(member.getId())) {
            if (member.getId().length() > 20) {
                errors.rejectValue("id", "maxlength", new Object[]{20}, "maxlength");
            }
            if (eachkoreanWord.matcher(member.getId()).find()) {
                errors.rejectValue("id", "invalid.language", "invalid");
            }
        }
        /* 비밀번호 자동생성(생년월일)
        if (!StringUtils.hasText(member.getPassword())) {
            errors.rejectValue("password", "required", "required");
        }
        if (!StringUtils.hasText(member.getRePassword())) {
            errors.rejectValue("rePassword", "required", "required");
        }
        */
        if (!StringUtils.hasText(member.getBirthday())) {
            errors.rejectValue("birthday", "required", "required");
        }
        if (StringUtils.hasText(member.getEmail())) {
            //이메일 패턴 체크
            if (!emailPattern.matcher(member.getEmail()).find()) {
                errors.rejectValue("email", "invalid", "invalid");
            }
        }
        if (StringUtils.hasText(member.getPassword())) {
            if (StringUtils.hasText(member.getRePassword())) {
                //비밀번호 패턴 체크
                if (!passwdPattern.matcher(member.getPassword()).find()) {
                    errors.rejectValue("password", "invalid", new Object[]{1, 8, 20}, null);
                }
                //비밀번호 일치여부
                if (!member.getPassword().equals(member.getRePassword())) {
                    errors.rejectValue("rePassword", "range", null);
                }
            }
        }
        if (!StringUtils.hasText(member.getName())) {
            errors.rejectValue("name", "required", "required");
        }
    }
}
