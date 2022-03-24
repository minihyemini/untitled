package jisungsoft.com.cop.bbs.service;

import lombok.Data;

@Data
public class BbsReb extends BbsRebApproval {
    /**
     * 대분류 검색
     */
    private String searchRebTypeLarge;
    /**
     * 중분류 검색
     */
    private String searchRebTypeMedium;
    /**
     * 소분류 검색
     */
    private String searchRebTypeSmall;

    /**
     * 부서명 검색
     */
    private String searchOrgnztId;

    /**
     * 팀명 검색
     */
    private String searchGroupId;

    /**
     * 팀명 검색
     */
    private String searchEsntlId;

    /**
     * 변경내역 갯수
     */
    private Integer historyCnt;
}
