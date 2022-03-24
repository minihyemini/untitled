package jisungsoft.com.mes.base.pdnum.api;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.service.PdNumService;
import jisungsoft.com.uss.umt.model.UserDefaultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mes/base/pdnum")
public class PdNumDataRestController {
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "pdNumService")
    private PdNumService pdNumService;

    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @PostMapping("/detail.json")
    public ModelAndView detail(Pdnum formData) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        Pdnum result = pdNumService.getPdNumDetail(formData);
        if (StringUtils.hasText(result.getImgFileId())) {
            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(result.getImgFileId());
            result.setImgFileList(fileMngService.selectFileInfs(fileVO));
        }
        if (StringUtils.hasText(result.getAtchFileId())) {
            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(result.getAtchFileId());
            result.setAtchFileList(fileMngService.selectFileInfs(fileVO));
        }

        modelAndView.addObject("data", result);

        return modelAndView;
    }

    @PostMapping("/list.json")
    public ModelAndView list(Pdnum formData) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<Pdnum> resultList = pdNumService.getPdNumAllList(formData);
        modelAndView.addObject("dataList", resultList);

        return modelAndView;
    }

    /**
     * 엑셀 출력
     */
    @RequestMapping("/list/excel.json")
    public ModelAndView userList(Pdnum formData) throws Exception {
        ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();

        String filename = "product_number_list";
        String[] columnArr = {"번호", "성명", "아이디", "가입일"};
        String[] columnVarArr = {"idx", "mberNm", "mberId", "sbscrbDe"};

        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", "product number list");
        dataMap.put("list", null);

        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", filename);

        return mav;
    }
}
