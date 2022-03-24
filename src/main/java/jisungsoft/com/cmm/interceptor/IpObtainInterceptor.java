package jisungsoft.com.cmm.interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IpObtainInterceptor extends HandlerInterceptorAdapter {
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
 
		String clientIp = request.getRemoteAddr();
 
		LoginVO loginVO = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
 
		if (loginVO != null) {
			loginVO.setIp(clientIp);
		}
 
		return true;
	}
}
