package jisungsoft.com.sec.gmt.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 그룹관리에 대한 model 클래스를 정의한다.
 */
@Data
public class GroupManage extends Default {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 그룹 관리
     */
    private GroupManage groupManage;
    /**
     * 그룹 ID
     */
    private String groupId;
    /**
     * 부서 ID
     */
    private String orgnztId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 그룹등록일시
     */
    private String groupCreatDe;
    /**
     * 그룹설명
     */
    private String groupDc;
}