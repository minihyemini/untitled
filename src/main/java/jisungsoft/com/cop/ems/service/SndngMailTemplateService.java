package jisungsoft.com.cop.ems.service;

import java.util.List;

public interface SndngMailTemplateService {

    public List<?> selectSndngMailTemplateList(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public List<SndngMailTemplateVO> selectSndngMailTemplateListAll(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public int selectSndngMailTemplateListCnt(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public SndngMailTemplateVO selectSndngMailTemplateDetail(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public void insertSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public void updateSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public void deleteSndngMailTemplate(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;

    public void updateSndngMailTemplateUse(SndngMailTemplateVO sndngMailTemplateVO) throws Exception;


}
