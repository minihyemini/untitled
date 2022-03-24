package jisungsoft.com.cop.bbs.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentVO implements Serializable{

	private static final long serialVersionUID = 4166399895538550959L;
	
	/** 게시글ID */
	private Integer nttId;

	/** 게시판ID */
	private String bbsId;

	/** 댓글번호 */
	private Integer answerNo = 0;

	/** 작성자ID */
	private String wrterId;

	/** 작성자명 */
	private String wrterNm;

	/** 댓글 */
	private String answer;

	/** 사용여부 */
	private String useAt;

	/** 비밀번호 */
	private String password;

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
