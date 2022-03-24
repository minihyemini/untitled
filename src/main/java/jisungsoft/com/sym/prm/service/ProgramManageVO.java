package jisungsoft.com.sym.prm.service;

import lombok.Data;

import java.util.List;

@Data
public class ProgramManageVO extends ProgramManage {

    private List<?> programManageList;

    /** 통합게시판 삭제 시 필요 - 게시판ID */
    private String bbsId;
}
