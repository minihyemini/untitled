package jisungsoft.com.uss.umt.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cmm.service.CmmDetailCode;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.service.GroupService;
import jisungsoft.com.sec.gmt.service.GroupManageVO;
import jisungsoft.com.service.AuthorGroupService;
import jisungsoft.com.uss.umt.model.UserManageVO;
import jisungsoft.com.uss.umt.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class Name  : UserManageController.java
 * Description : 일반회원(사용자)를 등록/수정/삭제 처리하는 컨트롤러  Business class
 * Menu : CMS
 */
//@Controller
//@RequestMapping("/cms/uss/umt")
public class UserManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageController.class);

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    /**
     * 업무사용자 관리 서비스
     */
//    @Resource(name = "UserManageService")
    private UserManageService userManageService;
    /**
     * 부서관리 서비스
     */
//    @Resource(name = "deptManageService")
//    DeptManageService deptManageService;
    /**
     * 그룹관리 서비스
     */
//    @Resource(name = "groupManageService")
    GroupService groupService;
    /**
     * 권한관리 서비스
     */
//    @Resource(name = "authorGroupService")
    AuthorGroupService authorGroupService;
    /**
     * 코드 서비스
     */
//    @Resource(name = "cmmUseService")
    CmmUseService cmmUseService;

    /**
     * 업무회원 목록 조회 (pageing)
     * @param userSearchVO 검색조건정보
     * @param model 화면모델
     * @return uss/umt/EgovMberManage
     * @throws Exception
     */
    @RequestMapping(value = "/userManageList.do")
    public String userList(@ModelAttribute("userSearchVO") UserManageVO userSearchVO, ModelMap model) throws Exception {

        /** EgovPropertyService */
        userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
        paginationInfo.setPageSize(userSearchVO.getPageSize());

        userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> userList = userManageService.selectUserList(userSearchVO);
        int totCnt = userManageService.selectUserListTotCnt(userSearchVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("userSearchVO", userSearchVO);
        model.addAttribute("resultList", userList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/uss/umt/user/userManageList.cms";
    }

    /**
     * 업무회원 등록/수정 폼
     * @param uniqId 상세조회대상 사용자아이디
     * @param userManageVO 검색조건
     * @param model 화면모델
     * @return uss/umt/EgovUserSelectUpdt
     * @throws Exception
     */
    @RequestMapping(value = "/userManageForm.do", method = RequestMethod.POST)
    public String userForm(@RequestParam("uniqId") String uniqId,
                           @ModelAttribute("userManage") UserManageVO userManageVO,
                           @RequestParam Map<String, Object> commandMap,
                           ModelMap model) throws Exception {

        String mode = commandMap.get("mode") == null ? "" : commandMap.get("mode").toString();

        //공통코드 인풋
        String[] codeArray = {
                "MEM001",   //회원구분 코드
                "MEM002",   //사용자유형 코드
                "MEM003",   //사용자상태 코드
                "MEM004",   //성별구분 코드
                "MEM005",   //이메일 코드
        };
        List<DefaultCodeVO> voList = new ArrayList<>();
        for (int i=0; i<codeArray.length; i++) {
            DefaultCodeVO defaultCodeVO = new DefaultCodeVO();
            defaultCodeVO.setCodeId(codeArray[i]);
            voList.add(defaultCodeVO);
        }
        Map<String, List<CmmDetailCode>> stringListMap = cmmUseService.selectCmmCodeDetails(voList);
        //model input
        for (int i=0; i<codeArray.length; i++) {
            model.addAttribute(codeArray[i], stringListMap.get(codeArray[i]));
        }

        //그룹정보
        GroupManageVO groupManageVO = new GroupManageVO();
//        List<?> groupList = groupService.getGroupAllList(groupManageVO);

        //부서정보
//        DeptManageVO deptManageVO = new DeptManageVO();
//        List<DeptManageVO> deptManageList = deptManageService.selectDeptManageNPList(deptManageVO);
//        List<?> authorList = authorGroupService.selectAuthorList();

        UserManageVO userManage = userManageService.selectUser(uniqId);
        if (userManage == null) {
            userManage = new UserManageVO();
        }

        userManage.setPageIndex(userManageVO.getPageIndex());
        userManage.setSearchKeyword(userManageVO.getSearchKeyword());
        userManage.setSearchCondition(userManageVO.getSearchCondition());

//        model.addAttribute("deptManageList", deptManageList);
//        model.addAttribute("groupList", groupList);
//        model.addAttribute("authorList", authorList);
        model.addAttribute("userManage", userManage);

        if(mode.equals("detail")){
            return "jisungsoft/com/cms/uss/umt/user/userManageDetail.cms";
        }

        return "jisungsoft/com/cms/uss/umt/user/userManageForm.cms";
    }

    /**
     * 업무회원 등록
     * @param userManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userManageInsert.do", method = RequestMethod.POST)
    public String userinsert(@ModelAttribute("userManage") UserManageVO userManageVO, ModelMap model) throws Exception{
        userManageService.insertUser(userManageVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/uss/umt/userManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 업무회원 수정
     * @param userManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userManageUpdate.do", method = RequestMethod.POST)
    public String userUpdate(@ModelAttribute("userManage") UserManageVO userManageVO, ModelMap model) throws Exception{

        userManageService.updateUser(userManageVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/uss/umt/userManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 업무회원 삭제
     * @param userManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userManageDelete.do", method = RequestMethod.POST)
    public String userDelete(@ModelAttribute("userManage") UserManageVO userManageVO, ModelMap model) throws Exception{

        String checkedIdForDel =  userManageVO.getUniqId();
        userManageService.deleteUser(checkedIdForDel);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/uss/umt/userManageList.do");

        return "jisungsoft/com/script/alertHref";
    }
}
