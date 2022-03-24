package jisungsoft.com.uss.ion.pwm.service;

import java.util.List;

public interface PopupManageService {
    /**
     * 팝업 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(팝업 목록)
     * @throws Exception
     */
    List<?> selectPopupList(PopupVO paramVO) throws Exception;
    /**
     * 팝업 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(팝업 목록)
     * @throws Exception
     */
    List<?> selectPopupNPList(PopupVO paramVO) throws Exception;
    /**
     * 팝업 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectPopupListCnt(PopupVO paramVO) throws Exception;
    /**
     * 팝업을 상세조회한다.
     * @param paramVO
     * @return StplatVO
     * @throws Exception
     */
    PopupVO selectPopupDetail(PopupVO paramVO) throws Exception;
    /**
     * 팝업을 등록한다.
     * @param popupVO
     * @throws Exception
     */
    void insertPopup(PopupVO popupVO) throws Exception;
    /**
     * 팝업을 삭제한다.
     * @param popupVO
     * @throws Exception
     */
    void deletePopup(PopupVO popupVO) throws Exception;
    /**
     * 팝업을 수정한다.
     * @param popupVO
     * @throws Exception
     */
    void updatePopup(PopupVO popupVO) throws Exception;

    /**
     * 팝업 목록을 조회한다.
     * @param paramVO
     * @return List(팝업 목록)
     * @throws Exception
     */
    List<?> selectPopupShowList(PopupVO paramVO) throws Exception;
}
