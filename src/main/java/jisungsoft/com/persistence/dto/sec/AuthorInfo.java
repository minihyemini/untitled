package jisungsoft.com.persistence.dto.sec;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class AuthorInfo extends Default {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 권한관리
     */
    private AuthorInfo authorManage;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 권한등록일자
     */
    private String authorCreatDe;
    /**
     * 권한코드설명
     */
    private String authorDc;
    /**
     * 권한 명
     */
    private String authorNm;
}
