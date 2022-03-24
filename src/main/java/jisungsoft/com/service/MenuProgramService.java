package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;

import java.util.List;

public interface MenuProgramService {

    /**
     * 프로그램 목록 조회
     */
    public List<MenuProgram> getProgramList(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 목록 전체 조회
     */
    public List<MenuProgram> getProgramAllList(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 목록 총건수 조회
     */
    public int getProgramListTotCnt(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 상세 조회
     */
    public MenuProgram getProgram(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 파일 존재여부 조회
     */
    public int getProgramNmByPk(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 정보 등록
     */
    public void addProgram(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 정보 수정
     */
    public void editProgram(MenuProgram menuProgram) throws Exception;

    /**
     * 프로그램 정보 삭제
     */
    public void removeProgram(MenuProgram menuProgram) throws Exception;

}
