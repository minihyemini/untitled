package jisungsoft.com.persistence.dto.sym.code;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class CmmnDetailCode extends Default {

	private static final long serialVersionUID = 4861619118930452502L;

	/** 공통상세코드ID */
	private String code;

	/** 공통상세코드명 */
	private String codeNm;

	/** 공통상세코드설명 */
	private String codeDc;

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

	/** 정렬순서 */
	private Integer sortOrdr;

	/** 분류코드ID (부모코드) */
	private String clCode;

	/** 공통코드ID (부모코드) */
	private String codeId;

	/** 공통코드명 (부모코드) */
	private String codeIdNm;

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
