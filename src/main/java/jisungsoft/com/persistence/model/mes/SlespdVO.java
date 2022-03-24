package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 판매품목
 */
@Getter
@Setter
public class SlespdVO {
    /**
     * 판매품목 ID
     */
    private Integer slespdId;
    /**
     * 판매계획수량
     */
    private Integer slspdQnty;
    /**
     * 판매단가
     */
    private Integer slspdUntprice;
    /**
     * 판매금액
     */
    private Integer slspdPrice;
    /**
     * 부가세
     */
    private Integer slspdSrtax;
    /**
     * 판매 ID
     */
    private Integer slsId;
}
