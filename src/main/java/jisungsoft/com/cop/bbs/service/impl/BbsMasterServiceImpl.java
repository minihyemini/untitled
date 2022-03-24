package jisungsoft.com.cop.bbs.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cop.bbs.service.BbsMasterAuthVO;
import jisungsoft.com.cop.bbs.service.BbsMasterVO;
import jisungsoft.com.cop.bbs.service.BbsMasterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bbsMasterService")
public class BbsMasterServiceImpl extends EgovAbstractServiceImpl implements BbsMasterService {

    @Resource(name="bbsMasterDAO")
    private BbsMasterDAO bbsMasterDAO;

	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService idgenService;

	/* 커뮤니티 */
	@Resource(name = "egovCmmntyIdGnrService")
	private EgovIdGnrService idgenCmmntyService;

	/**
	 * 게시판관리 목록을 조회한다. (Paging)
	 */
	@Override
	public List<?> selectBbsMasterList(BbsMasterVO paramVO) {
		return bbsMasterDAO.selectBbsMasterList(paramVO);
	}

	/**
	 * 게시판관리 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectBbsMasterNPList(BbsMasterVO paramVO) {
		return bbsMasterDAO.selectBbsMasterNPList(paramVO);
	}

	/**
	 * 게시판별권한 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectBbsMasterAuthList(BbsMasterAuthVO paramVO) {
		return bbsMasterDAO.selectBbsMasterAuthList(paramVO);
	}

	/**
	 * 게시판별 권한을 가져온다.
	 */
	@Override
	public BbsMasterAuthVO selectBbsMasterAuthInfo(BbsMasterAuthVO paramVO) {
		return bbsMasterDAO.selectBbsMasterAuthInfo(paramVO);
	}

	/**
	 * 게시판관리 건수를 조회한다.
	 */
	@Override
	public int selectBbsMasterListCnt(BbsMasterVO paramVO) {
		return bbsMasterDAO.selectBbsMasterListCnt(paramVO);
	}

	/**
	 * 게시판관리 목록을 상세조회한다.
	 */
	@Override
	public BbsMasterVO selectBbsMasterDetail(BbsMasterVO paramVO) {
		return bbsMasterDAO.selectBbsMasterDetail(paramVO);
	}

	/**
	 * 게시판관리를 등록한다.
	 */
	@Override
	public void insertBbsMaster(BbsMasterVO bbsMasterVO) throws Exception {
		/* 게시판ID 채번 */
		String bbsId = idgenService.getNextStringId();
		bbsMasterVO.setBbsId(bbsId);

		if(bbsMasterVO.getBbsTyCode().equals("BBST04")){
			/* 커뮤니티ID 채번 */
			String cmmntyId = idgenCmmntyService.getNextStringId();
			bbsMasterVO.setCmmntyId(cmmntyId);
		}

		if(bbsMasterVO.getAuthorCode() != null) {
			BbsMasterAuthVO bbsMasterAuthVO = new BbsMasterAuthVO();
			bbsMasterAuthVO.setBbsId(bbsId);
			String[] codeList = bbsMasterVO.getAuthorCode().split(",");

			for(int i = 0; i < codeList.length; i++){
				bbsMasterAuthVO.setAuthorCode(codeList[i]);
				bbsMasterDAO.insertBbsMasterAuth(bbsMasterAuthVO);
			}
		}

		bbsMasterDAO.insertBbsMaster(bbsMasterVO);
	}

	/**
	 * 게시판관리를 삭제한다.
	 */
	@Override
	public void deleteBbsMaster(BbsMasterVO bbsMasterVO) throws Exception {
		/* 권한 삭제 */
		BbsMasterAuthVO bbsMasterAuthVO = new BbsMasterAuthVO();
		bbsMasterAuthVO.setBbsId(bbsMasterVO.getBbsId());
		bbsMasterDAO.deleteBbsMasterAuth(bbsMasterAuthVO);
		bbsMasterDAO.deleteBbsMaster(bbsMasterVO);
	}

	/**
	 * 게시판관리를 수정한다.
	 */
	@Override
	public void updateBbsMaster(BbsMasterVO bbsMasterVO) throws Exception {
		/* 삭제 후 권한 재등록 */
		BbsMasterAuthVO bbsMasterAuthVO = new BbsMasterAuthVO();
		bbsMasterAuthVO.setBbsId(bbsMasterVO.getBbsId());
		bbsMasterDAO.deleteBbsMasterAuth(bbsMasterAuthVO);

		if(bbsMasterVO.getAuthorCode() != null) {
			String[] codeList = bbsMasterVO.getAuthorCode().split(",");

			for(int i = 0; i < codeList.length; i++){
				bbsMasterAuthVO.setAuthorCode(codeList[i]);
				bbsMasterDAO.insertBbsMasterAuth(bbsMasterAuthVO);
			}
		}

		bbsMasterDAO.updateBbsMaster(bbsMasterVO);
	}
}
