package jisungsoft.com.sec.ram.service;

import lombok.Data;

import java.util.List;

@Data
public class AuthorRoleManageVO extends AuthorRoleManage {

    private static final long serialVersionUID = 1L;

    List<AuthorRoleManageVO> authorRoleList;
}
