package jisungsoft.com.sec.gmt.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class GroupUserManage extends Default {

    /**
     * 그룹 ID
     */
    private String groupId;
    /**
     *
     */
    private String groupNm;
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
     * 사용자 이메일
     */
    private String userEmail;
}
