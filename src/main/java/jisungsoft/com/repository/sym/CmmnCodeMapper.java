package jisungsoft.com.repository.sym;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sym.code.CmmnCode;
import jisungsoft.com.persistence.model.sym.code.CmmnCodeVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("cmmnCodeMapper")
public interface CmmnCodeMapper {

    /**
     * 공통코드 목록을 조회한다. (Paging)
     */
    public List<CmmnCode> selectCmmnCodeList(CmmnCode cmmnCode);

    /**
     * 공통코드 목록을 조회한다. (Not Paging)
     */
    public List<CmmnCode> selectCmmnCodeAllList(CmmnCode cmmnCode);

    /**
     * 공통코드 건수를 조회한다.
     */
    public int selectCmmnCodeListCnt(CmmnCode cmmnCode);

    /**
     * 공통코드 상세항목을 조회한다.
     */
    public CmmnCode selectCmmnCodeDetail(CmmnCode cmmnCode);

    /**
     * 공통코드를 등록한다.
     */
    public void insertCmmnCode(CmmnCodeVO cmmnCodeVO) throws DataAccessException;

    /**
     * 공통코드를 삭제한다.
     */
    public void deleteCmmnCode(CmmnCodeVO cmmnCodeVO) throws DataAccessException;

    /**
     * 공통코드를 수정한다.
     */
    public void updateCmmnCode(CmmnCodeVO cmmnCodeVO) throws DataAccessException;

    /**
     * 공통코드를 수정한다. (사용하는 공통분류코드 기준)
     */
    public void updateCmmnCodeAll(CmmnCodeVO cmmnCodeVO) throws DataAccessException;
}
