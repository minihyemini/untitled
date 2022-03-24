package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 설비
 */
@Getter
@Setter
public class PdfciltsVO {
    /**
     * 설비 ID
     */
    private Integer pdfciltsId;
    /**
     * 설비코드
     */
    private String pdfciltsCode;
    /**
     * 설비명
     */
    private String pdfciltsNm;
    /**
     * PLC 코드
     */
    private String plcDvcode;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 설명
     */
    private String pdfciltsDc;
    /**
     * 최초등록자 ID
     */
    private String frstRegisterId;
    /**
     * 최종수정자 ID
     */
    private String lastUpdusrId;

    protected PdfciltsVO() {
    }

    public static PdfciltsVO createDataPdfcilts(Integer pdfciltsId, String pdfciltsCode, String pdfciltsNm, String plcDvcode, String useAt, String frstRegisterId, String lastUpdusrId) {
        PdfciltsVO pdfciltsVO = new PdfciltsVO();

        pdfciltsVO.pdfciltsId = pdfciltsId;
        pdfciltsVO.pdfciltsCode = pdfciltsCode;
        pdfciltsVO.pdfciltsNm = pdfciltsNm;
        pdfciltsVO.plcDvcode = plcDvcode;
        pdfciltsVO.useAt = useAt;
        pdfciltsVO.frstRegisterId = frstRegisterId;
        pdfciltsVO.lastUpdusrId = lastUpdusrId;

        return pdfciltsVO;
    }
}
