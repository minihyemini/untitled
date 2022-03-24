package jisungsoft.com.cop.bbs.service;

import jisungsoft.com.persistence.dto.Default;
import jisungsoft.com.cmm.FileVO;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDataVO extends Default {

	private static final long serialVersionUID = 8787496482729978000L;
	
	/** 게시글ID */
	private Integer nttId;

	/** 게시판ID */
	private String bbsId;

	/** 게시물번호 */
	private Integer nttNo = 0;

	/** 게시물제목 */
	private String nttSj;

	/** 게시물내용 */
	private String nttCn;

	/** 답글여부 */
	private String answerAt = "N";

	/** 답글위치 */
	private Integer answerLc;

	/** 정렬순서 */
	private Integer sortOrdr;

	/** 조회수 */
	private Integer rdcnt = 0;

	/** 사용여부 */
	private String useAt;

    /** 게시시작일 */
    private String ntceBgnde;

	/** 게시종료일 */
	private String ntceEndde;

	/** 게시자ID (작성자) */
	private String ntcrId;

	/** 게시자명 */
	private String ntcrNm;

	/** 첨부파일ID */
	private String atchFileId;

	/** 썸네일ID */
	private String thumbAtchFileId;

	/** 공지사항여부 */
	private String noticeAt = "N";

	/** 제목볼드여부 */
	private String sjBoldAt = "N";

	/** 비밀글여부 */
	private String secretAt = "N";

	/** 댓글여부 */
	private String replyAt = "N";

	/** 게시물내용검색용 */
	private String nttCnLookup;

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

	/** 게시판명 */
	private String bbsNm;

	/** 게시판유형 */
	private String bbsTyCode;

	/** 파일목록 */
	private List<FileVO> fileList;

	/** 답글 count */
	private Integer commentCo;

	/** 세미나시작기간 */
	private String seBeginDe;

	/** 세미나종료기간 */
	private String seEndDe;

	/** 세미나신청시작기간 */
	private String seRegBeginDe;

	/** 세미나신청종료기간 */
	private String seRegEndDe;

	/** 세미나정원 */
	private String seMaxPp;

	/** 세미나장소 */
	private String sePlace;

	/** 세미나담당자 */
	private String seHead;

	/** 구분 */
	private String gubun;

	/** 파일cnt */
	private Integer fileCnt = 0;

	/** 권한(등록/수정/삭제) */
	private String authority;

	/** sessionUniqId */
	private String sessionUniqId;

	/** 답글버튼권한 */
	private String replyAuth;

	/** 질문/답변 접근권한 */
	private String answerAuth;

	/** 활동현황 권한 */
	private String userAuth;

	private String pathSplit;

	/** 게시판 변경내역 ID */
	private Integer nttHstrId;
}
