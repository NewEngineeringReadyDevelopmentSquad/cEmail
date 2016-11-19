/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkEmail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * This class is for sending Emails (with attachments)from a UTRGV/Office360 account
 * to any other Email address.
 * @author Heromachine
 */

public class cEmail {
    
    private String smtpHost = "smtp.office365.com";
    private String port = "587";    
    
    /**
     * cEmail's Primary Overloaded Constructor
     * @param to Recipient's Email address
     * @param cc Carbon copy Email address
     * @param from User's UTRGV Email address
     * @param subject Subject line
     * @param textMessage Email message body
     * @param username User's UTRGV username/Email address
     * @param password User's URTGV account password
     * @param attachment Attachment file path and filename
     */
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
        } catch (MessagingException ex) {
            Logger.getLogger(cEmail.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /** 
     * Function to send an Email
     * @param to Recipient's Email address
     * @param cc Carbon copy Email address
     * @param from User's UTRGV Email address
     * @param subject Subject line
     * @param textMessage Email message body
     * @param username User's UTRGV username/Email address
     * @param password User's URTGV account password
     * @param attachment Attachment file path and filename
     */
    private void sendEmail(
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