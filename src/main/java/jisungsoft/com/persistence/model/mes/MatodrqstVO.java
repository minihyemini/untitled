package jisungsoft.com.persistence.model.mes;

import lombok.Getter;

/**
 * 자재구매요청
 */
@Getter
public class MatodrqstVO {

    /**
     * 자재구매요청ID
     */
    private Integer matodrqstId;
    /**
     * 자재구매요청수량
     */
    private Integer matodrqstQnty;
    /**
     * 자재구매요청사유
     */
    private String matodrqstRson;
    /**
     * 자재구매요청자
     */
    private String matodrqstRqster;
    /**
     * 자재구매입고요청일
     */
    private String matodrqstRcvdate;
    /**
     * 비고
     */
    private String matodrqstDesc;
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

    protected MatodrqstVO() {
    }

    public static MatodrqstVO createDataMatodrqst(Integer matodrqstId, Integer matodrqstQnty, String matodrqstRson, String matodrqstRqster, String matodrqstRcvdate, String matodrqstDesc, String frstRegisterId, String lastUpdusrId, Integer pdnumId, Integer cltId) {
        MatodrqstVO matodrqstVO = new MatodrqstVO();
        matodrqstVO.matodrqstId = matodrqstId;
        matodrqstVO.matodrqstQnty = matodrqstQnty;
        matodrqstVO.matodrqstRson = matodrqstRson;
        matodrqstVO.matodrqstRqster = matodrqstRqster;
        matodrqstVO.matodrqstRcvdate = matodrqstRcvdate;
        matodrqstVO.matodrqstDesc = matodrqstDesc;
        matodrqstVO.frstRegisterId = frstRegisterId;
        matodrqstVO.lastUpdusrId = lastUpdusrId;
        matodrqstVO.pdnumId = pdnumId;
        matodrqstVO.cltId = cltId;

        return matodrqstVO;
    }

    public static MatodrqstVO createDataMatodrqstId(Integer matodrqstId, Integer pdnumId, Integer cltId) {
        MatodrqstVO matodrqstVO = new MatodrqstVO();
        matodrqstVO.matodrqstId = matodrqstId;
        matodrqstVO.pdnumId = pdnumId;
        matodrqstVO.cltId = cltId;

        return matodrqstVO;
    }
}
