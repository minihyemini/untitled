package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 불량코드
 */
@Getter @Setter
public class FaultycodeVO {

    /**
     * 불량코드ID
     */
    private Integer fcId;
    /**
     * 불량코드
     */
    private String fcCode;
    /**
     * 불량명
     */
    private String fcNm;
    /**
     * 불량타입
     */
    private String fcType;
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

    protected FaultycodeVO() {
    }
}
