package jisungsoft.com.cop.ems.service;

public interface SndngMailService {

    /**
     * 메일을 발송한다
     * @param vo SndngMailVO
     * @return boolean
     * @exception Exception
     */
    boolean sndngMail(SndngMailVO vo) throws Exception;
}
