package ideablog.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class IdeaBlogMail {

    private static String myEmailAccount = "ideablog@163.com";
    private static String myEmailPassword = "ideablog2018";//授权码
    private static String myEmailSMTPHost = "smtp.163.com";

    /**
     * 发送简单邮件
     * @param receiveMailAccount
     * @param mailSubject
     * @param mailContent
     * @throws Exception
     */
    public static void sendMail(String receiveMailAccount, String mailSubject, String mailContent) throws Exception {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmailAccount, "Idea Blog官方", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "Idea Blog用户", "UTF-8"));
        message.setSubject(mailSubject, "UTF-8");
        message.setContent(mailContent, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
