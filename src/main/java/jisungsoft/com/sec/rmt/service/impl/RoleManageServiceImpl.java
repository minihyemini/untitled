package jisungsoft.com.sec.rmt.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.sec.rmt.service.RoleManage;
import jisungsoft.com.sec.rmt.service.RoleManageService;
import jisungsoft.com.sec.rmt.service.RoleManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleManageService")
public class RoleManageServiceImpl extends EgovAbstractServiceImpl implements RoleManageService {

    @Resource(name = "roleManageDAO")
    private RoleManageDAO roleManageDAO;

    @Override
    public RoleManageVO selectRole(RoleManageVO roleManageVO) throws Exception {
        return roleManageDAO.selectRole(roleManageVO);
    }

    @Override
    public List<?> selectRoleList(RoleManageVO roleManageVO) throws Exception {
        return roleManageDAO.selectRoleList(roleManageVO);
    }

    @Override
    public void deleteRole(RoleManage roleManage) throws Exception {
        roleManageDAO.deleteRole(roleManage);
    }

    @Override
    public void updateRole(RoleManage roleManage) throws Exception {
        roleManageDAO.updateRole(roleManage);
    }

    @Override
    public RoleManageVO insertRole(RoleManage roleManage, RoleManageVO roleManageVO) throws Exception {
        roleManageDAO.insertRole(roleManage);
        roleManageVO.setRoleCode(roleManage.getRoleCode());
        return roleManageDAO.selectRole(roleManageVO);
    }

    @Override
    public int selectRoleListTotCnt(RoleManageVO roleManageVO) throws Exception {
        return roleManageDAO.selectRoleListTotCnt(roleManageVO);
    }

    @Override
    public List<?> selectRoleAllList(RoleManageVO roleManageVO) throws Exception {
        return roleManageDAO.selectRoleAllList(roleManageVO);
    }
}
