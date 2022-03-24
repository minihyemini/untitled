package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.menu.Menu;
import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import jisungsoft.com.repository.sym.MenuMapper;
import jisungsoft.com.repository.sym.MenuProgramMapper;
import jisungsoft.com.service.MenuViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuViewService")
public class MenuViewServiceImpl extends EgovAbstractServiceImpl implements MenuViewService {

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

    @Resource(name = "menuProgramMapper")
    private MenuProgramMapper menuProgramMapper;

    @Override
    public List<Menu> selectMenuDataList(Menu menu) {
        return menuMapper.selectMenuViewDataList(menu);
    }

    @Override
    public Menu selectMenuDataDetail(Menu menu) {
        Menu menuData = menuMapper.selectMenuViewDataDetail(menu);
        //메뉴 정보를 못찾을 경우 프로그램명 조회 후 등록
        if (menuData == null) {
            MenuProgram menuProgram = new MenuProgram();
            menuProgram.setUrl(menu.getUrl());
            menuProgram.setProgrmFileNm(menu.getProgrmFileNm());

            MenuProgram programDetail = menuProgramMapper.selectMenuProgramDetail(menuProgram);
            if (programDetail != null) {
                menuData = new Menu();
                menuData.setMenuNm(programDetail.getProgrmKoreanNm());
                menuData.setUrl(programDetail.getUrl());
            }
        }

        return menuData;
    }
}
