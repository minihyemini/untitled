package jisungsoft.com.repository.mes;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.persistence.model.mes.PdfciltsVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("pdfciltsMapper")
public interface PdfciltsMapper {

    public List<Pdfcilts> selectPdfciltsAllList(Pdfcilts pdfcilts);

    public List<Pdfcilts> selectPdfciltsList(Pdfcilts pdfcilts);

    public int selectPdfciltsListCnt(Pdfcilts pdfcilts);

    public int selectPdfciltsCntByCode(Pdfcilts pdfcilts);

    public Pdfcilts selectPdfciltsDetail(Pdfcilts pdfcilts);

    public void insertPdfcilts(PdfciltsVO pdfcilts) throws DataAccessException;

    public void updatePdfcilts(PdfciltsVO pdfcilts) throws DataAccessException;

    public void deletePdfcilts(PdfciltsVO pdfcilts) throws DataAccessException;

}
