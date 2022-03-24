package jisungsoft.com.mes.base.group.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.persistence.dto.sec.Group;
import jisungsoft.com.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Name  : GroupController.java
 * Description : 그룹관리 등록/수정/삭제 처리하는 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/mes/base/group")
public class GroupApiController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "groupService")
    private GroupService groupService;

    /**
     * 그룹 상세 데이터
     */
    @PostMapping("/detail.json")
    public ModelAndView detail(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        Group group = new Group();
        group.setGroupId(id);
        Group result = groupService.getGroupDetail(group);

        modelAndView.addObject("data", result);

        return modelAndView;
    }

    /**
     * 엑셀 출력
     */
    @RequestMapping("/list/excel.json")
    public ModelAndView userList(Group formData) throws Exception {
        ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<Group> dataList = groupService.getGroupAllListMap(formData);


        String filename = "group_list";
        String[] columnArr = {
                "번호", "그룹ID", "그룹명", "그룹설명"};
        String[] columnVarArr = {"idx", "groupId", "groupNm", "groupDc"};

        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", "group_list");
        dataMap.put("list", dataList);

        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", filename);

        return mav;
    }
}
