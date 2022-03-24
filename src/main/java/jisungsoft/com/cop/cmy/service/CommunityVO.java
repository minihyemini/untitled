package jisungsoft.com.cop.cmy.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class CommunityVO extends Default {

	private static final long serialVersionUID = -6261424113800077211L;

	/** 게시판ID */
	private String bbsId;

	/** 커뮤니티ID */
	private String cmmntyId;

	/** 커뮤니티명 */
	private String cmmntyNm;

	/** 프로젝트명 */
	private String cmmntySubNm;

	/** 커뮤니티소개 */
	private String cmmntyIntrcn;

	/** 커뮤니티내용 */
	private String cmmntyCn;

	/** 사용여부 */
	private String useAt;

	/** 자동승인구분 */
	private String permitAt;

	/** 등록구분코드 */
	private String registSeCode;

	/** 템플릿ID */
	private String tmplatId;

	/** 첨부파일ID */
	private String atchFileId;

	/** 썸네일ID */
	private String thumbAtchFileId;

	/** 썸네일 순번 */
	private String thumbFileSn;

	/** 조회수 */
	private Integer rdcnt = 0;

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

	/** 권한 */
	private String authority;

	/** 쓰기/수정 권한 */
	private String cmmntyAuth;

	/** 활동현황 권한 */
	private String userAuth;
}
