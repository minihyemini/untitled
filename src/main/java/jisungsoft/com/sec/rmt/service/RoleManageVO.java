package jisungsoft.com.sec.rmt.service;

import lombok.Data;

import java.util.List;

@Data
public class RoleManageVO extends RoleManage{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 롤 목록
     */
    List<RoleManageVO> roleManageList;
    /**
     * 삭제대상 목록
     */
    String[] delYn;
}
