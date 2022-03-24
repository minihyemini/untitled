package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.mes.Pdnum;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PdNumService {

    public List<Pdnum> getPdNumAllList(Pdnum pdnum);

    public List<Pdnum> getPdNumList(Pdnum pdnum);

    public int getPdNumListCnt(Pdnum pdnum);

    public Pdnum getPdNumDetail(Pdnum pdnum);

    public void addPdNum(Pdnum pdnum) throws DataAccessException;

    public void editPdNum(Pdnum pdnum) throws DataAccessException;

    public void removePdNum(Pdnum pdnum) throws DataAccessException;
}
