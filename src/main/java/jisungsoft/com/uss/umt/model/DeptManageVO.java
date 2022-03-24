package jisungsoft.com.uss.umt.model;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class DeptManageVO extends Default {

    private static final long serialVersionUID = 1L;
    /**
     * 부서ID
     */
    private String orgnztId;
    /**
     * 부서명
     */
    private String orgnztNm;
    /**
     * 부서설명
     */
    private String orgnztDc;
}
