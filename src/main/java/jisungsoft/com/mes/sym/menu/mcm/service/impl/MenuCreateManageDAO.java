package jisungsoft.com.mes.sym.menu.mcm.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.persistence.dto.sym.menu.MenuCreat;
import jisungsoft.com.persistence.model.sym.menu.MenuCreatVO;

import java.util.List;

//@Repository("menuCreateManageDAO")
public class MenuCreateManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "menuCreateManageDAO";

    public List<?> selectMenuCreatList(MenuCreatVO menuCreatVO) throws Exception {
        return selectList(NAME_SPACE + ".selectMenuCreatList", menuCreatVO);
    }

    public int selectMenuCreatTotCnt(MenuCreatVO menuCreatVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectMenuCreatTotCnt", menuCreatVO);
    }

    public List<?> selectMenuAuthList(MenuCreatVO menuCreatVO) throws Exception {
        return selectList(NAME_SPACE + ".selectMenuAuthList", menuCreatVO);
    }

    public void insertMenuCreat(MenuCreat menuCreat) {
        insert(NAME_SPACE + ".insertMenuCreat", menuCreat);
    }

    public void updateMenuCreat(MenuCreat menuCreat) {
        insert(NAME_SPACE + ".updateMenuCreat", menuCreat);
    }

    public void deleteMenuCreat(MenuCreat menuCreat) {
        insert(NAME_SPACE + ".deleteMenuCreat", menuCreat);
    }

    public List<MenuCreatVO> selectMenuCreatAuthList(MenuCreatVO menuCreatVO) {
        return selectList(NAME_SPACE + ".selectMenuCreatAuthList", menuCreatVO);
    }

    public int selectMenuCreateCntByPk(MenuCreatVO menuCreatVO) throws Exception{
        return selectOne(NAME_SPACE + ".selectMenuCreateCntByPk", menuCreatVO);
    }
}
