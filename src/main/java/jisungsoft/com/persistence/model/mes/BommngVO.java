package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * BOM관리
 */
@Getter
@Setter
public class BommngVO {
    /**
     * BOM ID
     */
    private Integer bomId;
    /**
     * BOM 레벨
     */
    private Integer bomLv;
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
    private Integer lastUpdusrId;
    /**
     * 품번 ID
     */
    private Integer pdnumId;
}
