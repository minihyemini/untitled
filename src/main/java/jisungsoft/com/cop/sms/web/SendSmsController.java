package jisungsoft.com.cop.sms.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.sms.service.SmsService;
import jisungsoft.com.cop.sms.service.SmsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

//@Controller
//@RequestMapping("/{path}/cop/sms")
public class SendSmsController {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SendSmsController.class);

//    @Resource(name = "smsService")
    private SmsService smsService;

    /** 공통 Service */
//    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;
    /**
     * File Service
     */
//    @Resource(name = "fileMngService")
    private FileMngService fileMngService;
    /**
     * FileMngUtil
     */
//    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    @RequestMapping(value = "/sndngSmsList.do")
    public String sndngSmsList(@ModelAttribute("smsVO") SmsVO smsVO, ModelMap model) throws Exception {
        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(smsVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(smsVO.getPageUnit());
        paginationInfo.setPageSize(smsVO.getPageSize());
        smsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        smsVO.setLastIndex(paginationInfo.getLastRecordIndex());
        smsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = smsService.selectSmsInfs(smsVO);
        int totCnt = smsService.selectSmsInfsCnt(smsVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);
        return "jisungsoft/com/cms/cop/sms/sndngSmsList.cms";
    }

    @RequestMapping(value = "/sndngSmsForm.do")
    public String sndngSmsForm(@ModelAttribute("smsVO") SmsVO smsVO, ModelMap model) throws Exception {
        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }

        //일반 사용자
//        CmmDetailUser cmmDetailUser = new CmmDetailUser();
//        cmmDetailUser.setUserSe("GNR");
//        List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);

//        model.addAttribute("userList", userList);


        return "jisungsoft/com/cms/cop/sms/sndngSmsForm.cms";
    }

    @RequestMapping(value = "/sndngSmsDetail.do")
    public String sndngSmsDetail(@ModelAttribute("smsVO") SmsVO smsVO, ModelMap model) throws Exception {
        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }

        SmsVO resultVO = smsService.selectSmsInf(smsVO);
        model.addAttribute("smsVO", resultVO);
        return "jisungsoft/com/cms/cop/sms/sndngSmsDetail.cms";
    }

    @RequestMapping(value = "/sndngSmsInsert.do")
    public String sndngSmsInsert(SmsVO smsVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }

        LOGGER.debug(smsVO.toString());
        smsService.insertSms(smsVO);

        model.addAttribute("resultMsg", "success.sms.send");
        model.addAttribute("redirectUri", "/cms/cop/sms/sndngSmsForm.do");
        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 엑셀 주소록 샘플 파일 download
     * @param commandMap(fileId, fileSn)
     * @return String/alert
     * @throws Exception
     */
    @RequestMapping(value = "/ex/fileDown.do")
    public String fileDown(@RequestParam Map<String, Object> commandMap,
                           HttpServletResponse response,
                           ModelMap model) throws Exception {
        try {

            String streFileNm = new File(SendSmsController.class.getResource("/").getFile()).getParentFile().getParentFile().getAbsolutePath() + "/attachments/addressBook.xlsx";
            fileMngUtil.downFile(response, streFileNm, "샘플 주소록.xlsx");

            return null;
        }catch (Exception e){
            model.addAttribute("resultMsg", "errors.file.found");
            return "jisungsoft/com/script/alertBack";
        }
    }
}
