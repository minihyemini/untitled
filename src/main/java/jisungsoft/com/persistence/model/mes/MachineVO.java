package jisungsoft.com.persistence.model.mes;

import lombok.Getter;

/**
 * 기계정보
 */
@Getter
public class MachineVO {

    /**
     * 계측기정보ID
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
     * 계측기명
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
     * 제조사
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

    protected MachineVO() {
    }

    public static MachineVO createDataMsrinstrmt(Integer miId, String miMngNum, String miType, String miName, String miStandard, String miDvcNum, String miPurchaseDate, String miMenufacturer, Integer miCalInterval, Integer miCorInterval, String miUse, String miItem, String useAt) {
        MachineVO machineVO = new MachineVO();

        machineVO.miId = miId;
        machineVO.miMngNum = miMngNum;
        machineVO.miType = miType;
        machineVO.miName = miName;
        machineVO.miStandard = miStandard;
        machineVO.miDvcNum = miDvcNum;
        machineVO.miPurchaseDate = miPurchaseDate;
        machineVO.miMenufacturer = miMenufacturer;
        machineVO.miCalInterval = miCalInterval;
        machineVO.miCorInterval = miCorInterval;
        machineVO.miUse = miUse;
        machineVO.miItem = miItem;
        machineVO.useAt = useAt;

        return machineVO;
    }

    public static MachineVO createDataMsrinstrmtcal(Integer micId, String micDate, String micType, String micStatus, Integer miId) {
        MachineVO machineVO = new MachineVO();

        machineVO.micId = micId;
        machineVO.micDate = micDate;
        machineVO.micType = micType;
        machineVO.micStatus = micStatus;
        machineVO.miId = miId;

        return machineVO;
    }
}
