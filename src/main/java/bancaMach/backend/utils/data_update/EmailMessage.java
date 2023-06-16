package bancaMach.backend.utils.data_update;

import bancaMach.backend.api_cashier_dto.users.RequestPasswordDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailMessage {
    public boolean sendPasswordFromMail(RequestPasswordDTO requestPasswordDTO, String password) {
        String senderEmail = "";
        String senderPassword = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(requestPasswordDTO.getEmail()));
            message.setSubject(requestPasswordDTO.getSubject());
            message.setText(requestPasswordDTO.getMessage()+password);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;

        }
    }
}
