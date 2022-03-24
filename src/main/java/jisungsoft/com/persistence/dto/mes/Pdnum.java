package jisungsoft.com.persistence.dto.mes;

import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.dto.Default;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * 품번
 */
@Data
public class Pdnum extends Default {
    /**
     * 품번 ID
     */
    private Integer pdnumId;
    /**
     * 품번
     */
    private String pdnumNum;
    /**
     * 품명
     */
    private String pdnumNm;
    /**
     * 제품구분
     */
    private String pdnumType;
    /**
     * 제품구분명
     */
    private String pdnumTypeNm;
    /**
     * 단위중량
     */
    private Integer pdnumWght;
    /**
     * 품목규격
     */
    private String pdnumStnd;
    /**
     * 품목안전재고
     */
    private Integer pdnumSfstck;
    /**
     * 품목코드
     */
    private String pdnumCode;
    /**
     * 품목자재코드
     */
    private String pdnumMtrscode;
    /**
     * 내외작구분
     */
    private String pdnumIotype;
    /**
     * 내외작구분명
     */
    private String pdnumIotypeNm;
    /**
     * 도면승은일
     */
    private String pdnumDrwappldate;
    /**
     * 등록도면수량
     */
    private Integer pdnumDrwqnty;
    /**
     * 품목단위
     */
    private String pdnumUnit;
    /**
     * 첨부파일 form name
     */
    private List<MultipartFile> atchFile;
    /**
     * 첨부파일
     */
    private String atchFileId;
    /**
     * 첨부파일 목록
     */
    private List<FileVO> atchFileList;
    /**
     * 이미지파일 form name
     */
    private List<MultipartFile> imgFile;
    /**
     * 이미지파일
     */
    private String imgFileId;
    /**
     * 이미지 파일목록
     */
    private List<FileVO> imgFileList;
    /**
     * 최초등록일
     */
    private String frstRegistPnttm;
    /**
     * 최초등록자 ID
     */
    private String frstRegisterId;
    /**
     * 최종수정시점
     */
    private String lastUpdtPnttm;
    /**
     * 최종수정자 ID
     */
    private String lastUpdusrId;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 등록자ID
     */
    private String frstRegisterUserId;
    /**
     * 사용자명
     */
    private String frstRegisterUserNm;
    /**
     * 수정자ID
     */
    private String lastUpdusrUserId;
    /**
     * 수정자명
     */
    private String lastUpdusrUserNm;
}
