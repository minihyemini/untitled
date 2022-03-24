package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 수입검사 항목
 */
@Data
public class RcvIspCategory extends Default {

    /**
     * 수입검사항목ID
     */
    private Integer ricId;
    /**
     * 검사항목타입
     */
    private String ricType;
    /**
     * 검사판정구분
     */
    private String ricDecidetype;
    /**
     * 검사결과
     */
    private String ricResulttype;
    /**
     * 비고
     */
    private String ricDesc;
    /**
     * 수입검사ID
     */
    private Integer riiId;
    /**
     * 자재ID
     */
    private Integer matId;
    /**
     * 검사작업지시ID
     */
    private Integer iwoId;
}
