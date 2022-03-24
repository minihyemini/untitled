package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import jisungsoft.com.persistence.model.sym.menu.MenuProgramVO;
import jisungsoft.com.repository.sym.MenuProgramMapper;
import jisungsoft.com.service.MenuProgramService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuProgramService")
public class MenuProgramServiceImpl extends EgovAbstractServiceImpl implements MenuProgramService {

    @Resource(name = "menuProgramMapper")
    private MenuProgramMapper menuProgramMapper;

    @Override
    public List<MenuProgram> getProgramList(MenuProgram menuProgram) {
        return menuProgramMapper.selectProgramList(menuProgram);
    }

    @Override
    public List<MenuProgram> getProgramAllList(MenuProgram menuProgram) throws Exception {
        return menuProgramMapper.selectProgramAllList(menuProgram);
    }

    @Override
    public int getProgramListTotCnt(MenuProgram menuProgram) throws Exception {
        return menuProgramMapper.selectProgramListTotCnt(menuProgram);
    }

    @Override
    public MenuProgram getProgram(MenuProgram menuProgram) throws Exception {
        return menuProgramMapper.selectMenuProgramDetail(menuProgram);
    }

    @Override
    public int getProgramNmByPk(MenuProgram menuProgram) throws Exception {
        return menuProgramMapper.selectProgramNmByPk(menuProgram);
    }

    @Override
    public void addProgram(MenuProgram menuProgram) throws Exception {

        MenuProgramVO dataMenuProgram = MenuProgramVO.createDataMenuProgram(0, menuProgram.getProgrmFileNm(),
                menuProgram.getProgrmStrePath(),
                menuProgram.getProgrmKoreanNm(),
                menuProgram.getProgrmDc(),
                menuProgram.getUrl(),
                menuProgram.getProgrmCode(),
                menuProgram.getProgrmQuery(),
                null,
                null);
        menuProgramMapper.insertProgram(dataMenuProgram);
    }

    @Override
    public void editProgram(MenuProgram menuProgram) throws Exception {
        MenuProgramVO dataMenuProgram = MenuProgramVO.createDataMenuProgram(menuProgram.getProgrmId(), menuProgram.getProgrmFileNm(),
                menuProgram.getProgrmStrePath(),
                menuProgram.getProgrmKoreanNm(),
                menuProgram.getProgrmDc(),
                menuProgram.getUrl(),
                menuProgram.getProgrmCode(),
                menuProgram.getProgrmQuery(),
                null,
                null);

        menuProgramMapper.updateProgram(dataMenuProgram);
    }

    @Override
    public void removeProgram(MenuProgram menuProgram) throws Exception {
        MenuProgramVO dataIdMenuProgram = MenuProgramVO.createDataIdMenuProgram(menuProgram.getProgrmId());
        menuProgramMapper.deleteProgram(dataIdMenuProgram);
    }
}
