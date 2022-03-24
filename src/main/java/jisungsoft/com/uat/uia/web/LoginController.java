package jisungsoft.com.uat.uia.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import jisungsoft.com.cmm.DefaultCodeVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.CmmDetailUser;
import jisungsoft.com.cmm.service.CmmUseService;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.cop.ems.service.RegMberAuthService;
import jisungsoft.com.cop.ems.service.RegMberAuthVO;
import jisungsoft.com.cop.ems.service.SndngMailService;
import jisungsoft.com.cop.ems.service.SndngMailTemplateService;
import jisungsoft.com.cop.sms.service.SmsService;
import jisungsoft.com.cop.sms.service.SmsVO;
import jisungsoft.com.service.LoginService;
import jisungsoft.com.uss.umt.service.MberManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
public class LoginController {

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 인증키 서비스
	 */
//	@Resource(name = "regMberAuthService")
	private RegMberAuthService regMberAuthService;
	/**
	 * 로그인 서브시
	 */
//	@Resource(name = "loginService")
	private LoginService loginService;
	/**
	 * 일반회원 서비스
	 */
//	@Resource(name = "mberManageService")
	private MberManageService mberManageService;
	/**
	 * EgovMessageSource
	 */
//	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	/**
	 * 메일 템플릿 서비스
	 */
//	@Resource(name = "sndngMailTemplateService")
	private SndngMailTemplateService sndngMailTemplateService;
	/**
	 * 메일 발송 서비스
	 */
//	@Resource(name = "sndngMailService")
	private SndngMailService sndngMailService;
	/**
	 * 공통 서비스
	 */
//	@Resource(name = "cmmUseService")
	private CmmUseService cmmUseService;
	/**
	 * SMS 발송 서비스
	 */
//	@Resource(name = "smsService")
	private SmsService smsService;

