package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sec.AuthorInfo;
import jisungsoft.com.persistence.model.sec.AuthorInfoVO;
import jisungsoft.com.repository.sec.AuthorInfoMapper;
import jisungsoft.com.service.AuthorInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("authorInfoService")
public class AuthorInfoServiceImpl extends EgovAbstractServiceImpl implements AuthorInfoService {

    @Resource(name = "authorInfoMapper")
    private AuthorInfoMapper authorInfoMapper;

    @Override
    public List<AuthorInfo> getAuthorInfoAllList(AuthorInfo authorInfo) {
        return authorInfoMapper.selectAuthorAllList(authorInfo);
    }

    @Override
    public void removeAuthorInfo(AuthorInfo authorInfo) throws Exception {
        AuthorInfoVO dataAuthorInfo = AuthorInfoVO.createDataAuthorInfo(authorInfo.getAuthorCode(),
                null,
                null,
                null);
        authorInfoMapper.deleteAuthor(dataAuthorInfo);
    }

    @Override
    public void addAuthorInfo(AuthorInfo authorInfo) throws Exception {
        AuthorInfoVO dataAuthorInfo = AuthorInfoVO.createDataAuthorInfo(authorInfo.getAuthorCode(),
                authorInfo.getAuthorCreatDe(),
                authorInfo.getAuthorDc(),
                authorInfo.getAuthorNm());

        authorInfoMapper.insertAuthor(dataAuthorInfo);
    }

    @Override
    public AuthorInfo getAuthorInfoDetail(AuthorInfo authorInfo) {
        AuthorInfo result = authorInfoMapper.selectAuthor(authorInfo);

        return result;
    }

    @Override
    public List<AuthorInfo> getAuthorInfoList(AuthorInfo authorInfo) {
        return authorInfoMapper.selectAuthorList(authorInfo);
    }

    @Override
    public void editAuthorInfo(AuthorInfo authorInfo) throws Exception {
        AuthorInfoVO dataAuthorInfo = AuthorInfoVO.createDataAuthorInfo(authorInfo.getAuthorCode(),
                authorInfo.getAuthorCreatDe(),
                authorInfo.getAuthorDc(),
                authorInfo.getAuthorNm());

        authorInfoMapper.updateAuthor(dataAuthorInfo);
    }

    @Override
    public int getAuthorListTotCnt(AuthorInfo authorInfo) {
        return authorInfoMapper.selectAuthorListTotCnt(authorInfo);
    }
}
