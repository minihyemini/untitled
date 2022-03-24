package jisungsoft.com.mes.base.mber.form;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class GeneralMemberAddForm extends Default {

    private String name;

    private String id;

    private String password;

    private String rePassword;

    private String sexdstnCode;

    private String birthday;

    private String mobileNum;

    private String offmTelno;

    private String email;

    private String address;

    private String detailAddress;

    private String areaNo;

    private String zip;
}
