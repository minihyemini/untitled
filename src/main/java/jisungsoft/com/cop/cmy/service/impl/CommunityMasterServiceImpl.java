package jisungsoft.com.cop.cmy.service.impl;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cop.bbs.service.ArticleDataService;
import jisungsoft.com.cop.bbs.service.ArticleDataVO;
import jisungsoft.com.cop.bbs.service.BbsMasterAuthVO;
import jisungsoft.com.cop.bbs.service.BbsMasterVO;
import jisungsoft.com.cop.bbs.service.impl.BbsMasterDAO;
import jisungsoft.com.cop.cmy.service.CommunityMasterService;
import jisungsoft.com.cop.cmy.service.CommunityUserVO;
import jisungsoft.com.cop.cmy.service.CommunityVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jisungsoft.com.cmm.util.CommonUtil.unscript;

@Service("communityMasterService")
public class CommunityMasterServiceImpl extends EgovAbstractServiceImpl implements CommunityMasterService {
    @Resource(name="communityDAO")
    private CommunityMasterDAO communityDAO;

    /* 게시판 dao */
    @Resource(name="bbsMasterDAO")
    private BbsMasterDAO bbsMasterDAO;

    /** 게시판 서비스 */
    @Resource(name = "articleDataService")
    private ArticleDataService articleDataService;

    /* 게시판 idgen */
    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    /* 커뮤니티 idgen */
    @Resource(name = "egovCmmntyIdGnrService")
    private EgovIdGnrService idgenCmmntyService;

