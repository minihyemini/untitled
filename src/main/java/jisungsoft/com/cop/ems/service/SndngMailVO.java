package jisungsoft.com.cop.ems.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 발송메일 VO 클래스
 */
@Data
public class SndngMailVO extends Default {
	/**
	 * 메일설정
	 */
	private UserEmailConfgVO userEmailConfgVO;
	/**
	 * 메세지ID
	 */
	private String mssageId;
	/**
	 * 발신자
	 */
	private String dsptchPerson;
	/**
	 * 수신자
	 */
	private String recptnPerson;
	/**
	 * 제목
	 */
	private String sj;
	/**
	 * 발송결과코드
	 */
	private String sndngResultCode;
	/**
	 * 메일내용
	 */
	private String emailCn;
	/**
	 * 첨부파일ID
	 */
	private String atchFileId;
	/**
	 * 첨부파일경로
	 */
	private String fileStreCours;
	/**
	 * 첨부파일이름
	 */
	private String orignlFileNm;
	/**
	 * 발신일자
	 */
	private String sndngDe;
	/**
	 * 첨부파일ID 리스트
	 */
	private String atchFileIdList;
	/**
	 * 발송요청XML내용
	 */
	private String xmlContent;
	/**
	 * 팝업링크여부(Y/N)
	 */
	private String link;
	/**
	 * HTML 이메일 여부
	 */
	private boolean isHtml = false;
	/**
	 * 인증키
	 */
	private String authKey;
}
