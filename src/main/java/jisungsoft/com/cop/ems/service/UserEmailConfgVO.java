package jisungsoft.com.cop.ems.service;


import lombok.Data;

@Data
public class UserEmailConfgVO {

    /**
     * 고유ID
     */
    private String emplyrId;
    /**
     * 호스트
     */
    private String emHost;
    /**
     * 포트
     */
    private String emPort;
    /**
     * 프로토콜
     */
    private String emProtocol;
    /**
     * 계정ID
     */
    private String emId;
    /**
     * 계정 비밀번호
     */
    private String emPassword;
    /**
     * 발신자명
     */
    private String emSenderName;
    /**
     * 발신자 이메일 주소
     */
    private String emEmailAddress;
}
