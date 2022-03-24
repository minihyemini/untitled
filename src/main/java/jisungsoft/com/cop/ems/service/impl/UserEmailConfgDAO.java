package jisungsoft.com.cop.ems.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.ems.service.UserEmailConfgVO;
import org.springframework.stereotype.Repository;

@Repository("userEmailConfgDAO")
public class UserEmailConfgDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "UserEmailConfgDAO";

    public UserEmailConfgVO selectUserEmailConfgDetail(UserEmailConfgVO userEmailConfgVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectUserEmailConfgDetail", userEmailConfgVO);
    }

    public int selectUserEmailConfgCnt(UserEmailConfgVO userEmailConfgVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectUserEmailConfgCnt", userEmailConfgVO);
    }

    public void insertUserEmailConfg(UserEmailConfgVO userEmailConfgVO) throws Exception {
        insert(NAME_SPACE + ".insertUserEmailConfg", userEmailConfgVO);
    }

    public void updateUserEmailConfg(UserEmailConfgVO userEmailConfgVO) throws Exception {
        update(NAME_SPACE + ".updateUserEmailConfg", userEmailConfgVO);
    }
}
