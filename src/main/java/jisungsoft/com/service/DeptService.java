package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.member.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 부서를 관리하기 위해 등록된 부서목록을 조회한다.(Paging)
     */
    public List<Dept> getDeptList(Dept dept);

    /**
     * 부서를 관리하기 위해 등록된 부서목록을 조회한다.(Not Paging)
     */
    public List<Dept> getDeptAllList(Dept dept);

    /**
     * 부서를 관리하기 위해 등록된 부서목록을 조회한다.(엑셀 출력용)
     */
    public List<?> getDeptAllListMap(Dept dept);

    /**
     * 부서목록 총 갯수를 조회한다.
     */
    public int getDeptListTotCnt(Dept dept);

    /**
     * 등록된 부서의 상세정보를 조회한다.
     */
    public Dept getDeptDetail(Dept dept);

    /**
     * 부서정보를 신규로 등록한다.
     */
    public String addDept(Dept dept) throws Exception;

    /**
     * 기 등록된 부서정보를 수정한다.
     */
    public String editDept(Dept dept) throws Exception;

    /**
     * 기 등록된 부서정보를 삭제한다.
     */
    public void removeDept(Dept dept) throws Exception;
}
