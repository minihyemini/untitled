package jisungsoft.com.cop.bbs.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class BbsMasterVO extends Default {

	private static final long serialVersionUID = 8781704446347715988L;
	
	/** 게시판ID */
	private String bbsId;

	/** 게시판명 */
	private String bbsNm;

	/** 게시판주소 */
	private String bbsAddr;

	/** 게시판소개 */
	private String bbsIntrcn;

	/** 게시판유형코드 */
	private String bbsTyCode;

	/** 게시판유형명 */
	private String bbsTyCodeNm;

	/** 답장가능여부 */
	private String answerPosblAt = "N";

	/** 파일첨부가능여부 */
	private String fileAtchPosblAt = "N";

	/** 댓글가능여부 */
	private String replyPosblAt = "N";

	/** 사용여부 */
	private String useAt;

	/** 강의ID */
	private String letId;

	/** 과제ID */
	private String hwId;

	/** 커뮤니티ID */
	private String cmmntyId;

	/** 권한목록 */
	private String authorityList;

	/** 권한코드 */
	private String authorCode;

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
}
