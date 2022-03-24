package jisungsoft.com.persistence.model.sym.code;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class CmmnCodeVO extends Default {

	private static final long serialVersionUID = 4861619118930452502L;

	/** 코드 */
	private String codeId;

	/** 코드명 */
	private String codeIdNm;

	/** 코드설명 */
	private String codeIdDc;

	/** 사용여부 */
	private String useAt;

	/** 분류코드 */
	private String clCode;

	/** 분류코드명 */
	private String clCodeNm;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최종수정자ID */
	private String lastUpdusrId;

	protected CmmnCodeVO() {
	}

	public static CmmnCodeVO createDataCmmnCode(String codeId, String codeIdNm, String codeIdDc, String useAt, String clCode, String clCodeNm, String frstRegisterId, String lastUpdusrId) {
		CmmnCodeVO cmmnCodeVO = new CmmnCodeVO();

		cmmnCodeVO.codeId = codeId;
		cmmnCodeVO.codeIdNm = codeIdNm;
		cmmnCodeVO.codeIdDc = codeIdDc;
		cmmnCodeVO.useAt = useAt;
		cmmnCodeVO.clCode = clCode;
		cmmnCodeVO.clCodeNm = clCodeNm;
		cmmnCodeVO.frstRegisterId = frstRegisterId;
		cmmnCodeVO.lastUpdusrId = lastUpdusrId;
		return cmmnCodeVO;
	}
}
