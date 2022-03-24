package jisungsoft.com.uss.umt.model;

import lombok.Data;

/**
 * 업무사용자VO클래스로서 업무사용자관리 비지니스로직 처리용 항목을 구성한다.
 */
@Data
public class UserManageVO extends UserDefaultVO{

	private static final long serialVersionUID = 3640820362821490939L;

	/**
	 * 사용자고유아이디
	 */
	private String uniqId="";
	/**
	 * 사용자 ID
	 */
	private String emplyrId;
	/**
	 * 조직 ID
	 */
	private String orgnztId;
	/**
	 * 사용자 명
	 */
	private String emplyrNm;
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
	 * 그룹 ID
	 */
	private String groupId;
	/**
	 * 소속기관코드
	 */
	private String pstinstCode;
	/**
	 * 사용자 상태
	 */
	private String emplyrSttusCode;
	/**
	 * 잠금여부
	 */
	private String lockAt;
    /**
	 * 가입일
	 */
	private String sbscrbDe;
	/**
	 * 사용자 유형
	 */
	private String userTy;
	/**
	 * 검색조건 회원타입
	 */
	private String mberTy;
	/**
	 * DN 값
	 */
	private String subDn;
	/**
	 * 권한코드
	 */
	private String authorCode;
}