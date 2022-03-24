package jisungsoft.com.cmm.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Map;

@Controller
@RequestMapping("/cmm")
public class ComUtlController {
    /**
     * File Service
     */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;
    /**
     * FileMngUtil
     */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 이미지 view
     * @param commandMap
     * @return void
     * @throws Exception
     */
    @RequestMapping(value = "/photoView.do")
    public void photoView(@RequestParam Map<String, Object> commandMap, HttpServletResponse response)
            throws Exception {
        String src        = (String) commandMap.get("src");
        String atchFileId = (String) commandMap.get("atchFileId");
        String atchFileSn = (String) commandMap.get("sn");

        if(atchFileId != null){
            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(atchFileId);
            fileVO.setFileSn(atchFileSn);
            FileVO fvo = fileMngService.selectFileInf(fileVO);

            fileMngUtil.photoView(fvo, response);
        }
    }

    /**
     * 파일 download
     * @param commandMap(fileId, fileSn)
     * @return String/alert
     * @throws Exception
     */
    @RequestMapping(value = "/fileDown.do")
    public String fileDown(@RequestParam Map<String, Object> commandMap,
                           HttpServletResponse response,
                           ModelMap model) throws Exception {
        try {
            FileVO fileVO = new FileVO();
            String fileId = (String) commandMap.get("fileId");
            String fileSn = (String) commandMap.get("fileSn");

            fileVO.setAtchFileId(fileId);
            fileVO.setFileSn(fileSn);
            FileVO fvo = fileMngService.selectFileInf(fileVO);

            String streFileNm = fvo.getFileStreCours() + fvo.getStreFileNm() + "." + fvo.getFileExtsn();
            fileMngUtil.downFile(response, streFileNm, fvo.getOrignlFileNm());
            return null;
        }catch (Exception e){
            model.addAttribute("resultMsg", "errors.file.found");
            return "jisungsoft/com/script/alertBack";
        }
    }

    /**
     * 파일 delete
     * @param commandMap(fileId, fileSn, fileUrl)
     * @return String/alert
     * @throws Exception
     */
    @RequestMapping(value = "/fileDelete.do")
    public String fileDelete(@RequestParam Map<String, Object> commandMap,
                             HttpServletRequest request,
                             RedirectAttributes attr,
                             ModelMap model) throws Exception {

        FileVO fileVO = new FileVO();
        String fileId = (String) commandMap.get("fileId");
        String fileSn = (String) commandMap.get("fileSn");
        String menuId = (String) commandMap.get("menuId");

        attr.addFlashAttribute("menuId", menuId);
        String referer = request.getHeader("REFERER");
        URL url = new URL(referer);

        try {

            fileVO.setAtchFileId(fileId);
            fileVO.setFileSn(fileSn);
            fileMngService.deleteFileInf(fileVO);

            attr.addAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete"));

        }catch (Exception e){
            attr.addAttribute("resultMessage", egovMessageSource.getMessage("errors.file.delete"));
        }

        return "redirect:"+url.getPath();
    }
}
