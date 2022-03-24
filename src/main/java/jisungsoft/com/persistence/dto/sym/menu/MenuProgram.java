package jisungsoft.com.persistence.dto.sym.menu;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuProgram extends Default {

    /**
     * 프로그램ID
     */
    private Integer progrmId;
    /**
     * 프로그램파일명
     */
    private String progrmFileNm;
    /**
     * 프로그램저장경로
     */
    private String progrmStrePath;
    /**
     * 프로그램한글명
     */
    private String progrmKoreanNm;
    /**
     * 프로그램설명
     */
    private String progrmDc;
    /**
     * URL
     */
    private String url;
    /**
     * 게시판id
     */
    private String bbsId;
    /**
     * 프로그램분류코드
     */
    private String progrmCode;
    /**
     * 프로그램변수
     */
    private String progrmQuery;
    /**
     * 최초등록시점
     */
    private String frstRegistPnttm;
    /**
     * 최초수정시점
     */
    private String lastUpdtPnttm;
}
