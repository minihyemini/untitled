package jisungsoft.com.persistence.dto.sec;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 그룹
 */
@Data
public class Group extends Default {
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 그룹코드
     */
    private String groupCode;
    /**
     * 가입일자
     */
    private String groupCreatDe;
    /**
     * 그룹설명
     */
    private String groupDc;
    /**
     * 사용자ID
     */
    private String userId;
    /**
     * 사용자명
     */
    private String userNm;
    /**
     * 사용자이메일
     */
    private String userEmail;
    /**
     * 부서ID
     */
    private String orgnztId;
    /**
     * 부서명
     */
    private String orgnztNm;
}
