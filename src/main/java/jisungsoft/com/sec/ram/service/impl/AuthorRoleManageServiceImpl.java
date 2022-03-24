package jisungsoft.com.sec.ram.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.sec.ram.service.AuthorRoleManage;
import jisungsoft.com.sec.ram.service.AuthorRoleManageService;
import jisungsoft.com.sec.ram.service.AuthorRoleManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("authorRoleManageService")
public class AuthorRoleManageServiceImpl extends EgovAbstractServiceImpl implements AuthorRoleManageService {

    @Resource(name="authorRoleManageDAO")
    private AuthorRoleManageDAO authorRoleManageDAO;

    @Override
    public AuthorRoleManageVO selectAuthorRole(AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return authorRoleManageDAO.selectAuthorRole(authorRoleManageVO);
    }

    @Override
    public List<?> selectAuthorRoleList(AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return authorRoleManageDAO.selectAuthorRoleList(authorRoleManageVO);
    }

    @Override
    public void insertAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
        authorRoleManageDAO.insertAuthorRole(authorRoleManage);
    }

    @Override
    public void updateAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
        authorRoleManageDAO.updateAuthorRole(authorRoleManage);
    }

    @Override
    public void deleteAuthorRole(AuthorRoleManage authorRoleManage) throws Exception {
        authorRoleManageDAO.deleteAuthorRole(authorRoleManage);
    }

    @Override
    public int selectAuthorRoleListTotCnt(AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return authorRoleManageDAO.selectAuthorRoleListTotCnt(authorRoleManageVO);
    }
}
