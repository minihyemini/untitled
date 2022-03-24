package jisungsoft.com.sym.log.clg.service;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.persistence.model.LoginVO;

import javax.annotation.Resource;

public class LoginLogAspect {

    /**
     * 접속 이력 서비스
     */
	@Resource(name = "loginLogService")
	LoginLogService loginLogService;

    /**
     * 로그인 로그정보를 생성한다.
     * EgovLoginController.actionMain Method
     *
     * @param
     * @return void
     * @throws Exception
     */
    public void logLogin() throws Throwable {

        String uniqId = "";
        String ip = "";

        /* Authenticated  */
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(isAuthenticated.booleanValue()) {
            LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
            uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
            ip = (user == null || user.getIp() == null) ? "" : user.getIp();
        }

        LoginLogVO loginLog = new LoginLogVO();
        loginLog.setLoginId(uniqId);
        loginLog.setLoginIp(ip);
        loginLog.setLoginMthd("I"); // 로그인:I, 로그아웃:O
        loginLog.setErrOccrrAt("N");
        loginLog.setErrorCode("");
        loginLogService.logInsertLoginLog(loginLog);
    }

    /**
     * 로그아웃 로그정보를 생성한다.
     * EgovLoginController.actionLogout Method
     *
     * @param
     * @return void
     * @throws Exception
     */
    public void logLogout() throws Throwable {

        String uniqId = "";
        String ip = "";

        /* Authenticated  */
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(isAuthenticated.booleanValue()) {
            LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
            uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
            ip = (user == null || user.getIp() == null) ? "" : user.getIp();
        }

        LoginLogVO loginLog = new LoginLogVO();
        loginLog.setLoginId(uniqId);
        loginLog.setLoginIp(ip);
        loginLog.setLoginMthd("O"); // 로그인:I, 로그아웃:O
        loginLog.setErrOccrrAt("N");
        loginLog.setErrorCode("");
        loginLogService.logInsertLoginLog(loginLog);
    }
}
