package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 품번별라우팅관리
 */
@Getter
@Setter
public class PdnumbyroutgVO {
    /**
     * 품번별라우팅 ID
     */
    private Integer pbrId;
    /**
     * 라우팅코드
     */
    private String rtCode;
    /**
     * 공정코드
     */
    private String pmCode;
    /**
     * 공정순서
     */
    private Integer pmSeq;
    /**
     * 실적포인트여부
     */
    private String pbrPfmpoint;
    /**
     * 리드타임
     */
    private Integer pbrReadtm;
    /**
     * 샘플리드타임
     */
    private Integer pbrSmpreadtm;
    /**
     * 비고
     */
    private String pbrDesc;
    /**
     * 품번ID
     */
    private String pdnumId;

    protected PdnumbyroutgVO() {
    }

    public static PdnumbyroutgVO createDataPdnumByRoutg(Integer pbrId, String rtCode, String pmCode, Integer pmSeq, String pbrPfmpoint, Integer pbrReadtm, Integer pbrSmpreadtm, String pbrDesc, String pdnumId) {
        PdnumbyroutgVO pdnumbyroutgVO = new PdnumbyroutgVO();

        pdnumbyroutgVO.pbrId = pbrId;
        pdnumbyroutgVO.rtCode = rtCode;
        pdnumbyroutgVO.pmCode = pmCode;
        pdnumbyroutgVO.pmSeq = pmSeq;
        pdnumbyroutgVO.pbrPfmpoint = pbrPfmpoint;
        pdnumbyroutgVO.pbrReadtm = pbrReadtm;
        pdnumbyroutgVO.pbrSmpreadtm = pbrSmpreadtm;
        pdnumbyroutgVO.pbrDesc = pbrDesc;
        pdnumbyroutgVO.pdnumId = pdnumId;

        return pdnumbyroutgVO;
    }
}
