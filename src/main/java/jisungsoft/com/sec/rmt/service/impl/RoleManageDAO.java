package jisungsoft.com.sec.rmt.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.sec.rmt.service.RoleManage;
import jisungsoft.com.sec.rmt.service.RoleManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleManageDAO")
public class RoleManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "roleManageDAO";

    /**
     * 등록된 롤 정보 조회
     * @param roleManageVO RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    public RoleManageVO selectRole(RoleManageVO roleManageVO) throws Exception {
        return (RoleManageVO) selectOne(NAME_SPACE + ".selectRole", roleManageVO);
    }

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> selectRoleList(RoleManageVO roleManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectRoleList", roleManageVO);
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage RoleManage
     * @exception Exception
     */
    public void insertRole(RoleManage roleManage) throws Exception {
        insert(NAME_SPACE + ".insertRole", roleManage);
    }
    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage RoleManage
     * @exception Exception
     */
    public void updateRole(RoleManage roleManage) throws Exception {
        update(NAME_SPACE + ".updateRole", roleManage);
    }
    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage RoleManage
     * @exception Exception
     */
    public void deleteRole(RoleManage roleManage) throws Exception {
        delete(NAME_SPACE + ".deleteRole", roleManage);
    }

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param roleManageVO RoleManageVO
     * @return int
     * @exception Exception
     */
    public int selectRoleListTotCnt(RoleManageVO roleManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectAuthorListTotCnt", roleManageVO);
    }

    /**
     * 등록된 모든 롤 정보 목록 조회
     * @param roleManageVO RoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
     */
    public List<?> selectRoleAllList(RoleManageVO roleManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectRoleAllList", roleManageVO);
    }
}
