package jisungsoft.com.cop.ems.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cop.ems.service.RegMberAuthService;
import jisungsoft.com.cop.ems.service.RegMberAuthVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("regMberAuthService")
public class RegMberAuthServiceImpl extends EgovAbstractServiceImpl implements RegMberAuthService {

    @Resource(name = "regMberAuthDAO")
    private  RegMberAuthDAO regMberAuthDAO;

    @Override
    public List<RegMberAuthVO> selectRegAuthKeyList(RegMberAuthVO regMberAuthVO) throws Exception {
        return regMberAuthDAO.selectRegAuthKeyList(regMberAuthVO);
    }

    @Override
    public RegMberAuthVO selectRegAuthKeyDetail(RegMberAuthVO regMberAuthVO) throws Exception {
        return regMberAuthDAO.selectRegAuthKeyDetail(regMberAuthVO);
    }

    @Override
    public int selectRegAuthKeyCheck(RegMberAuthVO regMberAuthVO) throws Exception {
        return regMberAuthDAO.selectRegAuthKeyCheck(regMberAuthVO);
    }

    @Override
    public void insertRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception {
        regMberAuthDAO.insertRegAuthKey(regMberAuthVO);
    }

    @Override
    public void updateRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception {
        regMberAuthDAO.updateRegAuthKey(regMberAuthVO);
    }

    @Override
    public void deleteRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception {
        regMberAuthDAO.deleteRegAuthKey(regMberAuthVO);
    }
}
