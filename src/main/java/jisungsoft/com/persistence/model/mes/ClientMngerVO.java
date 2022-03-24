package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 거래처 담당자
 */
@Getter @Setter
public class ClientMngerVO {

    /**
     * 담당자ID
     */
    private Integer clmId;
    /**
     * 담당자명
     */
    private String clmName;
    /**
     * 담당자 전화번호
     */
    private String clmTel;
    /**
     * 담당자FAX
     */
    private String clmFax;
    /**
     * 담당자 이메일
     */
    private String clmEmail;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 비고
     */
    private String clmDesc;
    /**
     * 최초등록자ID
     */
    private String frstRegisterId;
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId;
    /**
     * 거래처ID
     */
    private Integer cltId;

    protected ClientMngerVO() {
    }

    public static ClientMngerVO createDataClientMnger(Integer clmId, String clmName, String clmTel, String clmFax, String clmEmail, String useAt, String clmDesc, String frstRegisterId, String lastUpdusrId, Integer cltId) {
        ClientMngerVO clientMngerVO = new ClientMngerVO();
        clientMngerVO.clmId = clmId;
        clientMngerVO.clmName = clmName;
        clientMngerVO.clmTel = clmTel;
        clientMngerVO.clmFax = clmFax;
        clientMngerVO.clmEmail = clmEmail;
        clientMngerVO.useAt = useAt;
        clientMngerVO.clmDesc = clmDesc;
        clientMngerVO.frstRegisterId = frstRegisterId;
        clientMngerVO.lastUpdusrId = lastUpdusrId;
        clientMngerVO.cltId = cltId;

        return clientMngerVO;
    }

    public static ClientMngerVO createDataClientMngerId(Integer clmId) {
        ClientMngerVO clientMngerVO = new ClientMngerVO();
        clientMngerVO.clmId = clmId;

        return clientMngerVO;
    }
}
