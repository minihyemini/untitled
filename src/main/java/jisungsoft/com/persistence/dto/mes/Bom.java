package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * BOM관리
 */
@Data
public class Bom extends Default {
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
