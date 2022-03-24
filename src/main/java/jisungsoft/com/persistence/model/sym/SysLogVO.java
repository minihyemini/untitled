package jisungsoft.com.persistence.model.sym;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SysLogVO {

    /**
     * 에러횟수
     */
    private int errorCo = 0;
    /**
     * 에러코드
     */
    private String errorCode;
    /**
     * 에러코드 명
     */
    private String errorCodeNm;
    /**
     * 에러구분
     */
    private String errorSe;
    /**
     * 기관코드
     */
    private String insttCode;
    /**
     * 기관코드 명
     */
    private String insttCodeNm;
    /**
     * 업무구분코드
     */
    private String jobSeCode;

    /**
     * 업무구분코드명
     */
    private String jobSeCodeNm;
    /**
     * 메서드명
     */
    private String methodNm;
    /**
     * 발생일자
     */
    private String occrrncDe;
    /**
     * 처리횟수
     */
    private int processCo;
    /**
     * 처리구분코드
     */
    private String processSeCode;
    /**
     * 처리구분코드명
     */
    private String processSeCodeNm;
    /**
     * 처리시간
     */
    private String processTime;
    /**
     * 요청아이디
     */
    private String requstId;
    /**
     * 요청자아이디
     */
    private String rqesterId;
    /**
     * 요청자 이름
     */
    private String rqsterNm;
    /**
     * 요청아이피
     */
    private String rqesterIp;
    /**
     * 응답코드
     */
    private String rspnsCode;
    /**
     * 응답코드 명
     */
    private String rspnsCodeNm;
    /**
     * 서비스명
     */
    private String srvcNm;
    /**
     * 대상메뉴명
     */
    private String trgetMenuNm;
    /**
     * 검색시작일
     */
    private String searchBgnDe;
    /**
     * 검색조건
     */
    private String searchCnd;
    /**
     * 검색종료일
     */
    private String searchEndDe;
    /**
     * 검색단어
     */
    private String searchWrd;
    /**
     * 정렬순서(DESC,ASC)
     */
    private String sortOrdr;

    /** 검색사용여부 */
    private String searchUseYn;

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** rowNo  */
    private int rowNo = 0;
}
