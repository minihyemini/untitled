package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 주문
 */
@Getter @Setter
public class OrderVO {
    /**
     * 주문ID
     */
    private Integer ordId;
    /**
     * 주문 LOT_NO
     */
    private String ordLotNo;
    /**
     * 주문 품목ID
     */
    private Integer oiId;
    /**
     * 판매번호
     */
    private String ordNum;
    /**
     * 주문타입
     */
    private String ordType;
    /**
     * 주문상태
     */
    private String ordStatus;
    /**
     * 거래유형
     */
    private String ordCategory;
    /**
     * 단가
     */
    private Integer unitPrice;
    /**
     * 수량
     */
    private Integer qunty;
    /**
     * 부가세포함여부
     */
    private String surtaxAt;
    /**
     * 부가세율
     */
    private Double surtaxRate;
    /**
     * 주문일
     */
    private String orderDate;
    /**
     * 납기일
     */
    private String ordDuedate;
    /**
     * 판매일/납품예정일
     */
    private String ordDlvrschdt;
    /**
     * 담당자
     */
    private String esntlId;
    /**
     * 비고
     */
    private String ordDesc;
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
     * 자재ID
     */
    private Integer matId;
    /**
     * 제품ID
     */
    private Integer pdId;
    /**
     * 거래처ID
     */
    private Integer cltId;
    /**
     * 자재구매요청ID
     */
    private Integer matodrqstId;

    protected OrderVO() {

    }

    public static OrderVO createDataOrderItem(Integer oiId, Integer ordId, Integer pdnumId, Integer unitPrice, Integer qunty, String surtaxAt, Double surtaxRate) {
        OrderVO itemVO = new OrderVO();

        itemVO.oiId = oiId;
        itemVO.ordId = ordId;
        itemVO.pdnumId = pdnumId;
        itemVO.unitPrice = unitPrice;
        itemVO.qunty = qunty;
        itemVO.surtaxAt = "N";
        itemVO.surtaxRate = surtaxRate;

        if (StringUtils.hasText(surtaxAt)) {
            itemVO.surtaxAt = surtaxAt;
        }

        return itemVO;
    }

    public static OrderVO createDataOrder(Integer ordId, String ordLotNo, String ordNum, String ordType, String ordStatus, String ordCategory, String orderDate, String ordDuedate, String ordDlvrschdt, String esntlId, String frstRegisterId, String lastUpdusrId, Integer matId, Integer pdId, Integer cltId, Integer matodrqstId, String ordDesc) {
        OrderVO orderVO = new OrderVO();

        orderVO.ordId = ordId;
        orderVO.ordLotNo = ordLotNo;
        orderVO.ordNum = ordNum;
        orderVO.ordType = ordType;
        orderVO.ordStatus = ordStatus;
        orderVO.ordCategory = ordCategory;
        orderVO.orderDate = StringUtils.hasText(orderDate) ? orderDate : null;
        orderVO.ordDuedate = StringUtils.hasText(ordDuedate) ? ordDuedate : null;
        orderVO.ordDlvrschdt = StringUtils.hasText(ordDlvrschdt) ? ordDlvrschdt : null;
        orderVO.esntlId = esntlId;
        orderVO.frstRegisterId = frstRegisterId;
        orderVO.lastUpdusrId = lastUpdusrId;
        orderVO.matId = matId;
        orderVO.pdId = pdId;
        orderVO.cltId = cltId;
        orderVO.matodrqstId = matodrqstId;
        orderVO.ordDesc = ordDesc;

        return orderVO;
    }

    public static OrderVO createDataOrderId(Integer ordId) {
        OrderVO orderVO = new OrderVO();

        orderVO.ordId = ordId;
        return orderVO;
    }

    public static OrderVO createDataOrderItemId(Integer oiId) {
        OrderVO itemVo = new OrderVO();

        itemVo.oiId = oiId;
        return itemVo;
    }
}
