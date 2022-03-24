package jisungsoft.com.cop.ems.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.cop.ems.service.SndngMailVO;
import org.springframework.stereotype.Repository;

@Repository("sndngMailRegistDAO")
public class SndngMailRegistDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "sndngMailRegistDAO";

    /**
     * 발송할 메일을 등록한다
     * @param vo SndngMailVO
     * @return SndngMailVO
     * @exception Exception
     */
    public SndngMailVO insertSndngMail(SndngMailVO vo) throws Exception {
        insert(NAME_SPACE + ".insertSndngMail", vo);
        return new SndngMailVO();
    }

    /**
     * 발송결과를 수정한다.
     * @param vo SndngMailVO
     * @return SndngMailVO
     * @exception Exception
     */
    public SndngMailVO updateSndngMail(SndngMailVO vo) throws Exception {
        update(NAME_SPACE + ".updateSndngMail", vo);
        return new SndngMailVO();
    }
}
