package jisungsoft.com.cmm.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

import java.util.List;

/**
 * 사용자 정보 공통
 */
@Data
public class CmmDetailUser extends Default {

    /**
     * 사용자 고유ID
     */
    private String esntlId;
    /**
     * 사용자 ID
     */
    private String userId;
    /**
     * 사용자명
     */
    private String userNm;
    /**
     * 사용자 이메일
     */
    private String userEmail;
    /**
     * 사용자 휴대폰 번호
     */
    private String mbtlnum;
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 사용자 구분(업무사용자:USR, 일반사용자:GNR)
     */
    private String userSe;
    /**
     * 부서ID
     */
    private String orgnztId;
    /**
     * 권한ID
     */
    private String authorCode;
    /**
     * 가입일
     */
    private String sbscrbDe;
    /**
     * 그룹 ID List
     */
    private List<String> groupIdList;
    /**
     * 휴대전화번호 공개여부
     */
    private boolean openMbtlnum = false;

    public void setOpenMbtlnum(boolean openMbtlnum) {
        this.openMbtlnum = openMbtlnum;
    }

    public boolean getOpenMbtlnum() {
        return openMbtlnum;
    }
}
