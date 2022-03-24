package jisungsoft.com.mes.sym.log;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import jisungsoft.com.service.SysLogService;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSysLogScheduling.java
 * @Description : 시스템 로그 요약을 위한 스케쥴링 클래스
 */
@Service("sysLogScheduling")
public class SysLogScheduling extends EgovAbstractServiceImpl {

	@Resource(name = "sysLogService")
	private SysLogService logInsertSysLog;

	/**
	 * 시스템 로그정보를 요약한다.
	 * 전날의 로그를 요약하여 입력하고, 6개월전의 로그를 삭제한다.
	 */
	public void sysLogSummary() throws Exception {
		logInsertSysLog.addSysLogSummary();
	}

}
