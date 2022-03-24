package jisungsoft.com.uss.ion.ntr.service;

import java.util.List;

public interface NoteRecptnService {
    /**
     * 받은쪽지함관리 목록 조회
     * @param noteRecptnVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteRecptnList(NoteRecptnVO noteRecptnVO) throws Exception;

    /**
     * 받은쪽지함관리 목록 전체 건수
     * @param noteRecptnVO
     * @return int
     * @throws Exception
     */
    public int selectNoteRecptnListCnt(NoteRecptnVO noteRecptnVO) throws Exception;

    /**
     * 받은쪽지함관리 상세조회
     * @param noteRecptnVO
     * @return Map
     * @throws Exception
     */
    public NoteRecptnVO selectNoteRecptnDetail(NoteRecptnVO noteRecptnVO) throws Exception;

    /**
     * 받은쪽지함관리 삭제
     * @param noteRecptnVO
     * @throws Exception
     */
    void  deleteNoteRecptn(NoteRecptnVO noteRecptnVO) throws Exception;
}
