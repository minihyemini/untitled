package jisungsoft.com.cop.cmy.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class CommunityUserVO extends Default {

	private static final long serialVersionUID = -3314842020187379595L;

	/** 커뮤니티ID */
	private String cmmntyId;

	/** 업무사용자ID */
	private String emplyrId;

	/** 관리자여부 */
	private String mngrAt;

	/** 회원상태 */
	private String mberSttus;

	/** 회원상태명 */
	private String mberSttusNm;

	/** 가입일자 */
	private String sbscrbDe;

	/** 탈퇴일 */
	private String secsnDe;

	/** 사용여부 */
	private String useAt;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록자명 */
	private String frstRegisterNm;

	/** 최초등록시점 */
	private String frstRegistPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종수정시점 */
	private String lastUpdtPnttm;

	/** 회원상태별 건수 */
	private Integer stts01 = 0;
	private Integer stts02 = 0;
	private Integer stts03 = 0;
	private Integer stts04 = 0;
}
