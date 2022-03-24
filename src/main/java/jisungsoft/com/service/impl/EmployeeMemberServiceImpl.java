package jisungsoft.com.service.impl;

import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cmm.code.MemberTypeCode;
import jisungsoft.com.persistence.dto.sec.AuthorGroup;
import jisungsoft.com.persistence.dto.member.EmployeeMember;
import jisungsoft.com.persistence.model.member.EmployeeMemberVO;
import jisungsoft.com.repository.member.EmployeeMemberMapper;
import jisungsoft.com.service.AuthorGroupService;
import jisungsoft.com.service.DeptService;
import jisungsoft.com.service.EmployeeMemberService;
import jisungsoft.com.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Transactional(readOnly = true)
@Service("employeeMemberService")
public class EmployeeMemberServiceImpl extends EgovAbstractServiceImpl implements EmployeeMemberService {

    /** egovUsrCnfrmIdGnrService */
    @Resource(name="usrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;
    /**
     * 업무회원 Mapper
     */
    @Resource(name = "employeeMemberMapper")
    private EmployeeMemberMapper employeeMemberMapper;
    /**
     * 권한그룹 Mapper
     */
    @Resource(name = "authorGroupService")
    private AuthorGroupService authorGroupService;

    @Override
    public EmployeeMember getEmployeeMemberDetail(EmployeeMember employeeMember) {
        return employeeMemberMapper.selectEmployeeMember(employeeMember);
    }

    @Override
    public List<EmployeeMember> getEmployeeMemberList(EmployeeMember employeeMember) {
        return employeeMemberMapper.selectEmployeeMemberList(employeeMember);
    }

    @Override
    public List<EmployeeMember> getEmployeeMemberAllList(EmployeeMember employeeMember) {
        return employeeMemberMapper.selectEmployeeMemberAllList(employeeMember);
    }

    @Override
    public int checkIdDplct(EmployeeMember employeeMember) {
        return employeeMemberMapper.selectIdFotDplct(employeeMember.getEmplyrId());
    }

    @Override
    public int checkEmailDplct(EmployeeMember employeeMember) {
        return employeeMemberMapper.selectEmailFotDplct(employeeMember.getEmailAdres());
    }

    @Override
    public int getEmployeeMemberTotCnt(EmployeeMember employeeMember) {
        return employeeMemberMapper.selectEmployeeMemberTotCnt(employeeMember);
    }

    @Override
    public void addEmployeeMember(EmployeeMember employeeMember) throws Exception {
        //고유아이디 셋팅
        String uniqId = idgenService.getNextStringId();
        employeeMember.setUniqId(uniqId);
        //사원번호생성
        String employeeNum = createEmployeeNumber(employeeMember);
        //패스워드 암호화(생년월일로 자동생성)
        String pass = EgovFileScrty.encryptPassword(employeeNum, employeeMember.getUniqId());
        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataEmployeeMember(employeeMember.getUniqId(),
                employeeNum,
                employeeMember.getOrgnztId(),
                employeeMember.getEmplyrNm(),
                employeeMember.getEmplyrNum(),
                pass,
                employeeMember.getSexdstnCode(),
                employeeMember.getForeignerAt(),
                employeeMember.getAttendanceAt(),
                employeeMember.getBrthdy(),
                employeeMember.getAdres(),
                employeeMember.getAreaNo(),
                employeeMember.getDetailAdres(),
                employeeMember.getZip(),
                employeeMember.getOffmTelno(),
                employeeMember.getMbtlnum(),
                employeeMember.getEmailAdres(),
                employeeMember.getOfcpsNm(),
                employeeMember.getGroupId(),
                employeeMember.getPstinstCode(),
                employeeMember.getEmplyrSttusCode(),
                employeeMember.getJoiningDate(),
                employeeMember.getQuittingDate());

        employeeMemberMapper.insertEmployeeMember(employeeMemberVO);

        AuthorGroup authorGroup = new AuthorGroup();
        authorGroup.setEsntlId(uniqId);
        authorGroup.setAuthorCode(employeeMember.getAuthorCode());
        authorGroup.setMberTyCode(MemberTypeCode.USR.name());
        authorGroupService.addAuthorGroupUser(authorGroup);
    }

    @Override
    public void editEmployeeMember(EmployeeMember employeeMember) throws Exception {
        String id = editEmployeeNumber(employeeMember);
        employeeMember.setEmplyrId(id);
        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataEmployeeMember(employeeMember.getUniqId(),
                employeeMember.getEmplyrId(),
                employeeMember.getOrgnztId(),
                employeeMember.getEmplyrNm(),
                employeeMember.getEmplyrNum(),
                null,
                employeeMember.getSexdstnCode(),
                employeeMember.getForeignerAt(),
                employeeMember.getAttendanceAt(),
                employeeMember.getBrthdy(),
                employeeMember.getAdres(),
                employeeMember.getAreaNo(),
                employeeMember.getDetailAdres(),
                employeeMember.getZip(),
                employeeMember.getOffmTelno(),
                employeeMember.getMbtlnum(),
                employeeMember.getEmailAdres(),
                employeeMember.getOfcpsNm(),
                employeeMember.getGroupId(),
                employeeMember.getPstinstCode(),
                employeeMember.getEmplyrSttusCode(),
                employeeMember.getJoiningDate(),
                employeeMember.getQuittingDate());

        employeeMemberMapper.updateEmployeeMember(employeeMemberVO);

        AuthorGroup authorGroup = new AuthorGroup();
        authorGroup.setEsntlId(employeeMember.getUniqId());
        authorGroup.setAuthorCode(employeeMember.getAuthorCode());
        authorGroup.setMberTyCode(employeeMember.getUserTy());
        authorGroupService.editAuthorGroupUser(authorGroup);
    }

    @Override
    public void removeEmployeeMember(EmployeeMember employeeMember) throws Exception {
        AuthorGroup authorGroup = new AuthorGroup();
        authorGroup.setEsntlId(employeeMember.getUniqId());
        authorGroupService.removeAuthorGroupUser(authorGroup);

        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataIdEmployeeMember(employeeMember.getUniqId(), employeeMember.getEmplyrId());
        employeeMemberMapper.deleteEmployeeMember(employeeMemberVO);
    }

    @Override
    public void editEmployeeMemberPassword(EmployeeMember employeeMember) throws Exception {
        //패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(employeeMember.getPassword(), employeeMember.getUniqId());

        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataPasswordEmployeeMember(employeeMember.getUniqId(), pass);
        employeeMemberMapper.updatePassword(employeeMemberVO);
    }

    @Override
    public EmployeeMember getEmployeeMemberPassword(EmployeeMember employeeMember) throws Exception {
        return null;
    }

    @Override
    public void editLockIncorrect(EmployeeMember employeeMember) throws Exception {
        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataIdEmployeeMember(employeeMember.getUniqId(), employeeMember.getEmplyrId());
        employeeMemberMapper.updateLockIncorrect(employeeMemberVO);
    }

    @Override
    public void editEmployeeMemberEmail(EmployeeMember employeeMember) throws Exception {
        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataEmailEmployeeMember(employeeMember.getUniqId(), employeeMember.getEmailAdres());
        employeeMemberMapper.updateEmployeeMemberEmail(employeeMemberVO);
    }

    @Override
    public EmployeeMember getEmployeeMemberByAuth(EmployeeMember employeeMember) throws Exception {
        return null;
    }

    @Override
    public void secessionEmployeeMemberAccount(EmployeeMember employeeMember) throws Exception {
        EmployeeMemberVO employeeMemberVO = EmployeeMemberVO.createDataIdEmployeeMember(employeeMember.getUniqId(), employeeMember.getEmplyrId());
        employeeMemberMapper.secessionEmployeeMemberAccount(employeeMemberVO);
    }

    /**
     * 사원번호 생성
     */
    public String createEmployeeNumber(EmployeeMember employeeMember) throws FdlException {
        LocalDate now = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy");
        String year = now.format(format);
        String indexId = employeeMember.getUniqId().substring(employeeMember.getUniqId().length() - 3, employeeMember.getUniqId().length());
        String group = employeeMember.getGroupId().replaceAll("GROUP_", "");

        String AA = year.substring(2);
        String BB = String.format("%02d", Integer.parseInt(group));
        String CCC = String.format("%03d", Integer.parseInt(indexId));
        return AA+BB+CCC;
    }

    /**
     * 사원번호 수정
     */
    public String editEmployeeNumber(EmployeeMember employeeMember) {
        String dateStr = employeeMember.getSbscrbDe();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, yyyyMMdd);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy");
        String year = date.format(format);

        String group = employeeMember.getGroupId().replaceAll("GROUP_", "");

        String AA = year.substring(2);
        String BB = String.format("%02d", Integer.parseInt(group));
        String CCC = String.format("%03d", Integer.parseInt(employeeMember.getEmplyrId().substring(4)));
        return AA+BB+CCC;
    }
}
