package jisungsoft.com.sym.prm.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class ProgramManage extends Default {
    /**
     * 프로그램파일명
     */
    private String progrmFileNm;
    /**
     * 프로그램 분류코드
     */
    private String progrmCode;
    /**
     *
     */
    private String progrmCodeNm;
    /**
     * 프로그램설명
     */
    private String progrmDc;
    /**
     * 프로그램한글명
     */
    private String progrmKoreanNm;
    /**
     * 프로그램저장경로
     */
    private String progrmStrePath;
    /**
     * URL
     */
    private String url;
    /**
     * url query string
     */
    private String progrmQuery;
    /**
     * 최초등록날짜
     */
    private String frstRegistPnttm;
}
