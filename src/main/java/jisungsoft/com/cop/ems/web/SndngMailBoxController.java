package jisungsoft.com.cop.ems.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

//@Controller
//@RequestMapping("/{path}/cop/ems")
public class SndngMailBoxController {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SndngMailBoxController.class);

    /**
     * FileMngUtil
     */
//    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;
    /**
     * 공통 서비스
     */
//    @Resource(name = "cmmUseService")
    private CmmUseService cmmUseService;
    /**
     * 파일구분자
    */
    static final char FILE_SEPARATOR = File.separatorChar;
    /**
     * EgovPropertyService
    */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

//    @Resource(name = "sndngMailDtlsService")
    private SndngMailDtlsService sndngMailDtlsService;
    /**
     * 메일설정 서비스
     */
//    @Resource(name = "userEmailConfgService")
    private UserEmailConfgService userEmailConfgService;
    /**
     * 메일발송등록 서비스
     */
//    @Resource(name = "sndngMailRegistService")
    private SndngMailRegistService sndngMailRegistService;
    /**
     * 메일발송 서비스
     */
//    @Resource(name = "sndngMailService")
    private SndngMailService sndngMailService;

    @RequestMapping(value = "/sndngMailBoxList.do")
    public String sndngMailBoxList(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO, ModelMap model) throws Exception {

        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }
        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        UserEmailConfgVO userEmailConfgVO = new UserEmailConfgVO();
        userEmailConfgVO.setEmplyrId(authenticatedUser.getUniqId());
        userEmailConfgVO = userEmailConfgService.selectUserEmailConfgDetail(userEmailConfgVO);
        sndngMailVO.setDsptchPerson(userEmailConfgVO.getEmEmailAddress());

        // 발송메일 내역 조회
        /** EgovPropertyService.sample */
        sndngMailVO.setPageUnit(propertiesService.getInt("pageUnit"));
        sndngMailVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(sndngMailVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(sndngMailVO.getPageUnit());
        paginationInfo.setPageSize(sndngMailVO.getPageSize());

        sndngMailVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        sndngMailVO.setLastIndex(paginationInfo.getLastRecordIndex());
        sndngMailVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sndngMailList = sndngMailDtlsService.selectSndngMailList(sndngMailVO);
        int totCnt = sndngMailDtlsService.selectSndngMailListTotCnt(sndngMailVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultList", sndngMailList);
        model.addAttribute("sndngMailVO", sndngMailVO);

        return "jisungsoft/com/cms/cop/ems/box/sndngMailBoxList.cms";
    }

    @RequestMapping(value = "/sndngMailBoxForm.do")
    public String sndngMailBoxForm(@ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO,
                                   ModelMap model) throws Exception {

        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }

        CmmDetailUser cmmDetailUser = new CmmDetailUser();
        cmmDetailUser.setUserSe("GNR");
        List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);

        model.addAttribute("userList", userList);

        return "jisungsoft/com/cms/cop/ems/box/sndngMailBoxForm.cms";
    }

    /**
     * 발송메일을 등록한다
     * @param multiRequest MultipartHttpServletRequest
     * @param sndngMailVO SndngMailVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/insertSndngMail.do")
    public String insertSndngMail(final MultipartHttpServletRequest multiRequest,
                                  @ModelAttribute("sndngMailVO") SndngMailVO sndngMailVO,
                                  ModelMap model, HttpServletRequest request)
            throws Exception {

        //보안 취약점 처리
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "error.bbs.login");
            model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

            return "jisungsoft/com/script/alertHref";
        }

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        UserEmailConfgVO userEmailConfgVO = new UserEmailConfgVO();
        userEmailConfgVO.setEmplyrId(authenticatedUser.getUniqId());
        userEmailConfgVO = userEmailConfgService.selectUserEmailConfgDetail(userEmailConfgVO);

        if (userEmailConfgVO == null) {
            model.addAttribute("resultMsg", "fail.sendEmail.email6");
            model.addAttribute("redirectUri", "/cms/cop/ems/userEmailConfgForm.do");

            return "jisungsoft/com/script/alertHref";
        }

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");
        List<FileVO> result = null;
        String atchFileId = "";

        if (!files.isEmpty()) {
            result = fileMngUtil.parseFileInf(files, "EMS_", 0, "", "");
            if (result.size() > 0) {
                for (FileVO file : result) {
                    sndngMailVO.setOrignlFileNm(file.getOrignlFileNm());
                    sndngMailVO.setFileStreCours(file.getFileStreCours() + file.getStreFileNm() + "." + file.getFileExtsn());
                }
            }
        }

        sndngMailVO.setUserEmailConfgVO(userEmailConfgVO);
        sndngMailVO.setDsptchPerson(userEmailConfgVO.getEmEmailAddress());
        sndngMailVO.setHtml(true);
        sndngMailService.sndngMail(sndngMailVO);

        model.addAttribute("resultMsg", "success.sendEmail.email");
        model.addAttribute("redirectUri", "/cms/cop/ems/sndngMailBoxForm.do");

        return "jisungsoft/com/script/alertHref";
    }
}
