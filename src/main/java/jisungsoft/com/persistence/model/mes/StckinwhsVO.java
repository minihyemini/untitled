package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 재고입고
 */
@Getter
@Setter
public class StckinwhsVO {
    /**
     * 재고입고 ID
     */
    private Integer stinwhId;
    /**
     * 재고입고일
     */
    private String stinwhInDate;
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
