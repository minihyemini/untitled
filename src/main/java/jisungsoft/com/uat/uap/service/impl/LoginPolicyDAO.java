package jisungsoft.com.uat.uap.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uat.uap.service.LoginPolicy;
import jisungsoft.com.uat.uap.service.LoginPolicyVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("loginPolicyDAO")
public class LoginPolicyDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "loginPolicyDAO";
    /**
     * 로그인정책 목록을 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return List - 로그인정책 목록
     */
    public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) throws Exception {
        return selectList(NAME_SPACE + ".selectLoginPolicyList", loginPolicyVO);
    }

    /**
     * 로그인정책 목록 수를 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return int
     */
    public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectLoginPolicyListTotCnt", loginPolicyVO);
    }

    /**
     * 로그인정책 목록의 상세정보를 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return LoginPolicyVO - 로그인정책 VO
     */
    public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) throws Exception {
        return (LoginPolicyVO)selectOne(NAME_SPACE + ".selectLoginPolicy", loginPolicyVO);
    }

    /**
     * 로그인정책 정보를 신규로 등록한다.
     * @param loginPolicy - 로그인정책 model
     */
    public void insertLoginPolicy(LoginPolicy loginPolicy) {
        insert(NAME_SPACE + ".insertLoginPolicy", loginPolicy);
    }

    /**
     * 기 등록된 로그인정책 정보를 수정한다.
     * @param loginPolicy - 로그인정책 model
     */
    public void updateLoginPolicy(LoginPolicy loginPolicy) {
        update(NAME_SPACE + ".updateLoginPolicy", loginPolicy);
    }

    /**
     * 기 등록된 로그인정책 정보를 삭제한다.
     * @param loginPolicy - 로그인정책 model
     */
    public void deleteLoginPolicy(LoginPolicy loginPolicy) {
        delete(NAME_SPACE + ".deleteLoginPolicy", loginPolicy);
    }

    /**
     * 로그인정책에 대한 현재 반영되어 있는 결과를 조회한다.
     * @param loginPolicyVO - 로그인정책 VO
     * @return LoginPolicyVO - 로그인정책 VO
     */
    public LoginPolicyVO selectLoginPolicyResult(LoginPolicyVO loginPolicyVO) throws Exception {
        return null;
    }
}
