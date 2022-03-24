package jisungsoft.com.persistence.dto.member;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

/**
 * 업무회원 DTO
 */
@Getter @Setter
public class EmployeeMember extends Default {

    /**
     * 사용자고유아이디
     */
    private String uniqId;
    /**
     * 사용자 ID
     */
    private String emplyrId;
    /**
     * (부서)조직 ID
     */
    private String orgnztId;
    /**
     * (부서)조직명
     */
    private String orgnztNm;
    /**
     * 사용자 명
     */
    private String emplyrNm;
    /**
     * 그룹 ID
     */
    private String groupId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 사원 번호
     */
    private String emplyrNum;
    /**
     * 비밀번호
     */
    private String password;
    /** 이전비밀번호 - 비밀번호 변경시 사용*/
    private String oldPassword = "";
    /**
     * 성별코드
     */
    private String sexdstnCode;
    /**
     * 성별코드명
     */
    private String sexdstnCodeNm;
    /**
     * 외국인여부
     */
    private String foreignerAt;
    /**
     * 근태체크여부
     */
    private String attendanceAt;
    /**
     * 생일
     */
    private String brthdy;
    /**
     * 주소
     */
    private String adres;
    /**
     * 지역번호
     */
    private String areaNo;
    /**
     * 상세주소
     */
    private String detailAdres;
    /**
     * 우편번호
     */
    private String zip;
    /**
     * 사무실전화번호
     */
    private String offmTelno;
    /**
     * 핸드폰번호
     */
    private String mbtlnum;
    /**
     * 이메일주소
     */
    private String emailAdres;
    /**
     * 직위명
     */
    private String ofcpsNm;
    /**
     * 소속기관코드
     */
    private String pstinstCode;
    /**
     * 사용자 상태
     */
    private String emplyrSttusCode;
    /**
     * 사용자 상태명
     */
    private String emplyrSttusCodeNm;
    /**
     * 잠금여부
     */
    private String lockAt;
    /**
     * 가입일
     */
    private String sbscrbDe;
    /**
     * 입사일
     */
    private String joiningDate;
    /**
     * 퇴사일
     */
    private String quittingDate;
    /**
     * 사용자 유형
     */
    private String userTy;
    /**
     * 검색조건 회원타입
     */
    private String mberTy;
    /**
     * 검색조건 가입일자 시작일
     */
    private String sbscrbDeBegin;
    /**
     * 검색조건 가입일자 종료일
     */
    private String sbscrbDeEnd;
    /**
     * DN 값
     */
    private String subDn;
    /**
     * 권한코드
     */
    private String authorCode;
}
