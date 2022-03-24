package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;
import jisungsoft.com.persistence.model.mes.PdnumbyroutgVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("pdnumByRoutgMapper")
public interface PdnumByRoutgMapper {

    public List<Pdnumbyroutg> selectRoutgByPdnumAllList(Pdnumbyroutg pdnumbyroutg);

    public List<Pdnumbyroutg> selectPdnumWithCountAllList(Pdnumbyroutg pdnumbyroutg);

    public List<Pdnumbyroutg> selectPdnumByRoutgDetail(Pdnumbyroutg pdnumbyroutg);

    public void insertPdnumbyroutg(PdnumbyroutgVO pdnumbyroutgVO) throws DataAccessException;

    public void updatePdnumbyroutg(PdnumbyroutgVO pdnumbyroutgVO) throws DataAccessException;

    public void deletePdnumbyroutg(PdnumbyroutgVO pdnumbyroutgVO) throws DataAccessException;

    public void deletePdnumbyPdNum(PdnumbyroutgVO pdnumbyroutgVO) throws DataAccessException;
}
