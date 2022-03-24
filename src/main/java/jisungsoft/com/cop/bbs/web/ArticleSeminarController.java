package jisungsoft.com.cop.bbs.web;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.bbs.service.ArticleSeminarService;
import jisungsoft.com.cop.bbs.service.ArticleSeminarVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//@Controller
//@RequestMapping("/{path}/cop/bbs")
public class ArticleSeminarController {

    /**
     * Service
     */
//    @Resource(name = "articleSeminarService")
    private ArticleSeminarService articleSeminarService;

    /**
     * FileMngUtil
     */
//    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;
    /**
     * File Service
     */
//    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    LoginVO authenticatedUser;
    private String redirectUri = "/cop/bbs/seminarList.do";

    @RequestMapping("/seminarList.do")
    public String seminarList(@PathVariable(value="path") String path,
                              @ModelAttribute("articleSeminarVO") ArticleSeminarVO articleSeminarVO,
                              ModelMap model) throws Exception
    {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(articleSeminarVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(articleSeminarVO.getPageUnit());
        paginationInfo.setPageSize(articleSeminarVO.getPageSize());

        articleSeminarVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        articleSeminarVO.setLastIndex(paginationInfo.getLastRecordIndex());
        articleSeminarVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> resultList = articleSeminarService.selectSeminarList(articleSeminarVO);
        paginationInfo.setTotalRecordCount(articleSeminarService.selectSeminarListCnt(articleSeminarVO));

        model.addAttribute("articleSeminarVO" , articleSeminarVO);
        model.addAttribute("resultList"       , resultList);
        model.addAttribute("paginationInfo"   , paginationInfo);

        return "jisungsoft/com/" + path + "/cop/bbs/seminar/seminarList."+path;
    }

    @RequestMapping("/seminarDetail.do")
    public String seminarDetail(@PathVariable(value="path") String path,
                                ArticleSeminarVO mainVO,
                                ModelMap model) throws Exception
    {
        ArticleSeminarVO resultData = articleSeminarService.selectSeminarDetail(mainVO);
        Boolean isLogin = EgovUserDetailsHelper.isAuthenticated();
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        if (isLogin) {
            resultData.setSessionUniqId(authenticatedUser.getUniqId());
            resultData.setEsntlId(authenticatedUser.getUniqId());
        }

        FileVO fileVO = new FileVO();
        fileVO.setAtchFileId(resultData.getAtchFileId());
        List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

        int seRegCnf = articleSeminarService.selectSeminarMberCnf(resultData);

        model.addAttribute("seRegCnf", seRegCnf);
        model.addAttribute("atchFileList", atchFileList);
        model.addAttribute("articleSeminarVO", resultData);

        return "jisungsoft/com/" + path + "/cop/bbs/seminar/seminarDetail."+path;
    }

    @RequestMapping("/seminarForm.do")
    public String seminarForm(@PathVariable(value="path") String path,
                              ArticleSeminarVO mainVO,
                              @RequestParam(value = "mode") String mode,
                              ModelMap model) throws Exception
    {
        switch (mode) {
            case "Insert":
                model.addAttribute("articleSeminarVO", new ArticleSeminarVO());
                break;
            case "Update":
                ArticleSeminarVO resultData = articleSeminarService.selectSeminarDetail(mainVO);

                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(resultData.getAtchFileId());
                List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

                model.addAttribute("atchFileList", atchFileList);
                model.addAttribute("articleSeminarVO", resultData);
                break;
        }
        return "jisungsoft/com/" + path + "/cop/bbs/seminar/seminarForm."+path;
    }

    @RequestMapping("/seminarInsert.do")
    public String seminarInsert(@PathVariable(value="path") String path,
                                ArticleSeminarVO mainVO,
                                @RequestParam Map<String, Object> commandMap,
                                MultipartHttpServletRequest multiRequest,
                                HttpServletRequest request,
                                ModelMap model) throws Exception
    {
        switch (path) {
            case "cms":
                final List<MultipartFile> files = multiRequest.getFiles("atchFile");
                List<FileVO> result = null;
                String atchFileId = "";

                if (!files.isEmpty()) {
                    result = fileMngUtil.parseFileInf(files, "SMN_", 0, "", "");
                    atchFileId = fileMngService.insertFileInfs(result);
                    mainVO.setAtchFileId(atchFileId);
                }
                articleSeminarService.insertSeminar(mainVO);
                break;
            case "page":
                /*사용자가 세미나를 신청하는 경우*/
                break;
        }
        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/"+path+redirectUri);

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping("/seminarUpdate.do")
    public String seminarUpdate(@PathVariable(value="path") String path,
                                ArticleSeminarVO mainVO,
                                @RequestParam Map<String, Object> commandMap,
                                MultipartHttpServletRequest multiRequest,
                                HttpServletRequest request,
                                ModelMap model) throws Exception
    {
        String atchFileId  = mainVO.getAtchFileId();
        String seId = mainVO.getSeId();

        final List<MultipartFile> files = multiRequest.getFiles("atchFile");

        if (!files.isEmpty()) {
            if ("".equals(atchFileId) || atchFileId == null) {
                List<FileVO> result = fileMngUtil.parseFileInf(files, "SMN_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
                mainVO.setAtchFileId(atchFileId);
            } else {
                FileVO fileVO = new FileVO();
                fileVO.setAtchFileId(atchFileId);
                int cnt = fileMngService.getMaxFileSN(fileVO);
                List<FileVO> _result = fileMngUtil.parseFileInf(files, "SMN_", cnt, atchFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }
        articleSeminarService.updateSeminar(mainVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/"+path+redirectUri);

        return "jisungsoft/com/script/alertHref";
    }

    @RequestMapping("/seminarDelete.do")
    public String seminarDelete(@PathVariable(value="path") String path,
                                ArticleSeminarVO mainVO,
                                ModelMap model) throws Exception
    {
        try{
            FileVO fileVO = new FileVO();
            String atchFileId = mainVO.getAtchFileId();

            if(atchFileId != null){
                fileVO.setAtchFileId(atchFileId);
                fileMngService.deleteFileInf(fileVO);
                fileMngService.deleteAllFileInf(fileVO);
            }

            articleSeminarService.deleteSeminar(mainVO);

            model.addAttribute("resultMsg", "success.common.delete");
            model.addAttribute("redirectUri", "/"+path+redirectUri);

            return "jisungsoft/com/script/alertHref";
        }catch (Exception e){
            model.addAttribute("resultMsg", "errors.sub.delete");
            model.addAttribute("resultArg", "세미나 신청자");

            return "jisungsoft/com/script/alertBack";
        }

    }

    /*세미나 신청*/
    @RequestMapping("/seminarApply.do")
    public String seminarApply(@PathVariable(value="path") String path,
                               ArticleSeminarVO mainVO,
                               ModelMap model) throws Exception
    {
        try {
            Boolean isLogin = EgovUserDetailsHelper.isAuthenticated();
            authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

            if (isLogin) {
                mainVO.setSessionUniqId(authenticatedUser.getUniqId());
                mainVO.setAuthority(authenticatedUser.getAuthority());
                mainVO.setEsntlId(authenticatedUser.getUniqId());
            } else {
                /* 로그인 정보가 없을 경우 */
                model.addAttribute("resultMsg", "errors.seminar.apply");
                model.addAttribute("resultArg", "로그인에 실패");

                return "jisungsoft/com/script/alertBack";
            }

            ArticleSeminarVO svo = articleSeminarService.selectSeminarDetail(mainVO);
            int seMaxPp = svo.getSeMaxPp(); //세미나 정원
            int seCurPp = articleSeminarService.selectSeminarMberCnt(mainVO); //현재 신청 인원

            int seRegCnf = articleSeminarService.selectSeminarMberCnf(mainVO);
            if (seRegCnf >= 1) {
                /* 중복 신청할 경우 */
                model.addAttribute("resultMsg", "errors.seminar.apply");
                model.addAttribute("resultArg", "중복 신청");

                return "jisungsoft/com/script/alertBack";
            } else if (seCurPp >= seMaxPp) {
                /* 정원이 초과할 경우 */
                model.addAttribute("resultMsg", "errors.seminar.apply");
                model.addAttribute("resultArg", "정원이 초과");

                return "jisungsoft/com/script/alertBack";
            } else {
                /*  행사 및 세미나 신청 */
                articleSeminarService.insertSeminarMber(mainVO);
                model.addAttribute("resultMsg", "success.seminar.apply");
                model.addAttribute("redirectUri", "/"+path+redirectUri);

                return "jisungsoft/com/script/alertHref";
            }
        }catch (Exception e) {
            model.addAttribute("resultMsg", "errors.seminar.apply");
            model.addAttribute("resultArg", "오류로 인");

            return "jisungsoft/com/script/alertBack";
        }
    }

    /*세미나 신청취소*/
    @RequestMapping("/seminarCancel.do")
    public String seminarCancel(@PathVariable(value="path") String path,
                                ArticleSeminarVO mainVO,
                                ModelMap model) throws Exception
    {
        Boolean isLogin = EgovUserDetailsHelper.isAuthenticated();
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        if (isLogin) {
            mainVO.setSessionUniqId(authenticatedUser.getUniqId());
            mainVO.setAuthority(authenticatedUser.getAuthority());
            mainVO.setEsntlId(authenticatedUser.getUniqId());
        }

        articleSeminarService.deleteSeminarMber(mainVO);

        model.addAttribute("resultMsg", "success.seminar.cancel");
        model.addAttribute("redirectUri", "/"+path+redirectUri);

        return "jisungsoft/com/script/alertHref";
    }
}
