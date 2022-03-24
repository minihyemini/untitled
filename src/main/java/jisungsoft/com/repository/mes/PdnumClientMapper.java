package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.PdnumClient;
import jisungsoft.com.persistence.model.mes.PdnumClientVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("pdnumClientMapper")
public interface PdnumClientMapper {

    public List<PdnumClient> selectPdnumClientAllList(PdnumClient pdnumClient);

    public List<PdnumClient> selectPdnumClientList(PdnumClient pdnumClient);

    public PdnumClient selectPdnumClientDetail(PdnumClient pdnumClient);

    public int selectPdnumClientDetailListCnt(PdnumClient pdnumClient);

    public void insertPdnumClientData(PdnumClientVO pdnumClientVO) throws DataAccessException;

    public void updatePdnumClientData(PdnumClientVO pdnumClientVO) throws DataAccessException;

    public void deletePdnumClientData(PdnumClientVO pdnumClientVO) throws DataAccessException;
}
