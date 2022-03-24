package jisungsoft.com.persistence.model;

import java.io.Serializable;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.Data;

@Data
public class LoginVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;
	
	/** 아이디 */
	private String id;
	/** 이름 */
	private String name;
	/** 이메일주소 */
	private String email;
	/** 휴대폰 번호 */
	private String mbtlnum;
	/** 비밀번호 */
	private String password;
	/** 사용자구분 */
	private String userSe;
	/** 조직(부서)ID */
	private String orgnztId;
	/** 조직(부서)명 */
	private String orgnztNm;
	/** 고유아이디 */
	private String uniqId;
	/** 그룹ID */
	private String groupId;
	/** 로그인 후 이동할 페이지 */
	private String url;
	/** 사용자 IP정보 */
	private String ip;
	/** 아이디저장 */
	private String rememberId;
	/** 자동로그인 */
	private String autologin;
	/** 권한 */
	private String authority;
	/** 인증번호 */
	private String authKey;
	/** 구분 */
	private String mberType;
	/** 가입일 */
	private String sbscrbDe;

	/**
	 * 아이디 찾기 validation
	 * @return
	 */
	public boolean validationSearchIdForm() {

		if (EgovStringUtil.isEmpty(name)) {
			return false;
		} else if (EgovStringUtil.isEmpty(email)) {
			return false;
		} else if (EgovStringUtil.isEmpty(authKey)) {
			return false;
		}

		return true;
	}

	/**
	 * 아이디 찾기 validation -- 휴대폰인증
	 * @return
	 */
	public boolean validationMbtlSearchIdForm() {

		if (EgovStringUtil.isEmpty(name)) {
			return false;
		} else if (EgovStringUtil.isEmpty(mbtlnum)) {
			return false;
		}

		return true;
	}

	/**
	 * 비밀번호 찾기 validation
	 * @return
	 */
	public boolean validationSearchPwForm() {
		if (EgovStringUtil.isEmpty(name)) {
			return false;
		} else if (EgovStringUtil.isEmpty(email)) {
			return false;
		} else if (EgovStringUtil.isEmpty(authKey)) {
			return false;
		}

		return true;
	}

	/**
	 * 비밀번호 찾기 validation -- 휴대폰인증
	 * @return
	 */
	public boolean validationMbtlSearchPwForm() {
		if (EgovStringUtil.isEmpty(id)) {
			return false;
		} else if (EgovStringUtil.isEmpty(name)) {
			return false;
		} else if (EgovStringUtil.isEmpty(mbtlnum)) {
			return false;
		}

		return true;
	}
	
}
