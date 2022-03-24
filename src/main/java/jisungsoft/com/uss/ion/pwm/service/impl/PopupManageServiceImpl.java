package jisungsoft.com.uss.ion.pwm.service.impl;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.uss.ion.pwm.service.PopupManageService;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static jisungsoft.com.cmm.util.CommonUtil.unscript;

@Service("popupManageService")
public class PopupManageServiceImpl extends EgovAbstractServiceImpl implements PopupManageService {

    @Resource(name="popupManageDAO")
    private PopupManageDAO popupManageDAO;

	@Resource(name = "egovPopupIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 팝업 목록을 조회한다. (Paging)
	 */
	@Override
	public List<?> selectPopupList(PopupVO paramVO)  throws Exception {
		return popupManageDAO.selectPopupList(paramVO);
	}

	/**
	 * 팝업 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectPopupNPList(PopupVO paramVO)  throws Exception {
		return popupManageDAO.selectPopupNPList(paramVO);
	}

	/**
	 * 팝업 건수를 조회한다.
	 */
	@Override
	public int selectPopupListCnt(PopupVO paramVO)  throws Exception {
		return popupManageDAO.selectPopupListCnt(paramVO);
	}

	/**
	 * 팝업 상세항목을 조회한다.
	 */
	@Override
	public PopupVO selectPopupDetail(PopupVO paramVO)  throws Exception {
		return popupManageDAO.selectPopupDetail(paramVO);
	}

	/**
	 * 팝업을 등록한다.
	 */
	@Override
	public void insertPopup(PopupVO popupVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		popupVO.setPopupId(idgenService.getNextStringId());
		popupVO.setPopupCn(unscript(popupVO.getPopupCn()));
		popupVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		popupManageDAO.insertPopup(popupVO);
	}

	/**
	 * 팝업을 삭제한다.
	 */
	@Override
	public void deletePopup(PopupVO popupVO) throws Exception {
		popupManageDAO.deletePopup(popupVO);
	}

	/**
	 * 팝업을 수정한다.
	 */
	@Override
	public void updatePopup(PopupVO popupVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		popupVO.setPopupCn(unscript(popupVO.getPopupCn()));
		popupVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		popupManageDAO.updatePopup(popupVO);
	}

	/**
	 * 팝업 목록을 조회한다.
	 */
	@Override
	public List<?> selectPopupShowList(PopupVO paramVO)  throws Exception {
		return popupManageDAO.selectPopupShowList(paramVO);
	}
}
