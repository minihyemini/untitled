package jisungsoft.com.uss.ion.pwm.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.ion.pwm.service.PopupManageService;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  Class Name  : PopupManageController.java
 *  Description : 팝업관리  Business class
 *  Menu : CMS > 사이트관리 > 팝업관리
 */
@Controller
@RequestMapping("/{path}/uss/ion/pwm")
public class PopupManageController {

    /** Service */
    @Resource(name = "popupManageService")
    private PopupManageService popupManageService;

    /** FileMngUtil */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @RequestMapping("/popupList.do")
    public String popupList(@ModelAttribute PopupVO mainVO,
                           ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(mainVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(mainVO.getPageUnit());
        paginationInfo.setPageSize(mainVO.getPageSize());

        mainVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        mainVO.setLastIndex(paginationInfo.getLastRecordIndex());
        mainVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = popupManageService.selectPopupList(mainVO);
        int totCnt = popupManageService.selectPopupListCnt(mainVO);

        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", resultList);
        model.addAttribute("resultCnt", totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/uss/ion/pwm/popupList.cms";
    }

    @RequestMapping("/popupForm.do")
    public String popupForm(@ModelAttribute PopupVO mainVO,
                             ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        if(!mainVO.getPopupId().isEmpty()){
            PopupVO resultData = popupManageService.selectPopupDetail(mainVO);
            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(resultData.getAtchFileId());
            List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
            model.addAttribute("popupVO", resultData);
            model.addAttribute("atchFileList", atchFileList);
        }else{
            model.addAttribute("popupVO", new PopupVO());
        }
        return "jisungsoft/com/cms/uss/ion/pwm/popupForm.cms";
    }

    @RequestMapping(value = "/popupInsert.do", method = RequestMethod.POST)
    public String popupInsert(ModelMap model,
                              MultipartHttpServletRequest multiRequest,
                             @ModelAttribute PopupVO mainVO) throws Exception {
        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        List<FileVO> result = null;
        String atchFileId = "";
        if (!files.isEmpty()) {
            result = fileMngUtil.parseFileInf(files, "POP_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
            mainVO.setAtchFileId(atchFileId);
        }
        popupManageService.insertPopup(mainVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/uss/ion/pwm/popupList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value="/popupUpdate.do", method = RequestMethod.POST)
    public String popupUpdate(@ModelAttribute PopupVO mainVO,
                              MultipartHttpServletRequest multiRequest,
                               ModelMap model) throws Exception {
        String atchFileId  = mainVO.getAtchFileId();
        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        final MultipartFile file = multiRequest.getFile("atchFile");

        if (file.getSize() > 0) {
            if ("".equals(atchFileId) || atchFileId == null) {
                List<FileVO> result = fileMngUtil.parseFileInf(files, "POP_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
                mainVO.setAtchFileId(atchFileId);
            } else {
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(atchFileId);
                fileMngService.deleteAllFileInf(fileVO);
                List<FileVO> result = fileMngUtil.parseFileInf(files, "POP_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
                mainVO.setAtchFileId(atchFileId);
            }
        }
        popupManageService.updatePopup(mainVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/uss/ion/pwm/popupList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value="/popupDelete.do", method = RequestMethod.POST)
    public String popupDlete(@ModelAttribute PopupVO mainVO,
                               ModelMap model) throws Exception {
        String atchFileId = mainVO.getAtchFileId();
        FileVO fileVO = new FileVO();

        if(atchFileId == null || atchFileId.equals("")){
            PopupVO resultData = popupManageService.selectPopupDetail(mainVO);
            fileVO.setAtchFileId(resultData.getAtchFileId());
        }else {
            fileVO.setAtchFileId(atchFileId);
        }

        fileMngService.deleteAllFileInf(fileVO);
        popupManageService.deletePopup(mainVO);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/uss/ion/pwm/popupList.do");

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping(value = "/popupOpen.do")
    public String popupOpen(@PathVariable(value="path") String path,
                            @RequestParam Map<String, Object> commandMap,
                            ModelMap model) throws Exception {
        String popupId = (String) commandMap.get("popupId");
        String stopVewAt = (String) commandMap.get("stopVewAt");

        PopupVO pvo = new PopupVO();
        pvo.setPopupId(popupId);
        PopupVO resultData = popupManageService.selectPopupDetail(pvo);
        model.addAttribute("page", path);
        model.addAttribute("stopVewAt", stopVewAt);
        model.addAttribute("popupId"  , popupId);
        model.addAttribute("atchFileId" , resultData.getAtchFileId());
        model.addAttribute("fileSn"     , resultData.getFileSn());

        return "jisungsoft/com/cms/uss/ion/pwm/sample/sample";
    }
}