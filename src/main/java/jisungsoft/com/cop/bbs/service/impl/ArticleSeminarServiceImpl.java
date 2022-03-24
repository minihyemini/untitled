package jisungsoft.com.cop.bbs.service.impl;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cop.bbs.service.ArticleSeminarService;
import jisungsoft.com.cop.bbs.service.ArticleSeminarVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("articleSeminarService")
public class ArticleSeminarServiceImpl extends EgovAbstractServiceImpl implements ArticleSeminarService {

    /* 세미나 dao */
    @Resource(name="articleSeminarDAO")
    private ArticleSeminarDAO articleSeminarDAO;

    /* 세미나 idgen */
    @Resource(name = "egovSeIdGnrService")
    private EgovIdGnrService idgenService;

    /* 세미나 idgen */
    @Resource(name = "egovSmberIdGnrService")
    private EgovIdGnrService idgenSmberService;

    /**
     * 세미나 목록을 조회한다. (Paging)
     */
    @Override
    public List<?> selectSeminarList(ArticleSeminarVO paramVO) {
        return articleSeminarDAO.selectSeminarList(paramVO);
    }

    /**
     * 세미나 건수를 조회한다.
     */
    @Override
    public int selectSeminarListCnt(ArticleSeminarVO paramVO) {
        return articleSeminarDAO.selectSeminarListCnt(paramVO);
    }

    /**
     * 세미나를 상세조회한다.
     */
    @Override
    public ArticleSeminarVO selectSeminarDetail(ArticleSeminarVO paramVO) throws Exception {
        return articleSeminarDAO.selectSeminarDetail(paramVO);
    }

    /**
     * 세미나를 등록한다.
     */
    @Override
    public void insertSeminar(ArticleSeminarVO paramVO) throws Exception {
        LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        paramVO.setEsntlId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        String seId      = idgenService.getNextStringId();

        paramVO.setSeId(seId);

        articleSeminarDAO.insertSeminar(paramVO);

    }

    /**
     * 세미나를 수정한다.
     */
    @Override
    public void updateSeminar(ArticleSeminarVO paramVO) throws Exception {
        articleSeminarDAO.updateSeminar(paramVO);
    }

    /**
     * 세미나를 삭제한다.
     */
    @Override
    public void deleteSeminar (ArticleSeminarVO paramVO) throws Exception {
        articleSeminarDAO.deleteSeminar(paramVO);
    }

    /**
     * 세미나 신청자 목록을 조회한다. (Paging)
     */
    @Override
    public List<?> selectSeminarMberList(ArticleSeminarVO paramVO) {
        return articleSeminarDAO.selectSeminarMberList(paramVO);
    }

    /**
     * 세미나 멤버를 등록한다.
     */
    @Override
    public void insertSeminarMber(ArticleSeminarVO paramVO) throws Exception {
        LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        paramVO.setEsntlId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        String smberId   = idgenSmberService.getNextStringId();

        paramVO.setSmberId(smberId);

        articleSeminarDAO.insertSeminarMber(paramVO);
    }

    /**
     * 세미나 신청여부를 조회한다.
     */
    @Override
    public int selectSeminarMberCnf(ArticleSeminarVO paramVO) {
        return articleSeminarDAO.selectSeminarMberCnf(paramVO);
    }

    /**
     * 세미나 신청건수를 조회한다.
     */
    @Override
    public int selectSeminarMberCnt(ArticleSeminarVO paramVO) {
        return articleSeminarDAO.selectSeminarMberCnt(paramVO);
    }

    /**
     * 세미나 신청을 취소한다.
     */
    public void deleteSeminarMber(ArticleSeminarVO paramVO) {
        articleSeminarDAO.deleteSeminarMber(paramVO);
    }
}
