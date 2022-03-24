package jisungsoft.com.uss.ion.ntr.web;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.ion.ntr.service.NoteRecptnService;
import jisungsoft.com.uss.ion.ntr.service.NoteRecptnVO;
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
 *  Description : 받은쪽지함  Business class
 *  Menu : CMS > 쪽지함 > 받은쪽지함
 *         LMS > 쪽지함 > 받은쪽지함
 */
@Controller
@RequestMapping("/{path}/uss/ion/ntr")
public class NoteRecptnController {

    /** Service */
    @Resource(name = "noteRecptnService")
    private NoteRecptnService noteRecptnService;

    /** Trnsmit Service */
    @Resource(name = "noteTrnsmitService")
    private NoteTrnsmitService noteTrnsmitService;

    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @RequestMapping("/noteRecptnList.do")
    public String noteRecptnList(@PathVariable(value="path") String path,
                                 @ModelAttribute("noteRecptnVO") NoteRecptnVO noteRecptnVO,
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
        paginationInfo.setCurrentPageNo(noteRecptnVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(noteRecptnVO.getPageUnit());
        paginationInfo.setPageSize(noteRecptnVO.getPageSize());

        noteRecptnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        noteRecptnVO.setLastIndex(paginationInfo.getLastRecordIndex());
        noteRecptnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        /* 수신자 설정 */
        noteRecptnVO.setRcverId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        List<?> reusltList = noteRecptnService.selectNoteRecptnList(noteRecptnVO);
        int totCnt = noteRecptnService.selectNoteRecptnListCnt(noteRecptnVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("noteRecptnVO", noteRecptnVO);
        model.addAttribute("resultList", reusltList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/" +path+"/uss/ion/ntr/noteRecptnList."+path;
    }

    @RequestMapping(value = "/noteRecptnDetail.do")
    public String noteRecptnDetail(@PathVariable(value="path") String path,
                                   @ModelAttribute NoteRecptnVO noteRecptnVO,
                                   @RequestParam Map<?, ?> commandMap,
                                   ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        //로그인 객체 선언/아이디설정
        LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        noteRecptnVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        noteRecptnVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        NoteRecptnVO noteRecptnMap = noteRecptnService.selectNoteRecptnDetail(noteRecptnVO);

        FileVO fileVO = new FileVO();
        fileVO.setAtchFileId(noteRecptnMap.getAtchFileId());
        List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

        NoteTrnsmitVO noteTrnsmitVO = new NoteTrnsmitVO();
        noteTrnsmitVO.setNoteId((String)commandMap.get("noteId"));

        List<?> resultRecptnEmp = noteTrnsmitService.selectNoteTrnsmitCnfirmNP(noteTrnsmitVO);
        model.addAttribute("noteRecptn"  , noteRecptnMap);
        model.addAttribute("atchFileList", atchFileList);
        model.addAttribute("resultRecptnEmp", resultRecptnEmp);
        return "jisungsoft/com/" +path+"/uss/ion/ntr/noteRecptnDetail."+path;
    }

    @RequestMapping("/noteRecptnDelete.do")
    public String noteRecptnDelete(@PathVariable(value="path") String path,
                                   @ModelAttribute NoteRecptnVO noteRecptnVO,
                                   @RequestParam Map<String, Object> commandMap,
                                   ModelMap model) throws Exception {
        //로그인 객체 선언
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String url = "/"+path+"/uss/ion/ntr/noteRecptnList.do";

        switch (path) {
            case "cms":
                noteRecptnVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                noteRecptnVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                noteRecptnService.deleteNoteRecptn(noteRecptnVO);
                break;
            case "lms":
                String[] sArr = noteRecptnVO.getCheckList().split(",");

                for(int i=0;i<(sArr.length)/3;i++){
                    int num = 3*i;
                    noteRecptnVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                    noteRecptnVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
                    noteRecptnVO.setNoteId(sArr[num+0]);
                    noteRecptnVO.setNoteTrnsmitId(sArr[num+1]);
                    noteRecptnVO.setNoteRecptnId(sArr[num+2]);

                    noteRecptnService.deleteNoteRecptn(noteRecptnVO);
                }
                break;
        }
        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", url);
        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping("/noteRecptnDetDelete.do")
    public String noteRecptnDetDelete(@PathVariable(value="path") String path,
                                      @ModelAttribute NoteRecptnVO noteRecptnVO,
                                      @RequestParam Map<String, Object> commandMap,
                                      ModelMap model) throws Exception {
        String url = "/"+path+"/uss/ion/ntr/noteRecptnList.do";
        noteRecptnService.deleteNoteRecptn(noteRecptnVO);
        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", url);
        return "jisungsoft/com/script/alertHref";
    }
}

