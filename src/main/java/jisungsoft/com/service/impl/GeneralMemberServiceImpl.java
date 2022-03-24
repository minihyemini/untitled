package jisungsoft.com.service.impl;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cmm.code.MemberTypeCode;
import jisungsoft.com.persistence.dto.sec.AuthorGroup;
import jisungsoft.com.persistence.dto.member.GeneralMember;
import jisungsoft.com.persistence.model.member.GeneralMemberVO;
import jisungsoft.com.repository.member.GeneralMemberMapper;
import jisungsoft.com.service.AuthorGroupService;
import jisungsoft.com.service.GeneralMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("generalMemberService")
public class GeneralMemberServiceImpl extends EgovAbstractServiceImpl implements GeneralMemberService {


    /** egovUsrCnfrmIdGnrService */
    @Resource(name="usrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 일반회원 Mapper
     */
    @Resource(name = "generalMemberMapper")
    private GeneralMemberMapper generalMemberMapper;

    /**
     * 권한그룹 Mapper
     */
    @Resource(name = "authorGroupService")
    private AuthorGroupService authorGroupService;

    @Override
    public GeneralMember getGeneralMemberDetail(GeneralMember GeneralMember) {
        return generalMemberMapper.selectGeneralMemberDetail(GeneralMember);
    }

    @Override
    public List<GeneralMember> getGeneralMemberList(GeneralMember generalMember) {
        return generalMemberMapper.selectGeneralMemberList(generalMember);
    }

    @Override
    public List<GeneralMember> getGeneralMemberAllList(GeneralMember generalMember) {
        return generalMemberMapper.selectGeneralMemberAllList(generalMember);
    }

    @Override
    public int getGeneralMemberTotCnt(GeneralMember generalMember) {
        return generalMemberMapper.selectGeneralMemberListTotCnt(generalMember);
    }

    @Override
    public void addGeneralMember(GeneralMember generalMember) throws Exception {
        //고유아이디 셋팅
        String uniqId = idgenService.getNextStringId();
        //패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(generalMember.getPassword(), EgovStringUtil.isNullToString(generalMember.getUniqId()));

        GeneralMemberVO generalMemberVO = GeneralMemberVO.createDataGeneralMember(
                uniqId, generalMember.getMberId(), pass, generalMember.getMberNm(),
                generalMember.getAreaNo(), generalMember.getZip(), generalMember.getAdres(),
                generalMember.getDetailAdres(), generalMember.getMberSttusCode(), generalMember.getMbtlnum(),
                generalMember.getGroupId(), generalMember.getMberEmailAdres(), generalMember.getSexdstnCode(), generalMember.getBrthdy());

        generalMemberMapper.insertGeneralMember(generalMemberVO);

        AuthorGroup authorGroup = new AuthorGroup();
        authorGroup.setEsntlId(uniqId);
        authorGroup.setAuthorCode(generalMember.getAuthorCode());
        authorGroup.setMberTyCode(MemberTypeCode.GNR.name());
        authorGroupService.addAuthorGroupUser(authorGroup);
    }

    @Override
    public void editGeneralMember(GeneralMember generalMember) throws Exception {
        GeneralMemberVO generalMemberVO = GeneralMemberVO.createDataGeneralMember(
                generalMember.getUniqId(), null, null, generalMember.getMberNm(),
                generalMember.getAreaNo(), generalMember.getZip(), generalMember.getAdres(),
                generalMember.getDetailAdres(), generalMember.getMberSttusCode(), generalMember.getMbtlnum(),
                generalMember.getGroupId(), generalMember.getMberEmailAdres(), generalMember.getSexdstnCode(), generalMember.getBrthdy());

        generalMemberMapper.updateGeneralMember(generalMemberVO);

        AuthorGroup authorGroup = new AuthorGroup();
        authorGroup.setEsntlId(generalMember.getUniqId());
        authorGroup.setAuthorCode(generalMember.getAuthorCode());
        authorGroup.setMberTyCode(MemberTypeCode.GNR.name());
        authorGroupService.editAuthorGroupUser(authorGroup);
    }

    @Override
    public void removeGeneralMember(GeneralMember generalMember) throws Exception {
        AuthorGroup authorGroup = new AuthorGroup();
        authorGroup.setEsntlId(generalMember.getUniqId());
        authorGroupService.removeAuthorGroupUser(authorGroup);

        GeneralMemberVO dataIdGeneralMember = GeneralMemberVO.createDataIdGeneralMember(generalMember.getUniqId());
        generalMemberMapper.deleteGeneralMember(dataIdGeneralMember);
    }

    @Override
    public void updateGeneralMemberPassword(GeneralMember generalMember) throws Exception {
        //패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(generalMember.getPassword(), EgovStringUtil.isNullToString(generalMember.getMberId()));

        GeneralMemberVO generalMemberVO = GeneralMemberVO.createDataPasswordGeneralMember(generalMember.getUniqId(), pass);
        generalMemberMapper.updatePasswordGeneralMember(generalMemberVO);
    }

    @Override
    public GeneralMember getGeneralMemberPassword(GeneralMember generalMember) throws Exception {
        return generalMemberMapper.selectGeneralMemberByUniqidAndPassword(generalMember);
    }

    @Override
    public void editLockIncorrect(GeneralMember generalMember) throws Exception {
        GeneralMemberVO generalMemberVO = GeneralMemberVO.createDataIdGeneralMember(generalMember.getUniqId());
        generalMemberMapper.updateLockIncorrect(generalMemberVO);
    }

    @Override
    public void editGeneralMemberEmail(GeneralMember generalMember) throws Exception {
        GeneralMemberVO generalMemberVO = GeneralMemberVO.createDataEmailGeneralMember(generalMember.getUniqId(), generalMember.getMberEmailAdres());
        generalMemberMapper.updateGeneralMemberEmail(generalMemberVO);
    }

    @Override
    public GeneralMember getGeneralMemberByAuth(GeneralMember generalMember) throws Exception {
        return generalMemberMapper.selectGeneralMemberByUniqidAndPassword(generalMember);
    }

    @Override
    public void secessionGeneralMemberAccount(GeneralMember generalMember) throws Exception {

        GeneralMemberVO generalMemberVO = GeneralMemberVO.createDataIdGeneralMember(generalMember.getUniqId());
        generalMemberMapper.updateGeneralMemberAccount(generalMemberVO);
    }
}
