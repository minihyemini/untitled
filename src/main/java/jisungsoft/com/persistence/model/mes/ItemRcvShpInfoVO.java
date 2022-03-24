package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 품목입출고정보
 */
@Getter
public class ItemRcvShpInfoVO {
    /**
     * 입출고ID
     */
    private Integer irsId;
    /**
     * 입출고 LOT_NO
     */
    private String irsLotNo;
    /**
     * 입고일시
     */
    private String irsRcvdate;
    /**
     * 출고일시
     */
    private String irsShpdate;
    /**
     * 타입
     */
    private String irsType;
    /**
     * 상태
     */
    private String irsStatus;
    /**
     * 자재ID
     */
    private Integer matId;
    /**
     * 제품ID
     */
    private Integer pdId;
    /**
     * 주문ID
     */
    private Integer ordId;

    protected ItemRcvShpInfoVO() {
    }

    public static ItemRcvShpInfoVO createDataItemRcvShpInfo(Integer irsId, String irsLotNo, String irsRcvdate, String irsShpdate, String irsType, String irsStatus, Integer matId, Integer pdId, Integer ordId) {
        ItemRcvShpInfoVO itemRcvShpInfoVO = new ItemRcvShpInfoVO();

        itemRcvShpInfoVO.irsId = irsId;
        itemRcvShpInfoVO.irsLotNo = irsLotNo;
        itemRcvShpInfoVO.irsRcvdate = StringUtils.hasText(irsRcvdate) ? irsRcvdate : null;
        itemRcvShpInfoVO.irsShpdate = StringUtils.hasText(irsShpdate) ? irsShpdate : null;
        itemRcvShpInfoVO.irsType = irsType;
        itemRcvShpInfoVO.irsStatus = irsStatus;
        itemRcvShpInfoVO.matId = matId;
        itemRcvShpInfoVO.pdId = pdId;
        itemRcvShpInfoVO.ordId = ordId;

        return itemRcvShpInfoVO;
    }
}
