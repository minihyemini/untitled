package jisungsoft.com.cop.bbs.service;

import java.util.List;

public interface ArticleSeminarService {

    /**
     * 세미나 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(세미나 목록)
     * @throws Exception
     */
    List<?> selectSeminarList(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectSeminarListCnt(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나를 상세조회한다.
     * @param paramVO
     * @return ArticleSeminarVO
     * @throws Exception
     */
    ArticleSeminarVO selectSeminarDetail(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나를 등록한다.
     * @param paramVO
     * @throws Exception
     */
    void insertSeminar(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나를 수정한다.
     * @param paramVO
     * @throws Exception
     */
    void updateSeminar(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나를 삭제한다.
     * @param paramVO
     * @throws Exception
     */
    void deleteSeminar(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나 신청자 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(세미나 목록)
     * @throws Exception
     */
    List<?> selectSeminarMberList(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나 멤버를 등록한다.
     * @param paramVO
     * @throws Exception
     */
    void insertSeminarMber(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나 신청여부를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectSeminarMberCnf(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나 신청건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectSeminarMberCnt(ArticleSeminarVO paramVO) throws Exception;

    /**
     * 세미나 신청을 취소한다.
     * @param paramVO
     * @throws Exception
     */
    void deleteSeminarMber(ArticleSeminarVO paramVO) throws Exception;
}
