package jisungsoft.com.cop.cmy.web;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.cmy.service.CommunityMasterService;
import jisungsoft.com.cop.cmy.service.CommunityUserVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/{path}/cop/cmy")
public class CommunityRestController {
    /**
     * Service
     */
    @Resource(name = "communityMasterService")
    private CommunityMasterService communityMasterService;

    /**
     * 커뮤니티 회원목록 조회
     * @param commandMap 파라메터전달용 commandMap
     * @param communityUserVO
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/user/list.json")
    public ModelAndView communityUserList(@RequestParam Map<String, Object> commandMap, CommunityUserVO communityUserVO) throws Exception
    {
        ModelAndView mav      = new ModelAndView("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            mav.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return mav;
        }

        String cmmntyId       = (String) commandMap.get("cmmntyId");

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(communityUserVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(communityUserVO.getPageUnit());
        paginationInfo.setPageSize(communityUserVO.getPageSize());
        communityUserVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        communityUserVO.setLastIndex(paginationInfo.getLastRecordIndex());
        communityUserVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        communityUserVO.setCmmntyId(cmmntyId);
        /* 커뮤니티 회원 목록 조회 */
        List<?> resultList = communityMasterService.selectCommunityUserList(communityUserVO);
        CommunityUserVO resultData = communityMasterService.selectCommunityUserDetail(communityUserVO);
        int totCnt = communityMasterService.selectCommunityUserListCnt(communityUserVO);
        paginationInfo.setTotalRecordCount(totCnt);

        mav.addObject("resultList", resultList);
        mav.addObject("resultData", resultData);
        mav.addObject("totCnt"    , totCnt);
        mav.addObject("paginationInfoJs", paginationInfo);
        return mav;
    }

    /**
     * 커뮤니티 회원 상태 처리
     * @param commandMap 파라메터전달용 commandMap
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/user/stts.json")
    public void updateStts(@RequestParam Map<String, Object> commandMap) throws Exception {
        CommunityUserVO userVO = new CommunityUserVO();
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        String cmmntyId = (String) commandMap.get("cmmntyId");
        String emplyrId = (String) commandMap.get("emplyrId");
        String flag = (String) commandMap.get("flag");
        userVO.setCmmntyId(cmmntyId);
        userVO.setEmplyrId(emplyrId);
        userVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        if (flag.equals("Y")) {
            userVO.setMberSttus("STTS02"); // 승인
        } else {
            userVO.setMberSttus("STTS03"); // 거부
        }
        communityMasterService.updateCommunityUserStts(userVO);
    }

    /**
     * 커뮤니티 가입 처리
     * @param commandMap 파라메터전달용 commandMap
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/user/join.json")
    public ModelAndView join(@RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView mav      = new ModelAndView("jsonView");
        LoginVO user  = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
        String cmmntyId = (String) commandMap.get("cmmntyId");
        String permitAt = (String) commandMap.get("permitAt");

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(cmmntyId);
        communityUserVO.setEmplyrId(uniqId);
        communityUserVO.setMngrAt("N"); //관리자여부
        if(permitAt.equals("Y")) {
            communityUserVO.setMberSttus("STTS02"); // 승인
        }else{
            communityUserVO.setMberSttus("STTS01"); // 대기
        }
        communityUserVO.setUseAt("Y");
        communityUserVO.setFrstRegisterId(uniqId);

        int checkCnt = communityMasterService.selectCommunityUserListCnt(communityUserVO);
        if(checkCnt > 0){
            mav.addObject("result", "error");
            mav.addObject("msg", "-1");
        }else {
            communityMasterService.insertCommunityUser(communityUserVO);
            if(communityUserVO.getMberSttus().equals("STTS01")){
                mav.addObject("result", "success");
                mav.addObject("msg", "100");
            }else{
                mav.addObject("result", "success");
                mav.addObject("msg", "1");
            }
        }
        return mav;
    }

    /**
     * 커뮤니티 탈퇴 처리
     * @param commandMap 파라메터전달용 commandMap
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/user/secsn.json")
    public ModelAndView secsn(@RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView mav      = new ModelAndView("jsonView");
        LoginVO user  = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
        String cmmntyId = (String) commandMap.get("cmmntyId");

        CommunityUserVO communityUserVO = new CommunityUserVO();
        communityUserVO.setCmmntyId(cmmntyId);
        communityUserVO.setEmplyrId(uniqId);
        communityUserVO.setLastUpdusrId(uniqId);
        communityMasterService.updateCommunityUserEnd(communityUserVO);
        mav.addObject("result", "success");
        mav.addObject("msg", "1");
        return mav;
    }
}