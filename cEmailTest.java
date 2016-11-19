/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkEmail;

import java.util.Properties;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adolfo Adrian Sierra
 */

public class cEmailTest {
    
    private String smtpHost = "smtp.office365.com";
    private String port = "587";
    // hardcode your UTRGV/Office365 Email address.
    private String testEmailAdd = "URTGVEMAIL@utrgv.edu";
    // hardcode your password for testing!
    private String password = "";
    
    public cEmailTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void sendEmail() throws MessagingException 
    {
        
        try {
                Properties props = new Properties();
                props.put("mail.smtp.auth", "false");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", smtpHost);
                props.put("mail.smtp.port", port);
                
                Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(testEmailAdd, password);
			}
		  });

                Message emailMessage = new MimeMessage(session);
                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(testEmailAdd));
                emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(testEmailAdd));
                emailMessage.setFrom(new InternetAddress(testEmailAdd));
                emailMessage.setSubject("Test Subject");
                emailMessage.setText("Test Text");
                
                BodyPart messageBodyPart = new MimeBodyPart();

                messageBodyPart.setText("Test Text 2");

                Multipart multipart = new MimeMultipart();

                multipart.addBodyPart(messageBodyPart);

                messageBodyPart = new MimeBodyPart();

                DataSource source = new FileDataSource("Alternating series.pdf");
                
                messageBodyPart.setDataHandler(new DataHandler(source));
                
                messageBodyPart.setFileName("GenericPDF.pdf");
                multipart.addBodyPart(messageBodyPart);
                 
                emailMessage.setContent(multipart);            
                 
                session.setDebug(true);

                Transport.send(emailMessage);
                
               
                
        } catch (AddressException e) {
                e.printStackTrace();
                fail("Invalid Email Address!");
        } catch (MessagingException e) {
                e.printStackTrace();
                fail("Invalid Message!");
        }
    }
    
}
