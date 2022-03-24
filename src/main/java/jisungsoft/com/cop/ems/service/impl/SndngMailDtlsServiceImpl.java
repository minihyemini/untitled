package jisungsoft.com.cop.ems.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cop.ems.service.SndngMailDtlsService;
import jisungsoft.com.cop.ems.service.SndngMailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sndngMailDtlsService")
public class SndngMailDtlsServiceImpl extends EgovAbstractServiceImpl implements SndngMailDtlsService {

    @Resource(name = "sndngMailDtlsDAO")
    private SndngMailDtlsDAO sndngMailDtlsDAO;

    @Override
    public List<?> selectSndngMailList(SndngMailVO vo) throws Exception {
        return sndngMailDtlsDAO.selectSndngMailList(vo);
    }

    @Override
    public int selectSndngMailListTotCnt(SndngMailVO vo) throws Exception {
        return sndngMailDtlsDAO.selectSndngMailListTotCnt(vo);
    }

    @Override
    public SndngMailVO selectSndngMailDetail(SndngMailVO vo) throws Exception {
        return sndngMailDtlsDAO.selectSndngMail(vo);
    }

    @Override
    public void deleteSndngMail(SndngMailVO vo) throws Exception {
        sndngMailDtlsDAO.deleteSndngMail(vo);

        //발송요청XML 파일 삭제처리
//        String xmlFile = Globals.MAIL_REQUEST_PATH + vo.getMssageId() + ".xml";
//        EgovFileTool.deleteFile(xmlFile);
    }
}
