package jisungsoft.com.mes.base.client.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.dto.mes.Pdfcilts;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 거래처 관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/base/client")
public class ClientDataRestController {
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "clientService")
    private ClientService clientService;

    /**
     *
     */
    @PostMapping("/detail.json")
    public ModelAndView detail(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setViewName("clientForm");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        Client formData = new Client();
        formData.setCltId(id);
        Client result = clientService.getClientDetail(formData);
        modelAndView.addObject("data", result);
        modelAndView.addObject("clientForm", result);

        return modelAndView;
    }

    /**
     * 엑셀 출력
     */
    @RequestMapping("/list/excel.json")
    public ModelAndView userList(Client formData) throws Exception {
        ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<?> dataList = clientService.getClientAllListMap(formData);

        String filename = "client_list";
        String[] columnArr = {
                "번호", "거래처명", "대표자성명", "업태",
                "사업자번호", "법인번호", "우편번호", "주소",
                "상세주소", "전화번호", "팩스번호", "이메일",
                "설립일자", "거래시작일", "거래종료일", "마감시작일"};
        String[] columnVarArr = {"idx", "cltNm", "cltOwnrnm", "cltBusstype",
                "cltBussnum", "cltCprtnum", "cltZip", "cltAddr",
                "cltDetailAddr", "cltTelno", "cltFaxnum", "cltEmail",
                "cltSetupdt", "cltDlbegindt", "cltDlendt", "cltDeadlndt"};

        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", "client_list");
        dataMap.put("list", dataList);

        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", filename);

        return mav;
    }
}
