package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.menu.Menu;

import java.util.List;

public interface MenuViewService {

    public List<Menu> selectMenuDataList(Menu menu);

    public Menu selectMenuDataDetail(Menu menu);
}
