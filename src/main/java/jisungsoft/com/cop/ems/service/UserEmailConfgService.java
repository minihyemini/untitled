package jisungsoft.com.cop.ems.service;

public interface UserEmailConfgService {

    UserEmailConfgVO selectUserEmailConfgDetail(UserEmailConfgVO userEmailConfgVO) throws Exception;

    int selectUserEmailConfgCnt(UserEmailConfgVO userEmailConfgVO) throws Exception;

    void insertUserEmailConfg(UserEmailConfgVO userEmailConfgVO) throws Exception;

    void updateUserEmailConfg(UserEmailConfgVO userEmailConfgVO) throws Exception;
}
