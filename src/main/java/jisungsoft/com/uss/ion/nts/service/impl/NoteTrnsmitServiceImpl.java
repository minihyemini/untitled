package jisungsoft.com.uss.ion.nts.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitService;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("noteTrnsmitService")
public class NoteTrnsmitServiceImpl extends EgovAbstractServiceImpl implements NoteTrnsmitService {

    @Resource(name="noteTrnsmitDAO")
    private NoteTrnsmitDAO noteTrnsmitDAO;

    /**
     * 보낸쪽지함관리 목록 조회
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    @Override
    public List<?> selectNoteTrnsmitList(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return noteTrnsmitDAO.selectNoteTrnsmitList(noteTrnsmitVO);
    }

    /**
     * 보낸쪽지함관리 목록 전체 건수 조회
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    @Override
    public int selectNoteTrnsmitListCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return noteTrnsmitDAO.selectNoteTrnsmitListCnt(noteTrnsmitVO);
    }

    /**
     * 보낸쪽지함관리 상세조회
     * @param noteTrnsmitVO
     * @return noteTrnsmitVO
     * @throws Exception
     */
    @Override
    public NoteTrnsmitVO selectNoteTrnsmitDetail(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return noteTrnsmitDAO.selectNoteTrnsmitDetail(noteTrnsmitVO);
    }

    /**
     * 보낸쪽지함관리를 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    @Override
    public void deleteNoteTrnsmit(NoteTrnsmitVO noteTrnsmitVO) throws Exception {

        //보낸쪽지함 건수를 조회함
        int nCnt = noteTrnsmitDAO.selectTrnsmitRelationCnt(noteTrnsmitVO);

        if(nCnt == 0){
            /* 받은쪽지 내역이 없는 경우, 전부 삭제 처리 */
            // 받은쪽지/쪽지관리 삭제 처리
            noteTrnsmitDAO.deleteNoteTrnsmitRelation(noteTrnsmitVO);
            // 쪽지정보를 삭제한다.
            noteTrnsmitDAO.deleteNoteManage(noteTrnsmitVO);
        }else{
            noteTrnsmitDAO.deleteNoteTrnsmit(noteTrnsmitVO); // 삭제여부 update
        }
    }

    /**
     * 보낸쪽지함관리 삭제
     * @param noteTrnsmitVO
     * @throws Exception
     */
    @Override
    public void deleteNoteRecptn(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        noteTrnsmitDAO.deleteNoteRecptn(noteTrnsmitVO);
    }

    /**
     * 수신자목록 조회(Paging)
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    @Override
    public List<?> selectNoteTrnsmitCnfirm(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return noteTrnsmitDAO.selectNoteTrnsmitCnfirm(noteTrnsmitVO);
    }

    /**
     * 수신자목록 조회(Not Paging)
     * @param noteTrnsmitVO
     * @return List
     * @throws Exception
     */
    @Override
    public List<?> selectNoteTrnsmitCnfirmNP(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return noteTrnsmitDAO.selectNoteTrnsmitCnfirmNP(noteTrnsmitVO);
    }

    /**
     * 수신자목록 조회 건수
     * @param noteTrnsmitVO
     * @return int
     * @throws Exception
     */
    @Override
    public int selectNoteTrnsmitCnfirmCnt(NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        return noteTrnsmitDAO.selectNoteTrnsmitCnfirmCnt(noteTrnsmitVO);
    }
}
