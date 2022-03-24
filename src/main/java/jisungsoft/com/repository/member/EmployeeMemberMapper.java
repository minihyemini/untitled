package jisungsoft.com.repository.member;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.member.EmployeeMember;
import jisungsoft.com.persistence.model.member.EmployeeMemberVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("employeeMemberMapper")
public interface EmployeeMemberMapper {

    public List<EmployeeMember> selectEmployeeMemberList(EmployeeMember employeeMember);

    public List<EmployeeMember> selectEmployeeMemberAllList(EmployeeMember employeeMember);

    public int selectEmployeeNumberForYearMaxCnt(EmployeeMember employeeMember);

    public int selectEmployeeMemberTotCnt(EmployeeMember employeeMember);

    public EmployeeMember selectEmployeeMember(EmployeeMember employeeMember);

    public int selectIdFotDplct(String checkId);

    public int selectEmailFotDplct(String checkId);

    public void insertEmployeeMember(EmployeeMemberVO employeeMemberVO) throws DataAccessException;

    public void updateEmployeeMember(EmployeeMemberVO employeeMemberVO) throws DataAccessException;

    public void deleteEmployeeMember(EmployeeMemberVO employeeMemberVO) throws DataAccessException;

    public void updatePassword(EmployeeMemberVO employeeMemberVO) throws DataAccessException;

    public void updateLockIncorrect(EmployeeMemberVO employeeMemberVO) throws DataAccessException;

    public void updateEmployeeMemberEmail(EmployeeMemberVO employeeMemberVO) throws DataAccessException;

    public void secessionEmployeeMemberAccount(EmployeeMemberVO employeeMemberVO) throws DataAccessException;
}
