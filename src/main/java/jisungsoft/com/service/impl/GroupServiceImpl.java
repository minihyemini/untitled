package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.persistence.dto.sec.Group;
import jisungsoft.com.persistence.model.sec.GroupVO;
import jisungsoft.com.repository.sec.GroupMapper;
import jisungsoft.com.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("groupService")
public class GroupServiceImpl extends EgovAbstractServiceImpl implements GroupService {

    @Resource(name = "groupMapper")
    private GroupMapper groupMapper;

    /** Message ID Generation */
    @Resource(name="egovGroupIdGnrService")
    private EgovIdGnrService egovGroupIdGnrService;


    @Override
    public Group getGroupDetail(Group group) {
        return groupMapper.selectGroupDetail(group);
    }

    @Override
    public List<Group> getGroupList(Group group) {
        return groupMapper.selectGroupList(group);
    }

    @Override
    public List<Group> getGroupUserList(Group group) {
        return groupMapper.selectGroupUserList(group);
    }

    @Override
    public int getGroupUserListTotCnt(Group group) {
        return groupMapper.selectGroupListTotCnt(group);
    }

    @Override
    public List<Group> getGroupAllList(Group group) {
        return groupMapper.selectGroupAllList(group);
    }

    @Override
    public List<Group> getGroupAllListMap(Group group) {
        return groupMapper.selectGroupAllListMap(group);
    }

    @Override
    public List<Group> getGroupDetailList(Group group) {
        return groupMapper.selectGroupDetailList(group);
    }

    @Override
    public Group addGroup(Group group) throws Exception {
        String nextId = egovGroupIdGnrService.getNextStringId();
        group.setGroupId(nextId);
        GroupVO dataGroup = GroupVO.createDataGroup(group.getGroupId(),
                group.getGroupNm(),
                group.getGroupCode(),
                group.getGroupDc(),
                group.getOrgnztId());

        groupMapper.insertGroup(dataGroup);
        return group;
    }

    @Override
    public void editGroup(Group group) throws Exception {
        GroupVO dataGroup = GroupVO.createDataGroup(group.getGroupId(),
                group.getGroupNm(),
                group.getGroupCode(),
                group.getGroupDc(),
                group.getOrgnztId());

        groupMapper.updateGroup(dataGroup);
    }

    @Override
    public void removeGroup(Group group) throws Exception {
        GroupVO dataGroup = GroupVO.createDataGroup(group.getGroupId(),
                group.getGroupNm(),
                group.getGroupCode(),
                group.getGroupDc(),
                group.getOrgnztId());

        groupMapper.deleteGroup(dataGroup);
    }

    @Override
    public int getGroupListTotCnt(Group group) {
        return groupMapper.selectGroupListTotCnt(group);
    }
}
