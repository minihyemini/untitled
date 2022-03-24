package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.menu.Menu;
import jisungsoft.com.persistence.model.sym.menu.MenuCreatVO;
import jisungsoft.com.persistence.model.sym.menu.MenuVO;
import jisungsoft.com.repository.sym.MenuCreateMapper;
import jisungsoft.com.repository.sym.MenuMapper;
import jisungsoft.com.service.MenuService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

    @Resource(name = "menuCreateMapper")
    private MenuCreateMapper menuCreateMapper;


    @Override
    public Menu getMenuManage(Menu menu) throws Exception {
        return menuMapper.selectMenuManageDetail(menu);
    }

    @Override
    public List<Menu> getMenuManageList(Menu menu) throws Exception {
        return menuMapper.selectMenuManageList(menu);
    }

    @Override
    public List<Menu> getMenuManageAllList(Menu menu) throws Exception {
        return menuMapper.selectMenuManageAllList(menu);
    }

    @Override
    public List<Menu> getMenuManageGroupList(Menu menu) throws Exception {
        return menuMapper.selectMenuManageGroupList(menu);
    }

    @Override
    public int getMenuManageListTotCnt(Menu menu) throws Exception {
        return menuMapper.selectMenuManageListTotCnt(menu);
    }

    @Override
    public int getMenuNoByPk(Menu menu) throws Exception {
        return menuMapper.selectMenuNoByPk(menu);
    }

    @Override
    public int getUpperMenuNoByPk(Menu menu) throws Exception {
        return menuMapper.selectUpperMenuNoByPk(menu);
    }

    @Override
    public void addMenuManage(Menu menu) throws Exception {
        final Integer MENU_NO_SEQ = 1000000;
        final Integer FIRST_SUB_MENU_NO_SEQ = 10000;
        final Integer SECOND_SUB_MENU_NO_SEQ = 100;
        final Integer THIRD_SUB_MENU_NO_SEQ = 1;

        int menuNo = menu.getMenuNo();
        int lv = menu.getMenuLv();

        Menu data = new Menu();
        data.setUpperMenuNo(menuNo);
        data.setMenuCategory(menu.getMenuCategory());
        Menu maxMenuData = menuMapper.selectMaxMenuDATA(data);
        if (maxMenuData != null) {
            menuNo = maxMenuData.getMenuNo();
        }

        switch (lv) {
            case 0 :
                menuNo = maxMenuData.getMenuNo()+MENU_NO_SEQ;
                break;
            case 1 :
                menuNo = menuNo+FIRST_SUB_MENU_NO_SEQ;
                break;
            case 2 :
                menuNo = menuNo+SECOND_SUB_MENU_NO_SEQ;
                break;
            case 3 :
                menuNo = menuNo+THIRD_SUB_MENU_NO_SEQ;
                break;
            default:
                return;
        }

        MenuVO dataMenu = MenuVO.createDataMenu(menuNo,
                menu.getMenuNm(),
                menu.getMenuCategory(),
                menu.getMenuNo(),
                lv + 1,
                menu.getMenuOrdr(),
                menu.getMenuDc(),
                menu.getRelateImagePath(),
                menu.getRelateImageNm(),
                menu.getUseAt(),
                menu.getTargetAt(),
                menu.getProgrmId());

        try {
            menuMapper.insertMenuManage(dataMenu);
            if (menu.getAuthorCode() != null) {
                String[] authorCodeArray = menu.getAuthorCode().split(",");
                if (authorCodeArray.length > 0) {
                    for (int i=0; i<authorCodeArray.length; i++) {
                        MenuCreatVO dataMenuCreate = MenuCreatVO.createDataMenuCreate(menu.getMenuNo(), null, authorCodeArray[i]);
                        menuCreateMapper.insertMenuCreat(dataMenuCreate);
                    }
                }
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editMenuManage(Menu menu) throws Exception {
        MenuVO dataMenu = MenuVO.createDataMenu(menu.getMenuNo(),
                menu.getMenuNm(),
                menu.getMenuCategory(),
                menu.getUpperMenuNo(),
                menu.getMenuLv(),
                menu.getMenuOrdr(),
                menu.getMenuDc(),
                menu.getRelateImagePath(),
                menu.getRelateImageNm(),
                menu.getUseAt(),
                menu.getTargetAt(),
                menu.getProgrmId());

        try {
            menuMapper.updateMenuManage(dataMenu);

            MenuCreatVO dataMenuCreate = MenuCreatVO.createDataMenuCreate(menu.getMenuNo(), null, null);
            menuCreateMapper.deleteMenuCreat(dataMenuCreate);

            if (menu.getAuthorCode() != null) {
                String[] authorCodeArray = menu.getAuthorCode().split(",");
                if (authorCodeArray.length > 0) {
                    for (int i=0; i<authorCodeArray.length; i++) {
                        dataMenuCreate = MenuCreatVO.createDataMenuCreate(menu.getMenuNo(), null, authorCodeArray[i]);
                        menuCreateMapper.insertMenuCreat(dataMenuCreate);
                    }
                }
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenuManage(Menu menu) throws Exception {

        MenuCreatVO dataMenuCreate = MenuCreatVO.createDataMenuCreate(menu.getMenuNo(), null, null);
        menuCreateMapper.deleteMenuCreat(dataMenuCreate);

        MenuVO dataIdMenu = MenuVO.createDataIdMenu(menu.getMenuNo());
        menuMapper.deleteMenuManage(dataIdMenu);
    }
}
