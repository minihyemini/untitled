package jisungsoft.com.cop.ems.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.ems.service.RegMberAuthVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("regMberAuthDAO")
public class RegMberAuthDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "RegMberAuthDAO";

    public List<RegMberAuthVO> selectRegAuthKeyList(RegMberAuthVO regMberAuthVO) throws Exception {
        return selectList(NAME_SPACE + ".selectRegAuthKeyList", regMberAuthVO);
    }

    public RegMberAuthVO selectRegAuthKeyDetail(RegMberAuthVO regMberAuthVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectRegAuthKeyDetail", regMberAuthVO);
    }

    public int selectRegAuthKeyCheck(RegMberAuthVO regMberAuthVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectRegAuthKeyCheck", regMberAuthVO);
    }

    public void insertRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception {
        insert(NAME_SPACE + ".insertRegAuthKey", regMberAuthVO);
    }

    public void updateRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception {
        update(NAME_SPACE + ".updateRegAuthKey", regMberAuthVO);
    }

    public void deleteRegAuthKey(RegMberAuthVO regMberAuthVO) throws Exception {
        delete(NAME_SPACE + ".deleteRegAuthKey", regMberAuthVO);
    }
}
