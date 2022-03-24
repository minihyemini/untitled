package jisungsoft.com.persistence.model.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * 업무회원 VO
 */
@Getter @Setter
public class EmployeeMemberVO {

    /**
     * 사용자고유아이디
     */
    private String uniqId;
    /**
     * 사원 ID
     */
    private String emplyrId;
    /**
     * 조직 ID
     */
    private String orgnztId;
    /**
     * 사원명
     */
    private String emplyrNm;
    /**
     * 사원 번호
     */
    private String emplyrNum;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 성별코드
     */
    private String sexdstnCode;
    /**
     * 외국인여부
     */
    private String foreignerAt;
    /**
     * 근태체크여부
     */
    private String attendanceAt;
    /**
     * 생일
     */
    private String brthdy;
    /**
     * 주소
     */
    private String adres;
    /**
     * 지역번호
     */
    private String areaNo;
    /**
     * 상세주소
     */
    private String detailAdres;
    /**
     * 우편번호
     */
    private String zip;
    /**
     * 사무실전화번호
     */
    private String offmTelno;
    /**
     * 핸드폰번호
     */
    private String mbtlnum;
    /**
     * 이메일주소
     */
    private String emailAdres;
    /**
     * 직위명
     */
    private String ofcpsNm;
    /**
     * 그룹 ID
     */
    private String groupId;
    /**
     * 소속기관코드
     */
    private String pstinstCode;
    /**
     * 사용자 상태
     */
    private String emplyrSttusCode;
    /**
     * 잠금여부
     */
    private String lockAt;
    /**
     * 가입일
     */
    private String sbscrbDe;
    /**
     * 입사일
     */
    private String joiningDate;
    /**
     * 퇴사일
     */
    private String quittingDate;

    protected EmployeeMemberVO() {
    }

    public static EmployeeMemberVO createDataEmployeeMember(String uniqId, String emplyrId, String orgnztId, String emplyrNm, String emplyrNum, String password, String sexdstnCode, String foreignerAt, String attendanceAt, String brthdy, String adres, String areaNo, String detailAdres, String zip, String offmTelno, String mbtlnum, String emailAdres, String ofcpsNm, String groupId, String pstinstCode, String emplyrSttusCode, String joiningDate, String quittingDate) {
        EmployeeMemberVO employeeMemberVO = new EmployeeMemberVO();

        employeeMemberVO.uniqId = uniqId;
        employeeMemberVO.emplyrId = emplyrId;
        employeeMemberVO.orgnztId = orgnztId;
        employeeMemberVO.emplyrNm = emplyrNm;
        employeeMemberVO.emplyrNum = emplyrNum;
        employeeMemberVO.password = password;
        employeeMemberVO.sexdstnCode = sexdstnCode;
        employeeMemberVO.foreignerAt = foreignerAt;
        employeeMemberVO.attendanceAt = attendanceAt;
        employeeMemberVO.brthdy = StringUtils.hasText(brthdy) ? brthdy : null;
        employeeMemberVO.adres = adres;
        employeeMemberVO.areaNo = areaNo;
        employeeMemberVO.detailAdres = detailAdres;
        employeeMemberVO.zip = zip;
        employeeMemberVO.offmTelno = offmTelno;
        employeeMemberVO.mbtlnum = mbtlnum;
        employeeMemberVO.emailAdres = emailAdres;
        employeeMemberVO.ofcpsNm = ofcpsNm;
        employeeMemberVO.groupId = groupId;
        employeeMemberVO.pstinstCode = pstinstCode;
        employeeMemberVO.emplyrSttusCode = emplyrSttusCode;
        employeeMemberVO.joiningDate = StringUtils.hasText(joiningDate) ? joiningDate : null;
        employeeMemberVO.quittingDate = StringUtils.hasText(quittingDate) ? quittingDate : null;

        return employeeMemberVO;
    }

    public static EmployeeMemberVO createDataIdEmployeeMember(String uniqId, String emplyrId) {
        EmployeeMemberVO employeeMemberVO = new EmployeeMemberVO();

        employeeMemberVO.uniqId = uniqId;
        employeeMemberVO.emplyrId = emplyrId;
        return employeeMemberVO;
    }

    public static EmployeeMemberVO createDataEmailEmployeeMember(String uniqId, String email) {
        EmployeeMemberVO employeeMemberVO = new EmployeeMemberVO();
        employeeMemberVO.uniqId = uniqId;
        employeeMemberVO.emailAdres = email;

        return employeeMemberVO;
    }

    public static EmployeeMemberVO createDataPasswordEmployeeMember(String uniqId, String password) {
        EmployeeMemberVO employeeMemberVO = new EmployeeMemberVO();
        employeeMemberVO.uniqId = uniqId;
        employeeMemberVO.password = password;

        return employeeMemberVO;
    }
}
