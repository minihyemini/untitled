package jisungsoft.com.cop.sms.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class SmsVO extends Default {

    /**
     * 권한 설정 데이터
     */
    private SmsAUthVO smsAUthVO;

    /**
     * 메세지ID
     */
    private String smsId;
    /**
     * 전송전화번호
     */
    private String trnsmisTelno;
    /**
     * 수신전화번호
     */
    private String recptnTelno;
    /**
     * 전송코드
     */
    private String trnsmisCode;
    /**
     * 내용
     */
    private String trnsmisCn;
    /**
     * 결과코드
     */
    private String resultCode;
    /**
     * 결과메세지
     */
    private String resultMssage;
    /**
     * 인증코드
     */
    private String authKey;
    /**
     * 인증여부
     */
    private String authAt;
    /**
     * 전송날짜
     */
    private String frstRegistPnttm;
    /**
     * 수신리스트
     */
    private String recptnList;
}
