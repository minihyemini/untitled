package jisungsoft.com.mes.sym.menu.program.form;

import lombok.Data;

@Data
public class MenuProgramAddForm {

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
     * 프로그램분류코드
     */
    private String progrmCode;
    /**
     * 프로그램변수
     */
    private String progrmQuery;
}
