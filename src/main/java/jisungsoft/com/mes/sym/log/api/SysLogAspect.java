package jisungsoft.com.mes.sym.log.api;

import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.SysLog;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class SysLogAspect {

    @Resource(name = "sysLogService")
    private SysLogService logInsertSysLog;

    /**
     * 시스템 로그정보를 생성한다.
     */
    public Object logInsert(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            Object retValue = joinPoint.proceed();
            return retValue;
        } catch (Throwable e) {
            throw e;
        } finally {
            stopWatch.stop();

            SysLog sysLog = new SysLog();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String processSeCode = "C";
            String processTime = Long.toString(stopWatch.getTotalTimeMillis());
            String uniqId = "";
            String ip = "";

            /* Authenticated  */
            Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
            if(isAuthenticated.booleanValue()) {
                LoginVO user = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
                uniqId = StringUtils.hasText(user.getUniqId()) ? user.getUniqId() : "";
                ip = StringUtils.hasText(user.getIp()) ? user.getIp() : "";
            }

            sysLog.setSrvcNm(className);
            sysLog.setMethodNm(methodName);
            sysLog.setProcessSeCode(processSeCode);
            sysLog.setProcessTime(processTime);
            sysLog.setRqesterId(uniqId);
            sysLog.setRqesterIp(ip);

            logInsertSysLog.addSysLog(sysLog);

        }
    }

    /**
     * 시스템 로그정보를 생성한다.
     */
    public Object logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            Object retValue = joinPoint.proceed();
            return retValue;
        } catch (Throwable e) {
            throw e;
        } finally {
            stopWatch.stop();

            SysLog sysLog = new SysLog();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String processSeCode = "U";
            String processTime = Long.toString(stopWatch.getTotalTimeMillis());
            String uniqId = "";
            String ip = "";

            /* Authenticated  */
            Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
            if(isAuthenticated.booleanValue()) {
                LoginVO user = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
                uniqId = StringUtils.hasText(user.getUniqId()) ? user.getUniqId() : "";
                ip = StringUtils.hasText(user.getIp()) ? user.getIp() : "";
            }

            sysLog.setSrvcNm(className);
            sysLog.setMethodNm(methodName);
            sysLog.setProcessSeCode(processSeCode);
            sysLog.setProcessTime(processTime);
            sysLog.setRqesterId(uniqId);
            sysLog.setRqesterIp(ip);

            logInsertSysLog.addSysLog(sysLog);

        }
    }

    /**
     * 시스템 로그정보를 생성한다.
     */
    public Object logDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            Object retValue = joinPoint.proceed();
            return retValue;
        } catch (Throwable e) {
            throw e;
        } finally {
            stopWatch.stop();

            SysLog sysLog = new SysLog();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String processSeCode = "D";
            String processTime = Long.toString(stopWatch.getTotalTimeMillis());
            String uniqId = "";
            String ip = "";

            /* Authenticated  */
            Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
            if(isAuthenticated.booleanValue()) {
                LoginVO user = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
                uniqId = StringUtils.hasText(user.getUniqId()) ? user.getUniqId() : "";
                ip = StringUtils.hasText(user.getIp()) ? user.getIp() : "";
            }

            sysLog.setSrvcNm(className);
            sysLog.setMethodNm(methodName);
            sysLog.setProcessSeCode(processSeCode);
            sysLog.setProcessTime(processTime);
            sysLog.setRqesterId(uniqId);
            sysLog.setRqesterIp(ip);

            logInsertSysLog.addSysLog(sysLog);

        }
    }

    /**
     * 시스템 로그정보를 생성한다.
     */
    public Object logSelect(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            Object retValue = joinPoint.proceed();
            return retValue;
        } catch (Throwable e) {
            throw e;
        } finally {
            stopWatch.stop();

            SysLog sysLog = new SysLog();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String processSeCode = "R";
            String processTime = Long.toString(stopWatch.getTotalTimeMillis());
            String uniqId = "";
            String ip = "";

            /* Authenticated  */
            Boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
            if(isAuthenticated.booleanValue()) {
                LoginVO user = (LoginVO)UserDetailsHelper.getAuthenticatedUser();
                uniqId = StringUtils.hasText(user.getUniqId()) ? user.getUniqId() : "";
                ip = StringUtils.hasText(user.getIp()) ? user.getIp() : "";
            }

            sysLog.setSrvcNm(className);
            sysLog.setMethodNm(methodName);
            sysLog.setProcessSeCode(processSeCode);
            sysLog.setProcessTime(processTime);
            sysLog.setRqesterId(uniqId);
            sysLog.setRqesterIp(ip);

            logInsertSysLog.addSysLog(sysLog);
        }
    }
}
