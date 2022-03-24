package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 품번별라우팅관리
 */
@Data
public class Pdnumbyroutg extends Default {
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
    /**
     * 품번
     */
    private String pdnumNum;
    /**
     * 품명
     */
    private String pdnumNm;
    /**
     * 적용수
     */
    private Integer pbrCnt;
    /**
     * 체크박스 적용여부
     */
    private String pbrCheck;

    List<Pdnumbyroutg> pdnumroutgList = new ArrayList<>();
}
