package jisungsoft.com.cop.bbs.web;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cop.bbs.service.ArticleDataService;
import jisungsoft.com.cop.bbs.service.ArticleDataVO;
import jisungsoft.com.cop.bbs.service.CommentVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static jisungsoft.com.cmm.util.CommonUtil.unscript;

//@RestController
//@RequestMapping("/{path}/cop/bbs")
public class ArticleDataRestController {

    /** Service */
//    @Resource(name = "articleDataService")
    private ArticleDataService articleDataService;

    /**
     * 게시글 목록 조회
     * @param commandMap 파라메터전달용 commandMap
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/article/list.json")
    public ModelAndView getArticleList(@RequestParam Map<String, Object> commandMap, ArticleDataVO articleDataVO) throws Exception
    {
        String bbsId           = (String) commandMap.get("bbsId");
        String searchCondition = (String) commandMap.get("searchCondition");
        String searchKeyword   = (String) commandMap.get("searchKeyword");
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(articleDataVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(articleDataVO.getPageUnit());
        paginationInfo.setPageSize(articleDataVO.getPageSize());

        articleDataVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        articleDataVO.setLastIndex(paginationInfo.getLastRecordIndex());
        articleDataVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        articleDataVO.setBbsId(bbsId);
        articleDataVO.setSearchCondition(searchCondition);
        articleDataVO.setSearchKeyword(searchKeyword);
        articleDataVO.setPathSplit("page");

        List<?> articleList = articleDataService.selectArticleList(articleDataVO);
        int articleCnt      = articleDataService.selectArticleListTotCnt(articleDataVO);
        paginationInfo.setTotalRecordCount(articleCnt);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("articleList"      , articleList);
        mav.addObject("articleCnt"       , articleCnt);
        mav.addObject("paginationInfoJs" , paginationInfo);

        return mav;
    }
    /**
     * 게시글 댓글 목록 조회
     * @param commandMap 파라메터전달용 commandMap
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/comment/list.json")
    public ModelAndView getCommentList(@RequestParam Map<String, Object> commandMap, ArticleDataVO articleDataVO) throws Exception
    {
        String nttId           = (String) commandMap.get("nttId");
        String bbsId           = (String) commandMap.get("bbsId");

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(articleDataVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(articleDataVO.getPageUnit());
        paginationInfo.setPageSize(articleDataVO.getPageSize());

        articleDataVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        articleDataVO.setLastIndex(paginationInfo.getLastRecordIndex());
        articleDataVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        articleDataVO.setBbsId(bbsId);
        articleDataVO.setNttId(Integer.parseInt(nttId));

        List<?> commentList = articleDataService.selectCommentList(articleDataVO);
        int commentCnt      = articleDataService.selectCommentCnt(articleDataVO);
        paginationInfo.setTotalRecordCount(commentCnt);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("commentList"      , commentList);
        mav.addObject("commentCnt"       , commentCnt);
        mav.addObject("paginationInfoJs" , paginationInfo);

        return mav;
    }
    /**
     * 게시글 댓글 등록
     * @param commentVO (form Data)
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/comment/insert.json")
    public void insertReply(CommentVO commentVO) throws Exception {
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        commentVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        commentVO.setWrterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        commentVO.setWrterNm((user == null || user.getName() == null) ? "" : user.getName());
        commentVO.setAnswer(unscript(commentVO.getAnswer()));
        articleDataService.insertReply(commentVO);
    }
    /**
     * 게시글 댓글 수정
     * @param commentVO (form Data)
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/comment/update.json")
    public void updateReply(CommentVO commentVO) throws Exception {
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        commentVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        commentVO.setWrterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        commentVO.setWrterNm((user == null || user.getName() == null) ? "" : user.getName());
        commentVO.setAnswer(unscript(commentVO.getAnswer()));
        articleDataService.updateReply(commentVO);
    }
    /**
     * 게시글 댓글 삭제
     * @param commentVO (form Data)
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/comment/delete.json")
    public void deleteReply(CommentVO commentVO) throws Exception {
        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        commentVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        articleDataService.deleteReply(commentVO);
    }
}