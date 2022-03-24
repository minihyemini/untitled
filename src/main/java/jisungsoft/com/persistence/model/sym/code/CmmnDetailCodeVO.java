package jisungsoft.com.persistence.model.sym.code;

import lombok.Data;

@Data
public class CmmnDetailCodeVO {

	private static final long serialVersionUID = 4861619118930452502L;

	/** 공통코드ID (부모코드) */
	private String codeId;

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

	/** 정렬순서 */
	private Integer sortOrdr;

	protected CmmnDetailCodeVO() {
	}

	public static CmmnDetailCodeVO createDataCmmnDetailCode(String code, String codeId, String codeNm, String codeDc, String useAt, String frstRegisterId, String lastUpdusrId, Integer sortOrdr) {
		CmmnDetailCodeVO cmmnDetailCodeVO = new CmmnDetailCodeVO();
		cmmnDetailCodeVO.code = code;
		cmmnDetailCodeVO.codeId = codeId;
		cmmnDetailCodeVO.codeNm = codeNm;
		cmmnDetailCodeVO.codeDc = codeDc;
		cmmnDetailCodeVO.useAt = useAt;
		cmmnDetailCodeVO.frstRegisterId = frstRegisterId;
		cmmnDetailCodeVO.lastUpdusrId = lastUpdusrId;
		cmmnDetailCodeVO.sortOrdr = sortOrdr;

		return cmmnDetailCodeVO;
	}
}
