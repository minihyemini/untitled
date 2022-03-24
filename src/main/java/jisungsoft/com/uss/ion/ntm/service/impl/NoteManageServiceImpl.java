package jisungsoft.com.uss.ion.ntm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.uss.ion.ntm.service.NoteManageService;
import jisungsoft.com.uss.ion.ntm.service.NoteVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

@Service("noteManageService")
public class NoteManageServiceImpl extends EgovAbstractServiceImpl implements NoteManageService {

    @Resource(name="noteManageDAO")
    private NoteManageDAO noteManageDAO;

    @Resource(name = "egovNoteIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "egovNoteTrnsIdGnrService")
    private EgovIdGnrService idgenTrnsService;

    @Resource(name = "egovNoteRecpIdGnrService")
    private EgovIdGnrService idgenRecpService;

    /**
     * 쪽지관리 정보 조회
     * @param noteVO
     * @throws Exception
     */
    @Override
    public NoteVO selectNoteManage(NoteVO noteVO) throws Exception {
        return noteManageDAO.selectNoteManage(noteVO);
    }

    /**
     * 쪽지 관리 등록
     * @param noteVO
     * @param commandMap
     * @throws Exception
     */
    @Override
    public void insertNote(NoteVO noteVO, @RequestParam Map<String, Object> commandMap)throws Exception{

        /* ****************************************************************
         * 쪽지관리 처리
         **************************************************************** */
        //쪽지 ID설정
        noteVO.setNoteId(idgenService.getNextStringId());
        //쪽지 등록
        noteManageDAO.insertNote(noteVO);
        /* ****************************************************************
         * 보낸쪽지 처리
         **************************************************************** */
        //보낸쪽지함 ID설정
        noteVO.setNoteTrnsmitId(idgenTrnsService.getNextStringId());
        //발신자 아이디설정
        noteVO.setTrnsmiterId(noteVO.getFrstRegisterId());

        //보낸쪽지등록
        noteManageDAO.insertNoteTrnsmit(noteVO);

        //수신자 리스트
        String sRecptnEmpList = (String)commandMap.get("recptnEmpList");
        String[] sRecptnEmpListResult = sRecptnEmpList.split(",");

        //수신자구분 리스트
        String sRecptnSeList = (String)commandMap.get("recptnSeList");
        String[] sRecptnSeListResult = sRecptnSeList.split(",");
        /* ****************************************************************
         * 받은쪽지함 처리
         **************************************************************** */
        for(int i=0;i<sRecptnEmpListResult.length;i++){
            //받은쪽지함 ID설정
            noteVO.setNoteRecptnId(idgenRecpService.getNextStringId());
            //받은쪽지함 수신여부 설정
            noteVO.setOpenYn("N");
            //받은쪽지함 수신자 설정
            noteVO.setRcverId(sRecptnEmpListResult[i]);
            //받은쪽지함 수신 구분설정
            noteVO.setRecptnSe(sRecptnSeListResult[i]);
            //받은쪽지함 등록
            noteManageDAO.insertNoteRecptn(noteVO);
        }


    }
}
