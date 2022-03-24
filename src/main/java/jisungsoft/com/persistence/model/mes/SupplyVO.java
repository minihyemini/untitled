package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 납품
 */
@Getter
@Setter
public class SupplyVO {
    /**
     * 납품 ID
     */
    private Integer sppId;
    /**
     * 판매 ID
     */
    private Integer slsId;
    /**
     * 납품일자
     */
    private String sppDate;
    /**
     * 납품수량
     */
    private Integer sppQnty;
    /**
     * 최초등록일
     */
    private String frstRegistPnttm;
    /**
     * 최조등록자 ID
     */
    private String frstRegisterId;
    /**
     * 최종수정시점
     */
    private String lastUpdtPnttm;
    /**
     * 최종수정자 ID
     */
    private String lastUpdusrId;
}
