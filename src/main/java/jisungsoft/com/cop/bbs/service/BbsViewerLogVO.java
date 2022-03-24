package jisungsoft.com.cop.bbs.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

/**
 * 게시판열람자
 */
@Data
public class BbsViewerLogVO extends Default {
    /**
     * 열람자ID
     */
    private Integer bvId;
    /**
     * 고유ID
     */
    private String esntlId;
    /**
     * 게시물ID
     */
    private Integer nttId;
    /**
     * 최초확인시점
     */
    private String frstRegistPnttm;
}
