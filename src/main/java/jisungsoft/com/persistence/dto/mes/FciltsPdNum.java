package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 설비별품번
 */
@Data
public class FciltsPdNum extends Default {
    /**
     * 설비품번 ID
     */
    private Integer fcpdId;
    /**
     * 품번 ID
     */
    private Integer pdnumId;
    /**
     * 설비 ID
     */
    private Integer pdfciltsId;
    /**
     * 품번
     */
    private String pdnumNum;
    /**
     * 품명
     */
    private String pdnumNm;
    /**
     * 품번 적용수
     */
    private Integer pdNumCnt;
    /**
     * 설비코드
     */
    private String pdfciltsCode;
    /**
     * 설비명
     */
    private String pdfciltsNm;
    /**
     * 품번ID array
     */
    private Integer[] pdnumIdArr;
}
