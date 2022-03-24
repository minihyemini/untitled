package jisungsoft.com.cop.bbs.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.bbs.service.BbsMasterAuthVO;
import jisungsoft.com.cop.bbs.service.BbsMasterVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bbsMasterDAO")
public class BbsMasterDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "BbsMasterDAO";

    /**
     * 게시판관리 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(게시판관리 목록)
     */
    public List<?> selectBbsMasterList(BbsMasterVO paramVO) {
        return selectList(NAME_SPACE + ".selectBbsMasterList", paramVO);
    }

    /**
     * 게시판관리 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시판관리 목록)
     */
    public List<?> selectBbsMasterNPList(BbsMasterVO paramVO) {
        return selectList(NAME_SPACE + ".selectBbsMasterNPList", paramVO);
    }

    /**
     * 게시판별 권한을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시판별권한 목록조회)
     */
    public List<?> selectBbsMasterAuthList(BbsMasterAuthVO paramVO) {
        return selectList(NAME_SPACE + ".selectBbsMasterAuthList", paramVO);
    }

    /**
     * 게시판별 권한을 조회한다.
     * @param paramVO
     * @return List(게시판별 권한)
     */
    public BbsMasterAuthVO selectBbsMasterAuthInfo(BbsMasterAuthVO paramVO) {
        return selectOne(NAME_SPACE + ".selectBbsMasterAuthInfo", paramVO);
    }

    /**
     * 게시판관리 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectBbsMasterListCnt(BbsMasterVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectBbsMasterListTotCnt", paramVO);
    }

    /**
     * 게시판관리 목록을 상세조회한다.
     * @param paramVO
     * @return BbsMasterVO
     */
    public BbsMasterVO selectBbsMasterDetail(BbsMasterVO paramVO) {
        return selectOne(NAME_SPACE + ".selectBbsMasterDetail", paramVO);
    }

    /**
     * 게시판관리를 등록한다.
     * @param bbsMasterVO
     * @throws Exception
     */
    public void insertBbsMaster(BbsMasterVO bbsMasterVO) throws Exception {
        insert(NAME_SPACE + ".insertBbsMaster", bbsMasterVO);
    }

    /**
     * 게시판관리를 삭제한다.
     * @param bbsMasterVO
     * @throws Exception
     */
    public void deleteBbsMaster(BbsMasterVO bbsMasterVO) throws Exception {
        delete(NAME_SPACE + ".deleteBbsMaster", bbsMasterVO);

    }

    /**
     * 게시판관리를 수정한다.
     * @param bbsMasterVO
     * @throws Exception
     */
    public void updateBbsMaster(BbsMasterVO bbsMasterVO) throws Exception{
        update(NAME_SPACE + ".updateBbsMaster", bbsMasterVO);
    }

    /**
     * 게시판관리를 수정한다. (게시판명)
     * @param bbsMasterVO
     * @throws Exception
     */
    public void updateBbsMasterNm(BbsMasterVO bbsMasterVO) throws Exception{
        update(NAME_SPACE + ".updateBbsMasterNm", bbsMasterVO);
    }

    /**
     * 게시판 권한 등록
     * @param bbsMasterAuthVO
     * @throws Exception
     */
    public void insertBbsMasterAuth(BbsMasterAuthVO bbsMasterAuthVO) throws Exception {
        insert(NAME_SPACE + ".insertBbsMasterAuth", bbsMasterAuthVO);
    }

    /**
     * 게시판 권한 삭제
     * @param bbsMasterAuthVO
     * @throws Exception
     */
    public void deleteBbsMasterAuth(BbsMasterAuthVO bbsMasterAuthVO) throws Exception {
        delete(NAME_SPACE + ".deleteBbsMasterAuth", bbsMasterAuthVO);
    }
}
