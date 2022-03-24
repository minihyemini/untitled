package jisungsoft.com.uss.ion.nts.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("noteTrnsmitDAO")
public class NoteTrnsmitDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "NoteTrnsmitDAO";

    /**
     * 보낸쪽지함관리 목록
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitList(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return selectList(NAME_SPACE + ".selectNoteTrnsmit", noteTrnsmitVO);
    }

    /**
     * 보낸쪽지함관리 목록 전체 건수
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    public int selectNoteTrnsmitListCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectNoteTrnsmitCnt", noteTrnsmitVO);
    }

    /**
     * 보낸쪽지함관리 상세조회
     * @param noteTrnsmitVO
     * @return NoteTrnsmitVO
     * @throws Exception
     */
    public NoteTrnsmitVO selectNoteTrnsmitDetail(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectNoteTrnsmitDetail", noteTrnsmitVO);
    }

    /**
     * 보낸쪽지함관리 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    public void deleteNoteTrnsmit(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteTrnsmit" , noteTrnsmitVO);
    }

    /**
     * 받은쪽지함 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    public void deleteNoteRecptn(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteRecptn" , noteTrnsmitVO);
    }

    /**
     * 쪽지 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    public void deleteNoteManage(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteManage" , noteTrnsmitVO);
    }

    /**
     * 쪽지관리/보낸족지함삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    public void deleteNoteTrnsmitRelation(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        delete(NAME_SPACE + ".deleteNoteTrnsmitRelation" , noteTrnsmitVO);
    }

    /**
     * 받은편지함 건수 조회
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    public int selectTrnsmitRelationCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectTrnsmitRelationCnt", noteTrnsmitVO);
    }

    /**
     * 수신자목록 조회(Paging)
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitCnfirm(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return selectList(NAME_SPACE + ".selectNoteTrnsmitCnfirm", noteTrnsmitVO);
    }

    /**
     * 수신자목록 조회(Not Paging)
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    public List<?> selectNoteTrnsmitCnfirmNP(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return selectList(NAME_SPACE + ".selectNoteTrnsmitCnfirmNP", noteTrnsmitVO);
    }

    /**
     * 수신자목록 전체 건수
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    public int selectNoteTrnsmitCnfirmCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectNoteTrnsmitCnfirmCnt", noteTrnsmitVO);
    }
}
