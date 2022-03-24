package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 자재품번
 */
@Getter
@Setter
public class MaterialnumVO {
    /**
     * 자재 ID
     */
    private Integer matId;
    /**
     * 자재품명
     */
    private String matName;
    /**
     * 자재코드
     */
    private String matCode;
    /**
     * 자재코드명
     */
    private String matCodename;
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
     * 사용여부
     */
    private String useAt;
    /**
     * 거래처 ID
     */
    private Integer clmId;
}
