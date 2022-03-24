package jisungsoft.com.uss.ion.ntm.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uss.ion.ntm.service.NoteVO;
import org.springframework.stereotype.Repository;

@Repository("noteManageDAO")
public class NoteManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "NoteManageDAO";

    /**
     * 쪽지관리 정보 조회
     * @param noteVO
     * @throws Exception
     */
    public NoteVO selectNoteManage(NoteVO noteVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectNoteManage", noteVO);
    }

    /**
     * 쪽지 관리 등록
     * @param noteVO
     * @throws Exception
     */
    public void insertNote(NoteVO noteVO) throws Exception {
        insert(NAME_SPACE + ".insertNote", noteVO);
    }

    /**
     * 보낸쪽지 등록
     * @param noteVO
     * @throws Exception
     */
    public void insertNoteTrnsmit(NoteVO noteVO) throws Exception {
        insert(NAME_SPACE + ".insertNoteTrnsmit", noteVO);
    }

    /**
     * 받은쪽지 등록
     * @param noteVO
     * @throws Exception
     */
    public void insertNoteRecptn(NoteVO noteVO) throws Exception {
        insert(NAME_SPACE + ".insertNoteRecptn", noteVO);
    }
}
