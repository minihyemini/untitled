package jisungsoft.com.cmm.service;

import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cop.bbs.service.ArticleDataVO;
import jisungsoft.com.uss.ion.ntm.service.NoteVO;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;

import java.util.List;
import java.util.Map;

public interface CmmUseService {

    /**
     * 공통코드를 조회한다.
     *
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List<?> selectCmmCodeDetail(DefaultCodeVO vo) throws Exception;

    /**
     * 공통상세코드 CODE_ID로 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmCodeDetailByCodeIdList(DefaultCodeVO vo) throws Exception;

    /**
     * 공통상세코드 PK CODE로 상세조회
     * @param vo
     * @return
     * @throws Exception
     */
    public DefaultCodeVO selectCmmCodeDetailByCode(DefaultCodeVO vo) throws Exception;

    /**
     * 공통코드 CL_CODE로 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmCodeByClCodeList(DefaultCodeVO vo) throws Exception;

    /**
     * 공통코드 PK CODE_ID로 상세 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public DefaultCodeVO selectCmmCodeByCodeId(DefaultCodeVO vo) throws Exception;

    /**
     * 공통분류코드 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmClCodeList(DefaultCodeVO vo) throws Exception;

    /**
     * 공통분류코드 CL_CODE로 상세 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public DefaultCodeVO selectCmmClCodeByClCode(DefaultCodeVO vo) throws Exception;

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서 리턴한다.
     *
     * @param voList
     * @return Map(코드)
     * @throws Exception
     */
    public Map<String, List<CmmDetailCode>> selectCmmCodeDetails(List<?> voList) throws Exception;

    /**
     * 그룹정보를 코드형태로 리턴한다.
     *
     * @param  vo
     * @return 그룹정보 List
     * @throws Exception
     */
    public List<?> selectCmmGroupList(CmmGroup vo) throws Exception;

    /**
     * 사용자(view)정보 목록 조회(Not paging)
     * 모바일번호 조회시 openMbtlnum == true
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public List<?> selectUserViewListAll(CmmDetailUser cmmDetailUser) throws Exception;

    /**
     * 사용자(view) 상세 조회
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public CmmDetailUser selectUserViewDetail(CmmDetailUser cmmDetailUser) throws Exception;

    /**
     * 사용자(view)정보 목록 조회
     * 모바일번호 조회시 openMbtlnum == true
     * @param  cmmDetailUser
     * @return List
     * @throws Exception
     */
    public List<?> selectUserViewList(CmmDetailUser cmmDetailUser) throws Exception;

    /**
     * 사용자(view)정보 목록 조회 (Not Paging)
     * @param  cmmDetailUser
     * @return List
     * @throws Exception
     */
    public List<?> selectUserViewNPList(CmmDetailUser cmmDetailUser) throws Exception;

    /**
     * 사용자(view)정보 건수
     * @param  cmmDetailUser
     * @return int
     * @throws Exception
     */
    public int selectUserViewTotCnt(CmmDetailUser cmmDetailUser) throws Exception;

    /**
     * 화면 메뉴 목록 정보
     * @param menuManageVO
     * @return
     * @throws Exception
     */
//    public List<MenuManageVO> selectMenuDataList(MenuManageVO menuManageVO) throws Exception;

    /**
     * 화면 메뉴 상세 정보
     * @param menuManageVO
     * @return
     * @throws Exception
     */
//    public MenuManageVO selectMenuDataDetail(MenuManageVO menuManageVO) throws Exception;

    /**
     * 권한 목록
     * @return
     * @throws Exception
     */
    public List<?> selectAuthDataList() throws Exception;

    /**
     * 메뉴 권한정보 목록
     * @return
     * @throws Exception
     */
    public List<?> selectMenuCreatDtls() throws Exception;

    /**
     * 팝업 목록
     * @return
     * @throws Exception
     */
    public List<?> selectPopupList(PopupVO popupVO) throws Exception;

    /**
     * 팝업 목록 건수
     * @param  popupVO
     * @return int
     * @throws Exception
     */
    public int selectPopupListCnt(PopupVO popupVO) throws Exception;

    /**
     * 받은 쪽지 목록
     * @return
     * @throws Exception
     */
    public List<?> selectNoteList(NoteVO noteVO) throws Exception;

    /**
     * 받은 쪽지 건수
     * @param  noteVO
     * @return int
     * @throws Exception
     */
    public int selectNoteListCnt(NoteVO noteVO) throws Exception;

    /**
     * 게시글 전체 목록 조회
     * @param articleDataVO
     * @return
     * @throws Exception
     */
    public List<?> selectCmmBbsArticleAllList(ArticleDataVO articleDataVO) throws Exception;
}
