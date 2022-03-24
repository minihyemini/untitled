package jisungsoft.com.sym.log.clg.service;

import java.util.List;

public interface LoginLogService {

    /**
     * 접속로그를 기록한다.
     *
     * @param loinLog
     */
    public void logInsertLoginLog(LoginLogVO loinLog) throws Exception;

    /**
     * 접속로그를 조회한다.
     *
     * @param loginLogVO
     * @return loginLog
     * @throws Exception
     */
    public LoginLogVO selectLoginLog(LoginLogVO loginLogVO) throws Exception;

    /**
     * 접속로그 목록을 조회한다.
     *
     * @param loinLog
     */
    public List<LoginLogVO> selectLoginLogInf(LoginLogVO loinLog) throws Exception;

    public int selectLoginLogInfCnt(LoginLogVO loinLog) throws Exception;
}
