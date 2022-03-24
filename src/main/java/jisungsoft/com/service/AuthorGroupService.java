package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sec.AuthorGroup;

import java.util.List;

public interface AuthorGroupService {

    List<AuthorGroup> getAuthorList();

    List<AuthorGroup> getGroupList(AuthorGroup param);

    List<AuthorGroup> getAuthorGroupUserList(AuthorGroup param);

    AuthorGroup getAuthorGroup(AuthorGroup param);

    int getAuthorGroupUserListTotCnt(AuthorGroup param);

    void addAuthorGroupUser(AuthorGroup param) throws Exception;

    void editAuthorGroupUser(AuthorGroup param) throws Exception;

    void removeAuthorGroupUser(AuthorGroup param) throws Exception;
}
