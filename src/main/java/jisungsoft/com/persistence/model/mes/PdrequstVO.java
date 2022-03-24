package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 생산의뢰
 */
@Getter
@Setter
public class PdrequstVO {
    /**
     * 생산의뢰 ID
     */
    private Integer pdreqId;
    /**
     * 품번 ID
     */
    private Integer pdnumId;
}
