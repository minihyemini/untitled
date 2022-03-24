package jisungsoft.com.repository.sym;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sym.menu.MenuCreat;
import jisungsoft.com.persistence.model.sym.menu.MenuCreatVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("menuCreateMapper")
public interface MenuCreateMapper {
    public List<MenuCreat> selectMenuCreatList(MenuCreat menuCreat);

    public int selectMenuCreatTotCnt(MenuCreat menuCreat);

    public List<MenuCreat> selectMenuAuthList(MenuCreat menuCreat);

    public List<MenuCreat> selectMenuCreatAuthList(MenuCreat menuCreat);

    public int selectMenuCreateCntByPk(MenuCreat menuCreat);

    public void insertMenuCreat(MenuCreatVO menuCreatVO) throws DataAccessException;

    public void updateMenuCreat(MenuCreatVO menuCreatVO) throws DataAccessException;

    public void deleteMenuCreat(MenuCreatVO menuCreatVO) throws DataAccessException;

}
