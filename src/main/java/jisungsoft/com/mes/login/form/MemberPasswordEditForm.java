package jisungsoft.com.mes.login.form;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

/**
 * 비밀번호 변경 Form
 */
@Getter @Setter
public class MemberPasswordEditForm extends Default {

    private String id;

    private String uniqId;

    private String password;

    private String rePassword;
}
