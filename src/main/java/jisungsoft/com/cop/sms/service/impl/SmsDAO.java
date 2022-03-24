package jisungsoft.com.cop.sms.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.sms.service.SmsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("smsDAO")
public class SmsDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "SmsDAO";

    /**
     * 문자메시지 목록을 조회한다.
     * @param smsVO
     */
    public List<SmsVO> selectSmsInfs(SmsVO smsVO) throws Exception {
        return selectList(NAME_SPACE + ".selectSmsInfs", smsVO);
    }

    /**
     * 문자메시지 목록 숫자를 조회한다
     * @param smsVO
     * @return
     * @throws Exception
     */
    public int selectSmsInfsCnt(SmsVO smsVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectSmsInfsCnt", smsVO);
    }

    /**
     * 문자메시지 정보를 등록한다.
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String insertSmsInf(SmsVO smsVO) throws Exception {
        return Integer.toString(insert(NAME_SPACE + ".insertSmsInf", smsVO));
    }

    /**
     * 문자메시지 수신정보 및 결과 정보를 등록한다.
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String insertSmsRecptnInf(SmsVO smsVO) throws Exception {
        return Integer.toString(insert(NAME_SPACE + ".insertSmsRecptnInf", smsVO));
    }

    /**
     * 문자메시지에 대한 상세정보를 조회한다.
     * @param smsVO
     * @return
     */
    public SmsVO selectSmsInf(SmsVO smsVO) {
        return (SmsVO)selectOne(NAME_SPACE + ".selectSmsInf", smsVO);
    }

    /**
     * 문자메시지 수신 및 결과 목록을 조회한다.
     * @param smsVO
     */
    public List<SmsVO> selectSmsRecptnInfs(SmsVO smsVO) throws Exception {
        return selectList(NAME_SPACE + ".selectSmsRecptnInfs", smsVO);
    }

    /**
     * 문자메시지 전송 결과 수신을 처리한다.
     * EgovSmsInfoReceiver(Schedule job)에 의해 호출된다.
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String updateSmsRecptnInf(SmsVO smsVO) throws Exception {
        return Integer.toString(insert(NAME_SPACE + ".updateSmsRecptnInf", smsVO));
    }

    /**
     * 문자메세지 인증키 상세조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    public SmsVO selectSmsAuthInf(SmsVO smsVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectSmsAuthInf", smsVO);
    }

    /**
     * 문자메세지 인증키 등록
     * @throws Exception
     */
    public void insertSmsAuth(SmsVO smsVO) throws Exception {
        insert(NAME_SPACE + ".insertSmsAuth", smsVO);
    }

    /**
     * 문자메세지 인증키 수정
     * @throws Exception
     */
    public void updateSmsAuth(SmsVO smsVO) throws Exception {
        update(NAME_SPACE + ".updateSmsAuth", smsVO);
    }

    /**
     * 문자메세지 인증키 삭제
     * @throws Exception
     */
    public void deleteSmsAuth(SmsVO smsVO) throws Exception {
        delete(NAME_SPACE + ".deleteSmsAuth", smsVO);
    }
}
