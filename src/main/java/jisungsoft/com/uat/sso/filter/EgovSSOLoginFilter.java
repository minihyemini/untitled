package jisungsoft.com.uat.sso.filter;

import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.uat.sso.service.SSOService;
import jisungsoft.com.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 2.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 2.    서준식        최초생성
 *
 *  </pre>
 */

public class EgovSSOLoginFilter implements Filter {

	private FilterConfig config;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSSOLoginFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		SSOService userSSOService = null;
		try {
			userSSOService = (SSOService) act.getBean("egovSSOService");
			if (userSSOService == null) {
				LOGGER.error("Fail to create 'EgovSSOService' object");
				chain.doFilter(request, response);
				return;
			}
		} catch (NoSuchBeanDefinitionException ex) {
			LOGGER.error("No SSO ServiceImpl Class!");
		}

		LoginService loginService = (LoginService) act.getBean("loginService");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String isLocallyAuthenticated = (String) session.getAttribute("isLocallyAuthenticated");
		String isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");
		boolean isSSOLoggedOn = false;

		if (isLocallyAuthenticated != null && (isLocallyAuthenticated.equals("true"))) {
			if (isRemotelyAuthenticated == null) {

				try {
					//sso서버에 토큰 생성
					userSSOService.requestIssueToken(request, response);
					//로컬 인증 적용 여부 완료를 세션에 저장
					session.setAttribute("isLocallyAuthenticated", "true");
					//sso 인증 완료 여부를 세션에 저장
					session.setAttribute("isRemotelyAuthenticated", "true");

				} catch (IllegalStateException ex) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
					session.setAttribute("isRemotelyAuthenticated", "fail");
					LOGGER.debug("SSO Authentication fail : invalidated session {}", ex.getMessage());
				} catch (Exception ex) {
					session.setAttribute("isRemotelyAuthenticated", "fail");
					LOGGER.debug("SSO Authentication fail : {}", ex.getMessage());
				}

			}
		} else if (isLocallyAuthenticated == null) {
			if (isRemotelyAuthenticated == null) {
				//sso서버에 토큰이 존재하는지 체크함
				isSSOLoggedOn = userSSOService.hasTokenInSSOServer(httpRequest, response);
				if (isSSOLoggedOn) {
					//서버에 토큰이 존재할 경우 로컬 인증을 위해 isRemotelyAuthenticated true로 변경
					session.setAttribute("isRemotelyAuthenticated", "true");

					//로컬 DB인증을 위한 loginVO 객체를 세션에 저장
					session.setAttribute("loginVOForDBAuthentication", userSSOService.getLoginVO(request, response));
				}
			}
		}

		chain.doFilter(request, response);

		isLocallyAuthenticated = (String) session.getAttribute("isLocallyAuthenticated");
		isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");

		if (isLocallyAuthenticated != null && isLocallyAuthenticated.equals("true")) {
			if (isRemotelyAuthenticated == null) {

			}
		} else if (isLocallyAuthenticated == null) {
			if (isRemotelyAuthenticated == null) {

			} else if (isRemotelyAuthenticated != null && isRemotelyAuthenticated.equals("true")) {

				try {
					//세션 토큰 정보를 가지고 DB로부터 사용자 정보를 가져옴
					LoginVO loginVO = (LoginVO) session.getAttribute("loginVOForDBAuthentication");
					loginVO = loginService.actionLoginByEsntlId(loginVO);
					if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
						//세션 로그인
						session.setAttribute("loginVO", loginVO);

						//로컬 인증결과 세션에 저장
						session.setAttribute("isLocallyAuthenticated", "true");
					} else {
						LOGGER.debug("Local authentication by sso is failed");
					}

				} catch (IllegalStateException ex) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
					LOGGER.debug("Local authentication by sso is failed (Invalidated session) : {}", ex.getMessage());
				} catch (Exception ex) {
					//DB인증 예외가 발생할 경우 로그를 남기고 로컬인증을 시키지 않고 그대로 진행함.
					LOGGER.debug("Local authentication by sso is failed : {}", ex.getMessage());
				}

			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;
	}
}
