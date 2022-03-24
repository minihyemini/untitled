package jisungsoft.com.repository.member;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.persistence.model.member.DeptVO;

import java.util.List;

/**
 * 부서
 */
@Mapper("deptMapper")
public interface DeptMapper {

    /**
     * 부서 목록 조회
     */
    public List<Dept> selectDeptList(Dept dept);

    /**
     * 부서 전체 목록 조회
     */
    public List<Dept> selectDeptAllList(Dept dept);

    /**
     * 부서 전체 목록 조회(엑셀 출력용)
     */
    public List<?> selectDeptAllListMap(Dept dept);

    /**
     * 부서 조회 카운트
     */
    public int selectDeptListTotCnt(Dept dept);

    /**
     * 부서 단건 조회
     */
    public Dept selectDeptDetail(Dept dept);

    /**
     * 부서 등록
     */
    public void insertDept(DeptVO deptVO) throws Exception;

    /**
     * 부서 수정
     */
    public void updateDept(DeptVO deptVO) throws Exception;

    /**
     * 부서 삭제
     */
    public void deleteDept(DeptVO deptVO) throws Exception;
}
