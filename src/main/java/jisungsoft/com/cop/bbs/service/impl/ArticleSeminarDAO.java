package jisungsoft.com.cop.bbs.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.bbs.service.ArticleSeminarVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("articleSeminarDAO")
public class ArticleSeminarDAO extends EgovComAbstractDAO {
    private final String NAME_SPACE = "Seminar";

    /**
     * 세미나목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(세미나 목록)
     */
    public List<?> selectSeminarList(ArticleSeminarVO paramVO) {
        return selectList(NAME_SPACE + ".selectSeminarList", paramVO);
    }

    /**
     * 세미나 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectSeminarListCnt(ArticleSeminarVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectSeminarListCnt", paramVO);
    }

    /**
     * 세미나를 상세조회한다.
     * @param paramVO
     * @return ArticleSeminarVO
     */
    public ArticleSeminarVO selectSeminarDetail(ArticleSeminarVO paramVO) {
        return selectOne(NAME_SPACE + ".selectSeminarDetail", paramVO);
    }

    /**
     * 세미나를 등록한다.
     * @param paramVO
     * @throws Exception
     */
    public void insertSeminar(ArticleSeminarVO paramVO) throws Exception {
        insert(NAME_SPACE + ".insertSeminar", paramVO);
    }

    /**
     * 세미나 멤버를 등록한다.
     * @param paramVO
     * @throws Exception
     */
    public void insertSeminarMber(ArticleSeminarVO paramVO) throws Exception {
        insert(NAME_SPACE + ".insertSeminarMber", paramVO);
    }

    /**
     * 세미나를 수정한다.
     * @param paramVO
     * @throws Exception
     */
    public void updateSeminar(ArticleSeminarVO paramVO) throws Exception {
        update(NAME_SPACE + ".updateSeminar", paramVO);
    }

    /**
     * 세미나를 삭제한다.
     * @param paramVO
     * @throws Exception
     */
    public void deleteSeminar(ArticleSeminarVO paramVO) throws Exception {
        delete(NAME_SPACE + ".deleteSeminar", paramVO);
    }

    /**
     * 세미나 신청자 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(세미나 목록)
     */
    public List<?> selectSeminarMberList(ArticleSeminarVO paramVO) {
        return selectList(NAME_SPACE + ".selectSeminarMberList", paramVO);
    }

    /**
     * 세미나 신청여부를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectSeminarMberCnf(ArticleSeminarVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectSeminarMberCnf", paramVO);
    }

    /**
     * 세미나 신청건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectSeminarMberCnt(ArticleSeminarVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectSeminarMberCnt", paramVO);
    }

    /**
     * 세미나 신청을 취소한다.
     * @param paramVO
     * @throws Exception
     */
    public void deleteSeminarMber(ArticleSeminarVO paramVO) {
        delete(NAME_SPACE + ".deleteSeminarMber", paramVO);
    }
}
