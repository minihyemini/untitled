package jisungsoft.com.persistence.dto.member;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

/**
 * 일반회원 DTO
 */
@Getter @Setter
public class GeneralMember extends Default {
    /**
     * 사용자고유아이디
     */
    private String uniqId;
    /**
     * 회원ID
     */
    private String mberId;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 회원명
     */
    private String mberNm;
    /**
     * 지역번호
     */
    private String areaNo;
    /**
     * 우편번호
     */
    private String zip;
    /**
     * 주소
     */
    private String adres;
    /**
     * 상세주소
     */
    private String detailAdres;
    /**
     * 회원상태
     */
    private String mberSttusCode;
    /**
     * 이동전화번호
     */
    private String mbtlnum;
    /**
     * 사무실전화번호
     */
    private String offmTelno;
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 회원이메일주소
     */
    private String mberEmailAdres;
    /**
     * 가입일자
     */
    private String sbscrbDe;
    /**
     * 성별코드
     */
    private String sexdstnCode;
    /**
     * 생년월일
     */
    private String brthdy;
    /**
     * 잠금여부
     */
    private String lockAt;
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
     * 권한코드
     */
    private String authorCode;
}
