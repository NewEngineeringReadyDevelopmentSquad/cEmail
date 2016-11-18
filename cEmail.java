/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkEmail;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pkg3340emaildemo.Main;


/**
 *
 * @author Heromachine
 */
public class cEmail {
    
    private String smtpHost = "smtp.office365.com";
    private String port = "587";    
    
    public cEmail(
            String to, 
            String cc, 
            String from, 
            String subject, 
            String textMessage,             
            String username, 
            String password, 
            String attachment
    )
    {
      
        try{
        sendEmail(to, cc, from, subject, textMessage, smtpHost, username, password, attachment);
        }catch(MessagingException ex){ Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);}
    }
    private  void sendEmail(
            String to, 
            String cc, 
            String from, 
            String subject, 
            String textMessage, 
            String smtpHost, 
            String username, 
            String password,
            String attachment
    ) throws MessagingException 
    {
        try {
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", smtpHost);
                props.put("mail.smtp.port", port);
                
                Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                      
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

                Message emailMessage = new MimeMessage(session);
                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                emailMessage.setFrom(new InternetAddress(from));
                emailMessage.setSubject(subject);
                emailMessage.setText(textMessage);
                
                BodyPart messageBodyPart = new MimeBodyPart();

                messageBodyPart.setText(textMessage);

                Multipart multipart = new MimeMultipart();

                multipart.addBodyPart(messageBodyPart);

                messageBodyPart = new MimeBodyPart();

                DataSource source = new FileDataSource(attachment);
                
                messageBodyPart.setDataHandler(new DataHandler(source));
                
                messageBodyPart.setFileName(attachment);
                multipart.addBodyPart(messageBodyPart);
                 
                emailMessage.setContent(multipart);            
                 
                session.setDebug(true);

                Transport.send(emailMessage);
                
               
                
        } catch (AddressException e) {
                e.printStackTrace();
                
                
        } catch (MessagingException e) {
            
                
                e.printStackTrace();
        }
    }
    
    
    
}
