package framework.service.observer;

import framework.model.Account;
import framework.model.Customer;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmailSender implements Observer<Account> {

    @Override
    public void update(Account account){
        Customer customer = account.getCustomer();
        String message= "Dear "+customer.getName()+", \n For account number: "+ account.getAccountNumber()+"\n Your new balance is: "+ account.getBalance()+"\n do not reply \n thank you";


        try {
            sendEmail(customer,message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void sendEmail(Customer customer,String messages) throws Exception{
        String receipt=customer.getEmail();
        System.out.println("preparing to send");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String myAccountEmail = "bankinggroup3asd@gmail.com";
        final String password = "Test1234!";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session,messages, myAccountEmail, receipt);
        Transport.send(message);
        System.out.println("Message Sent successfully");
    }

    private static Message prepareMessage(Session session,String messages, String myAccountEmail, String reciept){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reciept));
            message.setSubject("Change Account");
            message.setText(messages);
            return message;
        } catch (Exception e) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    }


