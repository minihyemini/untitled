package jisungsoft.com.cop.cmy.service;

import java.util.List;

public interface CommunityMasterService {
    /**
     * 커뮤니티 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(커뮤니티관리 목록)
     * @throws Exception
     */
    List<?> selectCommunityMasterList(CommunityVO paramVO) throws Exception;
    /**
     * 커뮤니티관리 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectCommunityMasterListCnt(CommunityVO paramVO) throws Exception;
    /**
     * 커뮤니티를 상세조회한다.
     * @param paramVO
     * @return CommunityVO
     * @throws Exception
     */
    CommunityVO selectCommunityMasterDetail(CommunityVO paramVO) throws Exception;
    /**
     * 커뮤니티를 등록한다.(소개)
     * @param communityVO
     * @throws Exception
     */
    void insertCommunity(CommunityVO communityVO) throws Exception;
    /**
     * 커뮤니티를 수정한다.
     * @param communityVO
     * @throws Exception
     */
    void updateCommunity(CommunityVO communityVO) throws Exception;
    /**
     * 커뮤니티를 삭제한다.
     * @param communityVO
     * @throws Exception
     */
    void deleteCommunity(CommunityVO communityVO) throws Exception;
    /**
     * 조회수 증가 처리
     * @param communityVO
     * @throws Exception
     */
    void updateRdcnt(CommunityVO communityVO) throws Exception;
    /**
     * 커뮤니티 회원 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(커뮤니티회원 목록)
     * @throws Exception
     */
    List<?> selectCommunityUserList(CommunityUserVO paramVO) throws Exception;
    /**
     * 커뮤니티 회원 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(커뮤니티회원 목록)
     * @throws Exception
     */
    List<?> selectCommunityUserNPList(CommunityUserVO paramVO) throws Exception;
    /**
     * 커뮤니티 회원 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectCommunityUserListCnt(CommunityUserVO paramVO) throws Exception;
    /**
     * 커뮤니티 회원 상태별 건수
     * @param paramVO
     * @return CommunityUserVO
     * @throws Exception
     */
    CommunityUserVO selectCommunityUserDetail(CommunityUserVO paramVO) throws Exception;
    /**
     * 커뮤니티 회원을 등록한다.
     * @param communityUserVO
     * @throws Exception
     */
    void insertCommunityUser(CommunityUserVO communityUserVO) throws Exception;
    /**
     * 커뮤니티 회원을 수정한다.
     * @param communityUserVO
     * @throws Exception
     */
    void updateCommunityUser(CommunityUserVO communityUserVO) throws Exception;
    /**
     * 커뮤니티 회원 상태처리
     * @param communityUserVO
     * @throws Exception
     */
    void updateCommunityUserStts(CommunityUserVO communityUserVO) throws Exception;
    /**
     * 커뮤니티 탈퇴처리
     * @param communityUserVO
     * @throws Exception
     */
    void updateCommunityUserEnd(CommunityUserVO communityUserVO) throws Exception;
    /**
     * 커뮤니티 회원을 삭제한다.
     * @param communityUserVO
     * @throws Exception
     */
    void deleteCommunityUser(CommunityUserVO communityUserVO) throws Exception;
}