package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.persistence.model.mes.PdfciltsVO;
import jisungsoft.com.repository.mes.PdfciltsMapper;
import jisungsoft.com.service.PdfciltsService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("pdfciltsService")
public class PdfciltsServiceImpl extends EgovAbstractServiceImpl implements PdfciltsService {

    @Resource(name = "pdfciltsMapper")
    private PdfciltsMapper pdfciltsMapper;

    @Override
    public List<Pdfcilts> getPdfciltsAllList(Pdfcilts pdfcilts) {
        return pdfciltsMapper.selectPdfciltsAllList(pdfcilts);
    }

    @Override
    public List<Pdfcilts> getPdfciltsList(Pdfcilts pdfcilts) {
        return pdfciltsMapper.selectPdfciltsList(pdfcilts);
    }

    @Override
    public int getPdfciltsListCnt(Pdfcilts pdfcilts) {
        return pdfciltsMapper.selectPdfciltsListCnt(pdfcilts);
    }

    @Override
    public int getPdfciltsCntByCode(Pdfcilts pdfcilts) {
        return pdfciltsMapper.selectPdfciltsCntByCode(pdfcilts);
    }

    @Override
    public Pdfcilts getPdfciltsDetail(Pdfcilts pdfcilts) {
        return pdfciltsMapper.selectPdfciltsDetail(pdfcilts);
    }

    @Override
    public void addPdfcilts(Pdfcilts pdfcilts) throws Exception {
        PdfciltsVO dataPdfcilts = PdfciltsVO.createDataPdfcilts(
                null,
                pdfcilts.getPdfciltsCode(),
                pdfcilts.getPdfciltsNm(),
                pdfcilts.getPlcDvcode(),
                pdfcilts.getUseAt(),
                pdfcilts.getFrstRegisterId(),
                null);
        pdfciltsMapper.insertPdfcilts(dataPdfcilts);
    }

    @Override
    public void editPdfcilts(Pdfcilts pdfcilts) throws Exception {
        PdfciltsVO dataPdfcilts = PdfciltsVO.createDataPdfcilts(
                pdfcilts.getPdfciltsId(),
                pdfcilts.getPdfciltsCode(),
                pdfcilts.getPdfciltsNm(),
                pdfcilts.getPlcDvcode(),
                pdfcilts.getUseAt(),
                null,
                pdfcilts.getLastUpdusrId());

        pdfciltsMapper.updatePdfcilts(dataPdfcilts);
    }

    @Override
    public void removePdfcilts(Pdfcilts pdfcilts) throws Exception {
        PdfciltsVO dataPdfcilts = PdfciltsVO.createDataPdfcilts(
                pdfcilts.getPdfciltsId(),
                null,
                null,
                null,
                null,
                null,
                null);

        pdfciltsMapper.deletePdfcilts(dataPdfcilts);
    }
}
