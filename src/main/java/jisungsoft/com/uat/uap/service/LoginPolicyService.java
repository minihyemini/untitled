package jisungsoft.com.uat.uap.service;

import java.util.List;

public interface LoginPolicyService {

    /**
     * 로그인정책 목록을 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return List - 로그인정책 목록
     */
    public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) throws Exception;

    /**
     * 로그인정책 목록 수를 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return int
     */
    public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) throws Exception;

    /**
     * 로그인정책 목록의 상세정보를 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return LoginPolicyVO - 로그인정책 VO
     */
    public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) throws Exception;

    /**
     * 로그인정책 정보를 신규로 등록한다.
     * @param loginPolicy - 로그인정책 model
     */
    public boolean insertLoginPolicy(LoginPolicy loginPolicy);

    /**
     * 기 등록된 로그인정책 정보를 수정한다.
     * @param loginPolicy - 로그인정책 model
     */
    public boolean updateLoginPolicy(LoginPolicy loginPolicy);

    /**
     * 기 등록된 로그인정책 정보를 삭제한다.
     * @param loginPolicy - 로그인정책 model
     */
    public void deleteLoginPolicy(LoginPolicy loginPolicy);

    /**
     * 로그인정책에 대한 현재 반영되어 있는 결과를 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return LoginPolicyVO - 로그인정책 VO
     */
    public LoginPolicyVO selectLoginPolicyResult(LoginPolicyVO loginPolicyVO) throws Exception;
}
