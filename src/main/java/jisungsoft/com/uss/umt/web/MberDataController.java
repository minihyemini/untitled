package jisungsoft.com.uss.umt.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.cmm.service.CmmDetailCode;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.RegMberAuthService;
import jisungsoft.com.cop.ems.service.RegMberAuthVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.GroupService;
import jisungsoft.com.service.AuthorGroupService;
import jisungsoft.com.service.CmmnCodeService;
import jisungsoft.com.service.CmmnDetailCodeService;
import jisungsoft.com.uss.sam.stp.service.StplatManageService;
import jisungsoft.com.uss.sam.stp.service.StplatVO;
import jisungsoft.com.uss.umt.model.MberManageVO;
import jisungsoft.com.uss.umt.service.MberManageService;
import jisungsoft.com.uss.umt.service.UserManageService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  Class Name  : MberDataController.java
 *  Description : 일반 회원정보, 일반회원가입, 이용약관 정보 처리 컨트롤러
 *  Menu : PAGE > 일반회원가입
 *       : LMS > 일반회원정보 > 회원정보수정 (ROLE_USER, ROLE_MANAGER)
 *       : CMS > 일반회원 등록/수정/삭제
 */
//@Controller
//@RequestMapping("/{path}/uss/umt")
public class MberDataController {

    /** EgovPropertyService */
//    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    /**
     * 일반회원 서비스
     */
//    @Resource(name = "mberManageService")
    private MberManageService mberManageService;
    /**
     * 업무회원 서비스
     */
//    @Resource(name = "UserManageService")
    private UserManageService userManageService;
    /**
     * 부서관리 서비스
     */
//    @Resource(name = "deptManageService")
//    DeptManageService deptManageService;
    /**
     * Code
     */
//    @Resource(name = "cmmnCodeManageService")
    private CmmnCodeService cmmnCodeManageService;
    /**
     * Code Detail
     */
//    @Resource(name = "cmmnDetailCodeManageService")
    private CmmnDetailCodeService cmmnDetailCodeManageService;
    /**
     *
     */
//    @Resource(name = "authorGroupService")
    AuthorGroupService authorGroupService;
    /**
     *
     */
//    @Resource(name = "stplatManageService")
    private StplatManageService stplatManageService;
    /**
     * 그룹관리 서비스
     */
//    @Resource(name = "groupManageService")
    GroupService groupService;
    /**
     * 공통 서비스
     */
//    @Resource(name = "cmmUseService")
    CmmUseService cmmUseService;

    /**
     * 인증키 서비스
     */
//    @Resource(name = "regMberAuthService")
    private RegMberAuthService regMberAuthService;
    /**
     * 로그인 사용자 정보 및 권한
     */
    private LoginVO authenticatedUser;
    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 일반회원 수정 폼
     * @param mberManageVO 검색조건
     * @param model 화면모델
     * @return uss/umt/EgovUserSelectUpdt
     * @throws Exception
     */
    @RequestMapping(value = "/mberForm.do")
    public String mberForm(@RequestParam(value = "mode", required = false, defaultValue = "false") boolean mode,
                           @PathVariable(value="path") String path,
                           HttpServletRequest request,
                           @ModelAttribute("mberManage") MberManageVO mberManageVO, ModelMap model) throws Exception {

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            LoginVO loginVO = new LoginVO();
            //사용자 로그인 화면
            loginVO.setUserSe("GNR");
            model.addAttribute("loginVO", loginVO);

            return "jisungsoft/com/page/uat/uia/loginUsr.page";
        }
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        String uniqId = authenticatedUser.getUniqId();

