package jisungsoft.com.cop.bbs.service;

import java.util.List;

public interface ArticleDataService {
    /**
     * 게시글 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(게시글 목록)
     * @throws Exception
     */
    List<?> selectArticleList(ArticleDataVO paramVO) throws Exception;
    /**
     * 게시글 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시글 목록)
     * @throws Exception
     */
    List<?> selectArticleNPList(ArticleDataVO paramVO) throws Exception;
    /**
     * 게시글 공지사항 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(게시글 목록)
     * @throws Exception
     */
    List<?> selectNoticeArticleList(ArticleDataVO paramVO) throws Exception;
    /**
     * 답변 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(답변 목록)
     * @throws Exception
     */
    List<?> selectAnswerDetailList(ArticleDataVO paramVO) throws Exception;
    /**
     * 댓글 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(댓글 목록)
     * @throws Exception
     */
    List<?> selectCommentList(ArticleDataVO paramVO) throws Exception;
    /**
     * 댓글 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(댓글 목록)
     * @throws Exception
     */
    List<?> selectCommentNPList(ArticleDataVO paramVO) throws Exception;
    /**
     * 최근 댓글 목록을 조회한다. (Not Paging)
     * @param paramVO
     * @return List(댓글 목록)
     * @throws Exception
     */
    List<?> selectCommentCurList(ArticleDataVO paramVO) throws Exception;
    /**
     * 게시글 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectArticleListTotCnt(ArticleDataVO paramVO) throws Exception;
    /**
     * 게시글 댓글 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectCommentCnt(ArticleDataVO paramVO) throws Exception;
    /**
     * 게시글 목록을 상세조회한다.
     * @param paramVO
     * @return BbsMasterVO
     * @throws Exception
     */
    ArticleDataVO selectArticleDetail(ArticleDataVO paramVO) throws Exception;
    /**
     * 게시글 댓글 목록을 상세조회한다.
     * @param paramVO
     * @return BbsMasterVO
     * @throws Exception
     */
    CommentVO selectReplyDetail(CommentVO paramVO) throws Exception;
    /**
     * 게시글을 등록한다.
     * @param articleDataVO
     * @throws Exception
     */
    void insertArticle(ArticleDataVO articleDataVO) throws Exception;
    /**
     * 게시글 답글을 등록한다.
     * @param articleDataVO
     * @throws Exception
     */
    void insertAnswer(ArticleDataVO articleDataVO) throws Exception;
    /**
     * 게시글을 삭제한다.
     * @param articleDataVO
     * @throws Exception
     */
    void deleteArticle(ArticleDataVO articleDataVO) throws Exception;
    /**
     * 게시글을 수정한다.
     * @param articleDataVO
     * @throws Exception
     */
    void updateArticle(ArticleDataVO articleDataVO) throws Exception;
    /**
     * 조회수 증가 처리
     * @param articleDataVO
     * @throws Exception
     */
    void updateRdcnt(ArticleDataVO articleDataVO) throws Exception;
    /**
     * 게시글 댓글을 등록한다.
     * @param commentVO
     * @throws Exception
     */
    void insertReply(CommentVO commentVO) throws Exception;
    /**
     * 게시글 댓글을 수정한다.
     * @param commentVO
     * @throws Exception
     */
    void updateReply(CommentVO commentVO) throws Exception;
    /**
     * 게시글 댓글을 삭제한다.
     * @param commentVO
     * @throws Exception
     */
    void deleteReply(CommentVO commentVO) throws Exception;

    /**
     * 게시판 열람자 목록
     * @param bbsViewerLogVO
     * @return
     */
    public List<?> selectBbsViewerList(BbsViewerLogVO bbsViewerLogVO) throws Exception;

    /**
     * 게시판 열람자 목록 카운트
     * @param bbsViewerLogVO
     * @return
     * @throws Exception
     */
    public int selectBbsViewerListCnt(BbsViewerLogVO bbsViewerLogVO) throws Exception;

    /**
     * 게시판 열람자 상세
     * @param bbsViewerLogVO
     * @return
     */
    public BbsViewerLogVO selectBbsViewerDetail(BbsViewerLogVO bbsViewerLogVO) throws Exception;

    /**
     * 게시판 열람자 추가
     * @param bbsViewerLogVO
     */
    public void insertBbsViewer(BbsViewerLogVO bbsViewerLogVO) throws Exception;

    /**
     * 게시판 열람자 수정
     * @param bbsViewerLogVO
     */
    public void updateBbsViewer(BbsViewerLogVO bbsViewerLogVO) throws Exception;

    /**
     * 게시판 열람자 삭제
     * @param bbsViewerLogVO
     */
    public void deleteBbsViewer(BbsViewerLogVO bbsViewerLogVO) throws Exception;

    /**
     * 게시판 열람자 권한 목록
     * @param bbsViewerAuthVO
     * @return
     */
    public List<?> selectBbsViewerAuthList(BbsViewerAuthVO bbsViewerAuthVO) throws Exception;

    /**
     * 게시판 열람자 권한 추가
     * @param bbsViewerAuthVO
     */
    public void insertBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) throws Exception;

    /**
     * 게시판 열람자 권한 수정
     * @param bbsViewerAuthVO
     */
    public void updateBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) throws Exception;

    /**
     * 게시판 열람자 권한삭제
     * @param bbsViewerAuthVO
     */
    public void deleteBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) throws Exception;

    /**
     * 게시판 열람자 권한 삭제 BY NTT_ID
     * @param bbsViewerAuthVO
     * @throws Exception
     */
    public void deleteBbsViewerAuthByNttId(BbsViewerAuthVO bbsViewerAuthVO) throws Exception;

}
