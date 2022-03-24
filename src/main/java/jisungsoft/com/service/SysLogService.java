package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sym.SysLog;

import java.util.List;

public interface SysLogService {

    /**
     * 시스템 로그정보를 생성한다.
     */
    public void addSysLog(SysLog sysLog) throws Exception;

    /**
     * 시스템 로그정보를 요약한다.
     */
    public void addSysLogSummary() throws Exception;

    /**
     * 시스템 로그정보 목록을 조회한다.
     */
    public List<SysLog> getSysLogInfoList(SysLog sysLog) throws Exception;

    /**
     * 시스템로그 상세정보를 조회한다.
     */
    public SysLog getSysLogDetail(SysLog sysLog) throws Exception;
}
