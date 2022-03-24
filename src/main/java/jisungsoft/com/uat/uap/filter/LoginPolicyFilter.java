package jisungsoft.com.uat.uap.filter;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.utl.sim.service.EgovClntInfo;
import jisungsoft.com.uat.uap.service.LoginPolicyService;
import jisungsoft.com.uat.uap.service.LoginPolicyVO;
import jisungsoft.com.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginPolicyFilter implements Filter {

    private FilterConfig config;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPolicyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        LoginPolicyService loginPolicyService = (LoginPolicyService) act.getBean("loginPolicyService");
        EgovMessageSource egovMessageSource = (EgovMessageSource) act.getBean("egovMessageSource");
        LoginService loginService = (LoginService) act.getBean("loginService");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String id = request.getParameter("id");
        //String password = request.getParameter("password");
        String userSe = request.getParameter("userSe");
        String userIp = "";

        if (id == null || userSe == null) {
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/cms/uat/uia/loginUsr.do");
        }

        // 1. LoginVO를 DB로 부터 가져오는 과정
        try {
            // 접속IP
            userIp = EgovClntInfo.getClntIP((HttpServletRequest) request);

            boolean loginPolicyYn = true;

            LoginPolicyVO loginPolicyVO = new LoginPolicyVO();
            loginPolicyVO.setUserId(id);
            loginPolicyVO = loginPolicyService.selectLoginPolicy(loginPolicyVO);

            if (loginPolicyVO == null) {
                loginPolicyYn = true;
            } else {
                if (loginPolicyVO.getLmttAt().equals("Y")) {
                    if (!userIp.equals(loginPolicyVO.getIpInfo())) {
                        loginPolicyYn = false;
                    }
                }
            }

            if (loginPolicyYn) {
                chain.doFilter(request, response);

            } else {
                String message = URLEncoder.encode(egovMessageSource.getMessage("fail.common.login.ip"),"UTF-8");
                ((HttpServletRequest) request).setAttribute("message", message);
                ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/uat/uia/egovLoginUsr.do?message="+message);
            }

        } catch (IOException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
            LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());
            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/uat/uia/egovLoginUsr.do?login_error=1");
        } catch (Exception e) {
//			LOGGER.error("Exception: {}", e.getClass().getName());
//			LOGGER.error("Exception  Message: {}", e.getMessage());
            // 2017-02-14  이정은          시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
            LOGGER.error("["+ e.getClass() +"] : ", e.getMessage());

            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/uat/uia/egovLoginUsr.do?login_error=1");
        }
    }

    @Override
    public void destroy() {

    }
}
