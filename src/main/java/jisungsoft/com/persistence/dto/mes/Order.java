package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문
 */
@Data
public class Order extends Default {
    /**
     * 주문ID
     */
    private Integer ordId;
    /**
     * 주문 LOT_NO
     */
    private String ordLotNo;
    /**
     * 주문 품목ID
     */
    private Integer oiId;
    /**
     * 판매번호
     */
    private String ordNum;
    /**
     * 주문타입
     */
    private String ordType;
    /**
     * 주문상태
     */
    private String ordStatus;
    /**
     * 주문상태명
     */
    private String ordStatusNm;
    /**
     * 거래유형
     */
    private String ordCategory;
    /**
     * 거래유형명
     */
    private String ordCategoryNm;
    /**
     * 단가
     */
    private Integer unitPrice = 0;
    /**
     * 판매금액(단가*수량)
     */
    private Integer unitTotPrice = 0;
    /**
     * 판매금액계(판매금액*리스트카운트)
     */
    private Integer totPrice = 0;
    /**
     * 부가세
     */
    private Integer surtax = 0;
    /**
     * 부가세 총합
     */
    private Integer totSurtax = 0;
    /**
     * 수량
     */
    private Integer qunty = 0;
    /**
     * 부가세포함여부
     */
    private String surtaxAt;
    /**
     * 부가세율
     */
    private Double surtaxRate;
    /**
     * 주문일(수주일,발주일)
     */
    private String orderDate;
    /**
     * 납기일
     */
    private String ordDuedate;
    /**
     * 판매일/납품예정일
     */
    private String ordDlvrschdt;
    /**
     * 담당자
     */
    private String esntlId;
    /**
     * 비고
     */
    private String ordDesc;
    /**
     * 최초등록일
     */
    private String frstRegistPnttm;
    /**
     * 최초등록자ID
     */
    private String frstRegisterId;
    /**
     * 최종수정시점
     */
    private String lastUpdtPnttm;
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId;
    /**
     * 등록자ID
     */
    private String frstRegisterUserId;
    /**
     * 사용자명
     */
    private String frstRegisterUserNm;
    /**
     * 수정자ID
     */
    private String lastUpdusrUserId;
    /**
     * 수정자명
     */
    private String lastUpdusrUserNm;
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
     * 자재ID
     */
    private Integer matId;
    /**
     * 제품ID
     */
    private Integer pdId;
    /**
     * 거래처ID
     */
    private Integer cltId;
    /**
     * 자재구매요청ID
     */
    private Integer matodrqstId;
    /**
     * 거래처코드
     */
    private String cltCode;
    /**
     * 거래처명
     */
    private String cltNm;

    private List<Order> itemFormList = new ArrayList<>();
}
