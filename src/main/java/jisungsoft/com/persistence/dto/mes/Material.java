package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 자재
 */
@Data
public class Material extends Default {
    /**
     * 자재ID
     */
    private String matId;
    /**
     * 제조번호
     */
    private String lotNo;
    /**
     * 상태
     */
    private String matStatuscode;
    /**
     * 바코드
     */
    private String matBarcode;
    /**
     * 자재코드
     */
    private String matCode;
    /**
     * 생산일시
     */
    private String matPrdcdate;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 품번ID
     */
    private String pdnumId;
    /**
     * 불량코드ID
     */
    private String fcId;
}
