package jisungsoft.com.cop.ems.service;

import lombok.Data;

/**
 * 발송메일 모델 클래스
 */
@Data
public class SndngMail {

	/** 발신자 */
	public String dsptchPerson;
	/** 내용 */
	public String emailCn;
	/** 메시지ID */
	public String mssageId;
	/** 수신자 */
	public String recptnPerson;
	/** 제목 */
	public String sj;
	/** 발송결과코드 */
	public String sndngResultCode;
	/** 발신일자 */
	private String sndngDe;
}
