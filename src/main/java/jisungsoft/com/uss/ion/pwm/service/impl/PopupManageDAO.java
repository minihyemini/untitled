package jisungsoft.com.uss.ion.pwm.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("popupManageDAO")
public class PopupManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "PopupManageDAO";

    /**
     * 팝업 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(팝업 목록)
     */
    public List<?> selectPopupList(PopupVO paramVO)  throws Exception {
        return selectList(NAME_SPACE + ".selectPopupList", paramVO);
    }

    /**
     * 팝업 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(팝업 목록)
     */
    public List<?> selectPopupNPList(PopupVO paramVO)  throws Exception {
        return selectList(NAME_SPACE + ".selectPopupNPList", paramVO);
    }

    /**
     * 팝업 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectPopupListCnt(PopupVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectPopupListCnt", paramVO);
    }
    /**
     * 팝업 상세항목을 조회한다.
     * @param paramVO
     * @return PopupVO(팝업)
     */
    public PopupVO selectPopupDetail(PopupVO paramVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectPopupDetail", paramVO);
    }

    /**
     * 팝업을 등록한다.
     * @param popupVO
     * @throws Exception
     */
    public void insertPopup(PopupVO popupVO) throws Exception {
        insert(NAME_SPACE + ".insertPopup", popupVO);
    }

    /**
     * 팝업을 삭제한다.
     * @param popupVO
     * @throws Exception
     */
    public void deletePopup(PopupVO popupVO) throws Exception {
        delete(NAME_SPACE + ".deletePopup", popupVO);
    }

    /**
     * 팝업을 수정한다.
     * @param popupVO
     * @throws Exception
     */
    public void updatePopup(PopupVO popupVO) throws Exception{
        update(NAME_SPACE + ".updatePopup", popupVO);
    }

    /**
     * 팝업 목록을 조회한다.
     * @param paramVO
     * @return List(팝업 목록)
     */
    public List<?> selectPopupShowList(PopupVO paramVO)  throws Exception {
        return selectList(NAME_SPACE + ".selectPopupShowList", paramVO);
    }
}
