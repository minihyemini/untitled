package jisungsoft.com.cop.bbs.service;

import lombok.Data;

/**
 * 결재
 */
@Data
public class BbsSignVO {

    /** 결재권ID */
    private Integer apprId;

    /** 게시물ID */
    private Integer nttId;

    /** 결재종류 */
    private String ssType;

    /** 결재결과 */
    private String ssResult;

    /** 코멘트 */
    private String ssComment;

    /** 받은일자 */
    private String receivedate;

    /** 결재일자 */
    private String signdate;

    /** 고유ID */
    private String ssApproval;

    /** 진행상황 코드 */
    private String rebProgressCode;
}
