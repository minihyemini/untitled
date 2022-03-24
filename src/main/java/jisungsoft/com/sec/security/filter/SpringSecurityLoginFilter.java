package jisungsoft.com.sec.security.filter;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovClntInfo;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uat.uap.service.LoginPolicyService;
import jisungsoft.com.uat.uap.service.LoginPolicyVO;
import jisungsoft.com.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

public class SpringSecurityLoginFilter implements Filter {

	@Autowired
	protected AuthenticationManager authenticationManager;

	private FilterConfig config;

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityLoginFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		LOGGER.info("EgovSpringSecurityLoginFilter called...");

		// 로그인 URL
		String loginURL = config.getInitParameter("loginURL");
		String usrLoginURL = config.getInitParameter("USRLoginURL");
		loginURL = loginURL.replaceAll("\r", "").replaceAll("\n", "");
		usrLoginURL = usrLoginURL.replaceAll("\r", "").replaceAll("\n", "");

		String loginProcessURL = config.getInitParameter("loginProcessURL");
		loginProcessURL = loginProcessURL.replaceAll("\r", "").replaceAll("\n", "");

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		LoginService loginService = (LoginService) act.getBean("loginService");
		EgovLoginConfig egovLoginConfig = (EgovLoginConfig) act.getBean("egovLoginConfig");
		LoginPolicyService loginPolicyService = (LoginPolicyService) act.getBean("loginPolicyService");
		
		EgovMessageSource egovMessageSource = (EgovMessageSource) act.getBean("egovMessageSource");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String isLocallyAuthenticated = (String)session.getAttribute("isLocallyAuthenticated");
		String isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");

		String requestURL = ((HttpServletRequest) request).getRequestURI();
		String userIp = "";
		String userSe = httpRequest.getParameter("userSe");

		if (!EgovStringUtil.isEmpty(userSe)) {
			if (userSe.equals("USR")) {
				loginURL = usrLoginURL;
			}
		}

		//자동로그인 쿠키 설정
		String autoLogin = httpRequest.getParameter("autologin");
		boolean isAutoLogin = autoLogin != null && autoLogin.equals("Y") ? true : false;

