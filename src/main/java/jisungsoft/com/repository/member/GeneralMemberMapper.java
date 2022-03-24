package jisungsoft.com.repository.member;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import jisungsoft.com.persistence.dto.member.GeneralMember;
import jisungsoft.com.persistence.model.member.GeneralMemberVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper("generalMemberMapper")
public interface GeneralMemberMapper {

    public List<GeneralMember> selectGeneralMemberList(GeneralMember generalMember);

    public List<GeneralMember> selectGeneralMemberAllList(GeneralMember generalMember);

    public int selectGeneralMemberListTotCnt(GeneralMember generalMember);

    public GeneralMember selectGeneralMemberDetail(GeneralMember generalMember);

    public GeneralMember selectGeneralMemberByUniqidAndPassword(GeneralMember generalMember);

    public void insertGeneralMember(GeneralMemberVO generalMemberVO) throws DataAccessException;

    public void updateGeneralMember(GeneralMemberVO generalMemberVO) throws DataAccessException;

    public void deleteGeneralMember(GeneralMemberVO generalMemberVO) throws DataAccessException;

    public void updateGeneralMemberAccount(GeneralMemberVO generalMemberVO) throws DataAccessException;

    public void updateGeneralMemberEmail(GeneralMemberVO generalMemberVO) throws DataAccessException;

    public void updateLockIncorrect(GeneralMemberVO generalMemberVO) throws DataAccessException;

    public void updatePasswordGeneralMember(GeneralMemberVO generalMemberVO) throws DataAccessException;
}
