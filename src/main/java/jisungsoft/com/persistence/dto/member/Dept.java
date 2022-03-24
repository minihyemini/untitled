package jisungsoft.com.persistence.dto.member;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Dept extends Default {

    /**
     * 부서ID
     */
    private String orgnztId;
    /**
     * 부서명
     */
    private String orgnztNm;
    /**
     * 부서코드
     */
    private String orgnztCode;
    /**
     * 부서설명
     */
    private String orgnztDc;
}
