package jisungsoft.com.uss.ion.ntm.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class NoteVO extends Default {

	private static final long serialVersionUID = 2815899376182571195L;

	/** 쪽지ID */
	private String noteId;

	/** 쪽지송신ID */
	private String noteTrnsmitId;

	/** 쪽지수신ID */
	private String noteRecptnId;

	/** 송신자ID */
	private String trnsmiterId;

	/** 송신자orgID */
	private String trnsmiterOrgId;

	/** 송신자 */
	private String trnsmiterNm;

	/** 송신자 */
	private String trnsmiterPnttm;

	/** 수신자ID */
	private String rcverId;

	/** 삭제여부부*/
	private String deleteAt;

	/** 개봉여부 */
	private String openYn;

	/** 쪽지제목 */
	private String noteSj;

	/** 쪽지내용 */
	private String noteCn;

	/** 첨부파일ID */
	private String atchFileId;

	/** 수신구분 */
	private String recptnSe;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록시점 */
	private String frstRegistPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종등록시점 */
	private String lastUpdtPnttm;

	/** 발송시간(~분전) */
	private String timeStr;
}
