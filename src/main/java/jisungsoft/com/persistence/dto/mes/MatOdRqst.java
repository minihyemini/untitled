package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 자재구매요청
 */
@Data
public class MatOdRqst extends Default {
    /**
     * 자재구매요청ID
     */
    private Integer matodrqstId;
    /**
     * 자재구매요청수량
     */
    private Integer matodrqstQnty;
    /**
     * 자재구매요청사유
     */
    private String matodrqstRson;
    /**
     * 자재구매요청자
     */
    private String matodrqstRqster;
    /**
     * 자재구매입고요청일
     */
    private String matodrqstRcvdate;
    /**
     * 비고
     */
    private String matodrqstDesc;
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
     * 거래처ID
     */
    private Integer cltId;
}
