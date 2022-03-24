package jisungsoft.com.persistence.model.mes;

import lombok.Getter;

/**
 * 검사작업지시
 */
@Getter
public class IspcWorkOrderVO {

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

    protected IspcWorkOrderVO() {
    }

    public static IspcWorkOrderVO createDataWorkOrder(String iwoId, String iwoLotNo, String iwoStatus, String iwoIspcqnty, String iwoEnterqnty, String iwoWaitingqnty, String iwoPriority, String ordId, String cltId, String irsId, String frstRegisterId, String lastUpdusrId) {
        IspcWorkOrderVO ispcWorkOrderVO = new IspcWorkOrderVO();
        ispcWorkOrderVO.iwoId = iwoId;
        ispcWorkOrderVO.iwoLotNo = iwoLotNo;
        ispcWorkOrderVO.iwoStatus = iwoStatus;
        ispcWorkOrderVO.iwoIspcqnty = iwoIspcqnty;
        ispcWorkOrderVO.iwoEnterqnty = iwoEnterqnty;
        ispcWorkOrderVO.iwoWaitingqnty = iwoWaitingqnty;
        ispcWorkOrderVO.iwoPriority = iwoPriority;
        ispcWorkOrderVO.ordId = ordId;
        ispcWorkOrderVO.cltId = cltId;
        ispcWorkOrderVO.irsId = irsId;
        ispcWorkOrderVO.frstRegisterId = frstRegisterId;
        ispcWorkOrderVO.lastUpdusrId = lastUpdusrId;

        return ispcWorkOrderVO;
    }
}
