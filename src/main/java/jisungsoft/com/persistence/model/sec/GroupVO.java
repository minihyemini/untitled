package jisungsoft.com.persistence.model.sec;

import lombok.Getter;
import lombok.Setter;

/**
 * 그룹
 */
@Getter @Setter
public class GroupVO {
    /**
     * 그룹ID
     */
    private String groupId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 그룹코드
     */
    private String groupCode;
    /**
     * 가입일자
     */
    private String groupCreatDe;
    /**
     * 그룹설명
     */
    private String groupDc;
    /**
     * 조직ID
     */
    private String orgnztId;

    protected GroupVO() {
    }

    public static GroupVO createDataGroup(String groupId, String groupNm, String groupCode, String groupDc, String orgnztId) {
        GroupVO groupVO = new GroupVO();

        groupVO.groupId = groupId;
        groupVO.groupNm = groupNm;
        groupVO.groupCode = groupCode;
        groupVO.groupDc = groupDc;
        groupVO.orgnztId = orgnztId;

        return groupVO;
    }
}
