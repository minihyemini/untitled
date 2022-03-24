package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 창고
 */
@Getter @Setter
public class StorageVO {
    /**
     * 창고ID
     */
    private Integer strgId;
    /**
     * 창고코드
     */
    private String strgCode;
    /**
     * 창고명
     */
    private String strgNm;
    /**
     * 창고구분
     */
    private String strgType;
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
     * 사용여부
     */
    private String useAt;
    /**
     * 제품ID
     */
    private Integer pdId;
    /**
     * 자재ID
     */
    private Integer matId;
}
