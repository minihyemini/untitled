package jisungsoft.com.uss.ion.ntm.web;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.ion.ntm.service.NoteManageService;
import jisungsoft.com.uss.ion.ntm.service.NoteVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  Class Name  : NoteManageController.java
 *  Description : 쪽지보내기  Business class
 *  Menu : CMS > 쪽지함 > 쪽지보내기
 *         LMS > 쪽지함 > 쪽지보내기
 */
@Controller
@RequestMapping("/{path}/uss/ion/ntm")
public class NoteManageController {

    /** Service */
    @Resource(name = "noteManageService")
    private NoteManageService noteManageService;

    /** FileMngUtil */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @RequestMapping("/noteManageInsertForm.do")
    public String noteInsertForm(@PathVariable(value="path") String path,
                                 @ModelAttribute NoteVO noteVO,
                                 @RequestParam(value = "mode", required = false, defaultValue = "") String mode,
                                 ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        switch (mode){
            case "Reply":
                NoteVO noteMap = noteManageService.selectNoteManage(noteVO);
                noteVO.setNoteSj("RE : " + noteMap.getNoteSj());
                noteVO.setNoteCn(
                    "\r\n" +
                    "\r\n" +
                    "\r\n" +
                    "\r\n" +
                    "\r\n" +
                    "[ 원 본 글 ]================================================================" + "\r\n" +
                    "* 발 신 자 : " + noteMap.getTrnsmiterNm() + "("+ noteMap.getTrnsmiterId() +")" + "\r\n" +
                    "* 발신시각 : "+ noteMap.getTrnsmiterPnttm() + "\r\n" + noteMap.getNoteCn()
                );
                noteVO.setAtchFileId(noteMap.getAtchFileId());
                model.addAttribute("noteVO" , noteVO);
                model.addAttribute("noteMap", noteMap);
                break;
            default:
                model.addAttribute("noteVO", new NoteVO());
                break;
        }
        return "jisungsoft/com/" +path+"/uss/ion/ntm/noteManageInsertForm."+path;
    }

    @RequestMapping("/noteManageInsert.do")
    public String noteManageInsert(@PathVariable(value="path") String path,
                                   @ModelAttribute NoteVO noteVO,
                                   MultipartHttpServletRequest multiRequest,
                                   @RequestParam Map<String, Object> commandMap,
                                   ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        String url      = "/"+path+"/uss/ion/ntm/noteManageInsertForm.do";
        //아이디 설정
        noteVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
        noteVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

        // 첨부파일 관련 첨부파일ID 생성
        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        List<FileVO> result = null;
        String atchFileId = "";

        if(!files.isEmpty()){
            result = fileMngUtil.parseFileInf(files, "DSCH_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
        }
        noteVO.setAtchFileId(atchFileId);

        noteManageService.insertNote(noteVO, commandMap);

        model.addAttribute("noteVO", new NoteVO());
        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", url);
        return "jisungsoft/com/script/alertHref";
    }
}

