package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.mes.Pdnumbyroutg;

import java.util.List;

public interface PdnumByRoutgService {

    public List<Pdnumbyroutg> getRoutgByPdnumAllList(Pdnumbyroutg pdnumbyroutg);

    public List<Pdnumbyroutg> getPdnumWithCountAllList(Pdnumbyroutg pdnumbyroutg);

    public List<Pdnumbyroutg> getPdnumByRoutgDetail(Pdnumbyroutg pdnumbyroutg);

    public void addPdnumbyroutg(Pdnumbyroutg pdnumbyroutg) throws Exception;

    public void editPdnumbyroutg(Pdnumbyroutg pdnumbyroutg) throws Exception;

    public void removePdnumbyroutg(Pdnumbyroutg pdnumbyroutg) throws Exception;
}
