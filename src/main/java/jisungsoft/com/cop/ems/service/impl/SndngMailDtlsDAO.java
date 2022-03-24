package jisungsoft.com.cop.ems.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.ems.service.SndngMailVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sndngMailDtlsDAO")
public class SndngMailDtlsDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "SndngMailDtlsDAO";

    /**
     * 발송메일 목록을 조회한다.
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    public List<?> selectSndngMailList(SndngMailVO vo) throws Exception {
        return selectList(NAME_SPACE + ".selectSndngMailList", vo);
    }

    /**
     * 발송메일 총건수를 조회한다.
     * @param vo ComDefaultVO
     * @return int
     * @exception
     */
    public int selectSndngMailListTotCnt(SndngMailVO vo) {
        return (Integer) selectOne(NAME_SPACE + ".selectSndngMailListTotCnt", vo);
    }

    /**
     * 발송메일을 상세 조회한다.
     * @param vo SndngMailVO
     * @return SndngMailVO
     * @exception Exception
     */
    public SndngMailVO selectSndngMail(SndngMailVO vo) throws Exception {
        return (SndngMailVO) selectOne(NAME_SPACE + ".selectSndngMail", vo);
    }

    /**
     * 발송메일을 삭제한다.
     * @param vo SndngMailVO
     * @exception
     */
    public void deleteSndngMail(SndngMailVO vo) throws Exception {
        delete(NAME_SPACE + ".deleteSndngMail", vo);
    }

    /**
     * 첨부파일 목록을 삭제한다.
     * @param vo SndngMailVO
     * @exception
     */
    public void deleteAtchmnFileList(SndngMailVO vo) throws Exception {
        delete(NAME_SPACE + ".deleteAtchmnFileList", vo);
    }

    /**
     * 발송메일에 첨부된 파일목록을 조회한다.
     * @param vo SndngMailVO
     * @return List
     * @exception
     */
    public List<?> selectAtchmnFileList(SndngMailVO vo) {
        return selectList(NAME_SPACE + ".selectAtchmnFileList", vo);
    }
}
