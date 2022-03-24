package jisungsoft.com.sec.ram.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.sec.ram.service.AuthorRoleManage;
import jisungsoft.com.sec.ram.service.AuthorRoleManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("authorRoleManageDAO")
public class AuthorRoleManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "authorRoleManageDAO";

    /**
     * 권한 롤 관계정보를 조회
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return AuthorRoleManageVO
     * @exception Exception
     */
    public AuthorRoleManageVO selectAuthorRole(AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectAuthorRole", authorRoleManageVO);
    }

    /**
     * 권한 롤 관계정보 목록 조회
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return List<AuthorRoleManageVO>
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> selectAuthorRoleList(AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectAuthorRoleList", authorRoleManageVO);
    }

    /**
     * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    public void insertAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
        insert(NAME_SPACE + ".insertAuthorRole", authorRoleManage);
    }

    /**
     * 수정된 권한 롤 관계정보를 데이터베이스에 반영
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    public void updateAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
        update(NAME_SPACE + ".updateAuthorRole", authorRoleManage);
    }

    /**
     * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param authorRoleManage AuthorRoleManage
     * @exception Exception
     */
    public void deleteAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
        delete(NAME_SPACE + ".deleteAuthorRole", authorRoleManage);
    }

    /**
     * 목록조회 카운트를 반환한다
     * @param authorRoleManageVO AuthorRoleManageVO
     * @return int
     * @exception Exception
     */
    public int selectAuthorRoleListTotCnt(AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectAuthorRoleListTotCnt", authorRoleManageVO);
    }
}
