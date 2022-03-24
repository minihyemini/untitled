package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.menu.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 메뉴 상세정보를 조회
     */
    Menu getMenuManage(Menu menu) throws Exception;

    /**
     * 메뉴 목록을 조회
     */
    List<Menu> getMenuManageList(Menu menu) throws Exception;

    /**
     */
    List<Menu> getMenuManageAllList(Menu menu) throws Exception;

    /**
     *
     */
    List<Menu> getMenuManageGroupList(Menu menu) throws Exception;

    /**
     * 메뉴목록 총건수를 조회한다.
     */
    int getMenuManageListTotCnt(Menu menu) throws Exception;

    /**
     * 메뉴번호 존재 여부를 조회한다.
     */
    int getMenuNoByPk(Menu menu) throws Exception;

    /**
     * 상위메뉴번호 존재 여부를 조회한다.
     */
    int getUpperMenuNoByPk(Menu menu) throws Exception;

    /**
     * 메뉴 정보를 등록
     */
    void addMenuManage(Menu menu) throws Exception;

    /**
     * 메뉴 정보를 수정
     */
    void editMenuManage(Menu menu) throws Exception;

    /**
     * 메뉴 정보를 삭제
     */
    void removeMenuManage(Menu menu) throws Exception;
}
