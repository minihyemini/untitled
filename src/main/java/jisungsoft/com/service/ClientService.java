package jisungsoft.com.service;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import jisungsoft.com.persistence.dto.mes.Client;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ClientService {

    /**
     * 거래처 리스트(Not paging)
     */
    public List<Client> getClientAllList(Client client);

    /**
     * 거래처 리스트(엑셀출력)
     */
    public List<?> getClientAllListMap(Client client);

    /**
     * 거래처 리스트(Paging)
     */
    public List<Client> getClientList(Client client);

    /**
     * 거래처 상세 
     */
    public Client getClientDetail(Client client);

    /**
     * 거래처 카운트
     */
    public int getClientDetailListCnt(Client client);

    /**
     * 거래처 등록 
     */
    public void addClientData(Client client) throws DataAccessException, FdlException;

    /**
     * 거래처 수정 
     */
    public void editClientData(Client client) throws DataAccessException;

    /**
     * 거래처 삭제
     */
    public void removeClientData(Client client) throws DataAccessException;
}
