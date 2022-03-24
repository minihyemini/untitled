package jisungsoft.com.persistence.model.sym.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * 프로그램 VO
 */
@Getter @Setter
public class MenuProgramVO {

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

    protected MenuProgramVO() {
    }


    public static MenuProgramVO createDataMenuProgram(Integer progrmId, String progrmFileNm, String progrmStrePath, String progrmKoreanNm, String progrmDc, String url, String progrmCode, String progrmQuery, String frstRegistPnttm, String lastUpdtPnttm) {
        MenuProgramVO menuProgramVO = new MenuProgramVO();
        menuProgramVO.progrmId = progrmId;
        menuProgramVO.progrmFileNm = progrmFileNm;
        menuProgramVO.progrmStrePath = progrmStrePath;
        menuProgramVO.progrmKoreanNm = progrmKoreanNm;
        menuProgramVO.progrmDc = progrmDc;
        menuProgramVO.url = url;
        menuProgramVO.progrmCode = progrmCode;
        menuProgramVO.progrmQuery = progrmQuery;
        menuProgramVO.frstRegistPnttm = frstRegistPnttm;
        menuProgramVO.lastUpdtPnttm = lastUpdtPnttm;

        return menuProgramVO;
    }

    public static MenuProgramVO createDataIdMenuProgram(Integer progrmId) {
        MenuProgramVO menuProgramVO = new MenuProgramVO();
        menuProgramVO.progrmId = progrmId;
        return menuProgramVO;
    }
}
