package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.member.GeneralMember;

import java.util.List;

/**
 * 일반회원 서비스
 */
public interface GeneralMemberService {

    public GeneralMember getGeneralMemberDetail(GeneralMember uniqId);

    public List<GeneralMember> getGeneralMemberList(GeneralMember generalMember);

    public List<GeneralMember> getGeneralMemberAllList(GeneralMember generalMember);

    public int getGeneralMemberTotCnt(GeneralMember generalMember);

    public void addGeneralMember(GeneralMember generalMember) throws Exception;

    public void editGeneralMember(GeneralMember generalMember) throws Exception;

    public void removeGeneralMember(GeneralMember generalMember) throws Exception;

    public void updateGeneralMemberPassword(GeneralMember generalMember) throws Exception;

    public GeneralMember getGeneralMemberPassword(GeneralMember generalMember) throws Exception;

    public void editLockIncorrect(GeneralMember generalMember) throws Exception;

    public void editGeneralMemberEmail(GeneralMember generalMember) throws Exception;

    public GeneralMember getGeneralMemberByAuth(GeneralMember generalMember) throws Exception;

    public void secessionGeneralMemberAccount(GeneralMember generalMember) throws Exception;
}
