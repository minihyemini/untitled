package jisungsoft.com.sym.log.clg.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.sym.log.clg.service.LoginLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("egovLoginLogDAO")
public class EgovLoginLogDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "LoginLog";
    /**
     * 접속로그를 기록한다.
     *
     * @param loginLogVO
     * @return
     * @throws Exception
     */
    public void logInsertLoginLog(LoginLogVO loginLogVO) throws Exception{
        insert(NAME_SPACE + ".logInsertLoginLog", loginLogVO);
    }

    /**
     * 접속로그 상세보기를 조회한다.
     *
     * @param loginLogVO
     * @return loginLog
     * @throws Exception
     */
    public LoginLogVO selectLoginLog(LoginLogVO loginLogVO) throws Exception{

        return (LoginLogVO) selectOne(NAME_SPACE + ".selectLoginLog", loginLogVO);
    }

    /**
     * 접속로그를 목록을 조회한다.
     *
     * @param loginLogVO
     * @return
     * @throws Exception
     */
    public List<LoginLogVO> selectLoginLogInf(LoginLogVO loginLogVO) throws Exception{
        return selectList(NAME_SPACE + ".selectLoginLogInf", loginLogVO);
    }

    /**
     * 접속로그 목록의 숫자를 조회한다.
     * @param loginLogVO
     * @return
     * @throws Exception
     */
    public int selectLoginLogInfCnt(LoginLogVO loginLogVO) throws Exception{

        return (Integer)selectOne(NAME_SPACE + ".selectLoginLogInfCnt", loginLogVO);
    }
}
