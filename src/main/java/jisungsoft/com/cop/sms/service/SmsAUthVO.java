package jisungsoft.com.cop.sms.service;

import lombok.Data;

@Data
public class SmsAUthVO {
    private String serviceName;
    private String telNumber;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String profileUrl;

    public SmsAUthVO(String serviceName, String telNumber, String clientId, String clientSecret) {
        this.serviceName = serviceName;
        this.telNumber = telNumber;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
