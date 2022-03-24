package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sec.AuthorGroup;
import jisungsoft.com.persistence.model.sec.AuthorGroupVO;
import jisungsoft.com.repository.sec.AuthorGroupMapper;
import jisungsoft.com.service.AuthorGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("authorGroupService")
public class AuthorGroupServiceImpl extends EgovAbstractServiceImpl implements AuthorGroupService {

    @Resource(name = "authorGroupMapper")
    private AuthorGroupMapper authorGroupMapper;

    @Override
    public List<AuthorGroup> getAuthorList() {
        return authorGroupMapper.selectAuthorList();
    }

    @Override
    public List<AuthorGroup> getGroupList(AuthorGroup param) {
        return authorGroupMapper.selectGroupList(param);
    }

    @Override
    public List<AuthorGroup> getAuthorGroupUserList(AuthorGroup param) {
        return authorGroupMapper.selectAuthorGroupUserList(param);
    }

    @Override
    public AuthorGroup getAuthorGroup(AuthorGroup param) {
        return authorGroupMapper.selectAuthorGroup(param);
    }

    @Override
    public int getAuthorGroupUserListTotCnt(AuthorGroup param) {
        return authorGroupMapper.selectAuthorGroupUserListTotCnt(param);
    }

    @Override
    public void addAuthorGroupUser(AuthorGroup param) throws Exception {

        int cnt = authorGroupMapper.selectAuthorListTotCnt(param);
        AuthorGroupVO authorGroupVO = AuthorGroupVO.createDataAuthorGroup(
                param.getMberTyCode(),
                param.getAuthorCode(),
                param.getEsntlId());

        if (StringUtils.hasText(param.getAuthorCode())) {
            if (cnt == 0) {
                authorGroupMapper.insertAuthorGroupUser(authorGroupVO);
            } else {
                authorGroupMapper.deleteAuthorGroupUser(authorGroupVO);
                authorGroupMapper.insertAuthorGroupUser(authorGroupVO);
            }
        } else {
            authorGroupMapper.deleteAuthorGroupUser(authorGroupVO);
        }

    }

    @Override
    public void editAuthorGroupUser(AuthorGroup param) throws Exception {
        AuthorGroupVO authorGroupVO = AuthorGroupVO.createDataAuthorGroup(
                param.getMberTyCode(),
                param.getAuthorCode(),
                param.getEsntlId());
        authorGroupMapper.updateAuthorGroupUser(authorGroupVO);
    }

    @Override
    public void removeAuthorGroupUser(AuthorGroup param) throws Exception {
        AuthorGroupVO authorGroupVO = AuthorGroupVO.createDataAuthorGroup(
                null,
                null,
                param.getEsntlId());
        authorGroupMapper.deleteAuthorGroupUser(authorGroupVO);
    }

}
