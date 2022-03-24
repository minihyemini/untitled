package jisungsoft.com.uss.ion.ntr.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.uss.ion.ntr.service.NoteRecptnService;
import jisungsoft.com.uss.ion.ntr.service.NoteRecptnVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("noteRecptnService")
public class NoteRecptnServiceImpl extends EgovAbstractServiceImpl implements NoteRecptnService {

    @Resource(name="noteRecptnDAO")
    private NoteRecptnDAO noteRecptnDAO;

    @Resource(name = "egovNoteRecpIdGnrService")
    private EgovIdGnrService idgenRecpService;

    /**
     * 받은쪽지함관리 목록 조회
     * @param noteRecptnVO
     * @return List
     * @throws Exception
     */
    @Override
    public List<?> selectNoteRecptnList(NoteRecptnVO noteRecptnVO) throws Exception {
        return noteRecptnDAO.selectNoteRecptnList(noteRecptnVO);
    }

    /**
     * 받은쪽지함관리 목록 전체 건수
     * @param noteRecptnVO
     * @return int
     * @throws Exception
     */
    @Override
    public int selectNoteRecptnListCnt(NoteRecptnVO noteRecptnVO) throws Exception {
        return noteRecptnDAO.selectNoteRecptnListCnt(noteRecptnVO);
    }

    @Override
    public NoteRecptnVO selectNoteRecptnDetail(NoteRecptnVO noteRecptn) throws Exception {
        noteRecptnDAO.updateNoteRecptnRelationOpenYn(noteRecptn);
        return noteRecptnDAO.selectNoteRecptnDetail(noteRecptn);
    }

    /**
     * 받은쪽지함관리 삭제
     * @param noteRecptnVO
     * @return void
     * @throws Exception
     */
    @Override
    public void deleteNoteRecptn(NoteRecptnVO noteRecptnVO) throws Exception {
        //보낸쪽지함 건수를 조회함
        int nNoteTrnsmitCnt = noteRecptnDAO.selectNoteTrnsmitRelationCnt(noteRecptnVO);
        //받은쪽지함 건수를 조회함
        int nNoteRecptnCnt = noteRecptnDAO.selectNoteRecptnRelationCnt(noteRecptnVO);

        if(nNoteTrnsmitCnt == 1 && nNoteRecptnCnt == 1){
            /* 보낸 쪽지가 삭제 된 후, 받은쪽지 삭제 하는 경우 전부 삭제 */
            // 받은쪽지함삭제
            noteRecptnDAO.deleteNoteRecptn(noteRecptnVO);
            // 보낸쪽지함삭제
            noteRecptnDAO.deleteNoteTrnsmit(noteRecptnVO);
            // 쪽지관리삭제
            noteRecptnDAO.deleteNoteManage(noteRecptnVO);
        }else{
            // 받은쪽지 삭제
            noteRecptnDAO.deleteNoteRecptn(noteRecptnVO);
        }
    }

}
