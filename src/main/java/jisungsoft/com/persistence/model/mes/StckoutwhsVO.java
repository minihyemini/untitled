package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 재고출고
 */
@Getter
@Setter
public class StckoutwhsVO {
    /**
     * 재고출고 ID
     */
    private Integer stoutwhId;
    /**
     * 재고출고일
     */
    private String stoutwhDate;
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
     * 재고 ID
     */
    private Integer strId;
}
