package jisungsoft.com.cmm.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.CalendarService;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cmm")
class ComUtlRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComUtlRestController.class);

    /** Calendar Service */
    @Resource(name = "calendarService")
    private CalendarService calendarService;

    /**
     * File Service
     */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @ResponseBody
    @RequestMapping(value = "/fileDelete.json", method = RequestMethod.POST)
    public ModelAndView fileDelete(@RequestParam Map<String, Object> commandMap) throws Exception
    {
        ModelAndView mav      = new ModelAndView("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            mav.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return mav;
        }

        try {
            FileVO fileVO = new FileVO();
            String fileId = (String) commandMap.get("fileId");
            String fileSn = (String) commandMap.get("fileSn");

            fileVO.setAtchFileId(fileId);
            fileVO.setFileSn(fileSn);
            fileMngService.deleteFileInf(fileVO);

            fileVO = new FileVO();
            fileVO.setAtchFileId(fileId);
            List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);

            mav.addObject("atchFileList", atchFileList);
            mav.addObject("result", "success");
            mav.addObject("msg", egovMessageSource.getMessage("success.common.delete"));
        }catch (Exception e){
            mav.addObject("result", "fail");
            mav.addObject("msg", egovMessageSource.getMessage("fail.common.delete"));
        }

        return mav;
    }
}
