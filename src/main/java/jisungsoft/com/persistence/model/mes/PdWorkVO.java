package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 생산작업
 */
@Getter @Setter
public class PdWorkVO {
    /**
     * 생산작업ID
     */
    private Integer pdwId;
    /**
     * 작업지시ID
     */
    private Integer pdexcdrId;
    /**
     * 총생산량
     */
    private Integer pdwTotqnty;
    /**
     * 작업일자
     */
    private String pdwWorkdate;
    /**
     * 시작일시
     */
    private String pdwWorkbegindt;
    /**
     * 종료일시
     */
    private String pdwWorkenddt;
    /**
     * 비고
     */
    private String pdwDesc;
    /**
     * 완료여부
     */
    private String completeAt;
    /**
     * 생산작업LOT_NO
     */
    private String pdwLotNo;
}
