package jisungsoft.com.repository.sec;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sec.AuthorGroup;
import jisungsoft.com.persistence.model.sec.AuthorGroupVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("authorGroupMapper")
public interface AuthorGroupMapper {

    public List<AuthorGroup> selectAuthorList();

    /**
     * 전체 그룹 목록
     */
    public List<AuthorGroup> selectGroupList(AuthorGroup authorGroupVO);

    /**
     * 그룹별 사용자 목록
     */
    public List<AuthorGroup> selectAuthorGroupUserList(AuthorGroup authorGroup);

    /**
     * 그룹별 사용자 목록 카운트
     */
    public int selectAuthorGroupUserListTotCnt(AuthorGroup authorGroup);

    /**
     * 그룹별 사용자 상세
     */
    public AuthorGroup selectAuthorGroup(AuthorGroup authorGroup);

    /**
     * 그룹별 사용자 권한 Cnt
     */
    public int selectAuthorListTotCnt(AuthorGroup authorGroup);

    /**
     * 그룹별 사용자 권한 등록
     */
    public void insertAuthorGroupUser(AuthorGroupVO authorGroupVO) throws DataAccessException;

    /**
     * 그룹별 사용자 권한 수정
     */
    public void updateAuthorGroupUser(AuthorGroupVO authorGroupVO) throws DataAccessException;

    /**
     * 그룹별 사용자 권한 삭제
     */
    public void deleteAuthorGroupUser(AuthorGroupVO authorGroupVO) throws DataAccessException;
}
