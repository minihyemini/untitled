package jisungsoft.com.sym.log.clg.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.sym.log.clg.service.LoginLogVO;
import jisungsoft.com.sym.log.clg.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("loginLogService")
public class EgovLoginLogServiceImpl extends EgovAbstractServiceImpl implements LoginLogService {

    @Resource(name = "egovLoginLogDAO")
    EgovLoginLogDAO egovLoginLogDAO;

    /** ID Generation */
    @Resource(name="egovLoginLogIdGnrService")
    private EgovIdGnrService egovLoginLogIdGnrService;

    @Override
    public void logInsertLoginLog(LoginLogVO loinLog) throws Exception {
        String logId = egovLoginLogIdGnrService.getNextStringId();
        loinLog.setLogId(logId);

        egovLoginLogDAO.logInsertLoginLog(loinLog);
    }

    @Override
    public LoginLogVO selectLoginLog(LoginLogVO loginLogVO) throws Exception {
        return egovLoginLogDAO.selectLoginLog(loginLogVO);
    }

    @Override
    public List<LoginLogVO> selectLoginLogInf(LoginLogVO loinLog) throws Exception {
        return egovLoginLogDAO.selectLoginLogInf(loinLog);
    }

    @Override
    public int selectLoginLogInfCnt(LoginLogVO loinLog) throws Exception {
        return egovLoginLogDAO.selectLoginLogInfCnt(loinLog);
    }
}
