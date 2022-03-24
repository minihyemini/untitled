package jisungsoft.com.cop.bbs.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.bbs.service.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *  Class Name  : ArticleDataController.java
 *  Description : 게시글 관리 Business class
 *  PAGE > 정보광장/커뮤니티, LMS > 강의관리 등에서 articleDataService도 참조하여 사용
 *  Menu : CMS > 게시판관리 > 게시글관리
 */
//@Controller
//@RequestMapping("/{path}/cop/bbs")
public class ArticleDataController {
    /** Service */
//    @Resource(name = "articleDataService")
    private ArticleDataService articleDataService;

    /** FileMngUtil */
//    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    /** File Service */
//    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    /** 게시판 Service */
//    @Resource(name = "bbsMasterService")
    private BbsMasterService bbsMasterService;

//    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    LoginVO authenticatedUser;
    private String redirectUri = "";

    @RequestMapping("/articleList.do")
    public String articleList(@PathVariable(value="path") String path, @ModelAttribute("articleDataVO") ArticleDataVO articleDataVO,
                              @RequestParam Map<String, Object> commandMap,
                              ModelMap model) throws Exception {
        List<?> bbsList = null;
        BbsMasterVO bbsMasterVO = new BbsMasterVO();
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        articleDataVO.setPathSplit(path);
        switch (path) {
            case "cms":
                bbsMasterVO.setUseAt("Y");
                bbsList = bbsMasterService.selectBbsMasterNPList(bbsMasterVO);
                if (bbsList.size() == 0) {
                    model.addAttribute("resultMsg", "error.bbs.found");
                    return "jisungsoft/com/script/alertBack";
                }
                model.addAttribute("bbsList", bbsList);
                break;
            case "page":
                articleDataVO.setBbsId(commandMap.get("bbsId").toString());

                BbsMasterAuthVO bbsMasterAuthVO = new BbsMasterAuthVO();
                bbsMasterAuthVO.setAuthorCode(AuthorityCode.ROLE_ANONYMOUS.name());
                if (isAuthenticated) {
                    bbsMasterAuthVO.setAuthorCode(authenticatedUser.getAuthority());
                }

                bbsMasterAuthVO.setBbsId(articleDataVO.getBbsId());
                bbsMasterAuthVO = bbsMasterService.selectBbsMasterAuthInfo(bbsMasterAuthVO);
                model.addAttribute("bbsMasterAuthVO", bbsMasterAuthVO);

                break;
        }
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(articleDataVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(articleDataVO.getPageUnit());
        paginationInfo.setPageSize(articleDataVO.getPageSize());

        articleDataVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        articleDataVO.setLastIndex(paginationInfo.getLastRecordIndex());
        articleDataVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        if(articleDataVO.getBbsId() != null) bbsMasterVO.setBbsId(articleDataVO.getBbsId());
        BbsMasterVO bbsData = bbsMasterService.selectBbsMasterDetail(bbsMasterVO);
        List<?> noticeList = articleDataService.selectNoticeArticleList(articleDataVO);
        List<?> resultList = articleDataService.selectArticleList(articleDataVO);
        paginationInfo.setTotalRecordCount(articleDataService.selectArticleListTotCnt(articleDataVO));

        model.addAttribute("articleDataVO", articleDataVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("bbsData", bbsData);
        model.addAttribute("paginationInfo", paginationInfo);
        return "jisungsoft/com/" + path + "/cop/bbs/article/articleList."+path;
    }

    @RequestMapping("/articleDetail.do")
    public String articleDetail(@PathVariable(value="path") String path, @ModelAttribute ArticleDataVO mainVO,
                                HttpServletRequest request, HttpServletResponse response,
                                ModelMap model) throws Exception
    {
        Boolean isLogin   = EgovUserDetailsHelper.isAuthenticated();
        //보안 취약점 처리
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        String sessionUniqId = null;

        BbsMasterVO bvo = new BbsMasterVO();
        bvo.setBbsId(mainVO.getBbsId());
        BbsMasterVO bbsMasterVO = bbsMasterService.selectBbsMasterDetail(bvo);
        ArticleDataVO resultData = articleDataService.selectArticleDetail(mainVO);

        BbsMasterAuthVO bbsMasterAuthVO = new BbsMasterAuthVO();
        bbsMasterAuthVO.setAuthorCode(AuthorityCode.ROLE_ANONYMOUS.name());

        if (isLogin) {
            sessionUniqId = authenticatedUser.getUniqId();
            bbsMasterAuthVO.setAuthorCode(authenticatedUser.getAuthority());

            if (resultData.getSecretAt().equals("Y")) {
                if (!authenticatedUser.getUniqId().equals(resultData.getFrstRegisterId())) {
                    if (!authenticatedUser.getAuthority().equals(AuthorityCode.ROLE_ADMIN.name()) && !authenticatedUser.getAuthority().equals(AuthorityCode.ROLE_MANAGER.name())) {
                        if(!resultData.getAnswerAuth().equals(authenticatedUser.getUniqId())) {
                            //관리자/운영자 예외 && 자신이 쓴 질문의 답변글 제외
                            model.addAttribute("resultMsg", "error.bbs.sessionId");
                            return "jisungsoft/com/script/alertBack";
                        }
                    }
                }
            }
        } else {
            //비밀글 체크
            if (resultData.getSecretAt().equals("Y")) {
                model.addAttribute("resultMsg", "error.bbs.sessionId");
                return "jisungsoft/com/script/alertBack";
            }
        }

        if(!bbsMasterVO.getBbsTyCode().equals("BBST04")) {  //커뮤니티 제외
            List<?> answerList = articleDataService.selectAnswerDetailList(mainVO);
            model.addAttribute("answerList", answerList);
        } else if (bbsMasterVO.getBbsTyCode().equals("BBST02")) {       //겔러리 게시판
            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(resultData.getThumbAtchFileId());
            List<FileVO> thumbFileList = fileMngService.selectImageFileList(fileVO);
            model.addAttribute("thumbFileList", thumbFileList);
        }

        //권한 조회
        bbsMasterAuthVO.setBbsId(mainVO.getBbsId());
        bbsMasterAuthVO = bbsMasterService.selectBbsMasterAuthInfo(bbsMasterAuthVO);

        FileVO fileVO = new FileVO();
        fileVO.setAtchFileId(resultData.getAtchFileId());
        List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

        //조회수 증가 중복방지 쿠키추가
        Cookie[] cookies = request.getCookies();
        Cookie viewCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (int j = 0; j < cookies.length; j++) {
                if (cookies[j].getName().equals("bbs"+resultData.getNttId())) {
                    viewCookie = cookies[j];
                }
            }
        }
        if (resultData != null) {
            if (viewCookie == null) {
                Cookie newCookie = new Cookie("bbs"+resultData.getNttId(), "|"+resultData.getNttId()+"|");
                response.addCookie(newCookie);

                //조회수 증가
                articleDataService.updateRdcnt(mainVO);
            }
        }

        model.addAttribute("bbsMasterVO", bbsMasterVO);
        model.addAttribute("bbsMasterAuthVO", bbsMasterAuthVO);
        model.addAttribute("commentVO", new CommentVO());
        model.addAttribute("articleDataVO", resultData);
        model.addAttribute("atchFileList", atchFileList);
        model.addAttribute("sessionUniqId", sessionUniqId);

        return "jisungsoft/com/" + path + "/cop/bbs/article/articleDetail."+path;
    }

    @RequestMapping("/articleForm.do")
    public String articleDataForm(@PathVariable("path") String path, @ModelAttribute ArticleDataVO mainVO,
                                  ModelMap model, @RequestParam(value = "mode") String mode) throws Exception
    {
        //보안 취약점 처리
        Boolean isLogin   = EgovUserDetailsHelper.isAuthenticated();
        if (!isLogin) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");
            return "jisungsoft/com/script/alertHref";
        }

        BbsMasterVO bbsMasterVO = new BbsMasterVO();
        bbsMasterVO.setBbsId(mainVO.getBbsId());
        bbsMasterVO = bbsMasterService.selectBbsMasterDetail(bbsMasterVO);
        model.addAttribute("bbsMasterVO", bbsMasterVO);

        switch (mode) {
            case "Insert":
                model.addAttribute("articleDataVO", mainVO);
                break;
            case "Answer":
                ArticleDataVO answerData = articleDataService.selectArticleDetail(mainVO);
                answerData.setAnswerAt("Y");
                model.addAttribute("resultVO", answerData);
                model.addAttribute("articleDataVO", new ArticleDataVO());
                break;
            case "Update":
                ArticleDataVO resultData = articleDataService.selectArticleDetail(mainVO);

                switch (bbsMasterVO.getBbsTyCode()) {
                    case "BBST02":
                        FileVO fileVO = new FileVO();
                        fileVO.setAtchFileId(resultData.getThumbAtchFileId());
                        List<FileVO> thumbFileList = fileMngService.selectImageFileList(fileVO);
                        model.addAttribute("thumbFileList", thumbFileList);
                        break;
                }

                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(resultData.getAtchFileId());
                List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

                model.addAttribute("commentVO", new CommentVO());
                model.addAttribute("articleDataVO", resultData);
                model.addAttribute("atchFileList", atchFileList);
                break;
        }
        return "jisungsoft/com/" + path + "/cop/bbs/article/articleForm."+path;
    }