    /**
     * 커뮤니티관리 목록을 조회한다. (Paging)
     */
    @Override
    public List<?> selectCommunityMasterList(CommunityVO paramVO) {
        return communityDAO.selectCommunityMasterList(paramVO);
    }
    /**
     * 커뮤니티관리 건수를 조회한다.
     */
    @Override
    public int selectCommunityMasterListCnt(CommunityVO paramVO) {
        return communityDAO.selectCommunityMasterListCnt(paramVO);
    }
    /**
     * 커뮤니티를 상세조회한다.
     */
    @Override
    public CommunityVO selectCommunityMasterDetail(CommunityVO paramVO) throws Exception {
        return communityDAO.selectCommunityMasterDetail(paramVO);
    }
    /**
     * 커뮤니티를 등록한다.(소개)
     */
    @Override
    public void insertCommunity(CommunityVO communityVO) throws Exception {
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String cmmntyId  = idgenCmmntyService.getNextStringId();
        String bbsId     = idgenService.getNextStringId();
        String uniqId    = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();

        /* 게시판마스터 data insert */
        List<Map> bbsMasterAuthList = new ArrayList<>();
        Map bbsMasterData = new HashMap();
        bbsMasterData.put("authCode", AuthorityCode.ROLE_ADMIN.name());
        bbsMasterAuthList.add(bbsMasterData);
        bbsMasterData = new HashMap();
        bbsMasterData.put("authCode", AuthorityCode.ROLE_MANAGER.name());
        bbsMasterAuthList.add(bbsMasterData);

        BbsMasterVO bbsMasterVO = new BbsMasterVO();
        bbsMasterVO.setBbsId(bbsId);
        bbsMasterVO.setBbsNm(communityVO.getCmmntyNm()+" 활동현황");
        bbsMasterVO.setBbsIntrcn(communityVO.getCmmntyNm()+" 활동현황");
        bbsMasterVO.setBbsTyCode("BBST04");
        bbsMasterVO.setFileAtchPosblAt("Y");
        bbsMasterVO.setReplyPosblAt("Y");
        bbsMasterVO.setUseAt("Y");
        bbsMasterVO.setFrstRegisterId(uniqId);
        bbsMasterDAO.insertBbsMaster(bbsMasterVO);

        /* 커뮤니티 data insert */
        communityVO.setCmmntyId(cmmntyId);
        communityVO.setBbsId(bbsId);
        communityVO.setCmmntyCn(unscript(communityVO.getCmmntyCn()));
        communityVO.setCmmntyIntrcn(unscript(communityVO.getCmmntyIntrcn()));
        communityVO.setFrstRegisterId(uniqId);
        communityDAO.insertCommunity(communityVO);

        /* 커뮤니티user 관리자 data insert */
        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(cmmntyId);
        communityUserVO.setEmplyrId(uniqId);
        communityUserVO.setMngrAt("Y"); //관리자여부
        communityUserVO.setMberSttus("STTS02"); // 승인
        communityUserVO.setUseAt("Y");
        communityUserVO.setFrstRegisterId(uniqId);
        communityDAO.insertCommunityUser(communityUserVO);

        //권한 생성
        for (Map bbsDataAuth : bbsMasterAuthList) {
            BbsMasterAuthVO bbsMasterAuthVO = new BbsMasterAuthVO();
            bbsMasterAuthVO.setBbsId(bbsId);
            bbsMasterAuthVO.setAuthorCode(bbsDataAuth.get("authCode").toString());
            bbsMasterDAO.insertBbsMasterAuth(bbsMasterAuthVO);
        }
    }
    /**
     * 커뮤니티를 수정한다.
     */
    @Override
    public void updateCommunity(CommunityVO communityVO) throws Exception {
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String uniqId    = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();

        BbsMasterVO bvo = new BbsMasterVO();
        bvo.setBbsId(communityVO.getBbsId());
        bvo.setBbsNm(communityVO.getCmmntyNm()+" 활동현황");
        bvo.setLastUpdusrId(uniqId);
        bbsMasterDAO.updateBbsMasterNm(bvo);

        communityVO.setLastUpdusrId(uniqId);
        communityDAO.updateCommunity(communityVO);
    }
    /**
     * 커뮤니티를 삭제한다.
     */
    @Override
    public void deleteCommunity(CommunityVO communityVO) throws Exception {
        communityDAO.deleteCommunity(communityVO);

        /* 커뮤니티 활동현황 내 게시글 삭제 */
        ArticleDataVO articleDataVO = new ArticleDataVO();
        articleDataVO.setBbsId(communityVO.getBbsId());
        articleDataService.deleteArticle(articleDataVO);
        /* 게시판 마스터 내 해당 커뮤니티 활동현황 삭제 */
        BbsMasterVO bbsMasterVO = new BbsMasterVO();
        bbsMasterVO.setBbsId(communityVO.getBbsId());
        bbsMasterDAO.deleteBbsMaster(bbsMasterVO);
        /* 커뮤니티user 삭제 */
        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(communityVO.getCmmntyId());
        communityDAO.deleteCommunityUser(communityUserVO);
    }
    /**
     * 커뮤니티 조회수
     */
    @Override
    public void updateRdcnt(CommunityVO communityVO) throws Exception {
        communityDAO.updateRdcnt(communityVO);
    }
    /**
     * 커뮤니티 회원 목록을 조회한다. (Paging)
     */
    @Override
    public List<?> selectCommunityUserList(CommunityUserVO paramVO) {
        return communityDAO.selectCommunityUserList(paramVO);
    }
    /**
     * 커뮤니티 회원 목록을 조회한다. (Not Paging)
     */
    @Override
    public List<?> selectCommunityUserNPList(CommunityUserVO paramVO) {
        return communityDAO.selectCommunityUserNPList(paramVO);
    }
    /**
     * 커뮤니티 회원 건수를 조회한다.
     */
    @Override
    public int selectCommunityUserListCnt(CommunityUserVO paramVO) {
        return communityDAO.selectCommunityUserListCnt(paramVO);
    }
    /**
     * 커뮤니티 회원 상태별 건수
     */
    @Override
    public CommunityUserVO selectCommunityUserDetail(CommunityUserVO paramVO) {
        return communityDAO.selectCommunityUserDetail(paramVO);
    }    
    /**
     * 커뮤니티 회원을 등록한다.
     */
    @Override
    public void insertCommunityUser(CommunityUserVO communityUserVO) throws Exception {
        communityDAO.insertCommunityUser(communityUserVO);
    }
    /**
     * 커뮤니티 회원을 수정한다.
     */
    @Override
    public void updateCommunityUser(CommunityUserVO communityUserVO) throws Exception {
        communityDAO.updateCommunityUser(communityUserVO);
    }
    /**
     * 커뮤니티 회원 상태처리
     */
    @Override
    public void updateCommunityUserStts(CommunityUserVO communityUserVO) throws Exception {
        communityDAO.updateCommunityUserStts(communityUserVO);
    }
    /**
     * 커뮤니티 탈퇴처리
     */
    @Override
    public void updateCommunityUserEnd(CommunityUserVO communityUserVO) throws Exception {
        communityDAO.updateCommunityUserEnd(communityUserVO);
    }
    /**
     * 커뮤니티 회원을 삭제한다.
     */
    @Override
    public void deleteCommunityUser(CommunityUserVO communityUserVO) throws Exception {
        communityDAO.deleteCommunityUser(communityUserVO);
    }
}
