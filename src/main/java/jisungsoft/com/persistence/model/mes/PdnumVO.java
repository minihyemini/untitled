package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 품번
 */
@Getter
@Setter
public class PdnumVO {
    /**
     * 품번 ID
     */
    private Integer pdnumId;
    /**
     * 품번
     */
    private String pdnumNum;
    /**
     * 품명
     */
    private String pdnumNm;
    /**
     * 제품구분
     */
    private String pdnumType;
    /**
     * 단위중량
     */
    private Integer pdnumWght;
    /**
     * 품목규격
     */
    private String pdnumStnd;
    /**
     * 품목안전재고
     */
    private Integer pdnumSfstck;
    /**
     * 품목코드
     */
    private String pdnumCode;
    /**
     * 품목자재코드
     */
    private String pdnumMtrscode;
    /**
     * 내외작구분
     */
    private String pdnumIotype;
    /**
     * 도면승은일
     */
    private String pdnumDrwappldate;
    /**
     * 등록도면수량
     */
    private Integer pdnumDrwqnty;
    /**
     * 품목단위
     */
    private String pdnumUnit;
    /**
     * 첨부파일
     */
    private String atchFileId;
    /**
     * 이미지파일
     */
    private String imgFileId;
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

    protected PdnumVO() {
    }

    public static PdnumVO createDataPdNum(Integer pdnumId, String pdnumNum, String pdnumNm, String pdnumType, Integer pdnumWght, String pdnumStnd, Integer pdnumSfstck, String pdnumCode, String pdnumMtrscode, String pdnumIotype, String pdnumDrwappldate, Integer pdnumDrwqnty, String pdnumUnit, String atchFileId, String imgFileId, String frstRegisterId, String lastUpdusrId, String useAt) {
        PdnumVO pdnumVO = new PdnumVO();
        pdnumVO.pdnumId = pdnumId;
        pdnumVO.pdnumNum = pdnumNum;
        pdnumVO.pdnumNm = pdnumNm;
        pdnumVO.pdnumType = pdnumType;
        pdnumVO.pdnumWght = pdnumWght;
        pdnumVO.pdnumStnd = pdnumStnd;
        pdnumVO.pdnumSfstck = pdnumSfstck;
        pdnumVO.pdnumCode = pdnumCode;
        pdnumVO.pdnumMtrscode = pdnumMtrscode;
        pdnumVO.pdnumIotype = pdnumIotype;
        pdnumVO.pdnumDrwqnty = pdnumDrwqnty;
        pdnumVO.pdnumUnit = pdnumUnit;
        pdnumVO.atchFileId = atchFileId;
        pdnumVO.imgFileId = imgFileId;
        pdnumVO.frstRegisterId = frstRegisterId;
        pdnumVO.lastUpdusrId = lastUpdusrId;
        pdnumVO.useAt = useAt;
        if (StringUtils.hasText(pdnumDrwappldate)) pdnumVO.pdnumDrwappldate = pdnumDrwappldate;
        return pdnumVO;
    }

    public static PdnumVO createDataPdNumId(Integer pdnumId) {
        PdnumVO pdnumVO = new PdnumVO();
        pdnumVO.pdnumId = pdnumId;
        return pdnumVO;
    }
}
