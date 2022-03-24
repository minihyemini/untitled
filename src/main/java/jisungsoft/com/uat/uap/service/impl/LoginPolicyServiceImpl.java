package jisungsoft.com.uat.uap.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.uat.uap.service.LoginPolicy;
import jisungsoft.com.uat.uap.service.LoginPolicyService;
import jisungsoft.com.uat.uap.service.LoginPolicyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("loginPolicyService")
public class LoginPolicyServiceImpl extends EgovAbstractServiceImpl implements LoginPolicyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPolicyServiceImpl.class);

    @Resource(name="loginPolicyDAO")
    LoginPolicyDAO loginPolicyDAO;

    @Override
    public List<LoginPolicyVO> selectLoginPolicyList(LoginPolicyVO loginPolicyVO) throws Exception {
        return loginPolicyDAO.selectLoginPolicyList(loginPolicyVO);
    }

    @Override
    public int selectLoginPolicyListTotCnt(LoginPolicyVO loginPolicyVO) throws Exception {
        return loginPolicyDAO.selectLoginPolicyListTotCnt(loginPolicyVO);
    }

    @Override
    public LoginPolicyVO selectLoginPolicy(LoginPolicyVO loginPolicyVO) throws Exception {
        return loginPolicyDAO.selectLoginPolicy(loginPolicyVO);
    }

    @Override
    public boolean insertLoginPolicy(LoginPolicy loginPolicy) {

        //ID, IP 중복 예외처리
        try {
            loginPolicyDAO.insertLoginPolicy(loginPolicy);
        } catch (Exception e) {
            LOGGER.debug("Duplicate entry ID and IP");
            return false;
        }

        return true;
    }

    @Override
    public boolean updateLoginPolicy(LoginPolicy loginPolicy) {

        //ID, IP 중복 예외처리
        try {
            loginPolicyDAO.updateLoginPolicy(loginPolicy);
        } catch (Exception e) {
            LOGGER.debug("Duplicate entry ID and IP");
            return false;
        }

        return true;
    }

    @Override
    public void deleteLoginPolicy(LoginPolicy loginPolicy) {
        loginPolicyDAO.deleteLoginPolicy(loginPolicy);
    }

    @Override
    public LoginPolicyVO selectLoginPolicyResult(LoginPolicyVO loginPolicyVO) throws Exception {
        return loginPolicyDAO.selectLoginPolicyResult(loginPolicyVO);
    }
}
