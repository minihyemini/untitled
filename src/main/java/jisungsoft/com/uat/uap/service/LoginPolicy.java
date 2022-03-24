package jisungsoft.com.uat.uap.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class LoginPolicy extends Default {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 고유 ID
     */
    private String esntlId;
    /**
     * 사용자 ID
     */
    private String userId;
    /**
     * 사용자 명
     */
    private String userNm;
    /**
     * 사용자 구분
     */
    private String userSe;
    /**
     * IP정보
     */
    private String ipInfo;
    /**
     * 중복허용여부
     */
    private String dplctPermAt;
    /**
     * 제한여부
     */
    private String lmttAt;
    /**
     * 등록일시
     */
    private String regDate;
    /**
     * 등록여부
     */
    private String regYn;
}
