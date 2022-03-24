package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 거래처관리
 */
@Data
public class Client extends Default {
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
     * 품번
     */
    private String pdnumNum;
    /**
     * 품명
     */
    private String pdnumNm;
    /**
     * 마감시작일
     */
    private String cltDeadlndt;
    /**
     * 거래처별 품번 적용수
     */
    private Integer pdnumClientCnt;
    /**
     * 최종등록일
     */
    private String frstRegistPnttm;
    /**
     * 최종등록자 ID
     */
    private String frstRegisterId;
    /**
     * 최종수정시점
     */
    private String lastUpdtPnttm;
    /**
     * 최종수정자 ID
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
}
