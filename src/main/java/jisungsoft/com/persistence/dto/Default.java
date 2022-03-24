package jisungsoft.com.persistence.dto;

import lombok.Data;

import java.io.Serializable;

@Data
@SuppressWarnings("serial")
public class Default implements Serializable {

    /** 검색조건 */
    private String searchCondition;

    /** 검색조건2 */
    private String searchCondition2;

    /** 검색조건3 */
    private String searchCondition3;

    /** 검색Keyword */
    private String searchKeyword;
    private String searchKeyword2;
    private String searchKeyword3;
    private String searchKeyword4;
    private String searchKeyword5;

    /** 검색사용여부 */
    private String searchUseYn;

    /** 검색 일자 */
    private String searchDate;

    /** 검색 년도 */
    private String searchYear;

    /** 검색 시작일자 */
    private String searchFromDate;

    /** 검색 종료일자 */
    private String searchToDate;

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

    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";

    /** 검색KeywordTo */
    private String searchKeywordTo = "";

    /** 검색Keyword 현재 Day */
    private String searchKeywordDay = "";

    /** 검색Keyword 현재 Month */
    private String searchKeywordMonth = "";

    /** 검색조건-회원상태     (0, A, D, P)*/
    private String sbscrbSttus = "0";

    /** 메뉴ID */
    private String menuId = "";

    /** sessionUniqId */
    private String sessionUniqId = "";

    /** procedure return value */
    private Integer procResultCode;

    private String procResultMsg;
}
