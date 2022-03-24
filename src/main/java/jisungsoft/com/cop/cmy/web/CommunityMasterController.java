package jisungsoft.com.cop.cmy.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.bbs.service.ArticleDataService;
import jisungsoft.com.cop.bbs.service.ArticleDataVO;
import jisungsoft.com.cop.bbs.service.BbsMasterService;
import jisungsoft.com.cop.cmy.service.CommunityMasterService;
import jisungsoft.com.cop.cmy.service.CommunityUserVO;
import jisungsoft.com.cop.cmy.service.CommunityVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 *  Class Name  : CommunityMasterController.java
 *  Description : 커뮤니티관리 Business class
 *  Menu : CMS > 게시판관리 > 커뮤니티관리
 */
@Controller
@RequestMapping("/cms/cop/cmy")
public class CommunityMasterController {
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

    private String redirectUri = "/cms/cop/cmy/cmyMasterList.do";

    @RequestMapping("/cmyMasterList.do")
    public String cmyMasterList(@ModelAttribute("communityVO") CommunityVO communityVO, ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(communityVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(communityVO.getPageUnit());
        paginationInfo.setPageSize(communityVO.getPageSize());

        communityVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        communityVO.setLastIndex(paginationInfo.getLastRecordIndex());
        communityVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = communityMasterService.selectCommunityMasterList(communityVO);
        paginationInfo.setTotalRecordCount(communityMasterService.selectCommunityMasterListCnt(communityVO));

        model.addAttribute("communityVO"   , communityVO);
        model.addAttribute("resultList"    , resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/cop/cmy/cmyMaster/cmyMasterList.cms";
    }

    @RequestMapping("/cmyMasterForm.do")
    public String cmyMasterForm(@ModelAttribute CommunityVO mainVO,
                                ModelMap model,
                                @RequestParam(value = "mode") String mode) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        switch (mode) {
            case "Insert":
                model.addAttribute("communityVO", new CommunityVO());
                model.addAttribute("communityUserVO", new CommunityUserVO());
                break;
            case "Update":
                CommunityVO resultData = communityMasterService.selectCommunityMasterDetail(mainVO);

                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(resultData.getAtchFileId());
                List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
                fileVO.setAtchFileId(resultData.getThumbAtchFileId());
                List<FileVO> thumbFileList = fileMngService.selectImageFileList(fileVO);

                model.addAttribute("communityVO", resultData);
                model.addAttribute("atchFileList", atchFileList);
                model.addAttribute("thumbFileList", thumbFileList);
                break;
        }
        return "jisungsoft/com/cms/cop/cmy/cmyMaster/cmyMasterForm.cms";
    }

    @RequestMapping(value = "/cmyMasterInsert.do", method = RequestMethod.POST)
    public String cmyMasterInsert(@ModelAttribute CommunityVO communityVO,
                                MultipartHttpServletRequest multiRequest,
                                ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
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

    @RequestMapping(value = "/cmyMasterUpdate.do", method = RequestMethod.POST)
    public String cmyMasterUpdate(@ModelAttribute CommunityVO communityVO,
                                  MultipartHttpServletRequest multiRequest,
                                  ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
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

    @RequestMapping(value = "/cmyMasterDelete.do", method = RequestMethod.POST)
    public String cmyMasterDelete(@ModelAttribute CommunityVO communityVO, ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        try {
            String cmmntyId = communityVO.getCmmntyId();

            CommunityUserVO uvo = new CommunityUserVO();
            uvo.setCmmntyId(cmmntyId);

            /* 삭제 시, 회원 존재 체크 */
            List<?> checkList = communityMasterService.selectCommunityUserNPList(uvo);
            if (checkList.size() > 0) {
                throw new Exception();
            }

            /* 삭제 시, 활동현황 내 게시글 댓글 체크 */
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
}