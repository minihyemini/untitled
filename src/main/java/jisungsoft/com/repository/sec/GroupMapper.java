package jisungsoft.com.repository.sec;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sec.Group;
import jisungsoft.com.persistence.model.sec.GroupVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("groupMapper")
public interface GroupMapper {

    /**
     * 검색조건에 따른 그룹정보를 조회
     */
    public Group selectGroupDetail(Group group);
    /**
     * 시스템사용 목적별 그룹 목록 조회(paging)
     */
    public List<Group> selectGroupList(Group group);
    /**
     * 그룹에 속한 사용자 목록 조회(paging)
     */
    public List<Group> selectGroupUserList(Group group);
    /**
     * 그룹에 속한 사용자 목록 카운트
     */
    public int selectGroupUserListTotCnt(Group group);
    /**
     * 시스템사용 목적별 그룹 목록 조회(not paging)
     */
    public List<Group> selectGroupAllList(Group group);

    /**
     * 그룹 목록 조회(엑셀 출력용)
     */
    public List<Group> selectGroupAllListMap(Group group);
    /**
     * 부서에 따른 팀 조회(not paging)
     */
    public List<Group> selectGroupDetailList(Group group);
    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     */
    public void insertGroup(GroupVO groupVO) throws DataAccessException;
    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     */
    public void updateGroup(GroupVO groupVO) throws DataAccessException;
    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     */
    public void deleteGroup(GroupVO groupVO) throws DataAccessException;
    /**
     * 롤목록 총 갯수를 조회한다.
     */
    public int selectGroupListTotCnt(Group group);
}
