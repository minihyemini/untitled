package jisungsoft.com.uss.umt.service.impl;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.service.AuthorGroupService;
import jisungsoft.com.uss.umt.service.UserManageService;
import jisungsoft.com.uss.umt.model.UserManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserManageService")
public class UserManageServiceImpl extends EgovAbstractServiceImpl implements UserManageService {

    @Resource(name = "UserManageDAO")
    private UserManageDAO userManageDAO;

    @Resource(name = "authorGroupService")
    AuthorGroupService authorGroupService;

    /** usrCnfrmIdGnrService */
    @Resource(name="usrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;

    @Override
    public int checkIdDplct(String checkId) throws Exception {
        int result = userManageDAO.checkIdDplct(checkId);
        return result;
    }

    @Override
    public int checkEmailDplct(String checkId) throws Exception {
        int result = userManageDAO.checkEmailDplct(checkId);
        return result;
    }

    @Override
    public void deleteUser(String checkedIdForDel) throws Exception {
        userManageDAO.deleteUser(checkedIdForDel);
    }

    @Override
    public String insertUser(UserManageVO userManageVO) throws Exception {
        //고유아이디 셋팅
        String id = idgenService.getNextStringId();
        //패스워드 암호화
        String pw = EgovFileScrty.encryptPassword(userManageVO.getPassword(), EgovStringUtil.isNullToString(userManageVO.getEmplyrId()));

        userManageVO.setUniqId(id);
        userManageVO.setPassword(pw);

        String result = userManageDAO.insertUser(userManageVO);
/*

        AuthorGroupVO authorGroupVO = new AuthorGroupVO();
        authorGroupVO.setAuthorCode(userManageVO.getAuthorCode());
        authorGroupVO.setEsntlId(id);
        authorGroupVO.setMberTyCode("GNR");

        authorGroupService.insertAuthorGroupUser(authorGroupVO);
*/

        return result;
    }

    @Override
    public UserManageVO selectUser(String emplyrId) throws Exception {
        UserManageVO userManageVO = userManageDAO.selectUser(emplyrId);
        return userManageVO;
    }

    @Override
    public List<?> selectUserList(UserManageVO userManageVO) throws Exception {
        return userManageDAO.selectUserList(userManageVO);
    }

    @Override
    public int selectUserListTotCnt(UserManageVO userManageVO) throws Exception {
        return userManageDAO.selectUserListTotCnt(userManageVO);
    }

    @Override
    public void updateUser(UserManageVO userManageVO) throws Exception {
        userManageDAO.updateUser(userManageVO);
/*
        AuthorGroupVO authorGroupVO = new AuthorGroupVO();
        authorGroupVO.setAuthorCode(userManageVO.getAuthorCode());
        authorGroupVO.setMberTyCode("GNR");
        authorGroupVO.setEsntlId(userManageVO.getUniqId());
        authorGroupService.updateAuthorGroupUser(authorGroupVO);
        */
    }

    @Override
    public String insertUserHistory(UserManageVO userManageVO) throws Exception {
        return null;
    }

    @Override
    public void updatePassword(UserManageVO userManageVO) throws Exception {
        String pw = EgovFileScrty.encryptPassword(userManageVO.getPassword(), EgovStringUtil.isNullToString(userManageVO.getEmplyrId()));
        userManageVO.setPassword(pw);
        userManageDAO.updatePassword(userManageVO);
    }

    @Override
    public UserManageVO selectPassword(UserManageVO passVO) throws Exception {
        userManageDAO.selectPassword(passVO);
        return null;
    }

    @Override
    public void updateLockIncorrect(UserManageVO userManageVO) throws Exception {
        userManageDAO.updateLockIncorrect(userManageVO);
    }

    @Override
    public List<?> selectUserAllList(UserManageVO userManageVO) throws Exception {
        return userManageDAO.selectUserAllList(userManageVO);
    }

    @Override
    public int selectUserAllListTotCnt(UserManageVO userManageVO) throws Exception {
        return userManageDAO.selectUserAllListTotCnt(userManageVO);
    }
}
