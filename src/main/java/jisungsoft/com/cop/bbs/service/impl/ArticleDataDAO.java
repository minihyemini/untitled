package jisungsoft.com.cop.bbs.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.bbs.service.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("articleDataDAO")
public class ArticleDataDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "ArticleDataDAO";

    /**
     * 게시글 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(게시글 목록)
     */
    public List<?> selectArticleList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectArticleList", paramVO);
    }
    /**
     * 게시글 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시글 목록)
     */
    public List<?> selectArticleNPList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectArticleNPList", paramVO);
    }
    /**
     * 게시글 공지사항 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시글 공지사항 목록)
     */
    public List<?> selectNoticeArticleList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectNoticeArticleList", paramVO);
    }
    /**
     * 게시글 답글 목록을 조회한다. (Not Paging)
     * @param paramVO -- 질문param
     * @return List(답글 목록)
     */
    public List<?> selectAnswerDetailList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectAnswerDetailList", paramVO);
    }
    /**
     * 댓글 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(댓글 목록)
     */
    public List<?> selectCommentList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectCommentList", paramVO);
    }
    /**
     * 댓글 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(댓글 목록)
     */
    public List<?> selectCommentNPList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectCommentNPList", paramVO);
    }
    /**
     * 최근 댓글 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(댓글 목록)
     */
    public List<?> selectCommentCurList(ArticleDataVO paramVO) {
        return selectList(NAME_SPACE + ".selectCommentCurList", paramVO);
    }
    /**
     * 게시글 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectArticleListTotCnt(ArticleDataVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectArticleListTotCnt", paramVO);
    }
    /**
     * 게시글 댓글 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectCommentCnt(ArticleDataVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectCommentListTotCnt", paramVO);
    }
    /**
     * 게시글 목록을 상세조회한다.
     * @param paramVO
     * @return ArticleDataVO
     */
    public ArticleDataVO selectArticleDetail(ArticleDataVO paramVO) {
        return selectOne(NAME_SPACE + ".selectArticleDetail", paramVO);
    }
    /**
     * 게시글 댓글 목록을 상세조회한다.
     * @param paramVO
     * @return ArticleDataVO
     */
    public CommentVO selectReplyDetail(CommentVO paramVO) {
        return selectOne(NAME_SPACE + ".selectReplyDetail", paramVO);
    }
    /**
     * 게시글을 등록한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void insertArticle(ArticleDataVO articleDataVO) throws Exception {
        insert(NAME_SPACE + ".insertArticle", articleDataVO);
    }
    /**
     * 게시글 답글을 등록한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void insertAnswer(ArticleDataVO articleDataVO) throws Exception {
        insert(NAME_SPACE + ".insertAnswer", articleDataVO);
    }
    /**
     * 게시글을 삭제한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void deleteArticle(ArticleDataVO articleDataVO) throws Exception {
        delete(NAME_SPACE + ".deleteArticle", articleDataVO);

    }
    /**
     * 게시글을 수정한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void updateArticle(ArticleDataVO articleDataVO) throws Exception{
        update(NAME_SPACE + ".updateArticle", articleDataVO);
    }
    /**
     * 게시글 댓글을 등록한다.
     * @param commentVO
     * @throws Exception
     */
    public void insertReply(CommentVO commentVO) throws Exception {
        insert(NAME_SPACE + ".insertReply", commentVO);
    }
    /**
     * 게시글 댓글을 삭제한다.
     * @param commentVO
     * @throws Exception
     */
    public void deleteReply(CommentVO commentVO) throws Exception {
        delete(NAME_SPACE + ".deleteReply", commentVO);
    }
    /**
     * 게시글 댓글을 수정한다.
     * @param commentVO
     * @throws Exception
     */
    public void updateReply(CommentVO commentVO) throws Exception{
        update(NAME_SPACE + ".updateReply", commentVO);
    }
    /**
     * 세미나 정보를 등록한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void insertSeminar(ArticleDataVO articleDataVO) throws Exception {
        insert(NAME_SPACE + ".insertSeminar", articleDataVO);
    }
    /**
     * 세미나 정보를 수정한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void updateSeminar(ArticleDataVO articleDataVO) throws Exception{
        update(NAME_SPACE + ".updateSeminar", articleDataVO);
    }
    /**
     * 세미나 정보를 삭제한다.
     * @param articleDataVO
     * @throws Exception
     */
    public void deleteSeminar(ArticleDataVO articleDataVO) throws Exception {
        delete(NAME_SPACE + ".deleteSeminar", articleDataVO);
    }
    /**
     * 게시글 조회수 count
     * @param articleDataVO
     * @throws Exception
     */
    public void updateRdcnt(ArticleDataVO articleDataVO) throws Exception{
        update(NAME_SPACE + ".updateRdcnt", articleDataVO);
    }

    /**
     * 게시판 열람자 목록
     * @param bbsViewerLogVO
     * @return
     */
    public List<?> selectBbsViewerList(BbsViewerLogVO bbsViewerLogVO) {
        return selectList(NAME_SPACE + ".selectBbsViewerList", bbsViewerLogVO);
    }

    /**
     * 게시판 열람자 목록 카운트
     * @param bbsViewerLogVO
     * @return
     */
    public int selectBbsViewerListCnt(BbsViewerLogVO bbsViewerLogVO) {
        return selectOne(NAME_SPACE + ".selectBbsViewerListCnt", bbsViewerLogVO);
    }

    /**
     * 게시판 열람자 상세
     * @param bbsViewerLogVO
     * @return
     */
    public BbsViewerLogVO selectBbsViewerDetail(BbsViewerLogVO bbsViewerLogVO) {
        return selectOne(NAME_SPACE + ".", bbsViewerLogVO);
    }

    /**
     * 게시판 열람자 추가
     * @param bbsViewerLogVO
     */
    public void insertBbsViewer(BbsViewerLogVO bbsViewerLogVO) {
        insert(NAME_SPACE + ".insertBbsViewer", bbsViewerLogVO);
    }

    /**
     * 게시판 열람자 수정
     * @param bbsViewerLogVO
     */
    public void updateBbsViewer(BbsViewerLogVO bbsViewerLogVO) {
        insert(NAME_SPACE + ".updateBbsViewer", bbsViewerLogVO);
    }

    /**
     * 게시판 열람자 삭제
     * @param bbsViewerLogVO
     */
    public void deleteBbsViewer(BbsViewerLogVO bbsViewerLogVO) {
        insert(NAME_SPACE + ".deleteBbsViewer", bbsViewerLogVO);
    }

    /**
     * 게시판 열람자 권한 목록
     * @param bbsViewerAuthVO
     * @return
     */
    public List<?> selectBbsViewerAuthList(BbsViewerAuthVO bbsViewerAuthVO) {
        return selectList(NAME_SPACE + ".selectBbsViewerAuthList", bbsViewerAuthVO);
    }

    /**
     * 게시판 열람자 권한 추가
     * @param bbsViewerAuthVO
     */
    public void insertBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) {
        insert(NAME_SPACE + ".insertBbsViewerAuth", bbsViewerAuthVO);
    }

    /**
     * 게시판 열람자 권한 수정
     * @param bbsViewerAuthVO
     */
    public void updateBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) {
        insert(NAME_SPACE + ".updateBbsViewerAuth", bbsViewerAuthVO);
    }

    /**
     * 게시판 열람자  권한삭제
     * @param bbsViewerAuthVO
     */
    public void deleteBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) {
        insert(NAME_SPACE + ".deleteBbsViewerAuth", bbsViewerAuthVO);
    }

    /**
     * 게시판 열람자  권한삭제
     * @param bbsViewerAuthVO
     */
    public void deleteBbsViewerAuthByNttId(BbsViewerAuthVO bbsViewerAuthVO) {
        insert(NAME_SPACE + ".deleteBbsViewerAuthByNttId", bbsViewerAuthVO);
    }
}
