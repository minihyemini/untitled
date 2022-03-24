package jisungsoft.com.persistence.model.mes;

import lombok.Getter;

/**
 * 수입검사 항목
 */
@Getter
public class RcvIspCategoryVO {

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

    private RcvIspCategoryVO() {
    }

    public static RcvIspCategoryVO createDataRcvIsp(Integer ricId, String ricType, String ricDecidetype, String ricResulttype, String ricDesc, Integer riiId, Integer matId, Integer iwoId) {
        RcvIspCategoryVO rcvIspCategoryVO = new RcvIspCategoryVO();
        rcvIspCategoryVO.ricId = ricId;
        rcvIspCategoryVO.ricType = ricType;
        rcvIspCategoryVO.ricDecidetype = ricDecidetype;
        rcvIspCategoryVO.ricResulttype = ricResulttype;
        rcvIspCategoryVO.ricDesc = ricDesc;
        rcvIspCategoryVO.riiId = riiId;
        rcvIspCategoryVO.matId = matId;
        rcvIspCategoryVO.iwoId = iwoId;

        return rcvIspCategoryVO;
    }
}
