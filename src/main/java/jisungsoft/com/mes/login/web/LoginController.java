package jisungsoft.com.mes.login.web;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.code.MemberTypeCode;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.mes.login.validation.LoginFormValidator;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;

@Slf4j
@Controller
public class LoginController {

	/**
	 * 로그인 서브시
	 */
	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;

	private final String VIEW_PATH = "jisungsoft/com/mes/login";

	/**
	 * 로그인 화면
	 */
	@GetMapping("/mes/{userSe}/login.do")
	public String loginView(@PathVariable("userSe") String userSe,
							ModelMap model,
							Locale locale,
							HttpServletRequest request) {
		log.info("{} ::: ", locale);
		LoginVO login = new LoginVO();
		String viewType = "/login.mes";

		//로그인 페이지가 다를경우 사용
		if (userSe.equals(MemberTypeCode.USR.name())) {
			viewType = "/loginUsr.mes";
		} else if (userSe.equals(MemberTypeCode.GNR.name())) {
			viewType = "/loginGnr.mes";
		} else if (userSe.equals(MemberTypeCode.ENT.name())) {
			viewType = "/loginEnt.mes";
		}

		login.setUserSe(userSe);
		model.addAttribute("loginVO", login);
		return VIEW_PATH + viewType;
	}

	@PostMapping("/mes/{userSe}/login.do")
	public String loginErrorView(@Validated @ModelAttribute("loginVO")LoginVO loginVO,
								 BindingResult bindingResult,
								 @PathVariable("userSe") String userSe,
								 ModelMap model,
								 HttpServletRequest request,
								 Locale locale) {

		new LoginFormValidator().validate(loginVO, bindingResult);
		log.info("{} ::: ", locale);

		//Validation
		if (bindingResult.hasErrors()) {
			log.info("{} ::: ", bindingResult);
		}
		String viewType = "/login.mes";
		//로그인 페이지가 다를경우 사용
		if (userSe.equals(MemberTypeCode.USR.name())) {
			viewType = "/loginUsr.mes";
		} else if (userSe.equals(MemberTypeCode.GNR.name())) {
			viewType = "/loginGnr.mes";
		} else if (userSe.equals(MemberTypeCode.ENT.name())) {
			viewType = "/loginEnt.mes";
		}

		loginVO.setUserSe(userSe);
		model.addAttribute("loginVO", loginVO);

		return VIEW_PATH + viewType;
	}

	@RequestMapping(value = "/mes/{userSe}/accountAction.do", method = RequestMethod.POST)
	public String accountAction(@ModelAttribute("loginVO") LoginVO loginVO,
								ModelMap model) throws Exception {

		return "jisungsoft/com/mes/login/login.mes";
	}

	/**
	 * 로그아웃
	 */
	@RequestMapping(value = "/logoutAction.do")
	public String logoutAction() {

		return "redirect:/successLogout.do";
	}

	/**
	 * 로그아웃 처리
	 */
	@RequestMapping(value = "/successLogout.do")
	public String logout(HttpServletRequest request, ModelMap model) {

		HashMap<String, Object> dataMap = (HashMap) request.getAttribute("dataMap");
		String userSe = dataMap.get("userSe") == null ? "" : dataMap.get("userSe").toString();

		String viewType = "/login.mes";
		//로그인 페이지가 다를경우 사용
		if (userSe.equals(MemberTypeCode.USR.name())) {
			viewType = "/loginUsr.mes";
		} else if (userSe.equals(MemberTypeCode.GNR.name())) {
			viewType = "/loginGnr.mes";
		} else if (userSe.equals(MemberTypeCode.ENT.name())) {
			viewType = "/loginEnt.mes";
		}

		return VIEW_PATH + viewType;
	}

	/**
	 * 권한처리
	 * @return
	 */
	@RequestMapping("/successAuth.do")
	public String successAuth() {
		// 1. Spring Security 사용자권한 처리
		Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "jisungsoft/com/mes/login/login.mes";
		}

		return "redirect:/mes/index.do";
	}
}
