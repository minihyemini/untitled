package jisungsoft.com.cmm.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cmm.service.CmmDetailCode;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmGroup;
import jisungsoft.com.cop.bbs.service.ArticleDataVO;
import jisungsoft.com.sym.prm.service.ProgramManageVO;
import jisungsoft.com.uss.ion.ntm.service.NoteVO;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cmmUseDAO")
public class CmmUseDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "cmmUseDAO";

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmCodeDetail(DefaultCodeVO vo) throws Exception {
        return selectList(NAME_SPACE + ".selectCmmCodeDetail", vo);
    }

    /**
     * 공통상세코드 CODE_ID로 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmCodeDetailByCodeIdList(DefaultCodeVO vo) throws Exception {
        return selectList(NAME_SPACE + ".selectCmmCodeDetailByCodeIdList", vo);
    }

    /**
     * 공통상세코드 PK CODE로 상세조회
     * @param vo
     * @return
     * @throws Exception
     */
    public DefaultCodeVO selectCmmCodeDetailByCode(DefaultCodeVO vo) throws Exception {
        return selectOne(NAME_SPACE + ".selectCmmCodeDetailByCode", vo);
    }

    /**
     * 공통코드 CL_CODE로 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmCodeByClCodeList(DefaultCodeVO vo) throws Exception {
        return selectList(NAME_SPACE + ".selectCmmCodeByClCodeList", vo);
    }

    /**
     * 공통코드 PK CODE_ID로 상세 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public DefaultCodeVO selectCmmCodeByCodeId(DefaultCodeVO vo) throws Exception {
        return selectOne(NAME_SPACE + ".selectCmmCodeByCodeId", vo);
    }

    /**
     * 공통분류코드 목록 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> selectCmmClCodeList(DefaultCodeVO vo) throws Exception {
        return selectList(NAME_SPACE + ".selectCmmClCodeList", vo);
    }

    /**
     * 공통분류코드 CL_CODE로 상세 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public DefaultCodeVO selectCmmClCodeByClCode(DefaultCodeVO vo) throws Exception {
        return selectOne(NAME_SPACE + ".selectCmmClCodeByClCode", vo);
    }

    /**
     * 공통코드로 사용할 조직정보를 를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<CmmDetailCode> selectOgrnztIdDetail(DefaultCodeVO vo) throws Exception {
        return selectList(NAME_SPACE + ".selectOgrnztIdDetail", vo);
    }

    /**
     * 공통코드로 사용할그룹정보를 를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> selectCmmGroupList(CmmGroup vo) throws Exception {
        return selectList(NAME_SPACE + ".selectCmmGroupList", vo);
    }

    /**
     * 공통 사용자 정보 목록 조회(Not paging)
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public List<?> selectUserViewListAll(CmmDetailUser cmmDetailUser) throws Exception {
        return selectList(NAME_SPACE + ".selectUserViewListAll", cmmDetailUser);
    }

    /**
     * 공통 사용자 정보 상세 조회 by esntlId
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public CmmDetailUser selectUserViewDetail(CmmDetailUser cmmDetailUser) throws Exception {
        return selectOne(NAME_SPACE + ".selectUserViewDetail", cmmDetailUser);
    }

    /**
     * 공통 사용자 정보 목록 조회
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public List<?> selectUserViewList(CmmDetailUser cmmDetailUser) throws Exception {
        return selectList(NAME_SPACE + ".selectUserViewList", cmmDetailUser);
    }

    /**
     * 공통 사용자 정보 목록 조회(Not paging)
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public List<?> selectUserViewNPList(CmmDetailUser cmmDetailUser) throws Exception {
        return selectList(NAME_SPACE + ".selectUserViewNPList", cmmDetailUser);
    }

    /**
     * 공통 사용자 정보 목록 건수
     * @param cmmDetailUser
     * @return
     * @throws Exception
     */
    public int selectUserViewTotCnt(CmmDetailUser cmmDetailUser) throws Exception {
        return selectOne(NAME_SPACE + ".selectUserViewTotCnt", cmmDetailUser);
    }

    /**
     * 공통 메뉴 목록
     * @param menuManageVO
     * @return
     * @throws Exception
     */
    /*
    public List<MenuManageVO> selectMenuDataList(MenuManageVO menuManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectMenuDataList", menuManageVO);
    }
*/
    /**
     * 메뉴 상세 정보
     * @param menuManageVO
     * @return
     * @throws Exception
     */
    /*
    public MenuManageVO selectMenuDataDetail(MenuManageVO menuManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectMenuDataDetail", menuManageVO);
    }
*/
    /**
     * 메뉴 프로그램 상세 정보
     * @param programManageVO
     * @return
     * @throws Exception
     */
    public ProgramManageVO selectMenuProgramDetail(ProgramManageVO programManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectMenuProgramDetail", programManageVO);
    }

    /**
     * 권한 목록
     * @return
     * @throws Exception
     */
    public List<?> selectAuthDataList() throws Exception {
        return selectList(NAME_SPACE + ".selectAuthDataList");
    }

    /**
     * 메뉴 권한정보 목록
     * @return
     * @throws Exception
     */
    public List<?> selectMenuCreatDtls() throws Exception {
        return selectList(NAME_SPACE + ".selectMenuCreatDtls");
    }

    /**
     * 팝업 목록
     * @return
     * @throws Exception
     */
    public List<PopupVO> selectPopupList(PopupVO popupVO) throws Exception {
        return selectList(NAME_SPACE + ".selectPopupShowList", popupVO);
    }

    /**
     * 팝업 목록 건수
     * @param popupVO
     * @return
     * @throws Exception
     */
    public int selectPopupListCnt(PopupVO popupVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectPopupShowListCnt", popupVO);
    }

    /**
     * 받은쪽지 목록
     * @return
     * @throws Exception
     */
    public List<NoteVO> selectNoteList(NoteVO noteVO) throws Exception {
        return selectList(NAME_SPACE + ".selectNoteList", noteVO);
    }

    /**
     * 받은쪽지 건수
     * @param noteVO
     * @return
     * @throws Exception
     */
    public int selectNoteListCnt(NoteVO noteVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectNoteListCnt", noteVO);
    }

    /**
     * 전체 게시글 목록 조회
     * @param articleDataVO
     * @return
     * @throws Exception
     */
    public List<?> selectCmmBbsArticleAllList(ArticleDataVO articleDataVO) throws Exception {
        return selectList(NAME_SPACE + ".selectCmmBbsArticleAllList", articleDataVO);
    }
}
