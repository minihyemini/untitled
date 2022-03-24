package jisungsoft.com.uss.ion.nts.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class NoteTrnsmitVO extends Default {

	private static final long serialVersionUID = -3508126835450804382L;

	/** 쪽지 ID */
	private String noteId;

	/** 쪽지 송신 ID */
	private String noteTrnsmitId;

	/** 쪽지 수신 ID */
	private String noteRecptnId;

	/** 쪽지 송신자 ID */
	private String trnsmiterId;

	/** 개봉여부 */
	private String openYn;

	/** 쪽지 제목 */
	private String noteSj;

	/** 쪽지 내용 */
	private String noteCn;

	/** 쪽지 첨부파일 */
	private String atchFileId;

	/** 보낸 시작날짜 */
	private String searchFromDate;

	/** 보낸 종료날짜 */
	private String searchToDate;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록아이디 */
	private String frstRegisterId;

	/** 최초등록자 */
	private String frstRegisterNm;

	/** 최종수정일 */
	private String lastUpdusrPnttm;

	/** 최종수정자 아이디 */
	private String lastUpdusrId;

	/** 전체 건수 */
	private String rcverTotal;

	/** 개봉 건수 */
	private String openY;

	/** 미개봉 건수 */
	private String openN;

	private String checkList;
}
