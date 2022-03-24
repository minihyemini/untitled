package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 머신정보
 */
@Data
public class Machine extends Default {

    /**
     * 머신정보ID
     */
    private Integer miId;
    /**
     * 관리번호
     */
    private String miMngNum;
    /**
     * 분류
     */
    private String miType;
    /**
     * 머신명
     */
    private String miName;
    /**
     * 규격
     */
    private String miStandard;
    /**
     * 기기번호
     */
    private String miDvcNum;
    /**
     * 구입일자
     */
    private String miPurchaseDate;
    /**
     * 제작처
     */
    private String miMenufacturer;
    /**
     * 검교정주기
     */
    private Integer miCalInterval;
    /**
     * 교정주기
     */
    private Integer miCorInterval;
    /**
     * 사용처
     */
    private String miUse;
    /**
     * 측정항목
     */
    private String miItem;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 검교정ID
     */
    private Integer micId;
    /**
     * 검교정일정
     */
    private String micDate;
    /**
     * 검교정타입
     */
    private String micType;
    /**
     * 검교정상태
     */
    private String micStatus;
}
