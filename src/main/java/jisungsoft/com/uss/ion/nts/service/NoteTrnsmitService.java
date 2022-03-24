package jisungsoft.com.uss.ion.nts.service;

import java.util.List;

public interface NoteTrnsmitService {
    /**
     * 보낸쪽지함관리 목록 조회
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitList(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 보낸쪽지함관리 목록 전체 건수 조회
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    public int selectNoteTrnsmitListCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 보낸쪽지함관리 상세조회
     * @param noteTrnsmitVO
     * @return Map
     * @throws Exception
     */
    public NoteTrnsmitVO selectNoteTrnsmitDetail(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 보낸쪽지함관리 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    void deleteNoteTrnsmit(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 받은쪽지함관리 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    void deleteNoteRecptn(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 수신자목록 조회(Paging)
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitCnfirm(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 수신자목록 조회(Not Paging)
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitCnfirmNP(NoteTrnsmitVO noteTrnsmitVO) throws Exception;

    /**
     * 수신자목록 건수
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    public int selectNoteTrnsmitCnfirmCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception;
}
