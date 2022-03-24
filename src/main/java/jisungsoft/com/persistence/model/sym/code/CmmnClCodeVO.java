package jisungsoft.com.persistence.model.sym.code;

import lombok.Data;

@Data
public class CmmnClCodeVO {

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

	protected CmmnClCodeVO() {
	}

	public static CmmnClCodeVO createDataCmmClCodeData(String clCode, String clCodeNm, String clCodeDc, String useAt, String frstRegisterId) {
		CmmnClCodeVO cmmnClCodeVO = new CmmnClCodeVO();
		cmmnClCodeVO.clCode = clCode;
		cmmnClCodeVO.clCodeNm = clCodeNm;
		cmmnClCodeVO.clCodeDc = clCodeDc;
		cmmnClCodeVO.useAt = useAt;
		cmmnClCodeVO.frstRegisterId = frstRegisterId;

		return cmmnClCodeVO;
	}
}
