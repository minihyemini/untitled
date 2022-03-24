package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.mes.FciltsPdNum;
import jisungsoft.com.persistence.model.mes.FciltsPdNumVO;
import jisungsoft.com.repository.mes.FciltsPdNumMapper;
import jisungsoft.com.service.FciltsPdNumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service("fciltsPdNumService")
public class FciltsPdNumServiceImpl extends EgovAbstractServiceImpl implements FciltsPdNumService {

    @Resource(name = "fciltsPdNumMapper")
    private FciltsPdNumMapper fciltsPdNumMapper;

    @Override
    public List<FciltsPdNum> getFciltsPdNumAllList(FciltsPdNum fciltsPdNum) {
        return fciltsPdNumMapper.selectFciltsPdNumAllList(fciltsPdNum);
    }

    @Override
    public List<FciltsPdNum> getFciltsAndPdNumAllList(FciltsPdNum fciltsPdNum) {
        return fciltsPdNumMapper.selectFciltsAndPdNumAllList(fciltsPdNum);
    }

    @Override
    public List<FciltsPdNum> getFciltsPdNumList(FciltsPdNum fciltsPdNum) {
        return fciltsPdNumMapper.selectFciltsPdNumList(fciltsPdNum);
    }

    @Override
    public int getFciltsPdNumListCnt(FciltsPdNum fciltsPdNum) {
        return fciltsPdNumMapper.selectFciltsPdNumListCnt(fciltsPdNum);
    }

    @Override
    public FciltsPdNum getFciltsPdNumDetail(FciltsPdNum fciltsPdNum) {
        return fciltsPdNumMapper.selectFciltsPdNumDetail(fciltsPdNum);
    }

    @Override
    public void addFciltsPdNum(FciltsPdNum fciltsPdNum) throws Exception {
        FciltsPdNumVO dataFciltsbypdnu = FciltsPdNumVO.createDataFciltsbypdnu(null,
                fciltsPdNum.getPdnumId(),
                fciltsPdNum.getPdfciltsId());
        fciltsPdNumMapper.insertFciltsPdNum(dataFciltsbypdnu);
    }

    @Override
    public void addFciltsPdNumList(FciltsPdNum fciltsPdNum) throws Exception {

        Integer[] pdnumIdArr = fciltsPdNum.getPdnumIdArr();
        FciltsPdNumVO dataFciltsbypdnu = FciltsPdNumVO.createDataFciltsbypdnu(null, null, fciltsPdNum.getPdfciltsId());
        fciltsPdNumMapper.deleteFciltsPdNumByPdfciltsId(dataFciltsbypdnu);

        for (int i=0; i < pdnumIdArr.length; i++) {
            Integer value = pdnumIdArr[i];
            dataFciltsbypdnu = FciltsPdNumVO.createDataFciltsbypdnu(fciltsPdNum.getFcpdId(), value, fciltsPdNum.getPdfciltsId());
            fciltsPdNumMapper.insertFciltsPdNum(dataFciltsbypdnu);
        }
    }

    @Override
    public void editFciltsPdNum(FciltsPdNum fciltsPdNum) throws Exception {
        FciltsPdNumVO dataFciltsbypdnu = FciltsPdNumVO.createDataFciltsbypdnu(fciltsPdNum.getFcpdId(),
                fciltsPdNum.getPdnumId(),
                fciltsPdNum.getPdfciltsId());
        fciltsPdNumMapper.updateFciltsPdNum(dataFciltsbypdnu);
    }

    @Override
    public void removeFciltsPdNum(FciltsPdNum fciltsPdNum) throws Exception {
        FciltsPdNumVO dataFciltsbypdnu = FciltsPdNumVO.createDataFciltsbypdnu(fciltsPdNum.getFcpdId(),
                fciltsPdNum.getPdnumId(),
                fciltsPdNum.getPdfciltsId());
        fciltsPdNumMapper.deleteFciltsPdNum(dataFciltsbypdnu);
    }
}
