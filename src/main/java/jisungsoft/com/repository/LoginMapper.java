package jisungsoft.com.repository;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.model.LoginVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

@Mapper("loginMapper")
public interface LoginMapper {

    /**
     * EsntlId를 이용한 로그인을 처리한다
     */
    public LoginVO actionLoginByEsntlId(LoginVO vo);

    /**
     * 일반 로그인을 처리한다
     */
    public LoginVO actionLogin(LoginVO vo);

    /**
     * 아이디를 찾는다.
     */
    public LoginVO searchId(LoginVO vo);

    /**
     * 비밀번호를 찾는다.
     */
    public LoginVO searchPassword(LoginVO vo);

    /**
     * 변경된 비밀번호를 저장한다.
     */
    public void updatePassword(LoginVO vo) throws DataAccessException;

    /**
     * 로그인인증제한 내역을 조회한다.
     */
    public Map<?,?> selectLoginIncorrect(LoginVO vo);

    /**
     * 로그인인증제한 내역을 업데이트 한다.
     */
    public void updateLoginIncorrect(Map<?,?> map) throws DataAccessException;
}
