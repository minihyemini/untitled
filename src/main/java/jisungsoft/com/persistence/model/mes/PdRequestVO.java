package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 생산의뢰
 */
@Getter @Setter
public class PdRequestVO {
    /**
     * 생산의뢰ID
     */
    private Integer prId;
    /**
     * 품번ID
     */
    private Integer pdnumId;
    /**
     * 거래처ID
     */
    private Integer cltId;
    /**
     * 발주수량
     */
    private Integer prOrderqnty;
    /**
     * 납기일자
     */
    private String prDuedate;
    /**
     * 생산의뢰수량
     */
    private Integer prQnty;
    /**
     * 생산완료요청일
     */
    private String prCompleted;
    /**
     * 용도
     */
    private String prUsage;
    /**
     * 비고
     */
    private String prDesc;
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
}
