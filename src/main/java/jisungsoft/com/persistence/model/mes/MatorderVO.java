package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 자재주문
 */
@Getter @Setter
public class MatorderVO {
    /**
     * 자재주문ID
     */
    private Integer matordId;
    /**
     * 단가
     */
    private Integer unitPrice;
    /**
     * 수량
     */
    private Integer qunty;
    /**
     * 공급가액
     */
    private Integer supplyPrice;
    /**
     * 부가세
     */
    private Integer surtax;
    /**
     * 주문일
     */
    private String orderDate;
    /**
     * 최초등록일
     */
    private String frstRegistPnttm;
    /**
     * 최초등록자ID
     */
    private String frstRegisterId;
    /**
     * 최종수정시점
     */
    private String lastUpdtPnttm;
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId;
    /**
     * 품번ID
     */
    private Integer pdnumId;
    /**
     * 창고ID
     */
    private Integer strgId;

    protected MatorderVO() {
    }
}
