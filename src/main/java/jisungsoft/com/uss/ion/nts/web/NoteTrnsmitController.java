package jisungsoft.com.uss.ion.nts.web;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitService;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  Class Name  : NoteRecptnController.java
 *  Description : 보낸쪽지함  Business class
 *  Menu : CMS > 쪽지함 > 보낸쪽지함
 *         LMS > 쪽지함 > 보낸쪽지함
 */
@Controller
@RequestMapping("/{path}/uss/ion/nts")
public class NoteTrnsmitController {
    /** Service */
    @Resource(name = "noteTrnsmitService")
    private NoteTrnsmitService noteTrnsmitService;

    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    /**
     * 보낸쪽지함관리 목록 조회
     * @param noteTrnsmitVO
     * @param commandMap
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping("/noteTrnsmitList.do")
    public String noteTrnsmitList(@PathVariable(value="path") String path,
                                  @ModelAttribute("noteTrnsmitVO") NoteTrnsmitVO noteTrnsmitVO,
                                  @RequestParam Map<String, Object> commandMap,
                                  ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        //로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(noteTrnsmitVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(noteTrnsmitVO.getPageUnit());
        paginationInfo.setPageSize(noteTrnsmitVO.getPageSize());

        noteTrnsmitVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        noteTrnsmitVO.setLastIndex(paginationInfo.getLastRecordIndex());
        noteTrnsmitVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        /* 발신자 설정 */
        noteTrnsmitVO.setTrnsmiterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        List<?> reusltList = noteTrnsmitService.selectNoteTrnsmitList(noteTrnsmitVO);
        int totCnt = noteTrnsmitService.selectNoteTrnsmitListCnt(noteTrnsmitVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("noteTrnsmitVO", noteTrnsmitVO);
        model.addAttribute("resultList", reusltList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/" +path+"/uss/ion/nts/noteTrnsmitList."+path;
    }

    /**
     * 보낸쪽지함관리 목록 상세조회
     * @param noteTrnsmitVO
     * @param model
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/noteTrnsmitDetail.do")
    public String noteTrnsmitDetail(@PathVariable(value="path") String path,
                                    @ModelAttribute NoteTrnsmitVO noteTrnsmitVO,
                                    ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        NoteTrnsmitVO noteTrnsmitMap = noteTrnsmitService.selectNoteTrnsmitDetail(noteTrnsmitVO);
        List<?> resultRecptnEmp = noteTrnsmitService.selectNoteTrnsmitCnfirmNP(noteTrnsmitVO);

        FileVO fileVO = new FileVO();
        fileVO.setAtchFileId(noteTrnsmitMap.getAtchFileId());
        List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

        model.addAttribute("noteTrnsmit", noteTrnsmitMap);
        model.addAttribute("atchFileList", atchFileList);
        model.addAttribute("resultRecptnEmp", resultRecptnEmp);
        return "jisungsoft/com/" +path+"/uss/ion/nts/noteTrnsmitDetail."+path;
    }

    @RequestMapping("/noteTrnsmitDelete.do")
    public String noteTrnsmitDelete(@PathVariable(value="path") String path,
                                    @ModelAttribute NoteTrnsmitVO noteTrnsmitVO,
                                    @RequestParam Map<String, Object> commandMap,
                                    ModelMap model) throws Exception {
        //로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String url = "/"+path+"/uss/ion/nts/noteTrnsmitList.do";

        switch (path) {
            case "cms":
                noteTrnsmitVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                noteTrnsmitVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);
                break;
            case "lms":
                String[] sArr = noteTrnsmitVO.getCheckList().split(",");

                for(int i=0;i<(sArr.length)/2;i++){
                    int num = 2*i;
                    noteTrnsmitVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                    noteTrnsmitVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                    noteTrnsmitVO.setNoteId(sArr[num+0]);
                    noteTrnsmitVO.setNoteTrnsmitId(sArr[num+1]);

                    noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);
                }
                break;
        }

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", url);
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping("/noteTrnsmitDetDelete.do")
    public String noteTrnsmitDetDelete(@PathVariable(value="path") String path,
                                       @ModelAttribute NoteTrnsmitVO noteTrnsmitVO,
                                       @RequestParam Map<String, Object> commandMap,
                                       ModelMap model) throws Exception {
        String url = "/"+path+"/uss/ion/nts/noteTrnsmitList.do";
        noteTrnsmitService.deleteNoteTrnsmit(noteTrnsmitVO);
        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", url);
        return "jisungsoft/com/script/alertHref";
    }
}

