package jisungsoft.com.cop.ems.service;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import jisungsoft.com.persistence.dto.Default;
import jisungsoft.com.cmm.util.CommonUtil;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class SndngMailTemplateVO extends Default {

    /**
     * 메일템플릿ID
     */
    private String etId;
    /**
     * 이메일 아이디
     */
    private String emailId;
    /**
     * 메일템플릿제목
     */
    private String etSj;
    /**
     * 메일템플릿내용
     */
    private String etCn;
    /**
     * 첨부파일ID
     */
    private String atchFileId;
    /**
     * 사용여부
     */
    private String useAt;
    /**
     * 메일템플릿 타입코드
     */
    private String etType;
    /**
     * 최종등록시점
     */
    private String frstRegistPnttm;
    /**
     * 최종등록자ID
     */
    private String frstRegisterId;
    /**
     * 최종수정시점
     */
    private String lastUpdtPnttm;
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId;
    /**
     * 인증비밀번호
     */
    private String authPassword;
    /**
     * 인증 회원명
     */
    private String authName;
    /**
     * 인증번호
     */
    private String authKey;
    /**
     * 대체 문자
     */
    final String RANDOM_KEY = "|random_key|";
    final String EMAIL_ID_KEY = "|email_id_key|";
    final String NAME_KEY = "|name_key|";
    final String PASSWORD_KEY = "|password_key|";

    private final String PATTERN = "[|](.*?)[|]";

    public void convertKey() {
        Pattern ptn = Pattern.compile(PATTERN);
        Matcher mtc = ptn.matcher(etCn);

        while (mtc.find()) {
            String match = mtc.group().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", "");

            switch (match) {
                case RANDOM_KEY :

                    String uuid = CommonUtil.getAuthCode(6);
                    authKey = uuid;
                    etCn = etCn.replace(match, uuid);

                    break;
                case EMAIL_ID_KEY :
                    if (!EgovStringUtil.isEmpty(emailId)) {
                        etCn = etCn.replace(match, emailId);
                    }

                    break;
                case NAME_KEY :
                    if (!EgovStringUtil.isEmpty(authName)) {
                        etCn = etCn.replace(match, authName);
                    }

                    break;
                case PASSWORD_KEY :
                    if (!EgovStringUtil.isEmpty(authPassword)) {
                        etCn = etCn.replace(match, authPassword);
                    }

                    break;
            }
        }
    }
}


