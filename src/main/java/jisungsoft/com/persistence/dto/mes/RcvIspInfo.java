package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 수입검사정보
 */
@Data
public class RcvIspInfo extends Default {

    /**
     * 수입검사ID
     */
    private Integer riiId;
    /**
     * 검사코드
     */
    private String riiCode;
    /**
     * 검사담당자
     */
    private String esntlId;
    /**
     * 검사명
     */
    private String riiName;
    /**
     * 검사방식구분
     */
    private String riiType;
    /**
     * 엄격도구분
     */
    private String riiLeveltype;
    /**
     * 판정구분
     */
    private String riiDecidetype;
    /**
     * 검사일자
     */
    private String riiDate;
    /**
     * 접수일자
     */
    private String riiReceiptdate;
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
     * 품번ID
     */
    private Integer pdnumId;
    /**
     * 거래처ID
     */
    private Integer cltId;

}
