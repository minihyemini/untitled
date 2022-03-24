package jisungsoft.com.repository.sym;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.model.sym.code.CmmnDetailCodeVO;

import java.util.List;

@Mapper("cmmnDetailCodeMapper")
public interface CmmnDetailCodeMapper {
    /**
     * 공통분류코드 목록을 조회한다. (Paging)
     */
    public List<CmmnDetailCode> selectCmmnDetailCodeList(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통분류코드 목록을 조회한다. (Not Paging)
     */
    public List<CmmnDetailCode> selectCmmnDetailCodeAllList(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통분류코드 건수를 조회한다.
     */
    public int selectCmmnDetailCodeListCnt(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통분류코드 상세항목을 조회한다.
     */
    public CmmnDetailCode selectCmmnDetailCodeDetail(CmmnDetailCode cmmnDetailCode);

    /**
     * 공통분류코드를 등록한다.
     */
    public void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

    /**
     * 공통분류코드를 삭제한다.
     */
    public void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);

    /**
     * 공통분류코드를 수정한다.
     */
    public void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO);
}
