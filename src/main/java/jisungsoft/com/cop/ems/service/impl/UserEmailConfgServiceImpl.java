package jisungsoft.com.cop.ems.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;
import jisungsoft.com.cop.ems.service.UserEmailConfgService;
import jisungsoft.com.cop.ems.service.UserEmailConfgVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userEmailConfgService")
public class UserEmailConfgServiceImpl extends EgovAbstractServiceImpl implements UserEmailConfgService {

    @Resource(name = "userEmailConfgDAO")
    private UserEmailConfgDAO userEmailConfgDAO;

    /** 암호화서비스 */
    @Resource(name = "egovEnvCryptoService")
    EgovEnvCryptoService cryptoService;

    @Override
    public UserEmailConfgVO selectUserEmailConfgDetail(UserEmailConfgVO userEmailConfgVO) throws Exception {
        return userEmailConfgDAO.selectUserEmailConfgDetail(userEmailConfgVO);
    }

    @Override
    public int selectUserEmailConfgCnt(UserEmailConfgVO userEmailConfgVO) throws Exception {
        return userEmailConfgDAO.selectUserEmailConfgCnt(userEmailConfgVO);
    }

    @Override
    public void insertUserEmailConfg(UserEmailConfgVO userEmailConfgVO) throws Exception {
        String pw = cryptoService.encrypt(userEmailConfgVO.getEmPassword());
        userEmailConfgVO.setEmPassword(pw);

        userEmailConfgDAO.insertUserEmailConfg(userEmailConfgVO);
    }

    @Override
    public void updateUserEmailConfg(UserEmailConfgVO userEmailConfgVO) throws Exception {
        String pw = cryptoService.encrypt(userEmailConfgVO.getEmPassword());
        userEmailConfgVO.setEmPassword(pw);

        userEmailConfgDAO.updateUserEmailConfg(userEmailConfgVO);
    }
}
