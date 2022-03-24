package jisungsoft.com.sym.log.clg.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class LoginLogVO extends Default {

    /** 로그ID */
    private String logId;

    /** 사용자ID */
    private String loginId;

    /** 사용자ID */
    private String userId;

    /** 사용자명 */
    private String loginNm;

    /** 접속IP */
    private String loginIp;

    /** 로그유형 */
    private String loginMthd;

    /** 에러발생여부 */
    private String errOccrrAt;

    /** 에러코드 */
    private String errorCode;

    /** 생성일시 */
    private String creatDt;
}
