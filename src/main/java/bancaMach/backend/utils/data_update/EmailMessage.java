package bancaMach.backend.utils.data_update;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;

public class EmailMessage {
    public static void main(String[] args) {
        String sendGridApiKey = "TU_API_KEY_DE_SENDGRID";

        Email fromEmail = new Email("tucorreo@gmail.com");
        Email toEmail = new Email("destinatario@gmail.com");
        Content content = new Content("text/plain", "Hola, esto es un correo de prueba.");

        Mail mail = new Mail();
        mail.setFrom(fromEmail);
        mail.setSubject("Correo de prueba");
        mail.addContent(content);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);
        mail.addPersonalization(personalization);

        SendGrid sendGrid = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sendGrid.api(request);
            System.out.println("Correo enviado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo.");
        }
    }
}
