package jisungsoft.com.persistence.model.mes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 거래처관리
 */
@Getter
@Setter
public class ClientVO {
    /**
     * 거래처 ID
     */
    private Integer cltId;
    /**
     * 거래처코드
     */
    private String cltCode;
    /**
     * 거래처명
     */
    private String cltNm;
    /**
     * 대표자성명
     */
    private String cltOwnrnm;
    /**
     * 거래처타입
     */
    private String cltType;
    /**
     * 업태_업체유형
     */
    private String cltBusstype;
    /**
     * 사업자번호
     */
    private String cltBussnum;
    /**
     * 법인번호
     */
    private String cltCprtnum;
    /**
     * 우편번호
     */
    private String cltZip;
    /**
     * 주소
     */
    private String cltAddr;
    /**
     * 상세주소
     */
    private String cltDetailAddr;
    /**
     * 대표번호
     */
    private String cltTelno;
    /**
     * 대표 FAX 번호
     */
    private String cltFaxnum;
    /**
     * 대표 EMAIL
     */
    private String cltEmail;
    /**
     * 설립일자
     */
    private String cltSetupdt;
    /**
     * 거래시작일
     */
    private String cltDlbegindt;
    /**
     * 거래종료일
     */
    private String cltDlendt;
    /**
     * 품목 ID
     */
    private Integer pdnumId;
    /**
     * 마감시작일
     */
    private String cltDeadlndt;
    /**
     * 최종등록자 ID
     */
    private String frstRegisterId;
    /**
     * 최종수정자 ID
     */
    private String lastUpdusrId;
    /**
     * 기업회원ID
     */
    private String cltMberId;

    protected ClientVO() {
    }

    public static ClientVO createDataClient(Integer cltId, String cltCode, String cltNm, String cltOwnrnm, String cltType, String cltBusstype, String cltBussnum, String cltCprtnum, String cltZip, String cltAddr, String cltDetailAddr, String cltTelno, String cltFaxnum, String cltEmail, String cltSetupdt, String cltDlbegindt, String cltDlendt, String cltDeadlndt, String frstRegisterId, String lastUpdusrId, Integer pdnumId) {
        ClientVO clientVO = new ClientVO();
        clientVO.cltId = cltId;
        clientVO.cltCode = cltCode;
        clientVO.cltNm = cltNm;
        clientVO.cltOwnrnm = cltOwnrnm;
        clientVO.cltType = cltType;
        clientVO.cltBusstype = cltBusstype;
        clientVO.cltBussnum = cltBussnum;
        clientVO.cltCprtnum = cltCprtnum;
        clientVO.cltZip = cltZip;
        clientVO.cltAddr = cltAddr;
        clientVO.cltDetailAddr = cltDetailAddr;
        clientVO.cltTelno = cltTelno;
        clientVO.cltFaxnum = cltFaxnum;
        clientVO.cltEmail = cltEmail;
        clientVO.frstRegisterId = frstRegisterId;
        clientVO.lastUpdusrId = lastUpdusrId;
        clientVO.pdnumId = pdnumId;
        if (StringUtils.hasText(cltSetupdt)) {
            clientVO.cltSetupdt = cltSetupdt;
        }
        if (StringUtils.hasText(cltDlendt)) {
            clientVO.cltDlendt = cltDlendt;
        }
        if (StringUtils.hasText(cltDlbegindt)) {
            clientVO.cltDlbegindt = cltDlbegindt;
        }
        if (StringUtils.hasText(cltDeadlndt)) {
            clientVO.cltDeadlndt = cltDeadlndt;
        }


        return clientVO;
    }

    public static ClientVO createDataClientId(Integer cltId) {
        ClientVO clientVO = new ClientVO();
        clientVO.cltId = cltId;

        return clientVO;
    }
}
