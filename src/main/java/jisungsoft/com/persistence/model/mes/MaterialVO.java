package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 자재
 */
@Getter @Setter
public class MaterialVO {
    /**
     * 자재ID
     */
    private Integer matId;
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
    private Integer pdnumId;
    /**
     * 불량코드ID
     */
    private Integer fcId;

    protected MaterialVO() {
    }

    public MaterialVO(Integer matId, String lotNo, String matStatuscode, String matBarcode, String matCode, String matPrdcdate, String useAt, Integer pdnumId, Integer fcId) {
        this.matId = matId;
        this.lotNo = lotNo;
        this.matStatuscode = matStatuscode;
        this.matBarcode = matBarcode;
        this.matCode = matCode;
        this.matPrdcdate = matPrdcdate;
        this.useAt = useAt;
        this.pdnumId = pdnumId;
        this.fcId = fcId;
    }

    public static MaterialVO createDataMaterial(Integer matId, String lotNo, String matStatuscode, String matBarcode, String matCode, String matPrdcdate, String useAt, Integer pdnumId, Integer fcId) {
        MaterialVO materialVO = new MaterialVO();
        materialVO.matId = matId;
        materialVO.lotNo = lotNo;
        materialVO.matStatuscode = matStatuscode;
        materialVO.matBarcode = matBarcode;
        materialVO.matCode = matCode;
        materialVO.matPrdcdate = StringUtils.hasText(matPrdcdate) ? matPrdcdate : null;
        materialVO.useAt = useAt;
        materialVO.pdnumId = pdnumId;
        materialVO.fcId = fcId;

        return materialVO;
    }
}
