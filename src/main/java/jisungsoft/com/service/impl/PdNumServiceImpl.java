package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.persistence.model.mes.PdnumVO;
import jisungsoft.com.repository.mes.PdNumMapper;
import jisungsoft.com.service.PdNumService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("pdNumService")
public class PdNumServiceImpl extends EgovAbstractServiceImpl implements PdNumService {

    @Resource(name = "pdNumMapper")
    private PdNumMapper pdNumMapper;

    @Override
    public List<Pdnum> getPdNumAllList(Pdnum pdnum) {
        return pdNumMapper.selectPdNumAllList(pdnum);
    }

    @Override
    public List<Pdnum> getPdNumList(Pdnum pdnum) {
        return pdNumMapper.selectPdNumList(pdnum);
    }

    @Override
    public int getPdNumListCnt(Pdnum pdnum) {
        return pdNumMapper.selectPdNumListCnt(pdnum);
    }

    @Override
    public Pdnum getPdNumDetail(Pdnum pdnum) {
        return pdNumMapper.selectPdNumDetail(pdnum);
    }

    @Override
    public void addPdNum(Pdnum pdnum) throws DataAccessException {
        PdnumVO dataPdNum = PdnumVO.createDataPdNum(null,
                pdnum.getPdnumNum(),
                pdnum.getPdnumNm(),
                pdnum.getPdnumType(),
                pdnum.getPdnumWght(),
                pdnum.getPdnumStnd(),
                pdnum.getPdnumSfstck(),
                pdnum.getPdnumCode(),
                pdnum.getPdnumMtrscode(),
                pdnum.getPdnumIotype(),
                pdnum.getPdnumDrwappldate(),
                pdnum.getPdnumDrwqnty(),
                pdnum.getPdnumUnit(),
                pdnum.getAtchFileId(),
                pdnum.getImgFileId(),
                pdnum.getFrstRegisterId(),
                null,
                pdnum.getUseAt());

        pdNumMapper.insertPdNum(dataPdNum);
    }

    @Override
    public void editPdNum(Pdnum pdnum) throws DataAccessException {
        PdnumVO dataPdNum = PdnumVO.createDataPdNum(pdnum.getPdnumId(),
                pdnum.getPdnumNum(),
                pdnum.getPdnumNm(),
                pdnum.getPdnumType(),
                pdnum.getPdnumWght(),
                pdnum.getPdnumStnd(),
                pdnum.getPdnumSfstck(),
                pdnum.getPdnumCode(),
                pdnum.getPdnumMtrscode(),
                pdnum.getPdnumIotype(),
                pdnum.getPdnumDrwappldate(),
                pdnum.getPdnumDrwqnty(),
                pdnum.getPdnumUnit(),
                pdnum.getAtchFileId(),
                pdnum.getImgFileId(),
                null,
                pdnum.getLastUpdusrId(),
                pdnum.getUseAt());
        pdNumMapper.updatePdNum(dataPdNum);
    }

    @Override
    public void removePdNum(Pdnum pdnum) throws DataAccessException {
        PdnumVO dataPdNum = PdnumVO.createDataPdNumId(pdnum.getPdnumId());
        pdNumMapper.deletePdNum(dataPdNum);
    }
}
