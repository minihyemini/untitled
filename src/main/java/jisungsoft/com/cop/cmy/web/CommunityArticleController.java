package jisungsoft.com.cop.cmy.web;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cop.bbs.service.*;
import jisungsoft.com.cop.cmy.service.CommunityMasterService;
import jisungsoft.com.cop.cmy.service.CommunityUserVO;
import jisungsoft.com.cop.cmy.service.CommunityVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Class Name  : CommunityArticleController.java
 *  Description : 커뮤니티 Business class
 *  Menu : PAGE > 커뮤니티
 */
@Controller
@RequestMapping("/page/cop/cmy")
public class CommunityArticleController {
    /** Service */
    @Resource(name = "communityMasterService")
    private CommunityMasterService communityMasterService;
    /** 게시판 Service */
    @Resource(name = "bbsMasterService")
    private BbsMasterService bbsMasterService;
    /** FileMngUtil */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;
    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;
    /** 게시글 Service */
    @Resource(name = "articleDataService")
    private ArticleDataService articleDataService;

    LoginVO authenticatedUser;

    private String redirectUri = "/page/cop/cmy/cmyArticleList.do";

    @RequestMapping("/cmyArticleList.do")
    public String cmyArticleList(CommunityVO mainVO, ModelMap model) throws Exception {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(mainVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(mainVO.getPageUnit());
        paginationInfo.setPageSize(mainVO.getPageSize());
        mainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        mainVO.setLastIndex(paginationInfo.getLastRecordIndex());
        mainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = communityMasterService.selectCommunityMasterList(mainVO);
        int totCnt = communityMasterService.selectCommunityMasterListCnt(mainVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", resultList);
        model.addAttribute("resultCnt", totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/page/cop/cmy/cmyArticleList.page";
    }

    @RequestMapping("/cmyArticleDetail.do")
    public String cmyArticleDetail(CommunityVO mainVO, ModelMap model,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> dataMap = (HashMap) request.getAttribute("dataMap");
        LoginVO authenticatedUser = (LoginVO) dataMap.get("user");
        Boolean isLogin = EgovUserDetailsHelper.isAuthenticated();
        String sessionUniqId = "";

        if(isLogin){
            sessionUniqId = authenticatedUser.getUniqId();
            mainVO.setSessionUniqId(authenticatedUser.getUniqId());
            mainVO.setAuthority(authenticatedUser.getAuthority());
        }

        CommunityVO communityVO = communityMasterService.selectCommunityMasterDetail(mainVO);

        //조회수 증가 중복방지 쿠키추가
        Cookie[] cookies = request.getCookies();
        Cookie viewCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (int j = 0; j < cookies.length; j++) {
                if (cookies[j].getName().equals("cmmnty"+communityVO.getCmmntyId())) {
                    viewCookie = cookies[j];
                }
            }
        }
        if (communityVO != null) {
            if (viewCookie == null) {
                Cookie newCookie = new Cookie("cmmnty"+communityVO.getCmmntyId(), "|"+communityVO.getCmmntyId()+"|");
                response.addCookie(newCookie);

                //조회수 증가
                communityMasterService.updateRdcnt(mainVO);
            }
        }

        FileVO fileVO = new FileVO();
        fileVO.setAtchFileId(communityVO.getAtchFileId());
        List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
        model.addAttribute("communityVO", communityVO);
        model.addAttribute("atchFileList", atchFileList);
        model.addAttribute("sessionUniqId", sessionUniqId);
        return "jisungsoft/com/page/cop/cmy/cmyArticleDetail.page";
    }

    @RequestMapping("/cmyArticleForm.do")
    public String cmyArticleForm(CommunityVO mainVO, ModelMap model, @RequestParam Map<String, Object> commandMap,
                                 HttpServletRequest request) throws Exception {
        String mode = (String) commandMap.get("mode");
        switch (mode) {
            case "Insert":
                model.addAttribute("communityVO", new CommunityVO());
                break;
            case "Update":
                if(!authCheck(mainVO, request)){
                    model.addAttribute("resultMsg", "errors.cmmnty.insert");
                    model.addAttribute("redirectUri", redirectUri);
                    return "jisungsoft/com/script/alertHref";
                }

                CommunityVO resultData = communityMasterService.selectCommunityMasterDetail(mainVO);
                CommunityUserVO userVO = new CommunityUserVO();
                userVO.setCmmntyId(mainVO.getCmmntyId());

                BbsMasterVO bvo = new BbsMasterVO();
                bvo.setBbsId(resultData.getBbsId());
                BbsMasterVO bbsMasterVO = bbsMasterService.selectBbsMasterDetail(bvo);

                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(resultData.getAtchFileId());
                List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
                fileVO.setAtchFileId(resultData.getThumbAtchFileId());
                List<FileVO> thumbFileList = fileMngService.selectImageFileList(fileVO);

                model.addAttribute("communityVO", resultData);
                model.addAttribute("atchFileList", atchFileList);
                model.addAttribute("thumbFileList", thumbFileList);
                model.addAttribute("bbsMasterVO", bbsMasterVO);
                break;
        }
        return "jisungsoft/com/page/cop/cmy/cmyArticleForm.page";
    }

    @RequestMapping("/cmyArticleSubForm.do")
    public String cmyArticleSubForm(CommunityVO mainVO, ModelMap model, @RequestParam Map<String, Object> commandMap,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> dataMap = (HashMap) request.getAttribute("dataMap");
        Boolean isLogin = EgovUserDetailsHelper.isAuthenticated();
        authenticatedUser = (LoginVO) dataMap.get("user");
        String sessionUniqId = null;
        if (isLogin) {
            sessionUniqId = authenticatedUser.getUniqId();
            mainVO.setSessionUniqId(authenticatedUser.getUniqId());
            mainVO.setAuthority(authenticatedUser.getAuthority());
        }
        String mode = (String) commandMap.get("mode");
        BbsMasterVO bvo = new BbsMasterVO();
        bvo.setBbsId(mainVO.getBbsId());
        BbsMasterVO bbsMasterVO = bbsMasterService.selectBbsMasterDetail(bvo);
        model.addAttribute("bbsMasterVO", bbsMasterVO);
        switch (mode) {
            case "Insert":
                model.addAttribute("articleDataVO", new ArticleDataVO());
                break;
            case "Update":
            case "Read":
                ArticleDataVO articleDataVO = new ArticleDataVO();
                articleDataVO.setBbsId(mainVO.getBbsId());
                articleDataVO.setNttId(Integer.parseInt((String) commandMap.get("nttId")));
                if (isLogin) {
                    articleDataVO.setSessionUniqId(authenticatedUser.getUniqId());
                    articleDataVO.setAuthority(authenticatedUser.getAuthority());
                }
                ArticleDataVO resultData = articleDataService.selectArticleDetail(articleDataVO);

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
                        articleDataService.updateRdcnt(articleDataVO);
                    }
                }
                CommunityVO communityVO = communityMasterService.selectCommunityMasterDetail(mainVO);
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(resultData.getAtchFileId());
                List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
                model.addAttribute("commentVO", new CommentVO());
                model.addAttribute("communityVO", communityVO);
                model.addAttribute("articleDataVO", resultData);
                model.addAttribute("atchFileList", atchFileList);
                model.addAttribute("sessionUniqId", sessionUniqId);
                break;
        }
        return "jisungsoft/com/page/cop/cmy/active/form";
    }

