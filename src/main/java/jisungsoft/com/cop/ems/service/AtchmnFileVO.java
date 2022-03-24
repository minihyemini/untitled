package jisungsoft.com.cop.ems.service;

import lombok.Data;

/**
 * 발송메일에 첨부되는 파일 VO 클래스
 */
@Data
public class AtchmnFileVO {

	/** 첨부파일ID */
	private String atchFileId;
	/** 파일연번 */
	private String fileSn;
	/** 원파일명 */
	private String orignlFileNm;
	/** 저장파일명 */
	private String streFileNm;
	/** 파일저장경로 */
	private String fileStreCours;
	/** 파일확장자 */
	private String fileExtsn;
	/** 파일크기 */
	private int fileMg;
}
