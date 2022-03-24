package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.model.mes.ClientVO;
import jisungsoft.com.repository.mes.ClientMapper;
import jisungsoft.com.service.ClientService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("clientService")
public class ClientServiceImpl extends EgovAbstractServiceImpl implements ClientService {

    /** clientCodeIdGnrService */
    @Resource(name="clientCodeIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "clientMapper")
    private ClientMapper clientMapper;

    @Override
    public List<Client> getClientAllList(Client client) {
        return clientMapper.selectClientAllList(client);
    }

    @Override
    public List<?> getClientAllListMap(Client client) {
        return clientMapper.selectClientAllListMap(client);
    }

    @Override
    public List<Client> getClientList(Client client) {
        return clientMapper.selectClientList(client);
    }

    @Override
    public Client getClientDetail(Client client) {
        return clientMapper.selectClientDetail(client);
    }

    @Override
    public int getClientDetailListCnt(Client client) {
        return clientMapper.selectClientDetailListCnt(client);
    }

    @Override
    public void addClientData(Client client) throws DataAccessException, FdlException {

        String clientCode = idgenService.getNextStringId();

        ClientVO dataClient = ClientVO.createDataClient(null,
                clientCode,
                client.getCltNm(),
                client.getCltOwnrnm(),
                client.getCltType(),
                client.getCltBusstype(),
                client.getCltBussnum(),
                client.getCltCprtnum(),
                client.getCltZip(),
                client.getCltAddr(),
                client.getCltDetailAddr(),
                client.getCltTelno(),
                client.getCltFaxnum(),
                client.getCltEmail(),
                client.getCltSetupdt(),
                client.getCltDlbegindt(),
                client.getCltDlendt(),
                client.getCltDeadlndt(),
                client.getFrstRegisterId(),
                null,
                client.getPdnumId());

        clientMapper.insertClientData(dataClient);
    }

    @Override
    public void editClientData(Client client) throws DataAccessException {
        ClientVO dataClient = ClientVO.createDataClient(client.getCltId(),
                client.getCltCode(),
                client.getCltNm(),
                client.getCltOwnrnm(),
                client.getCltType(),
                client.getCltBusstype(),
                client.getCltBussnum(),
                client.getCltCprtnum(),
                client.getCltZip(),
                client.getCltAddr(),
                client.getCltDetailAddr(),
                client.getCltTelno(),
                client.getCltFaxnum(),
                client.getCltEmail(),
                client.getCltSetupdt(),
                client.getCltDlbegindt(),
                client.getCltDlendt(),
                client.getCltDeadlndt(),
                null,
                client.getLastUpdusrId(),
                client.getPdnumId());

        clientMapper.updateClientData(dataClient);
    }

    @Override
    public void removeClientData(Client client) throws DataAccessException {
        ClientVO dataClientId = ClientVO.createDataClientId(client.getCltId());

        clientMapper.deleteClientData(dataClientId);
    }
}