    @RequestMapping(value = "/cmyArticleInsert.do", method = RequestMethod.POST)
    public String cmyArticleInsert(@ModelAttribute CommunityVO communityVO,
                                   MultipartHttpServletRequest multiRequest,
                                   HttpServletRequest request,
                                   ModelMap model) throws Exception
    {
        HashMap<String, Object> dataMap = (HashMap) request.getAttribute("dataMap");
        LoginVO authenticatedUser = (LoginVO) dataMap.get("user");

        /* 커뮤니티 마스터 권한 체크 */
        if(authenticatedUser == null){
            model.addAttribute("resultMsg", "errors.cmmnty.insert");
            model.addAttribute("redirectUri", redirectUri);
            return "jisungsoft/com/script/alertHref";
        }

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        final List<MultipartFile> thumbFiles = multiRequest.getFiles("thumbAtchFile");

        List<FileVO> result = null;
        String atchFileId = "";
        String thumbFileId = "";

        if (!files.isEmpty()) {
            result = fileMngUtil.parseFileInf(files, "CMM_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
            communityVO.setAtchFileId(atchFileId);
        }
        if (!thumbFiles.isEmpty()) {
            result = fileMngUtil.parseFileInf(thumbFiles, "CMM_", 0, "", "");
            thumbFileId = fileMngService.insertFileInfs(result);
            communityVO.setThumbAtchFileId(thumbFileId);
        }
        communityMasterService.insertCommunity(communityVO);
        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", redirectUri);
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/cmyArticleUpdate.do", method = RequestMethod.POST)
    public String cmyArticleUpdate(@ModelAttribute CommunityVO communityVO,
                                   MultipartHttpServletRequest multiRequest,
                                   HttpServletRequest request,
                                   ModelMap model) throws Exception
    {
        if (!authCheck(communityVO, request)) {
            model.addAttribute("resultMsg", "errors.cmmnty.update");
            model.addAttribute("redirectUri", redirectUri);
            return "jisungsoft/com/script/alertBack";
        }

        String atchFileId = communityVO.getAtchFileId();
        String thumbFileId = communityVO.getThumbAtchFileId();

        // 첨부파일 등록
        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        final List<MultipartFile> thumbFiles = multiRequest.getFiles("thumbAtchFile");

        if (!files.isEmpty()) {
            if ("".equals(atchFileId) || atchFileId == null) {
                List<FileVO> result = fileMngUtil.parseFileInf(files, "CMM_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
                communityVO.setAtchFileId(atchFileId);
            } else {
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(atchFileId);
                int cnt = fileMngService.getMaxFileSN(fileVO);
                List<FileVO> _result = fileMngUtil.parseFileInf(files, "CMM_", cnt, atchFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }
        if (!thumbFiles.isEmpty()) {
            if ("".equals(thumbFileId) || thumbFileId == null) {
                List<FileVO> result = fileMngUtil.parseFileInf(thumbFiles, "CMM_", 0, "", "");
                thumbFileId = fileMngService.insertFileInfs(result);
                communityVO.setThumbAtchFileId(thumbFileId);
            } else {
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(thumbFileId);
                int cnt = fileMngService.getMaxFileSN(fileVO);
                List<FileVO> _result = fileMngUtil.parseFileInf(thumbFiles, "CMM_", cnt, thumbFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }
        communityMasterService.updateCommunity(communityVO);
        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", redirectUri);
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/cmyArticleDelete.do", method = RequestMethod.POST)
    public String cmyArticleDelete(@ModelAttribute CommunityVO communityVO, ModelMap model, HttpServletRequest request) throws Exception
    {
        try {
            if(!authCheck(communityVO, request)){
                model.addAttribute("resultMsg", "errors.cmmnty.delete");
                model.addAttribute("redirectUri", redirectUri);
                return "jisungsoft/com/script/alertHref";
            }

            String cmmntyId = communityVO.getCmmntyId();

            CommunityUserVO uvo = new CommunityUserVO();
            uvo.setCmmntyId(cmmntyId);

            /* 삭제 시, 회원 존재 체크 */
            List<?> checkList = communityMasterService.selectCommunityUserNPList(uvo);
            if (checkList.size() > 0) {
                throw new Exception();
            }

            /* 삭제 시, 활동현황 내 게시글 댓글 */
            ArticleDataVO articleDataVO = new ArticleDataVO();
            articleDataVO.setBbsId(communityVO.getBbsId());
            List<?> commentList = articleDataService.selectCommentNPList(articleDataVO);
            if (commentList.size() > 0) {
                model.addAttribute("resultMsg", "errors.sub.delete");
                model.addAttribute("resultArg", "활동현황 내 댓글");
                return "jisungsoft/com/script/alertBack";
            }

            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(communityVO.getAtchFileId());
            fileMngService.deleteAllFileInf(fileVO);
            fileVO.setAtchFileId(communityVO.getThumbAtchFileId());
            fileMngService.deleteAllFileInf(fileVO);

            communityMasterService.deleteCommunity(communityVO);
            model.addAttribute("resultMsg", "success.common.delete");
            model.addAttribute("redirectUri", redirectUri);
            return "jisungsoft/com/script/alertHref";
        }catch (Exception e){
            model.addAttribute("resultMsg", "errors.sub.delete");
            model.addAttribute("resultArg", "회원");
            return "jisungsoft/com/script/alertBack";
        }
    }

    private Boolean authCheck(@ModelAttribute CommunityVO communityVO, HttpServletRequest request) throws Exception
    {
        HashMap<String, Object> dataMap = (HashMap) request.getAttribute("dataMap");
        LoginVO authenticatedUser = (LoginVO) dataMap.get("user");

        /* 커뮤니티 마스터 권한 체크 */
        if(authenticatedUser == null){
            return false;
        }else if (!authenticatedUser.getAuthority().equals(AuthorityCode.ROLE_ADMIN.name()) && !authenticatedUser.getAuthority().equals(AuthorityCode.ROLE_MANAGER.name())) {
            communityVO.setAuthority(authenticatedUser.getAuthority());
            communityVO.setSessionUniqId(authenticatedUser.getUniqId());
            CommunityVO resultData = communityMasterService.selectCommunityMasterDetail(communityVO);
            if (resultData == null || resultData.getCmmntyAuth().isEmpty()) {
                return false;
            }else{
                return true;
            }
        }
        return true;
    }
}