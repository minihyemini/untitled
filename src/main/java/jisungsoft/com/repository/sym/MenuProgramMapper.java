package jisungsoft.com.repository.sym;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import jisungsoft.com.persistence.model.sym.menu.MenuProgramVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("menuProgramMapper")
public interface MenuProgramMapper {

    /**
     * 메뉴 프로그램 상세 정보
     */
    public MenuProgram selectMenuProgramDetail(MenuProgram menuProgram);

    public List<MenuProgram> selectProgramList(MenuProgram menuProgram);

    public int selectProgramListTotCnt(MenuProgram menuProgram);

    public MenuProgram selectProgramDetail(MenuProgram menuProgram);

    public int selectProgramNmByPk(MenuProgram menuProgram);

    public List<MenuProgram> selectProgramAllList(MenuProgram menuProgram);

    public void insertProgram(MenuProgramVO menuProgramVO) throws DataAccessException;

    public void updateProgram(MenuProgramVO menuProgramVO) throws DataAccessException;

    public void updateMenuProgram(MenuProgramVO menuProgramVO) throws DataAccessException;

    public void deleteProgram(MenuProgramVO menuProgramVO) throws DataAccessException;
}
