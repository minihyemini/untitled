package jisungsoft.com.uss.sam.stp.service;

import java.util.List;

public interface StplatManageService {
    /**
     * 약관 목록을 조회한다. (Paging)
     * @param paramVO
     * @return List(약관 목록)
     * @throws Exception
     */
    List<?> selectStplatList(StplatVO paramVO) throws Exception;
    /**
     * 약관 건수를 조회한다.
     * @param paramVO
     * @return int
     * @throws Exception
     */
    int selectStplatListCnt(StplatVO paramVO) throws Exception;
    /**
     * 약관을 상세조회한다.
     * @param paramVO
     * @return StplatVO
     * @throws Exception
     */
    StplatVO selectStplatDetail(StplatVO paramVO) throws Exception;
    /**
     * 약관 상세항목에 대해서 'Y' 단일 조회한다.
     * @param paramVO
     * @return
     * @throws Exception
     */
    StplatVO selectStplatDetailUseAtY(StplatVO paramVO) throws Exception;
    /**
     * 약관을 등록한다.
     * @param stplatVO
     * @throws Exception
     */
    void insertStplat(StplatVO stplatVO) throws Exception;
    /**
     * 약관을 삭제한다.
     * @param stplatVO
     * @throws Exception
     */
    void deleteStplat(StplatVO stplatVO) throws Exception;
    /**
     * 약관을 수정한다.
     * @param stplatVO
     * @throws Exception
     */
    void updateStplat(StplatVO stplatVO) throws Exception;
}
