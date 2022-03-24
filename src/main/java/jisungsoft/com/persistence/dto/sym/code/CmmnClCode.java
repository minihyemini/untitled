package jisungsoft.com.persistence.dto.sym.code;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class CmmnClCode extends Default {

	private static final long serialVersionUID = 4861619118930452502L;

	/** 분류코드 */
	private String clCode;

	/** 분류코드명 */
	private String clCodeNm;

	/** 분류코드설명 */
	private String clCodeDc;

	/** 사용여부 */
	private String useAt;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최초등록시점 */
	private String frstRegistPnttm;

	/** 최종수정시점 */
	private String lastUpdtPnttm;
	/**
	 * 등록자ID
	 */
	private String frstRegisterUserId;
	/**
	 * 사용자명
	 */
	private String frstRegisterUserNm;
	/**
	 * 수정자ID
	 */
	private String lastUpdusrUserId;
	/**
	 * 수정자명
	 */
	private String lastUpdusrUserNm;
}
