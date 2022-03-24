package jisungsoft.com.persistence.dto.member;

import jisungsoft.com.persistence.dto.Default;
import lombok.Getter;
import lombok.Setter;

/**
 * 기업회원 DTO
 */
@Getter @Setter
public class EnterpriseMember extends Default {

    /**
     * 사용자고유아이디
     */
    private String uniqId;
    /**
     * 기업회원ID
     */
    private String entrprsMberId;
    /**
     * 기업구분코드
     */
    private String entrprsSeCode;
    /**
     * 기업회원비밀번호
     */
    private String entrprsMberPassword;
    /**
     * 기업회원명
     */
    private String entrprsMberNm;
    /**
     * 지역번호
     */
    private String areaNo;
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 우편번호
     */
    private String zip;
    /**
     * 주소
     */
    private String adres;
    /**
     * 상세주소
     */
    private String detailAdres;
    /**
     * 기업회원이메일주소
     */
    private String entrprsMberEmailAdres;
    /**
     * 직위명
     */
    private String ofcpsNm;
    /**
     * 기업회원상태
     */
    private String entrprsMberSttusCode;
    /**
     * 가입일자
     */
    private String sbscrbDe;
    /**
     * 이동전화번호
     */
    private String mbtlnum;
    /**
     * 전화번호
     */
    private String telno;
    /**
     * 성별코드
     */
    private String sexdstnCode;
    /**
     * 사용자 유형
     */
    private String userTy;
    /**
     * 고유ID
     */
    private String esntlId;
    /**
     * 잠금여부
     */
    private String lockAt;
    /**
     * 잠금회수
     */
    private String lockCnt;
    /**
     * 잠금최종시점
     */
    private String lockLastPnttm;
    /**
     * 권한코드
     */
    private String authorCode;
}
