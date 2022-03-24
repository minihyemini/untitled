package jisungsoft.com.cop.ems.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cop.ems.service.SndngMailRegistService;
import jisungsoft.com.cop.ems.service.SndngMailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sndngMailRegistService")
public class SndngMailRegistServiceImpl extends EgovAbstractServiceImpl implements SndngMailRegistService {

    @Resource(name = "sndngMailRegistDAO")
    private SndngMailRegistDAO sndngMailRegistDAO;

    @Override
    public SndngMailVO insertSndngMail(SndngMailVO vo) throws Exception {
        return sndngMailRegistDAO.insertSndngMail(vo);
    }

    @Override
    public boolean trnsmitXmlData(SndngMailVO vo) throws Exception {
        return false;
    }

    @Override
    public boolean recptnXmlData(String xml) throws Exception {
        return false;
    }
}
