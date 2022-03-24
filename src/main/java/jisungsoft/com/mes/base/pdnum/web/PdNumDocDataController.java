package jisungsoft.com.mes.base.pdnum.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.mes.base.pdnum.PdNumCode;
import jisungsoft.com.mes.base.pdnum.validation.PdNumFormValidator;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnDetailCodeService;
import jisungsoft.com.service.PdNumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/mes/base/pdnum/doc")
public class PdNumDocDataController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdNumService")
    private PdNumService pdNumService;
    /**
     * 상세코드 서비스
     */
    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    /** FileMngUtil */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;


    private final String VIEW_PATH = "jisungsoft/com/mes/base/pdnum/doc";
    private final String REDIRECT_PATH = "/mes/base/pdnum/doc";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("pdNumForm")
    public Pdnum newPdNumForm() {
        return new Pdnum();
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute Pdnum pdnum,
                       final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        List<Pdnum> resultList = pdNumService.getPdNumAllList(pdnum);

        model.addAttribute("resultList", resultList);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("pdNumForm") Pdnum form,
                             final BindingResult bindingResult, Locale locale,
                             final MultipartHttpServletRequest multiRequest,
                             final RedirectAttributes attr) {

        attr.addFlashAttribute("menuId", form.getMenuId());

        Pdnum detail = pdNumService.getPdNumDetail(form);
        if (detail == null) {
            bindingResult.rejectValue("pdnumId", "required", "required");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "pdNumForm", bindingResult);
            attr.addFlashAttribute("pdNumForm", form);

            return "redirect:"+REDIRECT_PATH+"/list.do";
        }

        form = detail;

        try {
            final List<MultipartFile> files = multiRequest.getFiles("atchFile");
            final List<MultipartFile> thumbFiles = multiRequest.getFiles("imgFile");
            List<FileVO> result = null;
            String atchFileId = "";
            String imgFileId = "";

            if (!files.isEmpty()) {
                result = fileMngUtil.parseFileInf(files, "MES_", 0, "", "");
                atchFileId = fileMngService.insertFileInfs(result);
                form.setAtchFileId(atchFileId);
            }
            if (!thumbFiles.isEmpty()) {
                result = fileMngUtil.parseFileInf(thumbFiles, "MES_", 0, "", "");
                imgFileId = fileMngService.insertFileInfs(result);
                form.setImgFileId(imgFileId);
            }

            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            pdNumService.editPdNum(form);
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.update");
        }

        return "redirect:"+REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute("pdNumForm") Pdnum form,
                               final BindingResult bindingResult, Model model, Locale locale,
                               final RedirectAttributes attr) {

        try {
            pdNumService.removePdNum(form);
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", "fail.common.delete");
        }

        return "redirect:"+REDIRECT_PATH+"/list.do";
    }
}
