package jisungsoft.com.uss.sam.stp.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uss.sam.stp.service.StplatVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stplatManageDAO")
public class StplatManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "StplatManageDAO";

    /**
     * 약관 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(약관 목록)
     */
    public List<?> selectStplatList(StplatVO paramVO)  throws Exception {
        return selectList(NAME_SPACE + ".selectStplatList", paramVO);
    }
    /**
     * 약관 건수를 조회한다.
     * @param paramVO
     * @return int
     */
    public int selectStplatListCnt(StplatVO paramVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectStplatListCnt", paramVO);
    }
    /**
     * 약관 상세항목을 조회한다.
     * @param paramVO
     * @return StplatVO(약관)
     */
    public StplatVO selectStplatDetail(StplatVO paramVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectStplatDetail", paramVO);
    }

    /**
     * 약관 상세항목에 대해서 'Y' 단일 조회한다.
     * @param paramVO
     * @return
     * @throws Exception
     */
    public StplatVO selectStplatDetailUseAtY(StplatVO paramVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectStplatDetailUseAtY", paramVO);
    }

    /**
     * 약관을 등록한다.
     * @param stplatVO
     * @throws Exception
     */
    public void insertStplat(StplatVO stplatVO) throws Exception {
        insert(NAME_SPACE + ".insertStplat", stplatVO);
    }

    /**
     * 약관을 삭제한다.
     * @param stplatVO
     * @throws Exception
     */
    public void deleteStplat(StplatVO stplatVO) throws Exception {
        delete(NAME_SPACE + ".deleteStplat", stplatVO);
    }

    /**
     * 약관을 수정한다.
     * @param stplatVO
     * @throws Exception
     */
    public void updateStplat(StplatVO stplatVO) throws Exception{
        update(NAME_SPACE + ".updateStplat", stplatVO);
    }

    /**
     * 약관 사용여부를 전체 'N' 업데이트 한다
     * @throws Exception
     */
    public void updateStplatUseAll() throws Exception {
        update(NAME_SPACE + ".updateStplatUseAll");
    }
}
