package jisungsoft.com.cmm.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.service.Summernote;
import jisungsoft.com.cmm.service.SummernoteDataService;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cmm/summernote")
public class SummernoteRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SummernoteRestController.class);
    /**
     * FileMngUtil
     */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    /**
     * File Service
     */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    //로그인 사용자 정보 및 권한
    private LoginVO authenticatedUser;

    //썸머노트 세션명 + 사용자 고유ID
    private final String PREFIX_SESSION_NAME = "sumAttach_";

    @Resource(name = "summernoteDataService")
    private SummernoteDataService summernoteDataService;

    /**
     * summernote 이미지 업로드 시 로컬서버에 파일 업로드(데이터베이스 저장 제외)
     *
     * @param multiRequest (MultipartHttpServletRequest)
     * @param commandMap
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/upload.json")
    public ModelAndView summernoteUpload(MultipartHttpServletRequest multiRequest,
                                         HttpSession session,
                                         @RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");

        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            mav.addObject("result", "error");
            mav.addObject("msg", "200");
            return mav;
        }

        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

        //이미지 저장 경로
        List<MultipartFile> files = multiRequest.getFiles("files");

        for (MultipartFile file : files) {
            String orginFileName = file.getOriginalFilename();
            int index = orginFileName.lastIndexOf(".");
            String fileExt = orginFileName.substring(index + 1);
            String extDown = fileExt.toLowerCase();

            /* 확장자 체크 return 100 */
            boolean check = CommonUtil.isImageFile(extDown);
            if (!check) {
                mav.addObject("result", "error");
                mav.addObject("msg", "100");
                return mav;
            }
        }

        if (!files.isEmpty()) {
            Summernote summernote = new Summernote();
            summernote.setFiles(files);
            summernote.setUniqId(authenticatedUser.getUniqId());
            Summernote summernoteResult = summernoteDataService.summernoteFileSave(summernote);

            mav.addObject("result", "success");
            mav.addObject("uploadList", summernoteResult.getFileList());
        }

        return mav;
    }

    @RequestMapping(value = "/photoView.json")
    public void photoView(@RequestParam Map<String, Object> commandMap,
                          HttpSession session,
                          HttpServletResponse response)
            throws Exception {

        String fileNm = (String) commandMap.get("fileNm");
        String ext = (String) commandMap.get("ext");
        String orgFileNm = (String) commandMap.get("orgFileNm");

        FileVO fileVO = new FileVO();
        fileVO.setStreFileNm(fileNm);
        fileVO.setFileExtsn(ext);
        fileVO.setOrignlFileNm(orgFileNm);

        Summernote summernote = new Summernote();
        summernote.setResponse(response);
        summernote.setFile(fileVO);

        summernoteDataService.summernoteFileView(summernote);
    }

    /**
     * summernote 이미지 삭제
     *
     * @param commandMap
     * @return ModelAndView
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete.json")
    public void summernoteDelete(@RequestParam Map<String, Object> commandMap) throws Exception {
        String atchFileId = (String) commandMap.get("atchFileId");
        String fileSn = (String) commandMap.get("fileSn");
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        fvo.setFileSn(fileSn);
        fileMngService.deleteSummernoteImage(fvo);
    }
}
