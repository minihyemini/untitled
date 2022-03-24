package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 공장정보
 */
@Getter @Setter
public class FactoryinfoVO {
    /**
     * 공정ID
     */
    private Integer fiId;
    /**
     * 공장코드
     */
    private String fiCode;
    /**
     * 공장명
     */
    private String fiNm;
    /**
     * 사용유무
     */
    private String useAt;
}
