package jisungsoft.com.service;

import java.util.Map;

import jisungsoft.com.persistence.model.LoginVO;

/**
 * 로그인 서비스
 */
public interface LoginService {

	/**
	 * EsntlId를 이용한 로그인을 처리한다
	 */
    public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception;
	
	/**
	 * 일반 로그인을 처리한다
	 */
    LoginVO actionLogin(LoginVO vo) throws Exception;
    
    /**
	 * 인증서 로그인을 처리한다
	 */
    LoginVO actionCrtfctLogin(LoginVO vo) throws Exception;
    
    /**
	 * 아이디를 찾는다.
	 */
    LoginVO searchId(LoginVO vo) throws Exception;
    
    /**
	 * 비밀번호를 찾는다.
	 */
	LoginVO searchPassword(LoginVO vo) throws Exception;

    /**
	 * 로그인인증제한을 처리한다.
	 */
    String processLoginIncorrect(LoginVO vo, Map<?,?> mapLockUserInfo) throws Exception;
    
    /**
	 * 로그인인증제한을 조회한다.
	 */
    Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception;
}
