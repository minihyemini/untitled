package jisungsoft.com.sec.rmt.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class RoleManage extends Default {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 롤 관리
     */
    private RoleManage roleManage;
    /**
     * 롤코드
     */
    private String roleCode;
    /**
     * 롤명
     */
    private String roleNm;
    /**
     * 롤패턴
     */
    private String rolePttrn;
    /**
     * 롤 설명
     */
    private String roleDc;
    /**
     * 롤 타입
     */
    private String roleTy;
    /**
     * 롤 Sort
     */
    private String roleSort;
    /**
     * 롤 등록일시
     */
    private String roleCreatDe;
    /**
     * 권한 코드
     */
    private String authorCode;
}
