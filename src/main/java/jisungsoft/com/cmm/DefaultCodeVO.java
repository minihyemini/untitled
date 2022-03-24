package jisungsoft.com.cmm;

import lombok.Data;

@Data
public class DefaultCodeVO {
    /** 분류코드 ID */
    private String clCode;

    /** 코드 ID */
    private String codeId;

    /** 상세코드 */
    private String code;

    /** 코드명 */
    private String codeNm;

    /** 코드설명 */
    private String codeDc;

    /** 특정테이블명 */
    private String tableNm;	//특정테이블에서 코드정보를추출시 사용

    /** 상세 조건 여부 */
    private String haveDetailCondition = "N";

    /** 상세 조건 */
    private String detailCondition;
}
