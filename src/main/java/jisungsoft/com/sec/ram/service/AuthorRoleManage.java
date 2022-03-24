package jisungsoft.com.sec.ram.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class AuthorRoleManage extends Default {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 권한 롤 관리
     */
    private AuthorRoleManage authorRole;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 롤코드
     */
    private String roleCode;
    /**
     * 롤명
     */
    private String roleNm;
    /**
     * 롤 패턴
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
     * 롤 순서정렬
     */
    private String roleSort;
    /**
     * 롤 등록여부
     */
    private String regYn;
    /**
     * 등록일자
     */
    private String creatDt;
}
