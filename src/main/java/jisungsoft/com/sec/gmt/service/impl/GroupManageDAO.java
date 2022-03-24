package jisungsoft.com.sec.gmt.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.sec.gmt.service.GroupManage;
import jisungsoft.com.sec.gmt.service.GroupManageVO;
import jisungsoft.com.sec.gmt.service.GroupUserManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository("groupManageDAO")
public class GroupManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "groupManageDAO";
    /**
     * 검색조건에 따른 그룹정보를 조회
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    public GroupManageVO selectGroup(GroupManageVO groupManageVO) throws Exception {
        return (GroupManageVO) selectOne(NAME_SPACE + ".selectGroup", groupManageVO);
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회(paging)
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> selectGroupList(GroupManageVO groupManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectGroupList", groupManageVO);
    }

    /**
     * 그룹에 속한 사용자 목록 조회(paging)
     * @param groupUserManageVO
     * @return
     * @throws Exception
     */
    public List<?> selectGroupUserList(GroupUserManageVO groupUserManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectGroupUserList", groupUserManageVO);
    }

    /**
     * 그룹에 속한 사용자 목록 카운트
     * @param groupUserManageVO
     * @return
     * @throws Exception
     */
    public int selectGroupUserListTotCnt(GroupUserManageVO groupUserManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectGroupUserListTotCnt", groupUserManageVO);
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회(not paging)
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> selectGroupNPList(GroupManageVO groupManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectGroupNPList", groupManageVO);
    }

    /**
     * 부서에 따른 팀 조회(not paging)
     * @param groupManageVO GroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    public List<?> selectGroupDetailList(GroupManageVO groupManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectGroupDetailList", groupManageVO);
    }

    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param groupManage GroupManage
     * @exception Exception
     */
    public void insertGroup(GroupManage groupManage) throws Exception {
        insert(NAME_SPACE + ".insertGroup", groupManage);
    }

    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param groupManage GroupManage
     * @exception Exception
     */
    public void updateGroup(GroupManage groupManage) throws Exception {
        update(NAME_SPACE + ".updateGroup", groupManage);
    }

    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param groupManage GroupManage
     * @exception Exception
     */
    public void deleteGroup(GroupManage groupManage) throws Exception {
        delete(NAME_SPACE + ".deleteGroup", groupManage);
    }

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param groupManageVO GroupManageVO
     * @return int
     * @exception Exception
     */
    public int selectGroupListTotCnt(GroupManageVO groupManageVO) throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectGroupListTotCnt", groupManageVO);
    }
}
