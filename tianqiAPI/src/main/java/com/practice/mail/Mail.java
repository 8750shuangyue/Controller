package com.youkeda.practice.mail;

import com.youkeda.practice.config.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    private static final Logger logger = LoggerFactory.getLogger(Mail.class);
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static Properties props = null;

    public Mail() {
        props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.auth", "true");
    }

    public void sendMail(String subject, String content) {
        String fromMail = ConfigLoader.getMailFrom();
        String fromMailKey = ConfigLoader.getMailKey();
        String toMail = ConfigLoader.getMailTo();

        if (isBlank(fromMail) || isBlank(fromMailKey) || isBlank(toMail)) {
            logger.warn("邮件配置不完整，跳过发送");
            return;
        }

        logger.info("开始发送邮件，发件人：{}，收件人：{}，主题：{}", fromMail, toMail, subject);

        try {
            props.put("mail.debug", "true");
            
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromMail, fromMailKey);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMail));
            message.setRecipients(Message.RecipientType.TO, toMail);
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=UTF-8");
            message.saveChanges();

            Transport.send(message);
            logger.info("邮件发送成功，请检查收件箱或垃圾箱");
        } catch (javax.mail.AuthenticationFailedException e) {
            logger.error("邮件发送失败：身份认证失败，请检查邮箱账号和授权码是否正确", e);
        } catch (javax.mail.MessagingException e) {
            logger.error("邮件发送失败：邮件服务异常", e);
        } catch (Exception e) {
            logger.error("邮件发送失败：未知异常", e);
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}