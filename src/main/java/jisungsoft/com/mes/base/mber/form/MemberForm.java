package jisungsoft.com.mes.base.mber.form;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 회원공통 폼
 */
@Data
public class MemberForm extends Default {
    /**
     * 유니크ID
     */
    private String uniqId;
    /**
     * 회원명
     */
    private String name;
    /**
     * 회원ID
     */
    private String id;
    /**
     * 직위명
     */
    private String ofcpsNm;
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 부서ID
     */
    private String deptId;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 비밀번호재입력
     */
    private String rePassword;
    /**
     * 성별
     */
    private String sexdstnCode;
    /**
     * 근태체크여부
     */
    private String attendanceAt;
    /**
     * 외국인여부
     */
    private String foreignerAt;
    /**
     * 생년월일
     */
    private String birthday;
    /**
     * 사용여부
     */
    private String userTy;
    /**
     * 휴대전화번호
     */
    private String mobileNum;
    /**
     * 사무실번호
     */
    private String offmTelno;
    /**
     * 이메일
     */
    private String email;
    /**
     * 주소
     */
    private String address;
    /**
     * 상세주소
     */
    private String detailAddress;
    /**
     * 지역번호
     */
    private String areaNo;
    /**
     * zip
     */
    private String zip;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 계정상태
     */
    private String emplyrSttusCode;
    /**
     * 입사일
     */
    private String joiningDate;
    /**
     * 퇴사일
     */
    private String quittingDate;
    /**
     * 등록일
     */
    private String sbscrbDe;
}
