package jisungsoft.com.cop.bbs.service;

import lombok.Data;

/**
 * 게시판변경내역
 */
@Data
public class BbsHistoryVO {
    /**
     *
     */
    private Integer nttHstrId;
    /**
     *
     */
    private String NttId;
    /**
     *
     */
    private String NttHstrSj;
    /**
     *
     */
    private String NttHstrCn;
    /**
     *
     */
    private String FrstRegistPnttm;
    /**
     *
     */
    private String FrstRegisterId;
    /**
     *
     */
    private String AtchFileId;
}
