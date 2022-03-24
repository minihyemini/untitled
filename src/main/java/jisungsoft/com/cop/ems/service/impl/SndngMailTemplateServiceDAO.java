package jisungsoft.com.cop.ems.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.ems.service.SndngMailTemplateVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sndngMailTemplateServiceDAO")
public class SndngMailTemplateServiceDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "SndngMailTemplateServiceDAO";

    /**
     * 발송메일 템플릿 목록 조회(paging)
     * @param sndngMailTemplateVO
     * @return
     * @throws Exception
     */
    public List<?> selectSndngMailTemplateList(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return selectList(NAME_SPACE + ".selectSndngMailTemplateList", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 목록 조회(not paging)
     * @param sndngMailTemplateVO
     * @return
     * @throws Exception
     */
    public List<SndngMailTemplateVO> selectSndngMailTemplateListAll(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return selectList(NAME_SPACE + ".selectSndngMailTemplateListAll", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 카운트
     * @param sndngMailTemplateVO
     * @return
     * @throws Exception
     */
    public int selectSndngMailTemplateListCnt(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectSndngMailTemplateListCnt", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 상세조회
     * @param sndngMailTemplateVO
     * @return
     * @throws Exception
     */
    public SndngMailTemplateVO selectSndngMailTemplateDetail(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectSndngMailTemplateDetail", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 등록
     * @param sndngMailTemplateVO
     * @throws Exception
     */
    public void insertSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {

        insert(NAME_SPACE + ".insertSndngMailTemplate", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 수정
     * @param sndngMailTemplateVO
     * @throws Exception
     */
    public void updateSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        update(NAME_SPACE + ".updateSndngMailTemplate", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 삭제
     * @param sndngMailTemplateVO
     * @throws Exception
     */
    public void deleteSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception {
        delete(NAME_SPACE + ".deleteSndngMailTemplate", sndngMailTemplateVO);
    }

    /**
     * 발송메일 템플릿 사용여부 수정
     * @throws Exception
     */
    public void updateSndngMailTemplateUseAll() throws Exception {
        update(NAME_SPACE + ".updateSndngMailTemplateUseAll");
    }
}
