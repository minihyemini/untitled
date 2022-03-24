package jisungsoft.com.cmm.service;

import jisungsoft.com.sec.gmt.service.GroupManage;
import lombok.Data;

@Data
public class CmmGroup {
    /**
     * 그룹 관리
     */
    private GroupManage groupManage;
    /**
     * 그룹 ID
     */
    private String groupId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 상세코드
     */
    private String code;
    /**
     * 상세코드명
     */
    private String codeNm;
    /**
     * 그룹등록일시
     */
    private String groupCreatDe;
    /**
     * 그룹설명
     */
    private String groupDc;
}