        //회원수정 전 재검증
        if (!mode) {
            mberManageVO.setMberId(authenticatedUser.getId());
            mberManageVO.setMberNm(authenticatedUser.getName());
            mberManageVO.setUniqId(uniqId);
            model.addAttribute("mberManageVO", "mberManageVO");
            return "jisungsoft/com/" +path+"/uss/umt/mber/mberCheckForm.lms";
        } else {
            if (mberManageVO.getPassword().equals("")) {
                model.addAttribute("resultMsg", "fail.common.login.password2");
                return "jisungsoft/com/script/alertBack";
            }
            String pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), EgovStringUtil.isNullToString(uniqId));

            MberManageVO mberCheck = new MberManageVO();
            mberCheck.setUniqId(uniqId);
            mberCheck.setPassword(pass);

            MberManageVO resultCheck = mberManageService.selectMberByAuth(mberCheck);
            if (resultCheck == null) {

                model.addAttribute("resultMsg", "fail.user.passwordUpdate1");
                return "jisungsoft/com/script/alertBack";
            }
        }

        //공통코드 인풋
        cmmCodeInput(model);

        MberManageVO mberManage = mberManageService.selectMber(uniqId);
        if (mberManage == null) {
            mberManage = new MberManageVO();
        }

        mberManage.setPageIndex(mberManageVO.getPageIndex());
        mberManage.setSearchKeyword(mberManageVO.getSearchKeyword());
        mberManage.setSearchCondition(mberManageVO.getSearchCondition());
        model.addAttribute("mberManage", mberManage);

        return "jisungsoft/com/" +path+"/uss/umt/mber/mberForm.lms";
    }

    /**
     * 일반회원 등록 처리(가입)
     * @param mberManageVO
     * @param path
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberInsert.do", method = RequestMethod.POST)
    public String mberInsert(@ModelAttribute("mber") MberManageVO mberManageVO,
                             @RequestParam Map<String, Object> commandMap,
                             @PathVariable(value="path") String path,
                             HttpServletRequest request,
                             ModelMap model) throws Exception {

        //비밀번호 조합 체크 보안 취약점 조치
        if (!CommonUtil.validationPassword(mberManageVO.getPassword())) {
            model.addAttribute("resultMsg", "errors.password1");

            return "jisungsoft/com/script/alertBack";
        }

        mberManageService.insertMber(mberManageVO);

        model.addAttribute("resultMsg", "success.common.insert");
        model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 일반회원 수정
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberUpdate.do", method = RequestMethod.POST)
    public String mberUpdate(@ModelAttribute("mberManage") MberManageVO mberManageVO,
                             @PathVariable(value="path") String path,
                             HttpServletRequest request,
                             ModelMap model) throws Exception {

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        String uniqId = authenticatedUser.getUniqId();
        //고유ID 보안 취약점 처리
        uniqId = CommonUtil.SQLInjectionFilter(uniqId, uniqId.length());

        //생년월일 validation
        if (!mberManageVO.validateionMberBrthdy()) {
            model.addAttribute("resultMsg", "fail.register.birthday");

            return "jisungsoft/com/script/alertBack";
        }

        //입력값 validation
        if (!mberManageVO.validationMberForm('U')) {
            model.addAttribute("resultMsg", "fail.common.update");
            return "jisungsoft/com/script/alertBack";
        }

        mberManageVO.setUniqId(uniqId);
//        mberManageVO.setMberEmailAdres(null);
//        mberManageVO.setMberId(null);
        mberManageService.updateMber(mberManageVO);

        model.addAttribute("resultMsg", "success.common.update");
        model.addAttribute("redirectUri", "/"+path+"/uss/umt/mberForm.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 일반회원 삭제(탈퇴)
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberDelete.do", method = RequestMethod.POST)
    public String mberDelete(@ModelAttribute("mberManage") MberManageVO mberManageVO,
                             @PathVariable(value="path") String path,
                             HttpServletRequest request, ModelMap model) throws Exception {

        LoginVO authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
        String uniqId = authenticatedUser.getUniqId();
        //고유ID 보안 취약점 처리
        uniqId = CommonUtil.SQLInjectionFilter(uniqId, uniqId.length());

        mberManageVO.setUniqId(uniqId);
        mberManageService.deleteMberAccount(mberManageVO);

        model.addAttribute("resultMsg", "success.user.delete");
        model.addAttribute("redirectUri", "/uat/uia/logoutAction.do");

        return "jisungsoft/com/script/alertHref";
    }

    /**
     * 일반 회원 가입 폼
     * @param mode
     * @param path
     * @param request
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberRegForm.do", method = RequestMethod.POST)
    public String mberRegForm(@PathVariable(value="path") String path,
                              @RequestParam(value = "sbscrbTy", required = false, defaultValue = "") String mode,
                              @ModelAttribute("mber") MberManageVO mberManageVO, ModelMap model,
                              @RequestParam Map<String, Object> commandMap,
                              HttpServletRequest request) throws Exception {

        String authKey = (String) commandMap.get("authKey");

        if (mberManageVO.getMberEmailAdres() == null || mberManageVO.getMberEmailAdres().equals("")) {
            model.addAttribute("resultMsg", "fail.sendEmail.authkey2");

            return "jisungsoft/com/script/alertBack";
        } else if (authKey == null || authKey.equals("")) {
            model.addAttribute("resultMsg", "fail.sendEmail.email2");

            return "jisungsoft/com/script/alertBack";
        }

        //이메일 계정 중복 여부
        int checkEmailDplct = userManageService.checkEmailDplct(mberManageVO.getMberEmailAdres());
        if (checkEmailDplct > 0) {
            model.addAttribute("resultMsg", "fail.sendEmail.email3");

            return "jisungsoft/com/script/alertBack";
        }
        
        //이메일 인증 여부
        RegMberAuthVO regMberAuthVO = new RegMberAuthVO();
        regMberAuthVO.setEmailAdres(mberManageVO.getMberEmailAdres());
        regMberAuthVO.setAuthKey(authKey);
        int auth = regMberAuthService.selectRegAuthKeyCheck(regMberAuthVO);
        if (auth == 0) {
            model.addAttribute("resultMsg", "fail.sendEmail.authkey2");
            return "jisungsoft/com/script/alertBack";
        }

        //공통코드 인풋
        cmmCodeInput(model);

        model.addAttribute("sbscrbTy", mode);
        model.addAttribute("mberManageVO", mberManageVO);

        return "jisungsoft/com/page/uss/umt/mber/mberForm.page";
    }

    /**
     * STEP별 회원가입페이지 호출
     * @param mode
     * @param mberManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mberJoinStep.do")
    public String mberJoinStep(@ModelAttribute("mber") MberManageVO mberManageVO, ModelMap model,
                               @RequestParam(value = "pageType", required = false, defaultValue = "intro") String pageType,
                               @RequestParam(value = "sbscrbTy", required = false, defaultValue = "") String mode,
                               @RequestParam(value = "agree01", required = false, defaultValue = "N") String agree01,
                               @RequestParam(value = "agree02", required = false, defaultValue = "N") String agree02,
                               @RequestParam Map<String, Object> commandMap) throws Exception
    {
        switch (pageType) {
            case "intro" :
                return "jisungsoft/com/page/uss/umt/mberSelection.page";
            case "1" :
                //이용약관 동의 검증
                if (mode.equals("GNR")) {
                    if (agree01.equals("Y") && agree02.equals("Y")) {
                        return "forward:/page/uss/umt/mberJoinStep.do?pageType=2";
                    } else {
                        model.addAttribute("message", egovMessageSource.getMessage("fail.common.stplat"));
                    }
                }

                //회원가입유형 설정-일반회원
                String sbscrbTy = "GNR";
                //약관정보 조회
                StplatVO stplatVO = stplatManageService.selectStplatDetailUseAtY(new StplatVO());

                model.addAttribute("stplatVO", stplatVO); //약관정보 포함
                model.addAttribute("sbscrbTy", sbscrbTy); //회원가입유형 포함

                return "jisungsoft/com/page/uss/umt/stplatCnfirm.page";
            case "2" :
            model.addAttribute("mberManageVO", mberManageVO);
            return "jisungsoft/com/page/uss/umt/joinAuthForm.page";
        }
        return "jisungsoft/com/page/uss/umt/mberSelection.page";
    }

    /**
     * 이용약관, 개인정보취급방침
     * @param model
     * @param path
     * @param mode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/useStplatDetail.do", "/infoProvdAgreDetail.do"})
    public String sbscrbDetail(Model model, @PathVariable(value="path") String path,
                               @RequestParam(value = "mode") String mode) throws Exception {

        StplatVO stplatVO = stplatManageService.selectStplatDetailUseAtY(new StplatVO());

        model.addAttribute("stplatVO", stplatVO);

        //mode 1:이용약관, 2:개인정보취급방침
        if (mode.equals("2")) {
            return "jisungsoft/com/page/uss/umt/infoProvdAgreDetail.page";
        }

        return "jisungsoft/com/page/uss/umt/useStplatDetail.page";
    }

    /**
     * 공통코드 add
     * @param model
     * @throws Exception
     */
    public void cmmCodeInput(ModelMap model) throws Exception {
        //공통코드 인풋
        String[] codeArray = {
//                "MEM001",   //회원구분 코드
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
    }

}