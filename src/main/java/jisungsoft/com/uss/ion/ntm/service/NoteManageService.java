package jisungsoft.com.uss.ion.ntm.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface NoteManageService {
    /**
     * 쪽지관리 정보 조회
     * @param noteVO
     * @throws Exception
     */
    public NoteVO selectNoteManage(NoteVO noteVO) throws Exception;

    /**
     * 쪽지 관리 등록
     * @param noteVO
     * @param commandMap
     * @throws Exception
     */
    void  insertNote(NoteVO noteVO, @RequestParam Map<String, Object> commandMap) throws Exception;
}
