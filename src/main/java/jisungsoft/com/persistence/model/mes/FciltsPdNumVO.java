package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 설비별품번
 */
@Getter
@Setter
public class FciltsPdNumVO {
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

    protected FciltsPdNumVO() {
    }

    public static FciltsPdNumVO createDataFciltsbypdnu(Integer fcpdId, Integer pdnumId, Integer pdfciltsId) {
        FciltsPdNumVO fciltsbypdnummngVO = new FciltsPdNumVO();
        fciltsbypdnummngVO.fcpdId = fcpdId;
        fciltsbypdnummngVO.pdnumId = pdnumId;
        fciltsbypdnummngVO.pdfciltsId = pdfciltsId;

        return fciltsbypdnummngVO;
    }
}
