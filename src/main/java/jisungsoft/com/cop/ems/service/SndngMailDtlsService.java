package jisungsoft.com.cop.ems.service;

import java.util.List;

public interface SndngMailDtlsService {

    /**
     * 발송메일 목록을 조회한다.
     * @param vo ComDefaultVO
     * @return List
     * @exception Exception
     */
    List<?> selectSndngMailList(SndngMailVO vo) throws Exception;

    /**
     * 발송메일 총건수를 조회한다.
     * @param vo ComDefaultVO
     * @return int
     * @exception
     */
    int selectSndngMailListTotCnt(SndngMailVO vo) throws Exception;

    /**
     * 발송메일을 상세 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    SndngMailVO selectSndngMailDetail(SndngMailVO vo) throws Exception;

    /**
     * 발송메일을 삭제한다.
     * @param vo SndngMailVO
     * @exception
     */
    void deleteSndngMail(SndngMailVO vo) throws Exception;
}
