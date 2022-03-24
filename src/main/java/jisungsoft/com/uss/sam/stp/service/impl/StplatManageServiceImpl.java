package jisungsoft.com.uss.sam.stp.service.impl;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.uss.sam.stp.service.StplatManageService;
import jisungsoft.com.uss.sam.stp.service.StplatVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("stplatManageService")
public class StplatManageServiceImpl extends EgovAbstractServiceImpl implements StplatManageService {

    @Resource(name="stplatManageDAO")
    private StplatManageDAO stplatManageDAO;

	@Resource(name = "egovSplatIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 약관 목록을 조회한다. (Paging)
	 */
	@Override
	public List<?> selectStplatList(StplatVO paramVO)  throws Exception {
		return stplatManageDAO.selectStplatList(paramVO);
	}

	/**
	 * 약관 건수를 조회한다.
	 */
	@Override
	public int selectStplatListCnt(StplatVO paramVO)  throws Exception {
		return stplatManageDAO.selectStplatListCnt(paramVO);
	}

	/**
	 * 약관 상세항목을 조회한다.
	 */
	@Override
	public StplatVO selectStplatDetail(StplatVO  paramVO)  throws Exception {
		return stplatManageDAO.selectStplatDetail(paramVO);
	}

	@Override
	public StplatVO selectStplatDetailUseAtY(StplatVO paramVO) throws Exception {
		return stplatManageDAO.selectStplatDetailUseAtY(paramVO);
	}

	/**
	 * 약관을 등록한다.
	 */
	@Override
	public void insertStplat(StplatVO stplatVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (stplatVO.getUseAt().equals("Y")) {
			stplatManageDAO.updateStplatUseAll();
		}
		stplatVO.setUseStplatId(idgenService.getNextStringId());
//		stplatVO.setUseStplatCn(stplatVO.getUseStplatCn());
//		stplatVO.setInfoProvdAgreCn(stplatVO.getInfoProvdAgreCn());
		stplatVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		stplatManageDAO.insertStplat(stplatVO);
	}

	/**
	 * 약관을 삭제한다.
	 */
	@Override
	public void deleteStplat(StplatVO stplatVO) throws Exception {
		stplatManageDAO.deleteStplat(stplatVO);
	}

	/**
	 * 약관을 수정한다.
	 */
	@Override
	public void updateStplat(StplatVO stplatVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if (stplatVO.getUseAt().equals("Y")) {
			stplatManageDAO.updateStplatUseAll();
		}
//		stplatVO.setUseStplatCn(stplatVO.getUseStplatCn());
//		stplatVO.setInfoProvdAgreCn(stplatVO.getInfoProvdAgreCn());
		stplatVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		stplatManageDAO.updateStplat(stplatVO);
	}
}
