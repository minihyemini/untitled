package jisungsoft.com.uss.umt.service.impl;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.service.GroupService;
import jisungsoft.com.uss.umt.service.MberManageService;
import jisungsoft.com.uss.umt.model.MberManageVO;
import jisungsoft.com.uss.umt.model.UserDefaultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mberManageService")
public class MberManageServiceImpl extends EgovAbstractServiceImpl implements MberManageService {

    /** egovUsrCnfrmIdGnrService */
    @Resource(name="usrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;
    /**
     * 일반회원 DAO
     */
    @Resource(name = "mberManageDAO")
    private MberManageDAO mberManageDAO;
    /**
     * 그룹권한 서비스
     */
//    @Resource(name = "authorGroupService")
//    AuthorGroupService authorGroupService;
    /**
     * 그룹관리 서비스
     */
//    @Resource(name = "groupManageService")
    GroupService groupService;
    /**
     * 암호화서비스
     */
    @Resource(name = "egovEnvCryptoService")
    EgovEnvCryptoService cryptoService;


    @Override
    public String insertMber(MberManageVO mberManageVO) throws Exception {
        //고유아이디 셋팅
        String uniqId = idgenService.getNextStringId();
        //이메일 아이디 셋팅
//        String id = mberManageVO.getMberEmailAdres();
//        mberManageVO.setMberId(id);
        //패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), EgovStringUtil.isNullToString(uniqId));
        mberManageVO.setUniqId(uniqId);
        mberManageVO.setPassword(pass);

        String result = mberManageDAO.insertMber(mberManageVO);
/*

        if (!EgovStringUtil.isEmpty(mberManageVO.getAuthorCode())) {
            AuthorGroupVO authorGroupVO = new AuthorGroupVO();
            authorGroupVO.setAuthorCode(mberManageVO.getAuthorCode());
            authorGroupVO.setEsntlId(uniqId);
            authorGroupVO.setMberTyCode("GNR");

            authorGroupService.insertAuthorGroupUser(authorGroupVO);
        }
*/

        return result;
    }

    @Override
    public MberManageVO selectMber(String mberId) throws Exception {
        return mberManageDAO.selectMber(mberId);
    }

    @Override
    public List<MberManageVO> selectMberList(UserDefaultVO userSearchVO) throws Exception {
        return mberManageDAO.selectMberList(userSearchVO);
    }

    @Override
    public int selectMberListTotCnt(UserDefaultVO userSearchVO) throws Exception {
        return mberManageDAO.selectMberListTotCnt(userSearchVO);
    }

    @Override
    public void updateMber(MberManageVO mberManageVO) throws Exception {
        mberManageDAO.updateMber(mberManageVO);
/*
        AuthorGroupVO authorGroupVO = new AuthorGroupVO();
        authorGroupVO.setAuthorCode(mberManageVO.getAuthorCode());
        authorGroupVO.setEsntlId(mberManageVO.getUniqId());
        authorGroupVO.setMberTyCode("GNR");

        authorGroupService.insertAuthorGroupUser(authorGroupVO);
        */
    }

    @Override
    public void deleteMber(String checkedIdForDel) throws Exception {
        /*
        AuthorGroupVO authorGroupVO = new AuthorGroupVO();
        authorGroupVO.setEsntlId(checkedIdForDel);
        authorGroupService.deleteAuthorGroupUser(authorGroupVO);

        mberManageDAO.deleteMber(checkedIdForDel);
        */
    }

    @Override
    public List<?> selectStplat(String stplatId) throws Exception {
        return mberManageDAO.selectStplat(stplatId);
    }

    @Override
    public void updatePassword(MberManageVO mberManageVO) throws Exception {
        //패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), EgovStringUtil.isNullToString(mberManageVO.getUniqId()));
        mberManageVO.setPassword(pass);

        mberManageDAO.updatePassword(mberManageVO);
    }

    @Override
    public MberManageVO selectPassword(MberManageVO passVO) throws Exception {
        return mberManageDAO.selectPassword(passVO);
    }

    @Override
    public void updateLockIncorrect(MberManageVO mberManageVO) throws Exception {
        mberManageDAO.updateLockIncorrect(mberManageVO);
    }

    @Override
    public void updateEmail(MberManageVO mberManageVO) throws Exception {
        mberManageDAO.updateEmail(mberManageVO);
    }

    @Override
    public MberManageVO selectMberByAuth(MberManageVO mberManageVO) throws Exception {
        return mberManageDAO.selectMberByAuth(mberManageVO);
    }

    @Override
    public void deleteMberAccount(MberManageVO mberManageVO) throws Exception {
        mberManageDAO.deleteMberAccount(mberManageVO);
    }
}
