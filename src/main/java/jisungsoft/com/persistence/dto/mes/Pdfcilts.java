package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 설비
 */
@Data
public class Pdfcilts extends Default {
    /**
     * 설비 ID
     */
    private Integer pdfciltsId;
    /**
     * 설비코드
     */
    private String pdfciltsCode;
    /**
     * 설비명
     */
    private String pdfciltsNm;
    /**
     * PLC코드
     */
    private String plcDvcode;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 설명
     */
    private String pdfciltsDc;
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
     * 등록자ID
     */
    private String frstRegisterUserId;
    /**
     * 사용자명
     */
    private String frstRegisterUserNm;

    /**
     * 수정자ID
     */
    private String lastUpdusrUserId;

    /**
     * 수정자명
     */
    private String lastUpdusrUserNm;
}
