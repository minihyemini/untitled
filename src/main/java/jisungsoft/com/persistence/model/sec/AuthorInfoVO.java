package jisungsoft.com.persistence.model.sec;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthorInfoVO {

    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 권한등록일자
     */
    private String authorCreatDe;
    /**
     * 권한코드설명
     */
    private String authorDc;
    /**
     * 권한 명
     */
    private String authorNm;

    protected AuthorInfoVO() {
    }

    public static AuthorInfoVO createDataAuthorInfo(String authorCode, String authorCreatDe, String authorDc, String authorNm) {
        AuthorInfoVO authorInfoVO = new AuthorInfoVO();
        authorInfoVO.authorCode = authorCode;
        authorInfoVO.authorCreatDe = authorCreatDe;
        authorInfoVO.authorDc = authorDc;
        authorInfoVO.authorNm = authorNm;

        return authorInfoVO;
    }
}
