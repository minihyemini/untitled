package jisungsoft.com.persistence.model.sec;

import lombok.Data;

@Data
public class AuthorGroupVO {

    /**
     * 설정대상 사용자 유형 코드
     */
    private String mberTyCode;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * Uniq ID
     */
    private String esntlId;

    protected AuthorGroupVO() {
    }

    public static AuthorGroupVO createDataAuthorGroup(String mberTyCode, String authorCode, String esntlId) {
        AuthorGroupVO authorGroupVO = new AuthorGroupVO();

        authorGroupVO.mberTyCode = mberTyCode;
        authorGroupVO.authorCode = authorCode;
        authorGroupVO.esntlId = esntlId;

        return authorGroupVO;
    }
}
