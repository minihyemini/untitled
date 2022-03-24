package jisungsoft.com.uss.umt.model;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class MberManageVO extends MberExtManageVO{
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
    /**
     * 사용자 유형
     */
    private String userTy;
    /**
     * 검색조건 회원타입
     */
    private String mberTy;
    /**
     * 검색조건 가입일자 시작일
     */
    private String sbscrbDeBegin;
    /**
     * 검색조건 가입일자 종료일
     */
    private String sbscrbDeEnd;
    /**
     * DN 값
     */
    private String subDn;
    /**
     * 권한코드
     */
    private String authorCode;

    /**
     * 일반회원 가입/수정 validation
     * @return
     */
    public boolean validationMberForm(char type) {
        boolean result = true;

        if (Character.compare(type, 'I') == 0) {    //회원가입
            if (EgovStringUtil.isEmpty(password)) {
                result = false;
            }
        } else if (Character.compare(type, 'U') == 0) {     //회원수정

        }

        if (EgovStringUtil.isEmpty(mberNm)) {
            result = false;
        }

        return result;
    }

    /**
     * 생년월일 형식 체크
     * @return
     */
    public boolean validateionMberBrthdy() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        if (!EgovStringUtil.isEmpty(brthdy)) {
            try {
                Date brthdyTo = format.parse(brthdy);
                int compare = brthdyTo.compareTo(today);
                if (compare > 0) {
                    return false;
                }

            } catch (ParseException e) {
                return false;
            }
        }

        return true;
    }

}
