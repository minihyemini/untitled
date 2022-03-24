package jisungsoft.com.sec.ram.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.persistence.dto.sec.AuthorInfo;
import jisungsoft.com.persistence.model.sec.AuthorInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("authorManageDAO")
public class AuthorManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "authorManageDAO";
    /**
     * 권한목록을 조회한다.
     * @param authorInfoVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> selectAuthorList(AuthorInfoVO authorInfoVO) throws Exception {
        return selectList(NAME_SPACE + ".selectAuthorList", authorInfoVO);
    }

    /**
     * 권한을 등록한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    public void insertAuthor(AuthorInfo authorManage) throws Exception {
        insert(NAME_SPACE + ".insertAuthor", authorManage);
    }

    /**
     * 권한을 수정한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    public void updateAuthor(AuthorInfo authorManage) throws Exception {
        update(NAME_SPACE + ".updateAuthor", authorManage);
    }

    /**
     * 권한을 삭제한다.
     * @param authorManage AuthorManage
     * @exception Exception
     */
    public void deleteAuthor(AuthorInfo authorManage) throws Exception {
        delete(NAME_SPACE + ".deleteAuthor", authorManage);
    }

    /**
     * 권한을 조회한다.
     * @param authorInfoVO AuthorManageVO
     * @return AuthorManageVO
     * @exception Exception
     */
    public AuthorInfoVO selectAuthor(AuthorInfoVO authorInfoVO) throws Exception {
        return (AuthorInfoVO) selectOne(NAME_SPACE + ".selectAuthor", authorInfoVO);
    }

    /**
     * 권한목록 총 갯수를 조회한다.
     * @param authorInfoVO AuthorManageVO
     * @return int
     * @exception Exception
     */
    public int selectAuthorListTotCnt(AuthorInfoVO authorInfoVO)  throws Exception {
        return (Integer)selectOne(NAME_SPACE + ".selectAuthorListTotCnt", authorInfoVO);
    }

    /**
     * 모든 권한목록을 조회한다.
     * @param authorInfoVO AuthorManageVO
     * @return List<AuthorManageVO>
     * @exception Exception
     */
    public List<AuthorInfoVO> selectAuthorAllList(AuthorInfoVO authorInfoVO) throws Exception {
        return selectList(NAME_SPACE + ".selectAuthorAllList", authorInfoVO);
    }
}
