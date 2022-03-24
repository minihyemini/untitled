package jisungsoft.com.uss.umt.service.impl;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jisungsoft.com.uss.umt.model.MberManageVO;
import jisungsoft.com.uss.umt.model.UserDefaultVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mberManageDAO")
public class MberManageDAO extends EgovComAbstractDAO {

    private final String NAME_SPACE = "mberManageDAO";
    /**
     * 기 등록된 특정 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<MberManageVO> 기업회원 목록정보
     */
    public List<MberManageVO> selectMberList(UserDefaultVO userSearchVO){
        return selectList(NAME_SPACE + ".selectMberList", userSearchVO);
    }

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 일반회원총갯수
     */
    public int selectMberListTotCnt(UserDefaultVO userSearchVO) {
        return (Integer)selectOne(NAME_SPACE + ".selectMberListTotCnt", userSearchVO);
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param mberId 상세조회대상 일반회원아이디
     * @return MberManageVO 일반회원 상세정보
     */
    public MberManageVO selectMber(String mberId) {
        return (MberManageVO) selectOne(NAME_SPACE + ".selectMber", mberId);
    }

    /**
     * 일반회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return String 등록결과
     */
    public String insertMber(MberManageVO mberManageVO) {
        return String.valueOf((int)insert(NAME_SPACE + ".insertMber", mberManageVO));
    }

    /**
     * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param mberManageVO 일반회원수정정보
     */
    public void updateMber(MberManageVO mberManageVO) {
        update(NAME_SPACE + ".updateMber",mberManageVO);
    }

    /**
     * 화면에 조회된 일반회원의 정보를 데이터베이스에서 삭제
     * @param delId 삭제 대상 일반회원아이디
     */
    public void deleteMber(String delId) {
        delete(NAME_SPACE + ".deleteMber", delId);
    }

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return List 일반회원약관정보
     */
    public List<?> selectStplat(String stplatId) {
        return selectList(NAME_SPACE + ".selectStplat", stplatId);
    }

    /**
     * 일반회원 암호수정
     * @param passVO 일반회원수정정보(비밀번호)
     */
    public void updatePassword(MberManageVO passVO) {
        update(NAME_SPACE + ".updatePassword", passVO);
    }

    /**
     * 일반회원 이메일수정
     * @param mberManageVO
     */
    public void updateEmail(MberManageVO mberManageVO) {
        update(NAME_SPACE + ".updateEmail", mberManageVO);
    }

    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param mberManageVO 일반회원암호 조회조건정보
     * @return MberManageVO 일반회원 암호정보
     */
    public MberManageVO selectPassword(MberManageVO mberManageVO){
        return (MberManageVO) selectOne(NAME_SPACE + ".selectPassword", mberManageVO);
    }

    /**
     * 로그인인증제한 해제
     * @param mberManageVO 일반회원정보
     */
    public void updateLockIncorrect(MberManageVO mberManageVO) {
        update(NAME_SPACE + ".updateLockIncorrect", mberManageVO);
    }

    /**
     * ID, PASSWORD로 회원정보 조회(검증용)
     * @param mberManageVO
     * @return
     * @throws Exception
     */
    public MberManageVO selectMberByAuth(MberManageVO mberManageVO) throws Exception {
        return selectOne(NAME_SPACE + ".selectMberByAuth", mberManageVO);
    }

    /**
     * 사용자 일반회원 탈퇴
     * @param mberManageVO
     * @throws Exception
     */
    public void deleteMberAccount(MberManageVO mberManageVO) throws Exception {
        update(NAME_SPACE + ".deleteMberAccount", mberManageVO);
    }
}