    @RequestMapping(value = "/articleInsert.do", method = RequestMethod.POST)
    public String articleInsert(@ModelAttribute ArticleDataVO articleDataVO,
                                @PathVariable("path") String path,
                                @RequestParam Map<String, Object> commandMap,
                                MultipartHttpServletRequest multiRequest,
                                HttpServletRequest request,
                                ModelMap model) throws Exception
    {
        //보안 취약점 처리
        Boolean isLogin   = EgovUserDetailsHelper.isAuthenticated();
        if (!isLogin) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");
            return "jisungsoft/com/script/alertHref";
        }

        String bbsId     = articleDataVO.getBbsId();
        /* 질문자가 비밀글로 작성했을 경우, 답변글 비밀여부 체크함 */
        String parntSecret = (String) commandMap.get("parntSecret");
        String answerAt = articleDataVO.getAnswerAt();
        String secretAt = articleDataVO.getSecretAt() == null? "N" : articleDataVO.getSecretAt();
        if(answerAt.equals("Y")){
            if(!parntSecret.equals(secretAt)){
                model.addAttribute("resultMsg", "error.bbs.secret");
                return "jisungsoft/com/script/alertBack";
            }
        }

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        final List<MultipartFile> thumbFiles = multiRequest.getFiles("thumbAtchFile");
        List<FileVO> result = null;
        String atchFileId = "";
        String thumbFileId = "";

