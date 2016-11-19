/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3340emaildemo;





import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
import pkEmail.cEmail;

/**
 *
 * @author Jessie Reyna
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("\n\n\n\n\n==========Current dir:"+current + "\n\n\n\n\n");
        }catch (Exception e)
        {}
        
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("EMAIL");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setText("Jesse.Reyna01@utrgv.edu");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        
        Label lbMailTo = new Label("Mail To:");
        grid.add(lbMailTo, 0, 3);
        TextField tfMailTo = new TextField();
        tfMailTo.setText("Jessie_reyna@live.com");
        grid.add(tfMailTo, 1, 3);
        
        Label lbSubject = new Label("Subject: ");
        grid.add(lbSubject, 0, 4);
        TextField tfSubject = new TextField();
        tfSubject.setText("TRANSCRIPT FOR YOU!!!!");
        grid.add(tfSubject, 1, 4);
        
        
        Label lbTexArea = new Label("Message: ");
        grid.add(lbTexArea, 0, 5);
        TextArea tfTexArea = new TextArea();
        tfTexArea.setText("DEAR YOU, ");
        grid.add(tfTexArea, 1, 5);
        
        
        Label lbOutpUt= new Label("");
        lbOutpUt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(lbOutpUt, 1, 8);
        
        
        
        
        
        Button btn = new Button();
        btn.setText("Send Email");
        
        grid.add(btn, 1, 7);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {            
    
            
                
                
                
                String mailSmtpHost = "smtp.office365.com";
                String Port = "587";
                cEmail  EM = new cEmail(
                        tfMailTo.getText(), 
                        "Jessie_Reyna@live.com; the_57flip@yahoo.com", 
                        "Jesse.Reyna01@utrgv.edu", 
                        "MyMail",
                        "HELLO",
                        "Jesse.Reyna01@utrgv.edu",
                        pwBox.getText(),                         
                        "MYPDF.pdf");
		
		
		
		
		

               
            }
        });
        
        
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("EMAIL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
//    public static void sendEmail(
//            String to, 
//            String cc, 
//            String from, 
//            String subject, 
//            String text, 
//            String smtpHost, 
//            String username, 
//            String password, 
//            String port, 
//            Label output
//    ) throws MessagingException 
//    {
//        try {
//                Properties props = new Properties();
//                props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", smtpHost);
//		props.put("mail.smtp.port", "25");
//                
//                Session session = Session.getInstance(props,
//		  new javax.mail.Authenticator() {
//                      
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		  });
//
//                Message emailMessage = new MimeMessage(session);
//                emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
//                emailMessage.setFrom(new InternetAddress(from));
//                emailMessage.setSubject(subject);
//                emailMessage.setText(text);
//                
//                BodyPart messageBodyPart = new MimeBodyPart();
//
//                messageBodyPart.setText(text);
//
//                Multipart multipart = new MimeMultipart();
//
//                multipart.addBodyPart(messageBodyPart);
//
//                messageBodyPart = new MimeBodyPart();
//
//                DataSource source = new FileDataSource("MYPDF.pdf");
//                
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                
//                messageBodyPart.setFileName("MYPDF.pdf");
//                multipart.addBodyPart(messageBodyPart);
//                 
//                emailMessage.setContent(multipart); 
//                 
//                 
//                 
//                 
//                session.setDebug(true);
//
//                Transport.send(emailMessage);
//                
//                output.setText("EMAIL SENT");
//                
//        } catch (AddressException e) {
//                e.printStackTrace();
//                
//                output.setText("EMAIL FAILED");
//        } catch (MessagingException e) {
//            
//                output.setText("EMAIL FAILED");
//                e.printStackTrace();
//        }
//    }

}
