package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.persistence.model.mes.PdnumVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("pdNumMapper")
public interface PdNumMapper {

    public List<Pdnum> selectPdNumAllList(Pdnum pdnum);

    public List<Pdnum> selectPdNumList(Pdnum pdnum);

    public int selectPdNumListCnt(Pdnum pdnum);

    public Pdnum selectPdNumDetail(Pdnum pdnum);

    public void insertPdNum(PdnumVO pdnumVO) throws DataAccessException;

    public void updatePdNum(PdnumVO pdnumVO) throws DataAccessException;

    public void deletePdNum(PdnumVO pdnumVO) throws DataAccessException;
}
