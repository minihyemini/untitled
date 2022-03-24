package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 판매
 */
@Data
public class Sles extends Default {
    /**
     * 판매 ID
     */
    private Integer slsId;
    /**
     * 주문 ID
     */
    private Integer ordId;
    /**
     * 거래처 ID
     */
    private Integer clmId;
    /**
     * 판매번호
     */
    private String slsNum;
    /**
     * 판매일
     */
    private String slsDate;
    /**
     * 판매등록일자
     */
    private String slsSelldt;
    /**
     * 비고
     */
    private String slsDesc;
    /**
     * 품번 ID
     */
    private Integer pdnumId;
    /**
     * 최초등록일
     */
    private String frstRegistPnttm;
    /**
     * 최초등록자 ID
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
