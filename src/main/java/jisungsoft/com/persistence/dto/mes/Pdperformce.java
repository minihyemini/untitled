package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 생산실적
 */
@Data
public class Pdperformce extends Default {

    /**
     * 생산실적ID
     */
    private Integer pdperId;
    /**
     * 실적수량
     */
    private Integer pdperQnty;
    /**
     * 불량수량
     */
    private Integer pdperDftvqnty;
    /**
     * 셋팅수량
     */
    private Integer pdperSetqnty;
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
     * 작업지시ID
     */
    private Integer pdexcdrId;
    /**
     * 생산작업ID
     */
    private Integer pdwId;
    /**
     * 완료여부
     */
    private String completeAt;
}
