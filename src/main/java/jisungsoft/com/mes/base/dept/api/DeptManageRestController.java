package jisungsoft.com.mes.base.dept.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.mes.base.dept.validation.DeptAddFormValidator;
import jisungsoft.com.mes.base.dept.validation.DeptEditormValidator;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Name  : DeptManageController.java
 * Description : 부서관리 등록/수정/삭제 처리하는 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/mes/base/dept")
public class DeptManageRestController {


    /**
     * 부서 서비스
     */
    @Resource(name = "deptService")
    private DeptService deptService;

    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @PostMapping("/detail.json")
    public ModelAndView detail(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        Dept formData = new Dept();
        formData.setOrgnztId(id);
        Dept result = deptService.getDeptDetail(formData);

        modelAndView.addObject("data", result);

        return modelAndView;
    }

    @PostMapping("/list.json")
    public ModelAndView list(Dept formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<Dept> dataList = deptService.getDeptAllList(formData);
        modelAndView.addObject("dataList", dataList);

        return modelAndView;
    }

    /**
     * 엑셀 출력
     */
    @RequestMapping("/list/excel.json")
    public ModelAndView exceList(Dept formData) throws Exception {
        ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<?> dataList = deptService.getDeptAllListMap(formData);

        String filename = "dept_list";
        String[] columnArr = {
                "번호", "부서ID", "부서명", "부서설명"};
        String[] columnVarArr = {"idx", "orgnztId", "orgnztNm", "orgnztDc"};

        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", "dept_list");
        dataMap.put("list", dataList);

        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", filename);

        return mav;
    }
}
