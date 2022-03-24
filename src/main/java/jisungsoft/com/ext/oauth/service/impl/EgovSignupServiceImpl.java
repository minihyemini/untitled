/*
 * eGovFrame OAuth
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author 이기하(슈퍼개발자K3)
 */
package jisungsoft.com.ext.oauth.service.impl;

import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.ext.oauth.service.EgovSignupService;

/**
 * 소셜 계정으로 일반회원 가입을 처리하는 컨트롤러 클래스
 */
@Service("signupService")
public class EgovSignupServiceImpl extends EgovAbstractServiceImpl implements EgovSignupService {

//	@Resource(name="mberManageDAO")
//	private MberManageDAO mberManageDAO;

	/**
	 * 소셜 계정으로 일반회원 가입을 처리한다
	 * @param UserProfile profile
	 * @param WebRequest request
	 * @param String key
	 * @return String
	 * @exception Exception
	 */
	@Override
	public String signup(UserProfile profile, WebRequest request, String key) throws Exception {

		return null;
	}

}