        if (!files.isEmpty()) {
            result = fileMngUtil.parseFileInf(files, "BBS_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
            articleDataVO.setAtchFileId(atchFileId);
        }
        if (!thumbFiles.isEmpty()) {
            result = fileMngUtil.parseFileInf(thumbFiles, "BBS_", 0, "", "");
            thumbFileId = fileMngService.insertFileInfs(result);
            articleDataVO.setThumbAtchFileId(thumbFileId);
        }
        articleDataService.insertArticle(articleDataVO);
        String referer = request.getHeader("REFERER");
        if(!referer.contains("bbs")){ // 커뮤니티활동현황 등록 후
            model.addAttribute("resultMsg", "success.common.insert");
            return "jisungsoft/com/script/alertBack";
        }
        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/"+path+"/cop/bbs/articleList.do?bbsId="+bbsId+"&menuId="+commandMap.get("menuId"));
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/articleUpdate.do", method = RequestMethod.POST)
    public String articleUpdate(@ModelAttribute ArticleDataVO articleDataVO,
                                @PathVariable("path") String path,
                                @RequestParam Map<String, Object> commandMap,
                                MultipartHttpServletRequest multiRequest,
                                HttpServletRequest request,
                                RedirectAttributes redirect,
                                ModelMap model) throws Exception
    {
        //보안 취약점 처리
        Boolean isLogin   = EgovUserDetailsHelper.isAuthenticated();
        if (!isLogin) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");
            return "jisungsoft/com/script/alertHref";
        }

        /* 질문자가 비밀글로 작성했을 경우, 답변글 비밀여부 체크함 */
        if(path.equals("page")){
            String parntSecret = (String) commandMap.get("parntSecret");
            String answerAt = articleDataVO.getAnswerAt();
            String secretAt = articleDataVO.getSecretAt() == null? "N" : articleDataVO.getSecretAt();
            if(answerAt.equals("Y")){
                if(!parntSecret.equals(secretAt)){
                    model.addAttribute("resultMsg", "error.bbs.secret");
                    return "jisungsoft/com/script/alertBack";
                }
            }
        }
        String atchFileId  = articleDataVO.getAtchFileId();
        String thumbFileId = articleDataVO.getThumbAtchFileId();
        String bbsId = articleDataVO.getBbsId();

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        final List<MultipartFile> thumbFiles = multiRequest.getFiles("thumbAtchFile");

        if (!files.isEmpty()) {
            if ("".equals(atchFileId) || atchFileId == null) {
                List<FileVO> result = fileMngUtil.parseFileInf(files, "BBS_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
                articleDataVO.setAtchFileId(atchFileId);
            } else {
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(atchFileId);
                int cnt = fileMngService.getMaxFileSN(fileVO);
                List<FileVO> _result = fileMngUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }
        if (!thumbFiles.isEmpty()) {
            if ("".equals(thumbFileId) || thumbFileId == null) {
                List<FileVO> result = fileMngUtil.parseFileInf(thumbFiles, "BBS_", 0, "", "");
                thumbFileId = fileMngService.insertFileInfs(result);
                articleDataVO.setThumbAtchFileId(thumbFileId);
            } else {
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(thumbFileId);
                int cnt = fileMngService.getMaxFileSN(fileVO);
                List<FileVO> _result = fileMngUtil.parseFileInf(thumbFiles, "BBS_", cnt, thumbFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }
        articleDataService.updateArticle(articleDataVO);
        String referer = request.getHeader("REFERER");
        if(!referer.contains("bbs")){ // 커뮤니티활동현황 등록 후
            model.addAttribute("resultMsg", "success.common.update");
            return "jisungsoft/com/script/alertBack";
        }

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/"+path+"/cop/bbs/articleList.do?bbsId="+bbsId+"&menuId="+commandMap.get("menuId"));
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/articleDelete.do", method = RequestMethod.POST)
    public String articleDelete(@ModelAttribute ArticleDataVO articleDataVO, HttpServletRequest request,
                                @PathVariable("path") String path,
                                @RequestParam Map<String, Object> commandMap,
                                ModelMap model) throws Exception
    {
        //보안 취약점 처리
        Boolean isLogin   = EgovUserDetailsHelper.isAuthenticated();
        if (!isLogin) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");
            return "jisungsoft/com/script/alertHref";
        }

        String bbsId  = articleDataVO.getBbsId();
        FileVO fileVO = new FileVO();
        String atchFileId      = articleDataVO.getAtchFileId();
        String thumbAtchFileId = articleDataVO.getThumbAtchFileId();

        List<?> checkList = articleDataService.selectCommentNPList(articleDataVO);
        if (checkList.size() > 0) {
            model.addAttribute("resultMsg", "errors.sub.delete");
            model.addAttribute("resultArg", "댓글");
            return "jisungsoft/com/script/alertBack";
        }
        List<?> answerList = articleDataService.selectAnswerDetailList(articleDataVO);
        if (answerList.size() > 0) {
            model.addAttribute("resultMsg", "errors.sub.delete");
            model.addAttribute("resultArg", "답변글");
            return "jisungsoft/com/script/alertBack";
        }

        if(atchFileId != null){
            fileVO.setAtchFileId(atchFileId);
            fileMngService.deleteAllFileInf(fileVO);
        } else if(thumbAtchFileId != null){
            fileVO.setAtchFileId(thumbAtchFileId);
            fileMngService.deleteAllFileInf(fileVO);
        }
        articleDataService.deleteArticle(articleDataVO);
        String referer = request.getHeader("REFERER");
        if(!referer.contains("bbs")){ // 커뮤니티활동현황 등록 후
            model.addAttribute("resultMsg", "success.common.delete");
            return "jisungsoft/com/script/alertBack";
        }
        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/"+path+"/cop/bbs/articleList.do?bbsId="+bbsId+"&menuId="+commandMap.get("menuId"));
        return "jisungsoft/com/script/alertHref";
    }
}