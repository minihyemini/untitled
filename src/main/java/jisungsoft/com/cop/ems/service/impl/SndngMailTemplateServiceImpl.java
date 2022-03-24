package jisungsoft.com.cop.ems.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cop.ems.service.SndngMailTemplateService;
import jisungsoft.com.cop.ems.service.SndngMailTemplateVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sndngMailTemplateService")
public class SndngMailTemplateServiceImpl extends EgovAbstractServiceImpl implements SndngMailTemplateService {

    /** Message ID Generation */
    @Resource(name = "egovMailTmpIdGnrService")
    private EgovIdGnrService egovMailTmpIdGnrService;

    @Resource(name = "sndngMailTemplateServiceDAO")
    private SndngMailTemplateServiceDAO sndngMailTemplateServiceDAO;


    @Override
    public List<?> selectSndngMailTemplateList(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return sndngMailTemplateServiceDAO.selectSndngMailTemplateList(sndngMailTemplateVO);
    }

    @Override
    public List<SndngMailTemplateVO> selectSndngMailTemplateListAll(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return sndngMailTemplateServiceDAO.selectSndngMailTemplateListAll(sndngMailTemplateVO);
    }

    @Override
    public int selectSndngMailTemplateListCnt(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return sndngMailTemplateServiceDAO.selectSndngMailTemplateListCnt(sndngMailTemplateVO);
    }

    @Override
    public SndngMailTemplateVO selectSndngMailTemplateDetail(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return sndngMailTemplateServiceDAO.selectSndngMailTemplateDetail(sndngMailTemplateVO);
    }

    @Override
    public void insertSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        String id = egovMailTmpIdGnrService.getNextStringId();
        sndngMailTemplateVO.setEtId(id);
        sndngMailTemplateServiceDAO.insertSndngMailTemplate(sndngMailTemplateVO);
    }

    @Override
    public void updateSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        sndngMailTemplateServiceDAO.updateSndngMailTemplate(sndngMailTemplateVO);
    }

    @Override
    public void deleteSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        sndngMailTemplateServiceDAO.deleteSndngMailTemplate(sndngMailTemplateVO);
    }

    @Override
    public void updateSndngMailTemplateUse(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {

    }
}
