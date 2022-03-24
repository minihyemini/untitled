package jisungsoft.com.sec.gmt.service;

import lombok.Data;

import java.util.List;

@Data
public class GroupUserManageVO extends GroupUserManage{

    /**
     * 그룹 사용자 목록
     */
    private List<GroupUserManageVO> groupUserManageList;
}
