package jisungsoft.com.cop.ems.service;

import java.util.List;

public interface RegMberAuthService {

    public List<RegMberAuthVO> selectRegAuthKeyList(RegMberAuthVO regMberAuthVO) throws Exception;

    public RegMberAuthVO selectRegAuthKeyDetail(RegMberAuthVO regMberAuthVO) throws Exception;

    public int selectRegAuthKeyCheck(RegMberAuthVO regMberAuthVO) throws Exception;

    public void insertRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception;

    public void updateRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception;

    public void deleteRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception;
}
