package jisungsoft.com.sym.prm.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.sym.prm.service.ProgramManage;
import jisungsoft.com.sym.prm.service.ProgramManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository("programManageDAO")
public class ProgramManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "programManageDAO";

    public List<?> selectProgramList(ProgramManageVO programManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectProgramList", programManageVO);
    }

    public int selectProgramListTotCnt(ProgramManageVO programManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectProgramListTotCnt", programManageVO);
    }

    public ProgramManageVO selectProgram(ProgramManageVO programManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectProgram", programManageVO);
    }

    public void insertProgram(ProgramManage programManage) throws Exception {
        insert(NAME_SPACE + ".insertProgram", programManage);
    }

    public void updateProgram(ProgramManage programManage) throws Exception {
        update(NAME_SPACE + ".updateProgram", programManage);
    }

    public void updateMenuProgram(ProgramManage programManage) throws Exception {
        update(NAME_SPACE + ".updateMenuProgram", programManage);
    }

    public void deleteProgram(ProgramManage programManage) throws Exception {
        delete(NAME_SPACE + ".deleteProgram", programManage);
    }

    public int selectProgramNmByPk(ProgramManageVO programManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectProgramNmByPk", programManageVO);
    }

    public List<?> selectProgramAllList(ProgramManageVO programManageVO) throws Exception {
        return selectList(NAME_SPACE + ".selectProgramAllList", programManageVO);
    }
}
