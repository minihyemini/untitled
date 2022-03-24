package jisungsoft.com.cop.bbs.service.impl;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cop.bbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static jisungsoft.com.cmm.util.CommonUtil.removeTag;
import static jisungsoft.com.cmm.util.CommonUtil.unscript;

@Service("articleDataService")
public class ArticleDataServiceImpl extends EgovAbstractServiceImpl implements ArticleDataService {

    @Resource(name="articleDataDAO")
    private ArticleDataDAO articleDataDAO;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService nttIdgenService;

	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService hstrIdgenService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	/**
	 * 게시글 목록을 조회한다. (Paging)
	 */
	@Override
	public List<?> selectArticleList(ArticleDataVO paramVO) {
		return articleDataDAO.selectArticleList(paramVO);
	}
	/**
	 * 게시글 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectArticleNPList(ArticleDataVO paramVO) {
		return articleDataDAO.selectArticleNPList(paramVO);
	}
	/**
	 * 게시글 공지사항 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectNoticeArticleList(ArticleDataVO paramVO) {
		return articleDataDAO.selectNoticeArticleList(paramVO);
	}
	/**
	 * 답글 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectAnswerDetailList(ArticleDataVO paramVO) {
		return articleDataDAO.selectAnswerDetailList(paramVO);
	}
	/**
	 * 댓글 목록을 조회한다. (Paging)
	 */
	@Override
	public List<?> selectCommentList(ArticleDataVO paramVO) {
		return articleDataDAO.selectCommentList(paramVO);
	}
	/**
	 * 댓글 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectCommentNPList(ArticleDataVO paramVO) {
		return articleDataDAO.selectCommentNPList(paramVO);
	}
	/**
	 * 최근 댓글 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<?> selectCommentCurList(ArticleDataVO paramVO) {
		return articleDataDAO.selectCommentCurList(paramVO);
	}
	/**
	 * 게시글 건수를 조회한다.
	 */
	@Override
	public int selectArticleListTotCnt(ArticleDataVO paramVO) {
		return articleDataDAO.selectArticleListTotCnt(paramVO);
	}
	/**
	 * 게시글 댓글 건수를 조회한다.
	 */
	@Override
	public int selectCommentCnt(ArticleDataVO paramVO) {
		return articleDataDAO.selectCommentCnt(paramVO);
	}
	/**
	 * 게시글 목록을 상세조회한다.
	 */
	@Override
	public ArticleDataVO selectArticleDetail(ArticleDataVO paramVO) throws Exception {
		return articleDataDAO.selectArticleDetail(paramVO);
	}
	/**
	 * 게시글 댓글목록을 상세조회한다.
	 */
	@Override
	public CommentVO selectReplyDetail(CommentVO paramVO) {
		return articleDataDAO.selectReplyDetail(paramVO);
	}
	/**
	 * 게시글을 등록한다.
	 */
	@Override
	public void insertArticle(ArticleDataVO articleDataVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		articleDataVO.setNtcrId(user.getUniqId());
		articleDataVO.setNtcrNm(user.getName());
		articleDataVO.setFrstRegisterId(user.getUniqId());
		articleDataVO.setNttCn(unscript(articleDataVO.getNttCn()));

		if ("Y".equals(articleDataVO.getAnswerAt())) {
			articleDataVO.setAnswerLc(articleDataVO.getAnswerLc()+1);
			articleDataVO.setNttId(nttIdgenService.getNextIntegerId());	// 답글에 대한 nttId 생성
			articleDataVO.setNttCnLookup(removeTag(articleDataVO.getNttCn()));
			articleDataDAO.insertAnswer(articleDataVO);

		} else {
			articleDataVO.setAnswerLc(0);
			articleDataVO.setAnswerAt("N");
			articleDataVO.setUseAt("Y");
			articleDataVO.setNttCnLookup(removeTag(articleDataVO.getNttCn()));
			articleDataVO.setNttId(nttIdgenService.getNextIntegerId());

			articleDataDAO.insertArticle(articleDataVO);
		}
	}
	/**
	 * 게시글 답글을 등록한다.
	 */
	@Override
	public void insertAnswer(ArticleDataVO articleDataVO) throws Exception {
		articleDataDAO.insertAnswer(articleDataVO);
	}
	/**
	 * 게시글을 삭제한다.
	 */
	@Override
	public void deleteArticle(ArticleDataVO articleDataVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		articleDataVO.setLastUpdusrId(user.getUniqId());

		articleDataDAO.deleteArticle(articleDataVO);
	}
	/**
	 * 게시글을 수정한다.
	 */
	@Override
	public void updateArticle(ArticleDataVO articleDataVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		articleDataVO.setLastUpdusrId(user.getUniqId());
		articleDataVO.setNttCn(unscript(articleDataVO.getNttCn()));
		articleDataVO.setNttCnLookup(removeTag(articleDataVO.getNttCn()));

		articleDataDAO.updateArticle(articleDataVO);
	}
	/**
	 * 게시글 조회수 증가
	 */
	@Override
	public void updateRdcnt(ArticleDataVO articleDataVO) throws Exception {

		if (articleDataVO != null) {
			//조회수 증가 중복방지 쿠키추가
			Cookie[] cookies = request.getCookies();
			Cookie viewCookie = null;
			if (cookies != null && cookies.length > 0) {
				for (int j = 0; j < cookies.length; j++) {
					if (cookies[j].getName().equals("bbs"+articleDataVO.getNttId())) {
						viewCookie = cookies[j];
					}
				}
			}
			if (viewCookie == null) {
				Cookie newCookie = new Cookie("bbs"+articleDataVO.getNttId(), "|"+articleDataVO.getNttId()+"|");
				response.addCookie(newCookie);

				//조회수 증가
				articleDataDAO.updateRdcnt(articleDataVO);
			}
		}
	}

