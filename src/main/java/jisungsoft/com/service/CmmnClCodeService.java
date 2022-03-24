package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;

import java.util.List;

public interface CmmnClCodeService {
    /**
     * 공통분류코드 목록을 조회한다. (Paging)
     */
    List<CmmnClCode> getCmmnClCodeList(CmmnClCode cmmnClCode);

    /**
     * 공통분류코드 목록을 조회한다. (Not Paging)
     */
    List<CmmnClCode> getCmmnClCodeAllList(CmmnClCode cmmnClCode);
    /**
     * 공통분류코드 건수를 조회한다.
     */
    int getCmmnClCodeListCnt(CmmnClCode cmmnClCode);

    /**
     *  공통분류코드 상세항목을 조회한다.
     */
    CmmnClCode getCmmnClCodeDetail(CmmnClCode cmmnClCode);

    /**
     * 공통분류코드를 등록한다.
     */
    void addCmmnClCode(CmmnClCode cmmnClCode) throws Exception;

    /**
     * 공통분류코드를 삭제한다.
     */
    void removeCmmnClCode(CmmnClCode cmmnClCode) throws Exception;

    /**
     * 공통분류코드를 수정한다.
     */
    void editCmmnClCode(CmmnClCode cmmnClCode) throws Exception;
}