		//스프링 시큐리티 인증이 처리 되었는지 EgovUserDetailsHelper.getAuthenticatedUser() 메서드를 통해 확인한다.
		//context-common.xml 빈 설정에 egovUserDetailsSecurityService를 등록 해서 사용해야 정상적으로 동작한다.
		if (UserDetailsHelper.getAuthenticatedUser() == null || requestURL.contains(loginProcessURL)) {

			if (isRemotelyAuthenticated != null && isRemotelyAuthenticated.equals("true")) {
				try {
					//세션 토큰 정보를 가지고 DB로부터 사용자 정보를 가져옴
					LoginVO loginVO = (LoginVO) session.getAttribute("loginVOForDBAuthentication");
					loginVO = loginService.actionLoginByEsntlId(loginVO);

					if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
						//세션 로그인
						session.setAttribute("loginVO", loginVO);

						//로컬 인증결과 세션에 저장
						session.setAttribute("isLocallyAuthenticated", "true");

						//스프링 시큐리티 로그인
						//httpResponse.sendRedirect(httpRequest.getContextPath() + "/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());

						UsernamePasswordAuthenticationFilter springSecurity = null;

						Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
						if (beans.size() > 0) {
							springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
							springSecurity.setUsernameParameter("egov_security_username");
							springSecurity.setPasswordParameter("egov_security_password");
							springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/gds_security_login", "POST"));
						} else {
							LOGGER.error("No AuthenticationProcessingFilter");
							throw new IllegalStateException("No AuthenticationProcessingFilter");
						}
						//springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

						LOGGER.debug("before security filter call....");
						springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getUniqId()), httpResponse, chain);
						LOGGER.debug("after security filter call....");

					}
				//2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
				} catch(IllegalArgumentException e) {
					LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
				} catch(Exception e) {
					LOGGER.error("["+e.getClass()+"] Try/Catch...Exception : " + e.getMessage());
				}

			} else if (isRemotelyAuthenticated == null) {
				if (requestURL.contains(loginProcessURL)) {

					String password = httpRequest.getParameter("password");
					String id = httpRequest.getParameter("id");
					
					// 보안점검 후속 조치(Id, Password 검증)
					if (!StringUtils.hasText(id) || !StringUtils.hasText(password)) {
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
						chain.doFilter(request, response);
						return;
					}

					LoginVO loginVO = new LoginVO();
					loginVO.setId(id);
					loginVO.setPassword(password);
					loginVO.setUserSe(userSe);
					loginVO.setAutologin(autoLogin);

					//------------------------------------------------------------------
				    // 로그인시 로그인인증제한 활성화 처리
				    //------------------------------------------------------------------
				    if(egovLoginConfig.isLock()) {
				        try{
				             Map<?,?> mapLockUserInfo = (EgovMap)loginService.selectLoginIncorrect(loginVO);
				             if(mapLockUserInfo != null){		
				                //로그인인증제한 처리
				                String sLoginIncorrectCode = loginService.processLoginIncorrect(loginVO, mapLockUserInfo);
				                if(!sLoginIncorrectCode.equals("E")){
				                    if(sLoginIncorrectCode.equals("L")){
				                        request.setAttribute("message", egovMessageSource.getMessageArgs("fail.common.loginIncorrect", new Object[] {egovLoginConfig.getLockCount(),request.getLocale()}));
				                    }else if(sLoginIncorrectCode.equals("C")){
				                        request.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
				                    }
				                    httpRequest.getRequestDispatcher(loginURL).forward(request, response);
				                    return;
				                }
				            }else{
				                request.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
				                httpRequest.getRequestDispatcher(loginURL).forward(request, response);
				                return;
				            }
				        } catch(IllegalArgumentException e) {
				            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
				        } catch(Exception ex) {
							LOGGER.error("Login Exception : {}", ex.getCause(), ex);
							httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
				        }
				    }

					//------------------------------------------------------------------
				    // 사용자 로그인 처리
				    //------------------------------------------------------------------
					try {
						//사용자 입력 id, password로 DB 인증을 실행함
						loginVO = loginService.actionLogin(loginVO);

						//권한 여부
						if (loginVO.getAuthority() == null || loginVO.getAuthority().equals("") || loginVO.getAuthority().equals(AuthorityCode.ROLE_ANONYMOUS.name())) {
							httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login.auth",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);

							return;
						}

						//사용자 IP 기록
						loginVO.setIp(request.getRemoteAddr());
						//사용자 자동로그인
						if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
							//세션 로그인
							session.setAttribute("loginVO", loginVO);

							//로컬 인증결과 세션에 저장
							session.setAttribute("isLocallyAuthenticated", "true");

							//스프링 시큐리티 로그인
//							httpResponse.sendRedirect(httpRequest.getContextPath() + "/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());

							UsernamePasswordAuthenticationFilter springSecurity = null;

							Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
							if (beans.size() > 0) {
								//로그인 성공시 자동 로그인 쿠키 생성
								if (isAutoLogin) {
									Cookie cookie = new Cookie("login_cookie", loginVO.getId());
									cookie.setPath("/");
//									cookie.setMaxAge(-1);
									((HttpServletResponse) response).addCookie(cookie);
									session.setMaxInactiveInterval(-1);

								} else {	//쿠키 만료
									Cookie cookie = new Cookie("login_cookie", null);
									cookie.setPath("/");
									cookie.setMaxAge(0);
									((HttpServletResponse) response).addCookie(cookie);
								}

								springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
								springSecurity.setUsernameParameter("egov_security_username");
								springSecurity.setPasswordParameter("egov_security_password");
								springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/gds_security_login", "POST"));
							} else {
								LOGGER.error("No AuthenticationProcessingFilter");
								throw new IllegalStateException("No AuthenticationProcessingFilter");
							}
							//springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

							LOGGER.debug("before security filter call....");
							springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getUniqId()), httpResponse, chain);
							LOGGER.debug("after security filter call....");

						} else {
							//사용자 정보가 없는 경우 로그인 화면으로 redirect 시킴
							httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
							
							//chain.doFilter(request, response);

							return;

						}
			        } catch(IllegalArgumentException e) {
			            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
					} catch (Exception ex) {
						//DB인증 예외가 발생할 경우 로그인 화면으로 redirect 시킴
						LOGGER.error("Login Exception : {}", ex.getCause(), ex);
						httpRequest.setAttribute("message", egovMessageSource.getMessage("error.security.runtime.error",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
						//chain.doFilter(request, response);

						return;

					}

					//------------------------------------------------------------------
					// 사용자 로그인 IP인증제한 처리
					//------------------------------------------------------------------
					try {
						// 접속IP
						userIp = EgovClntInfo.getClntIP((HttpServletRequest) request);
						UserDetailsHelper.getAuthenticatedUser();

						boolean loginPolicyYn = true;

						LoginPolicyVO loginPolicyVO = new LoginPolicyVO();
						loginPolicyVO.setUserId(loginVO.getId());
						loginPolicyVO = loginPolicyService.selectLoginPolicy(loginPolicyVO);

						//업무사용자에 대한 IP 제한 정책
						if (loginVO.getUserSe().equals("USR")) {
							if (loginPolicyVO != null) {
								if (loginPolicyVO.getLmttAt().equals("Y")) {
									if (userIp.equals(loginPolicyVO.getIpInfo())) {
										loginPolicyYn = false;
									}
								}
							}
						}

						if (loginPolicyYn) {
							UsernamePasswordAuthenticationFilter springSecurity = null;

							Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);

							if (beans.size() > 0) {
								springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
								springSecurity.setUsernameParameter("egov_security_username");
								springSecurity.setPasswordParameter("egov_security_password");
								springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/gds_security_login", "POST"));
							} else {
								LOGGER.error("No AuthenticationProcessingFilter");
								throw new IllegalStateException("No AuthenticationProcessingFilter");
							}
						} else {
							//사용자 IP 불일치인 경우 로그인 화면으로 redirect 시킴
							httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);

							//chain.doFilter(request, response);

							return;
						}

					} catch (IOException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
						LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
					} catch (Exception ex) {
						LOGGER.error("Login Exception : {}", ex.getCause(), ex);
						httpRequest.setAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
					}

					return;
				}

			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
}

class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String username = null;
	private String password = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
		super(request);

		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getServletPath() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/gds_security_login";
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/gds_security_login";
	}

	@Override
	public String getParameter(String name) {
		if (name.equals("egov_security_username")) {
			return username;
		}

		if (name.equals("egov_security_password")) {
			return password;
		}

		return super.getParameter(name);
	}
}