	/**
	 * 게시글 댓글을 등록한다.
	 */
	@Override
	public void insertReply(CommentVO commentVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		commentVO.setPassword((user == null || user.getName() == null) ? "" : user.getPassword());

		articleDataDAO.insertReply(commentVO);
	}
	/**
	 * 게시글 댓글을 수정한다.
	 */
	@Override
	public void updateReply(CommentVO commentVO) throws Exception {
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user     = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		commentVO.setPassword((user == null || user.getName() == null) ? "" : user.getPassword());

		articleDataDAO.updateReply(commentVO);
	}
	/**
	 * 게시글 댓글을 삭제한다.
	 */
	@Override
	public void deleteReply(CommentVO commentVO) throws Exception {
		articleDataDAO.deleteReply(commentVO);
	}

	@Override
	public List<?> selectBbsViewerList(BbsViewerLogVO bbsViewerLogVO) throws Exception {
		return articleDataDAO.selectBbsViewerList(bbsViewerLogVO);
	}

	@Override
	public int selectBbsViewerListCnt(BbsViewerLogVO bbsViewerLogVO) throws Exception {
		return articleDataDAO.selectBbsViewerListCnt(bbsViewerLogVO);
	}

	@Override
	public BbsViewerLogVO selectBbsViewerDetail(BbsViewerLogVO bbsViewerLogVO) throws Exception {
		return articleDataDAO.selectBbsViewerDetail(bbsViewerLogVO);
	}

	@Override
	public void insertBbsViewer(BbsViewerLogVO bbsViewerLogVO) throws Exception {
		articleDataDAO.insertBbsViewer(bbsViewerLogVO);
	}

	@Override
	public void updateBbsViewer(BbsViewerLogVO bbsViewerLogVO) throws Exception {
		articleDataDAO.updateBbsViewer(bbsViewerLogVO);
	}

	@Override
	public void deleteBbsViewer(BbsViewerLogVO bbsViewerLogVO) throws Exception {
		articleDataDAO.deleteBbsViewer(bbsViewerLogVO);
	}

	@Override
	public List<?> selectBbsViewerAuthList(BbsViewerAuthVO bbsViewerAuthVO) throws Exception {
		return articleDataDAO.selectBbsViewerAuthList(bbsViewerAuthVO);
	}

	@Override
	public void insertBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) throws Exception {
		articleDataDAO.insertBbsViewerAuth(bbsViewerAuthVO);
	}

	@Override
	public void updateBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) throws Exception {
		articleDataDAO.updateBbsViewerAuth(bbsViewerAuthVO);
	}

	@Override
	public void deleteBbsViewerAuth(BbsViewerAuthVO bbsViewerAuthVO) throws Exception {
		articleDataDAO.deleteBbsViewerAuth(bbsViewerAuthVO);
	}

	@Override
	public void deleteBbsViewerAuthByNttId(BbsViewerAuthVO bbsViewerAuthVO) throws Exception {
		articleDataDAO.deleteBbsViewerAuthByNttId(bbsViewerAuthVO);
	}
}
