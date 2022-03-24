package jisungsoft.com.cop.ems.service;

import lombok.Data;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * 메일 발송 쓰레드
 * (메일 발송시 클라이언트로 응답 속도 향상을 위한 서비스)
 */
@Data
public class SndngMailThreadService implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(SndngMailThreadService.class);

    private SndngMailVO sndngMailVO;

    private Queue<HtmlEmail> senderQ;

    @Override
    public void run() {
        LOGGER.info("mail thread start.");

        try {
            HtmlEmail email = senderQ.poll();
            if (email != null) {
                email.send();
            }

            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("mail thread stop.");
    }
}
