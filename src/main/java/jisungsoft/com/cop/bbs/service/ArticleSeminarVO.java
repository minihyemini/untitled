package jisungsoft.com.cop.bbs.service;

import jisungsoft.com.persistence.dto.Default;
import jisungsoft.com.cmm.FileVO;
import lombok.Data;

import java.util.List;

@Data
public class ArticleSeminarVO extends Default {
    //private static final long serialVersionUID

    /** 세미나ID */
    private String seId;

    /** 세미나제목 */
    private String seSj;

    /** 세미나내용 */
    private String seCn;

    /** 세미나시작기간 */
    private String seBeginDe;

    /** 세미나종료기간 */
    private String seEndDe;

    /** 세미나신청시작기간 */
    private String seRegBeginDe;

    /** 세미나신청종료기간 */
    private String seRegEndDe;

    /** 세미나 정원 */
    private Integer seMaxPp;

    /** 세미나장소 */
    private String sePlace;

    /** 세미나담당자 */
    private String seHead;

    /** 세미나멤버ID */
    private String smberId;

    /** 고유ID */
    private String esntlId;

    /** 신청일시 */
    private String seRegDate;

    /** 첨부파일ID */
    private String atchFileId;

    /** 파일cnt */
    private Integer fileCnt = 0;

    /** 파일목록 */
    private List<FileVO> fileList;

    /** 권한(등록/수정/삭제) */
    private String authority;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최초등록자명 */
    private String frstRegisterNm;

    /** 세미나 신청자 인원 */
    private String mberCnt;
}
