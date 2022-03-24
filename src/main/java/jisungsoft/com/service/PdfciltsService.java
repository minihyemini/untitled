package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PdfciltsService {

    public List<Pdfcilts> getPdfciltsAllList(Pdfcilts pdfcilts);

    public List<Pdfcilts> getPdfciltsList(Pdfcilts pdfcilts);

    public int getPdfciltsListCnt(Pdfcilts pdfcilts);

    public int getPdfciltsCntByCode(Pdfcilts pdfcilts);

    public Pdfcilts getPdfciltsDetail(Pdfcilts pdfcilts);

    public void addPdfcilts(Pdfcilts pdfcilts) throws Exception;

    public void editPdfcilts(Pdfcilts pdfcilts) throws Exception;

    public void removePdfcilts(Pdfcilts pdfcilts) throws Exception;
}
