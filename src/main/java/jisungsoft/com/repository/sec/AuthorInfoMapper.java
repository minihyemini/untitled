package jisungsoft.com.repository.sec;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.sec.AuthorInfo;
import jisungsoft.com.persistence.model.sec.AuthorInfoVO;

import java.util.List;

@Mapper("authorInfoMapper")
public interface AuthorInfoMapper {

    /**
     * 권한목록을 조회한다.
     */
    @SuppressWarnings("unchecked")
    public List<AuthorInfo> selectAuthorList(AuthorInfo authorManage);

    /**
     * 권한을 등록한다.
     */
    public void insertAuthor(AuthorInfoVO authorInfoVO);

    /**
     * 권한을 수정한다.
     */
    public void updateAuthor(AuthorInfoVO authorInfoVO);

    /**
     * 권한을 삭제한다.
     */
    public void deleteAuthor(AuthorInfoVO authorInfoVO);

    /**
     * 권한을 조회한다.
     */
    public AuthorInfo selectAuthor(AuthorInfo authorManage);

    /**
     * 권한목록 총 갯수를 조회한다.
     */
    public int selectAuthorListTotCnt(AuthorInfo authorManage);

    /**
     * 모든 권한목록을 조회한다.
     */
    public List<AuthorInfo> selectAuthorAllList(AuthorInfo authorManage);
}
