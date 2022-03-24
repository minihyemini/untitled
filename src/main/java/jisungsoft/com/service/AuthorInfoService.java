package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.sec.AuthorInfo;

import java.util.List;

public interface AuthorInfoService {

    /**
     * 모든 권한목록을 조회한다.
     */
    public List<AuthorInfo> getAuthorInfoAllList(AuthorInfo authorInfo);

    /**
     * 시스템 사용자중 불필요한 시스템권한정보를 화면에 조회하여 데이터베이스에서 삭제
     */
    public void removeAuthorInfo(AuthorInfo authorInfo) throws Exception;

    /**
     * 사용자의 시스테접근권한를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
     */
    public void addAuthorInfo(AuthorInfo authorInfo) throws Exception;

    /**
     * 개별사용자에게 할당된 권한 조회
     */
    public AuthorInfo getAuthorInfoDetail(AuthorInfo authorInfo);

    /**
     * 개별사용자에게 할당된 권한리스트 조회
     */
    public List<AuthorInfo> getAuthorInfoList(AuthorInfo authorInfo);

    /**
     * 화면에 조회된 사용자권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     */
    public void editAuthorInfo(AuthorInfo authorInfo) throws Exception;

    /**
     * 목록조회 카운트를 반환한다
     */
    public int getAuthorListTotCnt(AuthorInfo authorInfo);
}
