package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.code.CmmnCode;

import java.util.List;

public interface CmmnCodeService {
    /**
     * 공통코드 목록을 조회한다. (Paging)
     */
    List<CmmnCode> getCmmnCodeList(CmmnCode cmmnCode);

    /**
     * 공통코드 목록을 조회한다. (Not Paging)
     */
    List<CmmnCode> getCmmnCodeAllList(CmmnCode cmmnCode);

    /**
     * 공통코드 건수를 조회한다.
     */
    int getCmmnCodeListCnt(CmmnCode cmmnCode);

    /**
     * 공통코드 상세항목을 조회한다.
     */
    CmmnCode getCmmnCodeDetail(CmmnCode cmmnCode);

    /**
     * 공통코드를 등록한다.
     */
    void addCmmnCode(CmmnCode cmmnCode) throws Exception;

    /**
     * 공통코드를 삭제한다.
     */
    void removeCmmnCode(CmmnCode cmmnCode) throws Exception;

    /**
     * 공통코드를 수정한다.
     */
    void editCmmnCode(CmmnCode cmmnCode) throws Exception;

    /**
     * 공통분류코드 미사용코드를 수정한다.
     */
    void editCmmnCodeAll(CmmnCode cmmnCode) throws Exception;
}
