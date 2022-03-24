package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.member.EmployeeMember;

import java.util.List;

/**
 * 업무회원 서비스
 */
public interface EmployeeMemberService {

    public EmployeeMember getEmployeeMemberDetail(EmployeeMember employeeMember);

    public List<EmployeeMember> getEmployeeMemberList(EmployeeMember employeeMember);

    public List<EmployeeMember> getEmployeeMemberAllList(EmployeeMember employeeMember);

    public int checkIdDplct(EmployeeMember employeeMember);

    public int checkEmailDplct(EmployeeMember employeeMember);

    public int getEmployeeMemberTotCnt(EmployeeMember employeeMember);

    public void addEmployeeMember(EmployeeMember employeeMember) throws Exception;

    public void editEmployeeMember(EmployeeMember employeeMember) throws Exception;

    public void removeEmployeeMember(EmployeeMember employeeMember) throws Exception;

    public void editEmployeeMemberPassword(EmployeeMember employeeMember) throws Exception;

    public EmployeeMember getEmployeeMemberPassword(EmployeeMember employeeMember) throws Exception;

    public void editLockIncorrect(EmployeeMember employeeMember) throws Exception;

    public void editEmployeeMemberEmail(EmployeeMember employeeMember) throws Exception;

    public EmployeeMember getEmployeeMemberByAuth(EmployeeMember employeeMember) throws Exception;

    public void secessionEmployeeMemberAccount(EmployeeMember employeeMember) throws Exception;
}
