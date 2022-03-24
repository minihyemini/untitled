package jisungsoft.com.service.impl;

import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cmm.util.CommonUtil;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.repository.LoginMapper;
import jisungsoft.com.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
 */
@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {

	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;

    /** EgovSndngMailRegistService */
//	@Resource(name = "sndngMailRegistService")
//    private EgovSndngMailRegistService sndngMailRegistService;
	
	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/**
	 * EsntlId를 이용한 로그인을 처리한다
	 */
    @Override
	public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception {

    	LoginVO loginVO = loginMapper.actionLoginByEsntlId(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }


    /**
	 * 일반 로그인을 처리한다
	 */
    @Override
	public LoginVO actionLogin(LoginVO vo) throws Exception {

		Map<?, ?> map = loginMapper.selectLoginIncorrect(vo);
		String userSE = vo.getUserSe();
		String uniqId = map.get("uniqId").toString();
		// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = "";

		enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), uniqId);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
		vo.setPassword(enpassword);
		vo.setUserSe(userSE);
    	LoginVO loginVO = loginMapper.actionLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
    		return loginVO;
    	} else {
    		loginVO = new LoginVO();
    	}

    	return loginVO;
    }

    /**
	 * 인증서 로그인을 처리한다
	 */
	@Override
	public LoginVO actionCrtfctLogin(LoginVO vo) throws Exception {
		return null;
    }

    /**
	 * 아이디를 찾는다.
	 */
    @Override
	public LoginVO searchId(LoginVO vo) throws Exception {
    	// 이름, 이메일주소가 DB와 일치하는 사용자 ID를 조회한다.
    	LoginVO loginVO = loginMapper.searchId(vo);
    	if (loginVO != null) {
			if (loginVO.getMbtlnum() != null && !loginVO.getMbtlnum().equals("")) {
				String mbtlnum = CommonUtil.decryptAES256(loginVO.getMbtlnum(), Globals.ENCRYP_KEY);
				loginVO.setMbtlnum(mbtlnum);
			}
		}

    	return loginVO;
    }

    /**
	 * 비밀번호를 찾는다.
	 */
    @Override
	public LoginVO searchPassword(LoginVO vo) throws Exception {

    	// 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 DB와 일치하는 사용자 Password를 조회한다.
//    	LoginVO loginVO = loginDAO.searchPassword(vo);

    	/*
    	// 2. 임시 비밀번호를 생성한다.(영+영+숫+영+영+숫+영+영=8자리)
    	String newpassword = "";
    	for (int i = 1; i <= 8; i++) {
    		// 영자
    		if (i % 3 != 0) {
    			newpassword += EgovStringUtil.getRandomStr('a', 'z');
    		// 숫자
    		} else {
    			newpassword += EgovNumberUtil.getRandomNum(0, 9);
    		}
    	}

    	*/
		// 3. 비밀번호를 암호화하여 DB에 저장한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getUniqId());

		LoginVO pwd = new LoginVO();
		pwd.setId(vo.getId());
		pwd.setPassword(enpassword);
		pwd.setUserSe(vo.getUserSe());
		loginMapper.updatePassword(pwd);

    	// 4. 임시 비밀번호를 이메일 발송한다.(메일연동솔루션 활용)
    	/*
    	SndngMailVO sndngMailVO = new SndngMailVO();
    	sndngMailVO.setDsptchPerson("webmaster");
    	sndngMailVO.setRecptnPerson(vo.getEmail());
    	sndngMailVO.setSj("[MOIS] 임시 비밀번호를 발송했습니다.");
    	sndngMailVO.setEmailCn("고객님의 임시 비밀번호는 " + newpassword + " 입니다.");
    	sndngMailVO.setAtchFileId("");

    	result = sndngMailRegistService.insertSndngMail(sndngMailVO);
    	*/

    	return pwd;
    }
    
    
    /**
	 * 로그인인증제한을 조회한다.
	 */
    public Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception{
    	return loginMapper.selectLoginIncorrect(vo);
    }
    
    /**
	 * 로그인인증제한을 처리한다.
	 */
    public String processLoginIncorrect(LoginVO vo, Map<?,?> mapLockUserInfo) throws Exception {
		Map<?, ?> map = loginMapper.selectLoginIncorrect(vo);
		String uniqId = map.get("uniqId").toString();
    	String sRtnCode = "C";
    	//KISA 보안약점 조치 (2018-10-29, 윤창원)
		String userSe = vo.getUserSe();
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), EgovStringUtil.isNullToString(uniqId));

    	Map<String,String> mapParam = new HashMap<String,String>();
    	mapParam.put("USER_SE", userSe);
		mapParam.put("id", EgovStringUtil.isNullToString(vo.getId()));//KISA 보안약점 조치 (2018-10-29, 윤창원)
    	//잠김시 
		if("Y".equals(((String)mapLockUserInfo.get("lockAt")))){
			sRtnCode = "L";
		//패드워드 인증시 
		}else if( ((String)mapLockUserInfo.get("userPw")).equals(enpassword) ){
    		//LOCK 해제
    		mapParam.put("updateAt", "E");
			loginMapper.updateLoginIncorrect(mapParam);
    		sRtnCode = "E";
        //패드워드 비인증시 
    	}else if(!"Y".equals(((String)mapLockUserInfo.get("lockAt")))){
    		//LOCK 설정
    		if( Integer.parseInt(String.valueOf(mapLockUserInfo.get("lockCnt")))+1 >= egovLoginConfig.getLockCount() ){    			
	    		mapParam.put("updateAt", "L");
				loginMapper.updateLoginIncorrect(mapParam);
	    		sRtnCode = "L";
	    	//LOCK 증가
    		}else{
	    		mapParam.put("updateAt", "C");
				loginMapper.updateLoginIncorrect(mapParam);
	    		sRtnCode = "C";
    		}
    	}
    	return sRtnCode;
    }
}
