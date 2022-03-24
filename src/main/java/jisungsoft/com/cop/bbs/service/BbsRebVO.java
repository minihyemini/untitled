package jisungsoft.com.cop.bbs.service;

import lombok.Data;

/**
 * 게시판-상담지식
 */
@Data
public class BbsRebVO extends BbsReb  {
    /**
     * 분류
     */
    private String rebTypeCode;
    /**
     * 키워드
     */
    private String rebKeyword;
    /**
     * 중요도
     */
    private String rebGradeCode;
    /**
     * 진행상황코드
     */
    private String rebProgressCode;
    /**
     * 미처리내용
     */
    private String rebUnproDesc;
    /**
     * 요청코드
     */
    private String rebReqcode;
    /**
     * 요청결과
     */
    private String rebReqresult;
    /**
     * 대분류
     */
    private String rebTypeLargeCode;
    private String rebTypeLargeNm;
    /**
     * 중분류
     */
    private String rebTypeMediumCode;
    private String rebTypeMediumNm;
    /**
     * 소분류
     */
    private String rebTypeSmallNm;
    /**
     * 결재종류
     */
    private String ssType;
}
