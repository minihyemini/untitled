package jisungsoft.com.repository.sym;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;
import jisungsoft.com.persistence.model.sym.code.CmmnClCodeVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("cmmnClCodeMapper")
public interface CmmnClCodeMapper {

    /**
     * 공통분류코드 목록을 조회한다. (Paging)
     */
    public List<CmmnClCode> selectCmmnClCodeList(CmmnClCode cmmnClCode);

    /**
     * 공통분류코드 목록을 조회한다. (Not Paging)
     */
    public List<CmmnClCode> selectCmmnClCodeAllList(CmmnClCode cmmnClCode);

    /**
     * 공통분류코드 건수를 조회한다.
     */
    public int selectCmmnClCodeListCnt(CmmnClCode cmmnClCode);

    /**
     * 공통분류코드 상세항목을 조회한다.
     */
    public CmmnClCode selectCmmnClCodeDetail(CmmnClCode cmmnClCode);

    /**
     * 공통분류코드를 등록한다.
     */
    public void insertCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws DataAccessException;

    /**
     * 공통분류코드를 삭제한다.
     */
    public void deleteCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws DataAccessException;

    /**
     * 공통분류코드를 수정한다.
     */
    public void updateCmmnClCode(CmmnClCodeVO cmmnClCodeVO) throws DataAccessException;

    /**
     * 공통분류코드 미사용코드를 수정한다.
     */
    public void updateCmmnClCodeNonUse(CmmnClCodeVO cmmnClCodeVO) throws DataAccessException;
}
