package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 주거래품번
 */
@Getter @Setter
public class PdnumClientVO {

    /**
     * 주거래품번
     */
    private Integer pcId;
    /**
     * 거래처ID
     */
    private Integer cltId;
    /**
     * 품번ID
     */
    private Integer pdnumId;

    protected PdnumClientVO() {
    }

    public static PdnumClientVO createDataPdnumClient(Integer pcId, Integer cltId, Integer pdnumId) {
        PdnumClientVO pdnumClientVO = new PdnumClientVO();
        pdnumClientVO.pcId = pcId;
        pdnumClientVO.cltId = cltId;
        pdnumClientVO.pdnumId = pdnumId;

        return pdnumClientVO;
    }
}
