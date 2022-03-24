package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.model.mes.ClientVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("clientMapper")
public interface ClientMapper {

    public List<Client> selectClientAllList(Client client);

    public List<?> selectClientAllListMap(Client client);

    public List<Client> selectClientList(Client client);

    public Client selectClientDetail(Client client);

    public int selectClientDetailListCnt(Client client);

    public void insertClientData(ClientVO clientVO) throws DataAccessException;

    public void updateClientData(ClientVO clientVO) throws DataAccessException;

    public void deleteClientData(ClientVO clientVO) throws DataAccessException;

}
