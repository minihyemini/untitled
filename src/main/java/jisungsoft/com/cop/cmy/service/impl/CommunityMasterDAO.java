package jisungsoft.com.cop.cmy.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.cmy.service.CommunityUserVO;
import jisungsoft.com.cop.cmy.service.CommunityVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("communityDAO")
public class CommunityMasterDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "CommunityMasterDAO";

    /**
     * 커뮤니티관리 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(커뮤니티관리 목록)
     */
    public List<?> selectCommunityMasterList(CommunityVO paramVO) {
        return selectList(NAME_SPACE + ".selectCommunityMasterList", paramVO);
    }
    /**
     * 커뮤니티관리 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectCommunityMasterListCnt(CommunityVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectCommunityMasterListCnt", paramVO);
    }
    /**
     * 커뮤니티를 상세조회한다.
     * @param paramVO
     * @return CommunityVO
     */
    public CommunityVO selectCommunityMasterDetail(CommunityVO paramVO) {
        return selectOne(NAME_SPACE + ".selectCommunityMasterDetail", paramVO);
    }
    /**
     * 커뮤니티 회원목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(커뮤니티회원 목록)
     */
    public List<?> selectCommunityUserList(CommunityUserVO paramVO) {
        return selectList(NAME_SPACE + ".selectCommunityUserList", paramVO);
    }
    /**
     * 커뮤니티 회원목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(커뮤니티회원 목록)
     */
    public List<?> selectCommunityUserNPList(CommunityUserVO paramVO) {
        return selectList(NAME_SPACE + ".selectCommunityUserNPList", paramVO);
    }
    /**
     * 커뮤니티 회원 총건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectCommunityUserListCnt(CommunityUserVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectCommunityUserListCnt", paramVO);
    }
    /**
     * 커뮤니티 회원 상태별 건수
     * @param paramVO
     * @return CommunityUserVO
     */
    public CommunityUserVO selectCommunityUserDetail(CommunityUserVO paramVO) {
        return selectOne(NAME_SPACE + ".selectCommunityUserDetail", paramVO);
    }
    /**
     * 커뮤니티를 등록한다.(소개)
     * @param communityVO
     * @throws Exception
     */
    public void insertCommunity(CommunityVO communityVO) throws Exception {
        insert(NAME_SPACE + ".insertCommunity", communityVO);
    }
    /**
     * 커뮤니티를 수정한다.(소개)
     * @param communityVO
     * @throws Exception
     */
    public void updateCommunity(CommunityVO communityVO) throws Exception{
        update(NAME_SPACE + ".updateCommunity", communityVO);
    }
    /**
     * 커뮤니티를 삭제한다.(소개)
     * @param communityVO
     * @throws Exception
     */
    public void deleteCommunity(CommunityVO communityVO) throws Exception{
        delete(NAME_SPACE + ".deleteCommunity", communityVO);
    }
    /**
     * 커뮤니티 조회수 count
     * @param communityVO
     * @throws Exception
     */
    public void updateRdcnt(CommunityVO communityVO) throws Exception{
        update(NAME_SPACE + ".updateRdcnt", communityVO);
    }
    /**
     * 커뮤니티 회원을 등록한다.
     * @param communityUserVO
     * @throws Exception
     */
    public void insertCommunityUser(CommunityUserVO communityUserVO) throws Exception {
        insert(NAME_SPACE + ".insertCommunityUser", communityUserVO);
    }
    /**
     * 커뮤니티 회원을 수정한다.
     * @param communityUserVO
     * @throws Exception
     */
    public void updateCommunityUser(CommunityUserVO communityUserVO) throws Exception{
        update(NAME_SPACE + ".updateCommunityUser", communityUserVO);
    }
    /**
     * 커뮤니티 회원상태처리
     * @param communityUserVO
     * @throws Exception
     */
    public void updateCommunityUserStts(CommunityUserVO communityUserVO) throws Exception{
        update(NAME_SPACE + ".updateCommunityUserStts", communityUserVO);
    }
    /**
     * 커뮤니티 탈퇴처리
     * @param communityUserVO
     * @throws Exception
     */
    public void updateCommunityUserEnd(CommunityUserVO communityUserVO) throws Exception{
        update(NAME_SPACE + ".updateCommunityUserEnd", communityUserVO);
    }
    /**
     * 커뮤니티 회원을 삭제한다.
     * @param communityUserVO
     * @throws Exception
     */
    public void deleteCommunityUser(CommunityUserVO communityUserVO) throws Exception{
        delete(NAME_SPACE + ".deleteCommunityUser", communityUserVO);
    }
}
