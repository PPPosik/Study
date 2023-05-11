package book.toby1.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSender implements MailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        printMailMessage(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        for (SimpleMailMessage simpleMessage : simpleMessages) {
            printMailMessage(simpleMessage);
        }
    }

    private void printMailMessage(SimpleMailMessage mail) {
        System.out.println("send mail");
        System.out.println("from : " + mail.getFrom());
        System.out.println("to : " + mail.getReplyTo());
        System.out.println("subject : " + mail.getSubject());
        System.out.println("text : " + mail.getText());
    }
}
