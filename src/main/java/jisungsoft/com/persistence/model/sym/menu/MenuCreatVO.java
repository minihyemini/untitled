package jisungsoft.com.persistence.model.sym.menu;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuCreatVO {

    /** 메뉴번호 */
    private Integer menuNo;
    /** 맵생성ID */
    private String mapngCreatId;
    /** 권한코드 */
    private String authorCode;

    protected MenuCreatVO() {
    }

    public static MenuCreatVO createDataMenuCreate(Integer menuNo, String mapngCreatId, String authorCode) {
        MenuCreatVO menuCreatVO = new MenuCreatVO();
        menuCreatVO.menuNo = menuNo;
        menuCreatVO.mapngCreatId = mapngCreatId;
        menuCreatVO.authorCode = authorCode;

        return menuCreatVO;
    }
}
