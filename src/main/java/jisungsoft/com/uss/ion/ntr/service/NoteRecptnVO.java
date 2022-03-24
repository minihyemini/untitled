package jisungsoft.com.uss.ion.ntr.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class NoteRecptnVO extends Default {

	private static final long serialVersionUID = -4869554428423759977L;

	/** 쪽지ID */
	private String noteId;

	/** 쪽지송신ID */
	private String noteTrnsmitId;

	/** 쪽지수신ID */
	private String noteRecptnId;

	/** 수신자ID */
	private String rcverId;

	/** 수신자 */
	private String rcverNm;

	/** 수신시각 */
	private String rcverPnttm;

	/** 개봉여부 */
	private String openYn;

	/** 쪽지제목 */
	private String noteSj;

	/** 쪽지내용 */
	private String noteCn;

	/** 발신자 */
	private String trnsmiterNm;

	/** 발신시각 */
	private String trnsmiterPnttm;

	/** 보낸 시작날짜 */
	private String searchFromDate;

	/** 보낸 종료날짜 */
	private String searchToDate;

	/** 첨부파일ID */
	private String atchFileId;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록시점 */
	private String frstRegistPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종등록시점 */
	private String lastUpdtPnttm;

	private String checkList;
}
