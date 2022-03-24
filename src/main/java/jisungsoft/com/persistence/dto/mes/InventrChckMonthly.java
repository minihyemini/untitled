package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class InventrChckMonthly extends Default {

    /**
     * 품번 ID
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

}
