package jisungsoft.com.persistence.dto.sym.menu;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class MenuCreat extends Default {

    /** 메뉴번호 */
    private Integer menuNo;
    /** 맵생성ID */
    private String mapngCreatId;
    /** 권한코드 */
    private String authorCode;

    /** 메뉴정보 설정*/
    /**
     * 메뉴명
     */
    private String menuNm;
    /**
     * 프로그램명
     */
    private String progrmFileNm;
    /**
     * 카테고리
     */
    private String menuCategory;
    /**
     * 메뉴 설명
     */
    private String menuDc;

    /** 권한정보설정 */
    /** 권한명 */
    private String authorNm;
    /** 권한설명 */
    private String authorDc;
    /** 권한생성일자 */
    private String authorCreatDe;
}
