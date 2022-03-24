package jisungsoft.com.persistence.dto.mes;

import lombok.Data;

/**
 * 검사작업지시
 */
@Data
public class IspcWorkOrder {

    /**
     * 검사작업지시ID
     */
    private String iwoId;
    /**
     * 검사LotNo
     */
    private String iwoLotNo;
    /**
     * 검사상태
     */
    private String iwoStatus;
    /**
     * 검사수량
     */
    private String iwoIspcqnty;
    /**
     * 입고수량
     */
    private String iwoEnterqnty;
    /**
     * 대기수량
     */
    private String iwoWaitingqnty;
    /**
     * 우선순위
     */
    private String iwoPriority;
    /**
     * 주문ID
     */
    private String ordId;
    /**
     * 거래처ID
     */
    private String cltId;
    /**
     * 입출고ID
     */
    private String irsId;
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
