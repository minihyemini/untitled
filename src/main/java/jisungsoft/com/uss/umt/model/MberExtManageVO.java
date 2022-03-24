package jisungsoft.com.uss.umt.model;

import lombok.Data;

@Data
public class MberExtManageVO extends UserDefaultVO{
    /**
     * 직업
     */
    private String job;
    /**
     * 상세직업
     */
    private String jobDetail;
    /**
     * 소속
     */
    private String mberGroup;
    /**
     * 전공
     */
    private String major;
    /**
     * 전공분류
     */
    private String majorTypeCode;
    /**
     * 전공분류기타
     */
    private String majorTypeExt;
    /**
     * 회사 우편번호
     */
    private String jobZip;
    /**
     * 회사 주소
     */
    private String jobAdres;
    /**
     * 회사 상세주소
     */
    private String jobDetailAdres;
    /**
     * 부서
     */
    private String mberDept;
    /**
     * 직위
     */
    private String mberPosition;
    /**
     * 소속규모
     */
    private String deptSizeCode;
    /**
     * 산업분류
     */
    private String majorCategoryCode;
    /**
     * 개발경험유무
     */
    private String devExpAt;
    /**
     * AI관련경험유무
     */
    private String devAiAt;
    /**
     * 교육목적
     */
    private String eduPurpose;
    /**
     * 교육목적기타
     */
    private String eduPurposeExt;
    /**
     * 신청경로
     */
    private String regPath;
    /**
     * 신청경로기타
     */
    private String regPathExt;
    /**
     * 자기소개서
     */
    private String mberSelfFirst;
    /**
     * 수업참여를 위한 노력
     */
    private String mberSelfSecond;
    /**
     * AI교육 이수 후 활용계획
     */
    private String mberSelfThird;
}
