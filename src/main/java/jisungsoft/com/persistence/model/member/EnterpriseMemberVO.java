package jisungsoft.com.persistence.model.member;

import lombok.Getter;
import lombok.Setter;

/**
 * 기업회원 VO
 */
@Getter
@Setter
public class EnterpriseMemberVO {

    protected EnterpriseMemberVO() {
    }

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
     * 우편번호
     */
    private String zip;
    /**
     * 주소
     */
    private String adres;
    /**
     * 지역번호
     */
    private String areaNo;
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 상세주소
     */
    private String detailAdres;
    /**
     * 기업회원이메일주소
     */
    private String entrprsMberEmailAdres;
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
}
