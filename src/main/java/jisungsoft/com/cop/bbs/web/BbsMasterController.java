package jisungsoft.com.cop.bbs.web;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.bbs.service.*;
import jisungsoft.com.cop.cmy.service.CommunityMasterService;
import jisungsoft.com.service.MenuProgramService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Class Name  : BbsMasterController.java
 *  Description : 게시판마스터 관리 Business class
 *  Menu : CMS > 게시판관리 > 게시판관리
 */
//@Controller
//@RequestMapping("/cms/cop/bbs")
public class BbsMasterController {

    /** Service */
//    @Resource(name = "bbsMasterService")
    private BbsMasterService bbsMasterService;

    /** 게시글 Service */
//    @Resource(name = "articleDataService")
    private ArticleDataService articleDataService;

    /** 프로그램관리 Service */
//    @Resource(name = "programManageService")
    protected MenuProgramService programManageService;

    /** 커뮤니티 Service */
//    @Resource(name = "communityMasterService")
    private CommunityMasterService communityMasterService;

//    @Resource(name = "cmmUseService")
    protected CmmUseService cmmUseService;

    @RequestMapping("/bbsMasterList.do")
    public String bbsMasterList(@ModelAttribute("bbsMasterVO") BbsMasterVO bbsMasterVO,
                            ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(bbsMasterVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(bbsMasterVO.getPageUnit());
        paginationInfo.setPageSize(bbsMasterVO.getPageSize());

        bbsMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        bbsMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
        bbsMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = bbsMasterService.selectBbsMasterList(bbsMasterVO);
        paginationInfo.setTotalRecordCount(bbsMasterService.selectBbsMasterListCnt(bbsMasterVO));

        model.addAttribute("resultList"    , resultList);
        model.addAttribute("bbsMasterVO"   , bbsMasterVO);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/cop/bbs/master/masterList.cms";
    }

    @RequestMapping("/bbsMasterForm.do")
    public String bbsMasterForm(@ModelAttribute BbsMasterVO mainVO,
                                ModelMap model) throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        List<?> authList = cmmUseService.selectAuthDataList();
        if(!mainVO.getBbsId().isEmpty()){
            BbsMasterVO resultData = bbsMasterService.selectBbsMasterDetail(mainVO);
            BbsMasterAuthVO authVO = new BbsMasterAuthVO();
            authVO.setBbsId(resultData.getBbsId());
            List<?> resultAuthList = bbsMasterService.selectBbsMasterAuthList(authVO);
            model.addAttribute("bbsMasterVO", resultData);
            model.addAttribute("resultAuthList", resultAuthList);

        }else{
            model.addAttribute("bbsMasterVO", new BbsMasterVO());
        }
        model.addAttribute("authList", authList);
        return "jisungsoft/com/cms/cop/bbs/master/masterForm.cms";
    }

    @RequestMapping("/bbsMasterInsert.do")
    public String bbsMasterInsert(@ModelAttribute BbsMasterVO bbsMasterVO, ModelMap model)  throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        bbsMasterVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        bbsMasterService.insertBbsMaster(bbsMasterVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/cop/bbs/bbsMasterList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping("/bbsMasterUpdate.do")
    public String bbsMasterUpdate(@ModelAttribute BbsMasterVO bbsMasterVO, ModelMap model)  throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        bbsMasterVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        bbsMasterService.updateBbsMaster(bbsMasterVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/cop/bbs/bbsMasterList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping("/bbsMasterDelete.do")
    public String bbsMasterDelete(@ModelAttribute BbsMasterVO bbsMasterVO, ModelMap model)  throws Exception
    {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        // 로그인VO에서  사용자 정보 가져오기
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        bbsMasterVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        /* 해당 게시판 게시글 존재 체크 */
        ArticleDataVO paramVO = new ArticleDataVO();
        paramVO.setBbsId(bbsMasterVO.getBbsId());
        List<?> checkList = articleDataService.selectArticleNPList(paramVO);
        if (checkList.size() > 0) {
            model.addAttribute("resultMsg", "errors.sub.delete");
            model.addAttribute("resultArg", "게시판 및 게시글");
            return "jisungsoft/com/script/alertBack";
        }

        bbsMasterService.deleteBbsMaster(bbsMasterVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/cop/bbs/bbsMasterList.do");

        return "jisungsoft/com/script/alertHref";
    }
}

