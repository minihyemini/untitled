package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 수입검사정보
 */
@Getter
public class RcvIspInfoVO {

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

    protected RcvIspInfoVO() {
    }

    public static RcvIspInfoVO  createDataRcvIspInfo(Integer riiId, String riiCode, String esntlId, String riiName, String riiType, String riiLeveltype, String riiDecidetype, String riiDate, String riiReceiptdate, String frstRegisterId, String lastUpdusrId, Integer pdnumId, Integer cltId) {
        RcvIspInfoVO rcvIspInfoVO = new RcvIspInfoVO();
        rcvIspInfoVO.riiId = riiId;
        rcvIspInfoVO.riiCode = riiCode;
        rcvIspInfoVO.esntlId = esntlId;
        rcvIspInfoVO.riiName = riiName;
        rcvIspInfoVO.riiType = riiType;
        rcvIspInfoVO.riiLeveltype = riiLeveltype;
        rcvIspInfoVO.riiDecidetype = riiDecidetype;
        rcvIspInfoVO.riiDate = StringUtils.hasText(riiDate) ? riiDate : null;
        rcvIspInfoVO.riiReceiptdate = StringUtils.hasText(riiReceiptdate) ? riiReceiptdate : null;
        rcvIspInfoVO.frstRegisterId = frstRegisterId;
        rcvIspInfoVO.lastUpdusrId = lastUpdusrId;
        rcvIspInfoVO.pdnumId = pdnumId;
        rcvIspInfoVO.cltId = cltId;

        return rcvIspInfoVO;
    }
}
