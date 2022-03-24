package jisungsoft.com.cop.bbs.service;

import lombok.Data;

/**
 * 게시물결재권자/열람권한
 */
@Data
public class BbsRebApproval extends ArticleDataVO {

    /**
     * 결재권ID
     */
    private Integer apprId;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 고유ID
     */
    private String esntlId;
    /**
     * 부서장
     */
    private String deptHeader;
    /**
     * 그룹장/매니저
     */
    private String grpHeader;
}
