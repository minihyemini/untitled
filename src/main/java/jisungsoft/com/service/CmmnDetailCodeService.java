package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;

import java.util.List;

public interface CmmnDetailCodeService {
    /**
     * 공통상세코드 목록을 조회한다. (Paging)
     */
    List<CmmnDetailCode> getCmmnDetailCodeList(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통상세코드 목록을 조회한다. (Not Paging)
     */
    List<CmmnDetailCode> getCmmnDetailCodeAllList(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통상세코드 건수를 조회한다.
     */
    int getCmmnDetailCodeListCnt(CmmnDetailCode cmmnDetailCode);

    /**
     *  공통상세코드 상세항목을 조회한다.
     */
    CmmnDetailCode getCmmnDetailCodeDetail(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통상세코드를 등록한다.
     */
    void addCmmnDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception;

    /**
     * 공통상세코드를 삭제한다.
     */
    void removeCmmnDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception;

    /**
     * 공통상세코드를 수정한다.
     */
    void editCmmnDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception;
}
