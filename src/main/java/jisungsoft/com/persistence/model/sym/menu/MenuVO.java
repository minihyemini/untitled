package jisungsoft.com.persistence.model.sym.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * 메뉴 VO
 */
@Getter @Setter
public class MenuVO {

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

    protected MenuVO() {
    }

    public static MenuVO createDataMenu(Integer menuNo, String menuNm, String menuCategory, Integer upperMenuNo, Integer menuLv, Integer menuOrdr, String menuDc, String relateImagePath, String relateImageNm, String useAt, String targetAt, Integer progrmId) {
        MenuVO menuVO = new MenuVO();
        menuVO.menuNo = menuNo;
        menuVO.menuNm = menuNm;
        menuVO.menuCategory = menuCategory;
        menuVO.upperMenuNo = upperMenuNo;
        menuVO.menuLv = menuLv;
        menuVO.menuOrdr = menuOrdr;
        menuVO.menuDc = menuDc;
        menuVO.relateImagePath = relateImagePath;
        menuVO.relateImageNm = relateImageNm;
        menuVO.useAt = useAt;
        menuVO.targetAt = targetAt;
        menuVO.progrmId = progrmId;

        return menuVO;
    }

    public static MenuVO createDataIdMenu(Integer menuNo) {
        MenuVO menuVO = new MenuVO();
        menuVO.menuNo = menuNo;

        return menuVO;
    }
}
