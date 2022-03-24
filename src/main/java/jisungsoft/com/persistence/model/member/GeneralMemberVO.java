package jisungsoft.com.persistence.model.member;

import lombok.Getter;
import lombok.Setter;

/**
 * 일반회원 VO
 */
@Getter
@Setter
public class GeneralMemberVO {

    protected GeneralMemberVO() {
    }

    /**
     * 사용자고유아이디
     */
    private String uniqId;
    /**
     * 회원ID
     */
    private String mberId;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 회원명
     */
    private String mberNm;
    /**
     * 지역번호
     */
    private String areaNo;
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
     * 회원상태
     */
    private String mberSttusCode;
    /**
     * 이동전화번호
     */
    private String mbtlnum;
    /**
     * 사무실전화번호
     */
    private String offmTelno;
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 회원이메일주소
     */
    private String mberEmailAdres;
    /**
     * 가입일자
     */
    private String sbscrbDe;
    /**
     * 성별코드
     */
    private String sexdstnCode;
    /**
     * 생년월일
     */
    private String brthdy;
    /**
     * 잠금여부
     */
    private String lockAt;

    public static GeneralMemberVO createDataGeneralMember(String uniqId, String mberId, String password, String mberNm, String areaNo, String zip, String adres, String detailAdres, String mberSttusCode, String mbtlnum, String groupId, String mberEmailAdres, String sexdstnCode, String brthdy) {
        GeneralMemberVO generalMemberVO = new GeneralMemberVO();
        generalMemberVO.uniqId = uniqId;
        generalMemberVO.mberId = mberId;
        generalMemberVO.password = password;
        generalMemberVO.mberNm = mberNm;
        generalMemberVO.areaNo = areaNo;
        generalMemberVO.zip = zip;
        generalMemberVO.adres = adres;
        generalMemberVO.detailAdres = detailAdres;
        generalMemberVO.mberSttusCode = mberSttusCode;
        generalMemberVO.mbtlnum = mbtlnum;
        generalMemberVO.groupId = groupId;
        generalMemberVO.mberEmailAdres = mberEmailAdres;
        generalMemberVO.sexdstnCode = sexdstnCode;
        generalMemberVO.brthdy = brthdy;

        return generalMemberVO;
    }

    public static GeneralMemberVO createDataIdGeneralMember(String uniqId) {
        GeneralMemberVO generalMemberVO = new GeneralMemberVO();
        generalMemberVO.uniqId = uniqId;

        return generalMemberVO;
    }

    public static GeneralMemberVO createDataEmailGeneralMember(String uniqId, String email) {
        GeneralMemberVO generalMemberVO = new GeneralMemberVO();
        generalMemberVO.uniqId = uniqId;
        generalMemberVO.mberEmailAdres = email;

        return generalMemberVO;
    }

    public static GeneralMemberVO createDataPasswordGeneralMember(String uniqId, String password) {
        GeneralMemberVO generalMemberVO = new GeneralMemberVO();
        generalMemberVO.uniqId = uniqId;
        generalMemberVO.password = password;

        return generalMemberVO;
    }
}
