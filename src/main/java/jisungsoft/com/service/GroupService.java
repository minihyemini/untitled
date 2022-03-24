package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sec.Group;

import java.util.List;

public interface GroupService {
    /**
     * 검색조건에 따른 그룹정보를 조회
     */
    public Group getGroupDetail(Group group);
    /**
     * 시스템사용 목적별 그룹 목록 조회(paging)
     */
    public List<Group> getGroupList(Group group);
    /**
     *
     */
    public List<Group> getGroupUserList(Group group);
    /**
     *
     */
    public int getGroupUserListTotCnt(Group group);
    /**
     * 시스템사용 목적별 그룹 목록 조회(not paging)
     */
    public List<Group> getGroupAllList(Group group);
    /**
     * 그룹 목록 조회(엑셀 출력용)
     */
    public List<Group> getGroupAllListMap(Group group);
    /**
     * 부서에 따른 팀 조회(not paging)
     */
    public List<Group> getGroupDetailList(Group group);
    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     */
    public Group addGroup(Group group) throws Exception;
    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     */
    public void editGroup(Group group) throws Exception;
    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     */
    public void removeGroup(Group group) throws Exception;
    /**
     * 목록조회 카운트를 반환한다
     */
    public int getGroupListTotCnt(Group group);
}
