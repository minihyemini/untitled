package jisungsoft.com.cop.ems.service;

import lombok.Data;

@Data
public class RegMberAuthVO {

    /**
     * 이메일 아이디
     */
    private String emailAdres;
    /**
     * 인증여부
     */
    private String authAt;
    /**
     * 인증키 
     */
    private String authKey;
}
