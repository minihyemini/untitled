package jisungsoft.com.cop.ems.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cop.ems.service.*;
import org.apache.commons.mail.EmailAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sndngMailService")
public class SndngMailServiceImpl extends EgovAbstractServiceImpl implements SndngMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SndngMailServiceImpl.class);

    @Resource(name = "egovMultiPartEmail")
    private EgovMultiPartEmail egovMultiPartEmail;

    @Resource(name = "sndngMailRegistDAO")
    private SndngMailRegistDAO sndngMailRegistDAO;

    /** Message ID Generation */
    @Resource(name = "egovMailMsgIdGnrService")
    private EgovIdGnrService egovMailMsgIdGnrService;
    /**
     * 메일설정 서비스
     */
    @Resource(name = "userEmailConfgService")
    private UserEmailConfgService userEmailConfgService;
    /** 암호화서비스 */
    @Resource(name = "egovEnvCryptoService")
    EgovEnvCryptoService cryptoService;

    @Override
    public boolean sndngMail(SndngMailVO vo) throws Exception {

        vo.setMssageId(egovMailMsgIdGnrService.getNextStringId());
        vo.setDsptchPerson(egovMultiPartEmail.getEmailAddress());

        String recptnPerson = (vo.getRecptnPerson() == null) ? "" : vo.getRecptnPerson();   // 수신자
        String subject = (vo.getSj() == null) ? "" : vo.getSj();    // 메일제목
        String emailCn = (vo.getEmailCn() == null) ? "" : vo.getEmailCn();  // 메일내용
        String atchmnFileNm = (vo.getOrignlFileNm() == null) ? "" : vo.getOrignlFileNm();   // 첨부파일이름
        String atchmnFilePath = (vo.getFileStreCours() == null) ? "" : vo.getFileStreCours();   // 첨부파일경로

        //메일설정 데이터 생성
        EgovMultiPartEmail egovMultiPartEmailVO = new EgovMultiPartEmail();
        egovMultiPartEmailVO.setId(egovMultiPartEmail.getId());
        egovMultiPartEmailVO.setEmailAddress(egovMultiPartEmail.getEmailAddress());
        egovMultiPartEmailVO.setPassword(egovMultiPartEmail.getPassword());
        egovMultiPartEmailVO.setSenderName(egovMultiPartEmail.getSenderName());
        egovMultiPartEmailVO.setPort(egovMultiPartEmail.getPort());
        egovMultiPartEmailVO.setHost(egovMultiPartEmail.getHost());

        //메일설정 데이터 있을 경우
        if (vo.getUserEmailConfgVO() != null) {
            UserEmailConfgVO userEmailConfgVO = vo.getUserEmailConfgVO();

            egovMultiPartEmailVO.setId(userEmailConfgVO.getEmId());
            egovMultiPartEmailVO.setPassword(cryptoService.decrypt(userEmailConfgVO.getEmPassword()));
            egovMultiPartEmailVO.setHost(userEmailConfgVO.getEmHost());
            egovMultiPartEmailVO.setPort(Integer.parseInt(userEmailConfgVO.getEmPort()));
            egovMultiPartEmailVO.setEmailAddress(userEmailConfgVO.getEmEmailAddress());
        }

        try {
            EmailAttachment attachment = new EmailAttachment();

            // 첨부파일이 있을 때
            if (atchmnFileNm != "" && atchmnFileNm != null && atchmnFilePath != "" && atchmnFilePath != null) {
                // 첨부할 attachment 정보를 생성
                attachment.setPath(atchmnFilePath);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("첨부파일입니다");
                // attachment.setName(new String(atchmnFileNm.getBytes("UTF-8"),"latin1")); // 구버전의 경우 필요
                attachment.setName(atchmnFileNm);

                // 첨부파일 정보를 포함한 메일을 전송
                egovMultiPartEmailVO.send(recptnPerson, subject, emailCn, false, attachment);
            } else {
                // 메일을 전송
                boolean isHtml = vo.isHtml();
                egovMultiPartEmailVO.send(recptnPerson, subject, emailCn, isHtml);
            }

        } catch (MailParseException ex) {
            vo.setSndngResultCode("F"); // 발송결과 실패
            sndngMailRegistDAO.insertSndngMail(vo);

            LOGGER.error("Sending Mail Exception : {} [failure when parsing the message]", ex.getCause());
            return false;
        } catch (MailAuthenticationException ex) {
            vo.setSndngResultCode("F"); // 발송결과 실패
            sndngMailRegistDAO.insertSndngMail(vo);

            LOGGER.error("Sending Mail Exception : {} [authentication failure]", ex.getCause());
            return false;
        } catch (MailSendException ex) {
            vo.setSndngResultCode("F"); // 발송결과 실패
            sndngMailRegistDAO.insertSndngMail(vo);

            LOGGER.error("Sending Mail Exception : {} [failure when sending the message]", ex.getCause());
            return false;
        } catch (Exception ex) {
            vo.setSndngResultCode("F"); // 발송결과 실패
            sndngMailRegistDAO.insertSndngMail(vo);

            LOGGER.error("Sending Mail Exception : {} [unknown Exception]", ex.getCause());
            LOGGER.debug(ex.getMessage());
            return false;
        }

        vo.setSndngResultCode("S");
        sndngMailRegistDAO.insertSndngMail(vo);

        return true;
    }
}
