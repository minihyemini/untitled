package jisungsoft.com.uss.umt.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cmm.service.CmmDetailCode;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.GroupService;
import jisungsoft.com.sec.gmt.service.GroupManageVO;
import jisungsoft.com.uss.umt.model.MberManageVO;
import jisungsoft.com.uss.umt.model.UserDefaultVO;
import jisungsoft.com.uss.umt.service.MberManageService;
import jisungsoft.com.uss.umt.service.UserManageService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Name  : MberManageController.java
 * Description : 업무회원(관리자)를 등록/수정/삭제 처리하는 컨트롤러
 * Menu : CMS
 */
//@Controller
//@RequestMapping("/cms/uss/umt")
public class MberManageController {

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** mberManageService */
//    @Resource(name = "mberManageService")
    private MberManageService mberManageService;

//    @Resource(name = "deptManageService")
//    DeptManageService deptManageService;

//    @Resource(name = "groupManageService")
    GroupService groupService;

//    @Resource(name = "authorGroupService")
//    AuthorGroupService authorGroupService;

    /**
     * 업무회원 서비스
     */
//    @Resource(name = "UserManageService")
    private UserManageService userManageService;

    /**
     * 코드 서비스
     */
//    @Resource(name = "cmmUseService")
    CmmUseService cmmUseService;

    //로그인 사용자 정보 및 권한
    private LoginVO authenticatedUser;

    /**
     * 일반회원목록 (pageing)
     * @param userSearchVO 검색조건정보
     * @param model 화면모델
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberManageList.do")
    public String mberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
                           ModelMap model) throws Exception {

        // 미인증 사용자에 대한 보안처리
//        Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
//        if (!isAuthenticated) {
//            return "index";
//        }

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

        //공통코드 인풋
        cmmCodeInput(model);

        List<?> mberList = mberManageService.selectMberList(userSearchVO);
        int totCnt = mberManageService.selectMberListTotCnt(userSearchVO);
        paginationInfo.setTotalRecordCount(totCnt);

//        List<?> authorList = authorGroupService.selectAuthorList();

        model.addAttribute("userSearchVO", userSearchVO);
        model.addAttribute("resultList", mberList);
//        model.addAttribute("authorList", authorList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/cms/uss/umt/mber/mberManageList.cms";
    }

    /**
     * 일반회원 폼
     * @param uniqId 상세조회대상 사용자아이디
     * @param mberManageVO 검색조건
     * @param model 화면모델
     * @return uss/umt/EgovUserSelectUpdt
     * @throws Exception
     */
    @RequestMapping(value = "/mberManageForm.do", method = RequestMethod.POST)
    public String mberForm(@RequestParam("uniqId") String uniqId,
                           HttpServletRequest request,
                           @ModelAttribute("mberManage") MberManageVO mberManageVO, ModelMap model) throws Exception {

        //공통코드 인풋
        cmmCodeInput(model);

        GroupManageVO groupManageVO = new GroupManageVO();
//        List<?> groupList = groupService.getGroupAllList(groupManageVO);
//        List<?> authorList = authorGroupService.selectAuthorList();

//        model.addAttribute("groupList", groupList);
//        model.addAttribute("authorList", authorList);

//        DeptManageVO deptManageVO = new DeptManageVO();
//        List<DeptManageVO> deptManageNPList = deptManageService.selectDeptManageNPList(deptManageVO);
//        model.addAttribute("deptManageNPList", deptManageNPList);

        MberManageVO mberManage = mberManageService.selectMber(uniqId);
        if (mberManage == null) {
            mberManage = new MberManageVO();
        }

        mberManage.setPageIndex(mberManageVO.getPageIndex());
        mberManage.setSearchKeyword(mberManageVO.getSearchKeyword());
        mberManage.setSearchCondition(mberManageVO.getSearchCondition());

        model.addAttribute("mberManage", mberManage);

        return "jisungsoft/com/cms/uss/umt/mber/mberManageForm.cms";
    }

    /**
     * 일반회원 등록
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberManageInsert.do", method = RequestMethod.POST)
    public String mberinsert(@ModelAttribute("mberManage") MberManageVO mberManageVO,
                             ModelMap model) throws Exception{

        //생년월일 validation
        if (!mberManageVO.validateionMberBrthdy()) {
            model.addAttribute("resultMsg", "fail.register.birthday");

            return "jisungsoft/com/script/alertBack";
        }

        String mberId = mberManageVO.getMberEmailAdres();
        int resultMberCnt = userManageService.checkIdDplct(mberId);
        if (resultMberCnt > 0) {
            model.addAttribute("resultMsg", "fail.common.idDuplicate");

            return "jisungsoft/com/script/alertBack";
        }

        mberManageService.insertMber(mberManageVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/cms/uss/umt/mberManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 일반회원 수정
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberManageUpdate.do", method = RequestMethod.POST)
    public String mberUpdate(@ModelAttribute("mberManage") MberManageVO mberManageVO,
                             ModelMap model) throws Exception{

        //생년월일 validation
        if (!mberManageVO.validateionMberBrthdy()) {
            model.addAttribute("resultMsg", "fail.register.birthday");

            return "jisungsoft/com/script/alertBack";
        }

        mberManageService.updateMber(mberManageVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/cms/uss/umt/mberManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 일반회원 삭제
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberManageDelete.do", method = RequestMethod.POST)
    public String mberDelete(@ModelAttribute("mberManage") MberManageVO mberManageVO, ModelMap model) throws Exception{

        String checkedIdForDel =  mberManageVO.getUniqId();
        mberManageService.deleteMber(checkedIdForDel);

        model.addAttribute("resultMsg", "success.common.delete");
        model.addAttribute("redirectUri", "/cms/uss/umt/mberManageList.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 공통코드 add
     * @param model
     * @throws Exception
     */
    public void cmmCodeInput(ModelMap model) throws Exception {

        //공통코드 인풋
        String[] codeArray = {
                "MEM001",   //회원구분 코드
                "MEM002",   //사용자유형 코드
                "MEM003",   //사용자상태 코드
                "MEM004",   //성별구분 코드
                "MEM005",   //이메일 코드
                "MEM006",   //사용자 직업 코드
                "MEM007",   //사용자 전공 코드
                "MEM008",   //사용자 소속 규모 코드
                "MEM009",   //사용자 산업 분류 코드
                "MEM010",   //사용자 교육목적분류 코드
                "MEM011"    //사용자 신청경로 분류 코드
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

//        List<?> cmmGroups = cmmUseService.selectCmmGroupList(new CmmGroup());

//        GroupManageVO groupManageVO = new GroupManageVO();
//        List<?> groupList = groupManageService.selectGroupNPList(groupManageVO);
    }

    @RequestMapping("/userListExcel.do")
    public ModelAndView userList(UserDefaultVO userSearchVO) throws Exception {
        ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();

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

        List<?> mberList = mberManageService.selectMberList(userSearchVO);
        String filename = "게시물 목록_Test";
        String[] columnArr = {"번호", "성명", "아이디", "가입일"};
        String[] columnVarArr = {"idx", "mberNm", "mberId", "sbscrbDe"};

        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", "게시물 목록");
        dataMap.put("list", mberList);

        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", filename);

        return mav;
    }
}
