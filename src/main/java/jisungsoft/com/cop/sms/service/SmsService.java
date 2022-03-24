package jisungsoft.com.cop.sms.service;

import java.util.List;

public interface SmsService {

    List<SmsVO> selectSmsInfs(SmsVO paramVO) throws Exception;

    int selectSmsInfsCnt(SmsVO paramVO) throws Exception;

    SmsVO selectSmsInf(SmsVO smsVO) throws Exception;

    public SmsVO senderSms(SmsVO smsVO) throws Exception;

    void insertSms(SmsVO smsVO) throws Exception;

    SmsVO selectSmsAuthInf(SmsVO smsVO) throws Exception;

    void insertSmsAuth(SmsVO smsVO) throws Exception;

    void updateSmsAuth(SmsVO smsVO) throws Exception;

    void deleteSmsAuth(SmsVO smsVO) throws Exception;
}
