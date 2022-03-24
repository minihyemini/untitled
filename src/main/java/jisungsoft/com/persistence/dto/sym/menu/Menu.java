package jisungsoft.com.persistence.dto.sym.menu;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Menu extends Default {

    /**
     * 메뉴번호
     */
    private Integer menuNo;
    /**
     * 메뉴명
     */
    private String menuNm;
    /**
     * 메뉴카테고리
     */
    private String menuCategory;
    /**
     * 상위메뉴번호
     */
    private Integer upperMenuNo;
    /**
     * 부모상위메뉴
     */
    private Integer parentMenuNo;
    /**
     * 레벨
     */
    private Integer menuLv;
    /**
     * 메뉴순서
     */
    private Integer menuOrdr;
    /**
     * 메뉴설명
     */
    private String menuDc;
    /**
     * 관계이미지경로
     */
    private String relateImagePath;
    /**
     * 관계이미지명
     */
    private String relateImageNm;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 새창여부
     */
    private String targetAt;
    /**
     * 프로그램ID
     */
    private Integer progrmId;
    /**
     * url
     */
    private String url;
    /**
     * 프로그램명
     */
    private String progrmFileNm;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * url 쿼리
     */
    private String progrmQuery;

    private Integer subCnt;

    List<Menu> menuManageList;

    List<Menu> menuDataList;

    List<MenuCreat> menuCreatList;
}
