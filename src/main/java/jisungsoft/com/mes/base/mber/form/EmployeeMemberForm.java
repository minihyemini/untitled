package jisungsoft.com.mes.base.mber.form;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class EmployeeMemberForm extends Default {

    private String uniqId;

    private String name;

    private String id;

    private String employeeNum;

    private String ofcpsNm;

    private String password;

    private String rePassword;

    private String sexdstnCode;

    private String attendanceAt;

    private String foreignerAt;

    private String birthday;

    private String userTy;

    private String mobileNum;

    private String offmTelno;

    private String email;

    private String address;

    private String detailAddress;

    private String areaNo;

    private String zip;

    private String authorCode;

    private String emplyrSttusCode;
    /**
     * 입사일
     */
    private String joiningDate;
    /**
     * 퇴사일
     */
    private String quittingDate;
}
