package jisungsoft.com.cmm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cmm.service.CmmDetailCode;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmGroup;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cop.bbs.service.ArticleDataVO;
import jisungsoft.com.uss.ion.ntm.service.NoteVO;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("cmmUseService")
public class CmmUseServiceImpl extends EgovAbstractServiceImpl implements CmmUseService {

    @Resource(name = "cmmUseDAO")
    private CmmUseDAO cmmUseDAO;

    @Autowired
    private HttpServletRequest request;


    @Override
    public List<?> selectCmmCodeDetail(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmCodeDetail(vo);
    }

    @Override
    public List<?> selectCmmCodeDetailByCodeIdList(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmCodeDetailByCodeIdList(vo);
    }

    @Override
    public DefaultCodeVO selectCmmCodeDetailByCode(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmCodeDetailByCode(vo);
    }

    @Override
    public List<?> selectCmmCodeByClCodeList(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmCodeByClCodeList(vo);
    }

    @Override
    public DefaultCodeVO selectCmmCodeByCodeId(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmCodeByCodeId(vo);
    }

    @Override
    public List<?> selectCmmClCodeList(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmClCodeList(vo);
    }

    @Override
    public DefaultCodeVO selectCmmClCodeByClCode(DefaultCodeVO vo) throws Exception {
        return cmmUseDAO.selectCmmClCodeByClCode(vo);
    }

    @Override
    public Map<String, List<CmmDetailCode>> selectCmmCodeDetails(List<?> voList) throws Exception {
        DefaultCodeVO vo;
        Map<String, List<CmmDetailCode>> map = new HashMap<String, List<CmmDetailCode>>();

        Iterator<?> iter = voList.iterator();
        while (iter.hasNext()) {
            vo = (DefaultCodeVO)iter.next();
            map.put(vo.getCodeId(), (List<CmmDetailCode>) cmmUseDAO.selectCmmCodeDetail(vo));
        }

        return map;
    }

    @Override
    public List<?> selectCmmGroupList(CmmGroup vo) throws Exception {
        return cmmUseDAO.selectCmmGroupList(vo);
    }

    @Override
    public List<?> selectUserViewListAll(CmmDetailUser cmmDetailUser) throws Exception {
        return cmmUseDAO.selectUserViewListAll(cmmDetailUser);
    }

    @Override
    public CmmDetailUser selectUserViewDetail(CmmDetailUser cmmDetailUser) throws Exception {
        return cmmUseDAO.selectUserViewDetail(cmmDetailUser);
    }

    @Override
    public List<?> selectUserViewList(CmmDetailUser cmmDetailUser) throws Exception {
        return  cmmUseDAO.selectUserViewList(cmmDetailUser);
    }

    @Override
    public List<?> selectUserViewNPList(CmmDetailUser cmmDetailUser) throws Exception {
        return  cmmUseDAO.selectUserViewNPList(cmmDetailUser);
    }

    @Override
    public int selectUserViewTotCnt(CmmDetailUser cmmDetailUser) throws Exception {
        return cmmUseDAO.selectUserViewTotCnt(cmmDetailUser);
    }
/*

    @Override
    public List<MenuManageVO> selectMenuDataList(MenuManageVO menuManageVO) throws Exception {
        List<MenuManageVO> menuList = cmmUseDAO.selectMenuDataList(menuManageVO);

        return menuList;
    }
*/
/*

    @Override
    public MenuManageVO selectMenuDataDetail(MenuManageVO menuManageVO) throws Exception {
        MenuManageVO menuData = cmmUseDAO.selectMenuDataDetail(menuManageVO);

        //메뉴 정보를 못찾을 경우 프로그램명 조회 후 등록
        if (menuData == null) {
            ProgramManageVO programManageVO = new ProgramManageVO();
            programManageVO.setUrl(menuManageVO.getUrl());
            programManageVO.setProgrmFileNm(menuManageVO.getProgrmFileNm());
            ProgramManageVO resultProgramData = cmmUseDAO.selectMenuProgramDetail(programManageVO);

            if (resultProgramData != null) {
                menuData = new MenuManageVO();
                menuData.setMenuNm(resultProgramData.getProgrmKoreanNm());
                menuData.setUrl(resultProgramData.getUrl());
            }
        }

        return menuData;
    }
*/

    @Override
    public List<?> selectAuthDataList() throws Exception {
        return cmmUseDAO.selectAuthDataList();
    }

    @Override
    public List<?> selectMenuCreatDtls() throws Exception {
        return cmmUseDAO.selectMenuCreatDtls();
    }

    @Override
    public List<?> selectPopupList(PopupVO popupVO) throws Exception {
        Cookie[] cookies = request.getCookies();
        List<PopupVO> popList = cmmUseDAO.selectPopupList(popupVO);
        if (cookies != null) {
            for (PopupVO vo : popList) {
                for (int i=0; i<cookies.length; i++) {
                    if (vo.getPopupId().equals(cookies[i].getName())) {
                        if (cookies[i].getValue().equals("none")) {
                            vo.setNtceAt("N");
                        }
                    }
                }
            }
        }

        return popList;
    }

    @Override
    public int selectPopupListCnt(PopupVO popupVO) throws Exception {
        return cmmUseDAO.selectPopupListCnt(popupVO);
    }

    @Override
    public List<?> selectNoteList(NoteVO noteVO) throws Exception {
        return cmmUseDAO.selectNoteList(noteVO);
    }

    @Override
    public int selectNoteListCnt(NoteVO noteVO) throws Exception {
        return cmmUseDAO.selectNoteListCnt(noteVO);
    }

    @Override
    public List<?> selectCmmBbsArticleAllList(ArticleDataVO articleDataVO) throws Exception {
        return cmmUseDAO.selectCmmBbsArticleAllList(articleDataVO);
    }
}
