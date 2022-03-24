package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.mes.PdnumClient;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PdnumClientService {

    public List<PdnumClient> getPdnumClientAllList(PdnumClient pdnumClient);

    public List<PdnumClient> getPdnumClientList(PdnumClient pdnumClient);

    public PdnumClient getPdnumClientDetail(PdnumClient pdnumClient);

    public int getPdnumClientDetailListCnt(PdnumClient pdnumClient);

    public void addPdnumClientData(PdnumClient pdnumClient) throws DataAccessException;

    public void editPdnumClientData(PdnumClient pdnumClient) throws DataAccessException;

    public void removePdnumClientData(PdnumClient pdnumClient) throws DataAccessException;
}