	/**
	 * 로그인 화면
	 * @param loginVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{path}/uat/uia/loginUsr.do")
	public String loginUsrView(@PathVariable("path") String path, @ModelAttribute("loginVO") LoginVO loginVO,
							   HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		LOGGER.debug("로그인 화면");

		//관리자 로그인 화면
		if (path.equals("cms")) {
			loginVO.setUserSe("USR");
			model.addAttribute("loginVO", loginVO);

			return "jisungsoft/com/cms/uat/uia/loginUsr.cms";
		}

		//사용자 로그인 화면
		loginVO.setUserSe("GNR");
		model.addAttribute("loginVO", loginVO);
		return "jisungsoft/com/page/uat/uia/loginUsr.page";
	}

	/**
	 * 아이디/비밀번호 찾기 폼
	 * @param loginVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/uat/uia/findAccountView.do")
	public String findAccountView(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model) {

		loginVO.setUserSe("GNR");
		model.addAttribute("loginVO", loginVO);

		return "jisungsoft/com/page/uat/uia/findAccountView.page";
	}

	/**
	 * 아이디 찾기 폼
	 * @param loginVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/uat/uia/findIdForm.do")
	public String findIdForm(@ModelAttribute("loginVO") LoginVO loginVO,
							 @RequestParam Map<String, Object> commandMap,
							 ModelMap model) {

		loginVO.setUserSe("GNR");
		model.addAttribute("loginVO", loginVO);
		model.addAttribute("auth"   , commandMap.get("auth"));

		return "jisungsoft/com/page/uat/uia/findIdForm.page";
	}

	/**
	 * 비밀번호 찾기 폼
	 * @param loginVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/uat/uia/findPasswordForm.do")
	public String findIdPasswordForm(@ModelAttribute("loginVO") LoginVO loginVO,
									 @RequestParam Map<String, Object> commandMap,
									 ModelMap model) {

		loginVO.setUserSe("GNR");
		model.addAttribute("loginVO", loginVO);
		model.addAttribute("auth"   , commandMap.get("auth"));

		return "jisungsoft/com/page/uat/uia/findPasswordForm.page";
	}

	/**
	 * 아이디 찾기 문자 발송
	 * @param loginVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/uat/uia/findIdAction.do")
	public String findIdAction(@ModelAttribute("loginVO") LoginVO loginVO,
							   @RequestParam(value = "mode") String mode,
									 ModelMap model) throws Exception {

	loginVO.setUserSe("GNR");
	boolean result = false;

		if (mode.equals("mbtl")) {    //휴대폰번호로 ID 찾을 경우
			if ((loginVO.getMbtlnum() == null || loginVO.getMbtlnum().equals("")) || (loginVO.getName() == null || loginVO.getName().equals(""))) {
				model.addAttribute("resultMsg", "fail.sendMbtl.phone2");

				return "jisungsoft/com/script/alertBack";
			}

			CmmDetailUser cmmDetailUser = new CmmDetailUser();
			cmmDetailUser.setUserSe("GNR");
			cmmDetailUser.setSearchCondition("1");
			cmmDetailUser.setSearchKeyword(loginVO.getName());
			cmmDetailUser.setOpenMbtlnum(true);
			List<?> userList = cmmUseService.selectUserViewListAll(cmmDetailUser);
			for (Object obj : userList) {
				EgovMap user = (EgovMap) obj;
				String mbtlnum = user.get("mbtlnum").toString();
				if (mbtlnum == null && !mbtlnum.equals("")) break;

				if (loginVO.getMbtlnum().equals(mbtlnum)) {
					result = true;
					loginVO.setId(user.get("userId").toString());
				}
			}

			if (result == false) {
				model.addAttribute("resultMsg", "fail.sendMbtl.phone4");
				model.addAttribute("resultArg", loginVO.getMbtlnum());
				return "jisungsoft/com/script/alertBack";
			}

			SmsVO smsVO = new SmsVO();
			smsVO.setTrnsmisCn("[동북권ICT이노베이션스퀘어]\n아이디 [" + loginVO.getId() + "] 입니다.\n감사합니다.");
			smsVO.setRecptnTelno(loginVO.getMbtlnum());
			smsService.insertSms(smsVO);

			model.addAttribute("resultMsg", "success.sendMbtl.confirm.auth3");
			return "jisungsoft/com/script/alert";
		}
		return "redirect:/page/uat/uia/loginUsr.do";
	}

	/**
	 * 아이디/비밀번호 찾기 검증 처리
	 * @param loginVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/uat/uia/accountAction.do", method = RequestMethod.POST)
	public String accountAction(@ModelAttribute("loginVO") LoginVO loginVO,
								@RequestParam("mode") String mode,
								@RequestParam("auth") String auth,
								ModelMap model) throws Exception {
		if(auth.equals("E")) {
			//인증 확인
			RegMberAuthVO regMberAuthVO = new RegMberAuthVO();
			regMberAuthVO.setEmailAdres(loginVO.getEmail());
			regMberAuthVO.setAuthKey(loginVO.getAuthKey());
			int resultAUth = regMberAuthService.selectRegAuthKeyCheck(regMberAuthVO);

			if (resultAUth == 0) {

				model.addAttribute("resultMsg", "fail.sendEmail.confirm.auth3");
				model.addAttribute("redirectUri", "/page/uat/uia/findAccountView.do");
				return "jisungsoft/com/script/alertHref";
			}

			//인증키 초기화
			regMberAuthVO.setAuthAt("N");
			regMberAuthVO.setAuthKey("000000");
			regMberAuthService.updateRegAuthKey(regMberAuthVO);
		}

		switch (mode) {
			case "1" :
				//보안 취약점 조치
				if (!loginVO.validationSearchIdForm() && auth.equals("E")) {
					model.addAttribute("resultMsg", "fail.sendEmail.data");
					model.addAttribute("redirectUri", "/page/uat/uia/findAccountView.do");

					return "jisungsoft/com/script/alertHref";
				} else if (!loginVO.validationMbtlSearchIdForm() && auth.equals("P")) {
					model.addAttribute("resultMsg", "fail.sendMbtl.data");
					return "jisungsoft/com/script/alertBack";
				}

				LoginVO result = loginService.searchId(loginVO);

				if (result == null) {
					model.addAttribute("resultMsg", "fail.search.id");
					return "jisungsoft/com/script/alertBack";
				}

				model.addAttribute("result", result);

				break;
			case "2" :
				//보안 취약점 조치
				if (!loginVO.validationSearchPwForm() && auth.equals("E")) {
					model.addAttribute("resultMsg", "fail.sendEmail.data");
					model.addAttribute("redirectUri", "/page/uat/uia/findAccountView.do");

					return "jisungsoft/com/script/alertHref";
				}else if(!loginVO.validationMbtlSearchPwForm() && auth.equals("P")) {
					model.addAttribute("resultMsg", "fail.sendMbtl.data");
					return "jisungsoft/com/script/alertBack";
				}

				loginVO.setMbtlnum(null);
				result = loginService.searchId(loginVO);

				if (result == null) {
					model.addAttribute("resultMsg", "fail.search.id");
					return "jisungsoft/com/script/alertBack";
				}

				loginVO.setId(result.getId());
				loginVO.setUniqId(result.getUniqId());

				return "jisungsoft/com/page/uat/uia/passwordResetForm.page";
		}

		//회원구분 코드
		DefaultCodeVO defaultCodeVO = new DefaultCodeVO();
		defaultCodeVO.setCodeId("MEM001");
		List<?> mem001 = cmmUseService.selectCmmCodeDetail(defaultCodeVO);

		model.addAttribute("loginVO", loginVO);
		model.addAttribute("mem001", mem001);

		return "jisungsoft/com/page/uat/uia/idView.page";
	}

	/**
	 * 비밀번호 변경 처리
	 * @param loginVO
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/page/uat/uia/passwordUpte.do", method = RequestMethod.POST)
	public String passwordReset(@ModelAttribute("loginVO") LoginVO loginVO,
								@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		boolean isCorrectPassword = false;
		String resultMsg = "";

		String password = loginVO.getPassword();
		String password2 = (String) commandMap.get("passwordSe");

		//보안 취약점 조치
		if (password.equals(password2)) {
			if (CommonUtil.validationPassword(password)) {
				isCorrectPassword = true;
			} else {
				resultMsg = "errors.password1";
			}
		} else {
			resultMsg = "errors.password5";
		}

		if (isCorrectPassword) {
			loginService.searchPassword(loginVO);

			resultMsg = "success.common.update";
		}

		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("redirectUri", "/page/uat/uia/loginUsr.do");
		return "jisungsoft/com/script/alertHref";
	}


	/**
	 * 로그인 후 메인화면으로 들어간다
	 * @param
	 * @return 로그인 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/loginAction.do")
	public String actionMain(ModelMap model) throws Exception {

		// 1. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		LOGGER.debug("User Id : {}", user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

		// 3. 메인 페이지 이동
		String main_page = Globals.MAIN_PAGE;

		LOGGER.debug("Globals.MAIN_PAGE > " + Globals.MAIN_PAGE);
		LOGGER.debug("main_page > {}", main_page);

		if (main_page.startsWith("/")) {
			return "forward:" + main_page;
		} else {
			return main_page;
		}
	}

	/**
	 * 로그아웃 성공 처리
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logoutAction.do")
	public String logoutAction(HttpServletRequest request, ModelMap model) {
		LOGGER.debug("logout...");
		LOGGER.debug(request.getRequestURI());
		LOGGER.debug(request.getContextPath());
		LOGGER.debug(request.getRequestURL().toString());

		HashMap<String, Object> dataMap = (HashMap) request.getAttribute("dataMap");
		String userSe = dataMap.get("userSe") == null ? "" : dataMap.get("userSe").toString();

		if (userSe.equals("USR")) {
			//업무사용자(관리자)
			return "redirect:/cms/index.do";
		}

		return "redirect:/page/index.do";
	}

	/**
	 *
	 * @return
	 */
//	@RequestMapping("/successAuth.do")
	public String successAuth() {
		// 1. Spring Security 사용자권한 처리
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "redirect:/cms/uat/uia/loginUsr.do";
		}
/*
		List<String> authorities = UserDetailsHelper.getAuthorities();
		for (String authority : authorities) {
			if (authority.equals(AuthorityData.ROLE_ADMIN.name()) || authority.equals(AuthorityData.ROLE_MANAGER.name())) {
				return "redirect:/cms/index.do";
			}
		}
*/

		return "redirect:/cms/index.do";
	}
}
