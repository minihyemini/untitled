package jisungsoft.com.persistence.dto.sec;

import jisungsoft.com.persistence.dto.Default;
import jisungsoft.com.persistence.model.sec.AuthorGroupVO;
import lombok.Data;

import java.util.List;

@Data
public class AuthorGroup extends Default {

    /**
     * 권한그룹관리
     */
    private AuthorGroup authorGroup;
    /**
     *
     */
    List<AuthorGroupVO> authorGroupList;
    /**
     * 설정대상 사용자 ID
     */
    private String userId;
    /**
     * 설정대상 사용자 명
     */
    private String userNm;
    /**
     * 설정대상 그룹 ID
     */
    private String groupId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 설정대상 사용자 유형 코드
     */
    private String mberTyCode;
    /**
     * 설정대상 사용자 유형 명
     */
    private String mberTyNm;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 권한코드명
     */
    private String authorName;
    /**
     * 등록 여부
     */
    private String regYn;
    /**
     * Uniq ID
     */
    private String esntlId;
}
