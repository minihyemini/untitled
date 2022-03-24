package jisungsoft.com.cop.bbs.service;

import lombok.Data;

@Data
public class BbsViewerAuthVO {

    /** 열람권한 ID*/
    private Integer bvaId;
    /** 게시판 ID*/
    private Integer nttId;
    /** 고유 ID*/
    private String esntlId;
    /** 게시판 변경내역 ID */
    private Integer nttHstrId;
}
