package jisungsoft.com.uss.ion.ntr.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uss.ion.ntr.service.NoteRecptnVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("noteRecptnDAO")
public class NoteRecptnDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "NoteRecptnDAO";

    /**
     * 받은쪽지함관리 목록
     * @param noteRecptnVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteRecptnList(NoteRecptnVO noteRecptnVO) throws Exception {
        return selectList(NAME_SPACE + ".selectNoteRecptn", noteRecptnVO);
    }

    /**
     * 받은쪽지함관리 목록 전체 건수
     * @param noteRecptnVO
     * @return int
     * @throws Exception
     */
    public int selectNoteRecptnListCnt(NoteRecptnVO noteRecptnVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectNoteRecptnCnt", noteRecptnVO);
    }

    /**
     * 받은쪽지함관리를 개봉으로 update
     * @param noteRecptnVO
     * @throws Exception
     */
    public void updateNoteRecptnRelationOpenYn(NoteRecptnVO noteRecptnVO) throws Exception {
        update(NAME_SPACE + ".updateNoteRecptnRelationOpenYn" , noteRecptnVO);
    }

    /**
     * 받은쪽지함관리 상세조회
     * @param noteRecptnVO
     * @return NoteRecptnVO
     * @throws Exception
     */
    public NoteRecptnVO selectNoteRecptnDetail(NoteRecptnVO noteRecptnVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectNoteRecptnDetail", noteRecptnVO);
    }

    /**
     * 보낸쪽지함관리 건수 조회
     * @param  noteRecptnVO
     * @return int
     * @throws Exception
     */
    public int selectNoteTrnsmitRelationCnt(NoteRecptnVO noteRecptnVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectNoteTrnsmitRelationCnt", noteRecptnVO);
    }

    /**
     * 받은쪽지함관리 건수 조회
     * @param noteRecptnVO
     * @return int
     * @throws Exception
     */
    public int selectNoteRecptnRelationCnt(NoteRecptnVO noteRecptnVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectNoteRecptnRelationCnt", noteRecptnVO);
    }

    /**
     * 쪽지관리/쪽지관리,보낸보낸쪽지함, 받은쪽지함 삭제
     * @param noteRecptnVO
     * @throws Exception
     */
    public void deleteNoteRecptnRelation(NoteRecptnVO noteRecptnVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteRecptnRelation" , noteRecptnVO);
    }

    /**
     * 받은쪽지함관리 삭제
     * @param noteRecptnVO
     * @throws Exception
     */
    public void deleteNoteRecptn(NoteRecptnVO noteRecptnVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteRecptn" , noteRecptnVO);
    }

    /**
     * 쪽지관리/보낸족지함삭제
     * @param noteRecptnVO  보낸쪽지함관리 정보 객체
     * @throws Exception
     */
    public void deleteNoteTrnsmit(NoteRecptnVO noteRecptnVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteTrnsmit" , noteRecptnVO);
    }

    /**
     * 쪽지관리/쪽지관리삭제
     * @param noteRecptnVO
     * @throws Exception
     */
    public void deleteNoteManage(NoteRecptnVO noteRecptnVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteManage" , noteRecptnVO);
    }
}
