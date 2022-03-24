package jisungsoft.com.persistence.model.member;

import lombok.Getter;
import lombok.Setter;

/**
 * 부서 VO
 */
@Getter
@Setter
public class DeptVO {

    protected DeptVO() {
    }

    /**
     * 부서ID
     */
    private String orgnztId;
    /**
     * 부서명
     */
    private String orgnztNm;
    /**
     * 부서코드
     */
    private String orgnztCode;
    /**
     * 부서설명
     */
    private String orgnztDc;

    public static DeptVO createDataDept(String orgnztId, String orgnztNm, String orgnztCode, String orgnztDc) {
        DeptVO deptVO = new DeptVO();
        deptVO.orgnztId = orgnztId;
        deptVO.orgnztNm = orgnztNm;
        deptVO.orgnztCode = orgnztCode;
        deptVO.orgnztDc = orgnztDc;

        return deptVO;
    }

    public static DeptVO createDataIdDept(String orgnztId) {
        DeptVO deptVO = new DeptVO();
        deptVO.orgnztId = orgnztId;

        return deptVO;
    }
}
