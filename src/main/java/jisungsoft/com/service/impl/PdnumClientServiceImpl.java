package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.mes.PdnumClient;
import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;
import jisungsoft.com.persistence.model.mes.PdnumClientVO;
import jisungsoft.com.repository.mes.PdnumClientMapper;
import jisungsoft.com.service.PdnumClientService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("pdnumClientService")
public class PdnumClientServiceImpl extends EgovAbstractServiceImpl implements PdnumClientService {

    @Resource(name = "pdnumClientMapper")
    private PdnumClientMapper pdnumClientMapper;

    @Override
    public List<PdnumClient> getPdnumClientAllList(PdnumClient pdnumClient) {
        return pdnumClientMapper.selectPdnumClientAllList(pdnumClient);
    }

    @Override
    public List<PdnumClient> getPdnumClientList(PdnumClient pdnumClient) {
        return pdnumClientMapper.selectPdnumClientList(pdnumClient);
    }

    @Override
    public PdnumClient getPdnumClientDetail(PdnumClient pdnumClient) {
        return pdnumClientMapper.selectPdnumClientDetail(pdnumClient);
    }

    @Override
    public int getPdnumClientDetailListCnt(PdnumClient pdnumClient) {
        return pdnumClientMapper.selectPdnumClientDetailListCnt(pdnumClient);
    }

    @Override
    public void addPdnumClientData(PdnumClient pdnumClient) throws DataAccessException {
        List<PdnumClient> pdnumList = pdnumClient.getPdnumList();

        for (PdnumClient data : pdnumList) {
            PdnumClientVO dataPdnumClient = PdnumClientVO.createDataPdnumClient(
                    data.getPcId(),
                    pdnumClient.getCltId(),
                    data.getPdnumId());

            if (data.getPcId() != null) {
                if (StringUtils.hasText(data.getFormCheck())) {
                    if (data.getFormCheck().equals("Y")) {
                        pdnumClientMapper.updatePdnumClientData(dataPdnumClient);
                    }
                } else {
                    pdnumClientMapper.deletePdnumClientData(dataPdnumClient);
                }
            } else {
                if (StringUtils.hasText(data.getFormCheck())) {
                    if (data.getFormCheck().equals("Y")) {
                        pdnumClientMapper.insertPdnumClientData(dataPdnumClient);
                    }
                }
            }
        }
    }

    @Override
    public void editPdnumClientData(PdnumClient pdnumClient) throws DataAccessException {
        PdnumClientVO dataPdnumClient = PdnumClientVO.createDataPdnumClient(
                pdnumClient.getPcId(),
                pdnumClient.getCltId(),
                pdnumClient.getPdnumId());

        pdnumClientMapper.updatePdnumClientData(dataPdnumClient);
    }

    @Override
    public void removePdnumClientData(PdnumClient pdnumClient) throws DataAccessException {
        PdnumClientVO dataPdnumClient = PdnumClientVO.createDataPdnumClient(
                pdnumClient.getPcId(),
                pdnumClient.getCltId(),
                pdnumClient.getPdnumId());

        pdnumClientMapper.deletePdnumClientData(dataPdnumClient);
    }
}
