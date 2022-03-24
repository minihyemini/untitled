package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.FciltsPdNum;
import jisungsoft.com.persistence.model.mes.FciltsPdNumVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("fciltsPdNumMapper")
public interface FciltsPdNumMapper {

    public List<FciltsPdNum> selectFciltsPdNumAllList(FciltsPdNum fciltsPdNum);

    public List<FciltsPdNum> selectFciltsAndPdNumAllList(FciltsPdNum fciltsPdNum);

    public List<FciltsPdNum> selectFciltsPdNumList(FciltsPdNum fciltsPdNum);

    public int selectFciltsPdNumListCnt(FciltsPdNum fciltsPdNum);

    public FciltsPdNum selectFciltsPdNumDetail(FciltsPdNum fciltsPdNum);

    public void insertFciltsPdNum(FciltsPdNumVO fciltsPdNumVO) throws DataAccessException;

    public void updateFciltsPdNum(FciltsPdNumVO fciltsPdNumVO) throws DataAccessException;

    public void deleteFciltsPdNum(FciltsPdNumVO fciltsPdNumVO) throws DataAccessException;

    public void deleteFciltsPdNumByPdfciltsId(FciltsPdNumVO fciltsPdNumVO) throws DataAccessException;
}
