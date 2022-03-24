package jisungsoft.com.uat.uap.service;

import lombok.Data;

import java.util.List;

@Data
public class LoginPolicyVO extends LoginPolicy {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 로그인 정책 목록
     */
    List<LoginPolicyVO> loginPolicyList;
    /**
     * 삭제 여부
     */
    String [] delYn;
}
