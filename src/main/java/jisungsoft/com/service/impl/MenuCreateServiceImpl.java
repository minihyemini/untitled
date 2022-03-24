package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.mes.sym.menu.mcm.service.impl.MenuCreateManageDAO;
import jisungsoft.com.service.MenuCreateService;

//@Service("menuCreateService")
public class MenuCreateServiceImpl extends EgovAbstractServiceImpl implements MenuCreateService {

//    @Resource(name = "menuCreateManageDAO")
    private MenuCreateManageDAO menuCreateManageDAO;
/*
    @Override
    public List<?> getMenuCreatList(MenuCreatVO menuCreatVO) throws Exception {
        return menuCreateManageDAO.selectMenuCreatList(menuCreatVO);
    }

    @Override
    public int getMenuCreatTotCnt(MenuCreatVO menuCreatVO) throws Exception {
        return menuCreateManageDAO.selectMenuCreatTotCnt(menuCreatVO);
    }

    @Override
    public List<?> getMenuAuthList(MenuCreatVO menuCreatVO) throws Exception {
        return menuCreateManageDAO.selectMenuAuthList(menuCreatVO);
    }

    @Override
    public List<MenuCreatVO> getMenuCreatAuthList(MenuCreatVO menuCreatVO) throws Exception {
        return menuCreateManageDAO.selectMenuCreatAuthList(menuCreatVO);
    }

    @Override
    public void fullUpdateMenuCreat(MenuCreatVO menuCreatVO) throws Exception{
        int cnt = menuCreateManageDAO.selectMenuCreateCntByPk(menuCreatVO);
        String authorCode = menuCreatVO.getAuthorCode();

        if (authorCode == null || authorCode.equals("")) {
            menuCreateManageDAO.deleteMenuCreat(menuCreatVO);
        } else {
            if (cnt == 0) {
                menuCreateManageDAO.insertMenuCreat(menuCreatVO);
            } else {
                menuCreateManageDAO.updateMenuCreat(menuCreatVO);
            }
        }
    }

    @Override
    public void addMenuCreat(MenuCreatVO menuCreatVO) {
        menuCreateManageDAO.insertMenuCreat(menuCreat);
    }

    @Override
    public void editMenuCreat(MenuCreatVO menuCreatVO) {
        menuCreateManageDAO.updateMenuCreat(menuCreat);
    }

    @Override
    public void removeMenuCreat(MenuCreatVO menuCreatVO) {
        menuCreateManageDAO.deleteMenuCreat(menuCreat);
    }
    */
}
