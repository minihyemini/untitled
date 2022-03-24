package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 검수마감관리
 */
@Data
public class SalesClose extends Default {
    /**
     * 판매ID
     */
    private Integer slsId;
    /**
     * 거래처ID
     */
    private Integer cltId;
    /**
     * 품번ID
     */
    private Integer pdnumId;
    /**
     * 제품ID
     */
    private Integer pdId;
    /**
     * 주문ID
     */
    private Integer ordId;
}
