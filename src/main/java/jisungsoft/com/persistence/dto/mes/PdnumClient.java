package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 주거래품번
 */
@Data
public class PdnumClient extends Default {

    /**
     * 주거래품번
     */
    private Integer pcId;
    /**
     * 거래처ID
     */
    private Integer cltId;
    /**
     * 품번ID
     */
    private Integer pdnumId;
    /**
     * 품번
     */
    private String pdnumNum;
    /**
     * 품명
     */
    private String pdnumNm;
    /**
     * 제품구분
     */
    private String pdnumType;
    /**
     * 제품구분명
     */
    private String pdnumTypeNm;
    /**
     * 품목규격
     */
    private String pdnumStnd;
    /**
     * 품목단위
     */
    private String pdnumUnit;
    /**
     * 품목코드
     */
    private String pdnumCode;
    /**
     * 폼 체크여부
     */
    private String formCheck;
    /**
     * form data
     */
    List<PdnumClient> pdnumList = new ArrayList<>();
}
