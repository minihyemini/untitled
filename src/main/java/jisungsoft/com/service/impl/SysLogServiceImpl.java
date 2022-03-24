package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.SysLog;
import jisungsoft.com.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("sysLogService")
public class SysLogServiceImpl extends EgovAbstractServiceImpl implements SysLogService {


    @Override
    public void addSysLog(SysLog sysLog) throws Exception {

        log.info("[][] ::: addSysLog");
    }

    @Override
    public void addSysLogSummary() throws Exception {
        log.info("[][] ::: addSysLogSummary");
    }

    @Override
    public List<SysLog> getSysLogInfoList(SysLog sysLog) throws Exception {
        log.info("[][] ::: getSysLogInfoList");
        return null;
    }

    @Override
    public SysLog getSysLogDetail(SysLog sysLog) throws Exception {
        log.info("[][] ::: getSysLogDetail");
        return null;
    }
}
