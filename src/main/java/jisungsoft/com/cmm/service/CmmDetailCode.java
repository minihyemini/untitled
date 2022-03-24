package jisungsoft.com.cmm.service;

import lombok.Data;

@Data
public class CmmDetailCode {

    /*
     * 분류코드
     */
    private String clCode = "";

    /*
     * 코드ID
     */
    private String codeId = "";

    /*
     * 코드ID명
     */
    private String codeIdNm = "";

    /*
     * 상세코드
     */
    private String code = "";

    /*
     * 상세코드명
     */
    private String codeNm = "";

    /*
     * 상세코드설명
     */
    private String codeDc = "";

    /*
     * 사용여부
     */
    private String useAt = "";

    /*
     * 최초등록자ID
     */
    private String frstRegisterId = "";

    /*
     * 최종수정자ID
     */
    private String lastUpdusrId   = "";
}
