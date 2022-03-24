package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 공정마스터관리
 */
@Getter
@Setter
public class PrssmstrmngVO {
    /**
     * 공정 ID
     */
    private Integer pmId;
    /**
     * 공정코드
     */
    private String pmCode;
    /**
     * 공정명
     */
    private String pmNm;
}
