package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 생산작업지시
 */
@Getter
@Setter
public class PdexcdrectionVO {
    /**
     * 작업지시 ID
     */
    private Integer pdexcdrId;
    /**
     * 설비 ID
     */
    private Integer pdfciltsId;
    /**
     * 품번 ID
     */
    private Integer pdnumId;
    /**
     * 공장ID
     */
    private Integer fiId;
    /**
     * 작업지시_코드
     */
    private String pdexcdrCode;
    /**
     * 작업지시_작업순서
     */
    private Integer pdexcdrOrder;
    /**
     * 작업지시_계획수량
     */
    private Integer pdexcdrQnty;
    /**
     * 작업지시_비고
     */
    private String pdexcdrDesc;
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
    /**
     * 사용여부
     */
    private String useAt;
}
