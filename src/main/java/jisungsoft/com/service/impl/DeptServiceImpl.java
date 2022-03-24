package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.dto.member.Dept;
import jisungsoft.com.persistence.model.member.DeptVO;
import jisungsoft.com.repository.member.DeptMapper;
import jisungsoft.com.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("deptService")
public class DeptServiceImpl extends EgovAbstractServiceImpl implements DeptService {

    /** Message ID Generation */
    @Resource(name = "deptManageIdGnrService")
    private EgovIdGnrService deptIdGnrService;

    @Resource(name = "deptMapper")
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getDeptList(Dept dept) {
        return deptMapper.selectDeptList(dept);
    }

    @Override
    public List<Dept> getDeptAllList(Dept dept) {
        return deptMapper.selectDeptAllList(dept);
    }

    @Override
    public List<?> getDeptAllListMap(Dept dept) {
        return deptMapper.selectDeptAllListMap(dept);
    }

    @Override
    public int getDeptListTotCnt(Dept dept) {
        return deptMapper.selectDeptListTotCnt(dept);
    }

    @Override
    public Dept getDeptDetail(Dept dept) {
        return deptMapper.selectDeptDetail(dept);
    }

    @Override
    public String addDept(Dept dept) throws Exception {
        String id = deptIdGnrService.getNextStringId();
        DeptVO dataDept = DeptVO.createDataDept(
                id,
                dept.getOrgnztNm(),
                dept.getOrgnztCode(),
                dept.getOrgnztDc());
        deptMapper.insertDept(dataDept);

        return id;
    }

    @Override
    public String editDept(Dept dept) throws Exception {
        DeptVO dataDept = DeptVO.createDataDept(
                dept.getOrgnztId(),
                dept.getOrgnztNm(),
                dept.getOrgnztCode(),
                dept.getOrgnztDc());
        deptMapper.updateDept(dataDept);

        return dataDept.getOrgnztId();
    }

    @Override
    public void removeDept(Dept dept) throws Exception {
        DeptVO dataIdDept = DeptVO.createDataIdDept(dept.getOrgnztId());
        deptMapper.deleteDept(dataIdDept);
    }
}
