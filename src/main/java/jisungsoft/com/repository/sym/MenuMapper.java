package jisungsoft.com.repository.sym;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sym.menu.Menu;
import jisungsoft.com.persistence.model.sym.menu.MenuVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("menuMapper")
public interface MenuMapper {
    /**
     * 메뉴 뷰 목록
     */
    public List<Menu> selectMenuViewDataList(Menu menu);
    /**
     * 메뉴 뷰 상세 정보
     */
    public Menu selectMenuViewDataDetail(Menu menu);

    public Menu selectMenuManageDetail(Menu menu);

    public List<Menu> selectMenuManageList(Menu menu);

    public List<Menu> selectMenuManageAllList(Menu menu);

    public List<Menu> selectMenuManageGroupList(Menu menu);

    public int selectMenuManageListTotCnt(Menu menu);

    public int selectMenuNoByPk(Menu menu);

    public int selectUpperMenuNoByPk(Menu menu);

    public Menu selectMaxMenuDATA(Menu menu) throws DataAccessException;

    public void insertMenuManage(MenuVO menuVO) throws DataAccessException;

    public void updateMenuManage(MenuVO menuVO) throws DataAccessException;

    public void deleteMenuManage(MenuVO menuVO) throws DataAccessException;
}
