package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 생산계획
 */
@Getter
@Setter
public class PdplanVO {
    /**
     * 생산계획 ID
     */
    private Integer pdplId;
    /**
     * 설비 ID
     */
    private Integer pdfciltsId;
    /**
     * 품번 ID
     */
    private Integer pdnumId;
    /**
     * 생산계획일
     */
    private String pdplDate;
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
}
