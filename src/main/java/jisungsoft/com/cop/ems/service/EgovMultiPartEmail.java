package jisungsoft.com.cop.ems.service;

import lombok.Data;
import org.apache.commons.mail.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class EgovMultiPartEmail {

    private static final long serialVersionUID = -4322006921324597283L;
    /**
     * 아이디
     */
    private String id;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * EMAIL PORT 587
     */
    private int port;
    /**
     * 이메일SMTP주소
     */
    private String host;
    /**
     * 이메일주소
     */
    private String emailAddress;
    /**
     * System
     */
    private String senderName;

    @Deprecated
    public String send() throws EmailException {
        MultiPartEmail email = new MultiPartEmail();

        email.setCharset("UTF-8");
        email.setHostName(this.host);
        email.setSmtpPort(this.port);
        email.setStartTLSEnabled(true);
        email.setAuthenticator(new DefaultAuthenticator(this.id, this.password));
        email.setSocketConnectionTimeout(60000);
        email.setSocketTimeout(60000);
        email.setFrom(this.emailAddress, this.senderName);

        return email.send();
    }

    public String send(String addTo, String subject, String msg, boolean isHtml) throws EmailException {
        return send(addTo, subject, msg, isHtml,null);
    }

    public String send(String addTo, String subject, String msg, boolean isHtml, EmailAttachment attachment) throws EmailException {
        HtmlEmail email = new HtmlEmail();

        if (isHtml) {
            email.setHtmlMsg(msg);
            email.setTextMsg("Your email client does not support HTML messages");
        }

        email.setCharset("UTF-8");
        email.setHostName(this.host);
        email.setSmtpPort(this.port);
        email.setStartTLSEnabled(true);
        email.setAuthenticator(new DefaultAuthenticator(this.id, this.password));
        email.setStartTLSEnabled(true);
        email.setSocketConnectionTimeout(60000);
        email.setSocketTimeout(60000);
        email.setFrom(this.emailAddress, this.senderName);
        email.setSubject(subject);
        email.setMsg(msg);

        if (attachment != null) {
            email.attach(attachment);
        }

        return send(email, addTo);
    }

    public String send(HtmlEmail email, String addTo) throws EmailException {
        //홍길동<test@test.com> 이름 이메일 포함 체크
        Pattern namePtn = Pattern.compile("^.*(<).*(>)$");

        if (addTo.indexOf(";") > -1) {
            String[] split = addTo.split(";");

            for (int i=0; i<split.length; i++) {
                Matcher nameMtc = namePtn.matcher(split[i].trim());

                if (nameMtc.matches()) {
                    Map<String, String> data = new HashMap<>();
                    String name = split[i].substring(0, split[i].indexOf("<"));
                    String emailAddr = split[i].substring(split[i].indexOf("<")+1, split[i].indexOf(">"));
                    email.addTo(emailAddr, name, "UTF-8");
                } else {
                    email.addTo(split[i]);
                }
                //최종 발송
                send(email);
            }

            return "send";
        }

        email.addTo(addTo);

        return send(email);
    }

    public String send(HtmlEmail email) {
        Queue senderQ = new LinkedList<>();
        senderQ.add(email);

        SndngMailThreadService sndngMailThreadService = new SndngMailThreadService();
        sndngMailThreadService.setSenderQ(senderQ);

        Thread t = new Thread(sndngMailThreadService);
        t.start();

        return "true";
    }
}
