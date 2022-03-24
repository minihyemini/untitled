package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 제품/상품/완성품
 */
@Getter @Setter
public class ProductVO {
    /**
     * ID
     */
    private Integer pdId;
    /**
     * 제품코드
     */
    private String pdCode;
    /**
     * 바코드
     */
    private String pdBarcode;
    /**
     * 상태
     */
    private String pdStatus;
    /**
     * 입고일
     */
    private String pdRcvng;
    /**
     * 출고일
     */
    private String pdShppng;
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
     * 사용여부
     */
    private String useAt;
    /**
     * 품번ID
     */
    private String pdnumId;
}
