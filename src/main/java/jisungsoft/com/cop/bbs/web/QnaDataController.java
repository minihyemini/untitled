package jisungsoft.com.cop.bbs.web;

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
 *  Class Name  : QnaDataController.java
 *  Description : 지식요청 관리 Business class
 *  Menu : CMS > 상담지식관리 > 지식요청
 */
//@Controller
//@RequestMapping("/{path}/cop/bbs")
public class QnaDataController {

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

    LoginVO authenticatedUser;

    @RequestMapping("/qnaList.do")
    public String qnaList(@PathVariable(value="path") String path, @ModelAttribute("articleDataVO") ArticleDataVO articleDataVO,
                              @RequestParam Map<String, Object> commandMap,
                              ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(articleDataVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(articleDataVO.getPageUnit());
        paginationInfo.setPageSize(articleDataVO.getPageSize());

        articleDataVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        articleDataVO.setLastIndex(paginationInfo.getLastRecordIndex());
        articleDataVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        articleDataVO.setPathSplit(path);

        BbsMasterVO bbsMasterVO = new BbsMasterVO();
        if(articleDataVO.getBbsId() != null) bbsMasterVO.setBbsId(articleDataVO.getBbsId());
        BbsMasterVO bbsData = bbsMasterService.selectBbsMasterDetail(bbsMasterVO);
        List<?> resultList = articleDataService.selectArticleList(articleDataVO);
        paginationInfo.setTotalRecordCount(articleDataService.selectArticleListTotCnt(articleDataVO));

        model.addAttribute("articleDataVO", articleDataVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("bbsData", bbsData);
        model.addAttribute("paginationInfo", paginationInfo);
        return "jisungsoft/com/" + path + "/cop/bbs/qna/qnaList."+path;
    }

    @RequestMapping("/qnaDetail.do")
    public String qnaDetail(@PathVariable(value="path") String path, @ModelAttribute ArticleDataVO mainVO,
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
        }

        if(!bbsMasterVO.getBbsTyCode().equals("BBST04")) {  //커뮤니티 제외
            List<?> answerList = articleDataService.selectAnswerDetailList(mainVO);
            model.addAttribute("answerList", answerList);
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

        return "jisungsoft/com/" + path + "/cop/bbs/qna/qnaDetail."+path;
    }

    @RequestMapping("/qnaForm.do")
    public String qnaForm(@PathVariable("path") String path, @ModelAttribute ArticleDataVO mainVO,
                                  ModelMap model, @RequestParam(value = "mode") String mode) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        BbsMasterVO bbsMasterVO = new BbsMasterVO();
        bbsMasterVO.setBbsId(mainVO.getBbsId());
        bbsMasterVO = bbsMasterService.selectBbsMasterDetail(bbsMasterVO);
        model.addAttribute("bbsMasterVO", bbsMasterVO);

        switch (mode) {
            case "Insert":
                model.addAttribute("articleDataVO", new ArticleDataVO());
                break;
            case "Answer":
                ArticleDataVO answerData = articleDataService.selectArticleDetail(mainVO);
                answerData.setAnswerAt("Y");
                model.addAttribute("resultVO", answerData);
                model.addAttribute("articleDataVO", new ArticleDataVO());
                break;
            case "Update":
                ArticleDataVO resultData = articleDataService.selectArticleDetail(mainVO);

                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(resultData.getAtchFileId());
                List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

                model.addAttribute("articleDataVO", resultData);
                model.addAttribute("atchFileList", atchFileList);
                break;
        }
        return "jisungsoft/com/" + path + "/cop/bbs/qna/qnaForm."+path;
    }

    @RequestMapping(value = "/qnaInsert.do", method = RequestMethod.POST)
    public String qnaInsert(@ModelAttribute ArticleDataVO articleDataVO,
                                @PathVariable("path") String path,
                                @RequestParam Map<String, Object> commandMap,
                                MultipartHttpServletRequest multiRequest,
                                HttpServletRequest request,
                                ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }
        String bbsId     = articleDataVO.getBbsId();

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        List<FileVO> result = null;
        String atchFileId = "";

        if (!files.isEmpty()) {
            result = fileMngUtil.parseFileInf(files, "BBS_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
            articleDataVO.setAtchFileId(atchFileId);
        }
        articleDataService.insertArticle(articleDataVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/"+path+"/cop/bbs/qnaList.do?bbsId="+bbsId+"&menuId="+commandMap.get("menuId"));
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/qnaUpdate.do", method = RequestMethod.POST)
    public String qnaUpdate(@ModelAttribute ArticleDataVO articleDataVO,
                                @PathVariable("path") String path,
                                @RequestParam Map<String, Object> commandMap,
                                MultipartHttpServletRequest multiRequest,
                                HttpServletRequest request,
                                RedirectAttributes redirect,
                                ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        String atchFileId  = articleDataVO.getAtchFileId();
        String bbsId = articleDataVO.getBbsId();

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");

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

        articleDataService.updateArticle(articleDataVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/"+path+"/cop/bbs/qnaList.do?bbsId="+bbsId+"&menuId="+commandMap.get("menuId"));
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/qnaDelete.do", method = RequestMethod.POST)
    public String qnaDelete(@ModelAttribute ArticleDataVO articleDataVO, HttpServletRequest request,
                                @PathVariable("path") String path,
                                @RequestParam Map<String, Object> commandMap,
                                ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        String bbsId  = articleDataVO.getBbsId();
        FileVO fileVO = new FileVO();

        List<?> answerList = articleDataService.selectAnswerDetailList(articleDataVO);
        if (answerList.size() > 0) {
            model.addAttribute("resultMsg", "errors.sub.delete");
            model.addAttribute("resultArg", "답변글");
            return "jisungsoft/com/script/alertBack";
        }

        ArticleDataVO resultData = articleDataService.selectArticleDetail(articleDataVO);

        if(resultData.getAtchFileId() != null && !resultData.getAtchFileId().equals("")){
            fileVO.setAtchFileId(resultData.getAtchFileId());
            fileMngService.deleteAllFileInf(fileVO);
        }
        articleDataService.deleteArticle(articleDataVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/"+path+"/cop/bbs/qnaList.do?bbsId="+bbsId+"&menuId="+commandMap.get("menuId"));
        return "jisungsoft/com/script/alertHref";
    }
}
