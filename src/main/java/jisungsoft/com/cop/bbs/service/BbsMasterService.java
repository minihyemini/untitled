package jisungsoft.com.cop.bbs.service;

import java.util.List;

public interface BbsMasterService {
    /**
     * 게시판관리 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(게시판관리 목록)
     * @throws Exception
     */
    List<?> selectBbsMasterList(BbsMasterVO paramVO) throws Exception;
    /**
     * 게시판관리 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시판관리 목록)
     * @throws Exception
     */
    List<?> selectBbsMasterNPList(BbsMasterVO paramVO) throws Exception;
    /**
     * 게시판별권한 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시판별 권한목록)
     * @throws Exception
     */
    List<?> selectBbsMasterAuthList(BbsMasterAuthVO paramVO) throws Exception;
    /**
     * 게시판별 권한을 가져온다.
     * @param paramVO
     * @return List(게시판별 권한)
     * @throws Exception
     */
    BbsMasterAuthVO selectBbsMasterAuthInfo(BbsMasterAuthVO paramVO) throws Exception;
    /**
     * 게시판관리 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectBbsMasterListCnt(BbsMasterVO paramVO) throws Exception;
    /**
     * 게시판관리 목록을 상세조회한다.
     * @param paramVO
     * @return BbsMasterVO
     * @throws Exception
     */
    BbsMasterVO selectBbsMasterDetail(BbsMasterVO paramVO) throws Exception;
    /**
     * 게시판관리를 등록한다.
     * @param bbsMasterVO
     * @throws Exception
     */
    void insertBbsMaster(BbsMasterVO bbsMasterVO) throws Exception;
    /**
     * 게시판관리를 삭제한다.
     * @param bbsMasterVO
     * @throws Exception
     */
    void deleteBbsMaster(BbsMasterVO bbsMasterVO) throws Exception;
    /**
     * 게시판관리를 수정한다.
     * @param bbsMasterVO
     * @throws Exception
     */
    void updateBbsMaster(BbsMasterVO bbsMasterVO) throws Exception;
}
