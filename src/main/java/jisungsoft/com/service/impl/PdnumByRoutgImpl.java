package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;
import jisungsoft.com.persistence.model.mes.PdnumbyroutgVO;
import jisungsoft.com.repository.mes.PdnumByRoutgMapper;
import jisungsoft.com.service.PdnumByRoutgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("pdnumByRoutgService")
public class PdnumByRoutgImpl extends EgovAbstractServiceImpl implements PdnumByRoutgService {

    @Resource(name = "pdnumByRoutgMapper")
    private PdnumByRoutgMapper pdnumByRoutgMapper;

    @Override
    public List<Pdnumbyroutg> getRoutgByPdnumAllList(Pdnumbyroutg pdnumbyroutg) {
        return pdnumByRoutgMapper.selectRoutgByPdnumAllList(pdnumbyroutg);
    }

    @Override
    public List<Pdnumbyroutg> getPdnumWithCountAllList(Pdnumbyroutg pdnumbyroutg) {
        return pdnumByRoutgMapper.selectPdnumWithCountAllList(pdnumbyroutg);
    }

    @Override
    public List<Pdnumbyroutg> getPdnumByRoutgDetail(Pdnumbyroutg pdnumbyroutg) {
        return pdnumByRoutgMapper.selectPdnumByRoutgDetail(pdnumbyroutg);
    }

    @Override
    public void addPdnumbyroutg(Pdnumbyroutg pdnumbyroutg) throws Exception {
        List<Pdnumbyroutg> pdnumroutgList = pdnumbyroutg.getPdnumroutgList();

        for (Pdnumbyroutg data : pdnumroutgList) {
            PdnumbyroutgVO dataPdnumByRoutg = PdnumbyroutgVO.createDataPdnumByRoutg(data.getPbrId(),
                    pdnumbyroutg.getRtCode(),
                    data.getPmCode(),
                    data.getPmSeq(),
                    data.getPbrPfmpoint(),
                    data.getPbrReadtm(),
                    data.getPbrSmpreadtm(),
                    data.getPbrDesc(),
                    pdnumbyroutg.getPdnumId());

            if (data.getPbrId() != null) {
                if (StringUtils.hasText(data.getPbrCheck())) {
                    if (data.getPbrCheck().equals("Y")) {
                        pdnumByRoutgMapper.updatePdnumbyroutg(dataPdnumByRoutg);
                    }
                } else {
                    pdnumByRoutgMapper.deletePdnumbyroutg(dataPdnumByRoutg);
                }
            } else {
                if (StringUtils.hasText(data.getPbrCheck())) {
                    if (data.getPbrCheck().equals("Y")) {
                        pdnumByRoutgMapper.insertPdnumbyroutg(dataPdnumByRoutg);
                    }
                }
            }
        }
    }

    @Override
    public void editPdnumbyroutg(Pdnumbyroutg pdnumbyroutg) throws Exception {
        PdnumbyroutgVO dataPdnumByRoutg = PdnumbyroutgVO.createDataPdnumByRoutg(pdnumbyroutg.getPbrId(),
                pdnumbyroutg.getRtCode(),
                pdnumbyroutg.getPmCode(),
                pdnumbyroutg.getPmSeq(),
                pdnumbyroutg.getPbrPfmpoint(),
                pdnumbyroutg.getPbrReadtm(),
                pdnumbyroutg.getPbrSmpreadtm(),
                pdnumbyroutg.getPbrDesc(),
                pdnumbyroutg.getPdnumId());
        pdnumByRoutgMapper.updatePdnumbyroutg(dataPdnumByRoutg);
    }

    @Override
    public void removePdnumbyroutg(Pdnumbyroutg pdnumbyroutg) throws Exception {
        PdnumbyroutgVO dataPdnumByRoutg = PdnumbyroutgVO.createDataPdnumByRoutg(pdnumbyroutg.getPbrId(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        pdnumByRoutgMapper.deletePdnumbyroutg(dataPdnumByRoutg);
    }
}
