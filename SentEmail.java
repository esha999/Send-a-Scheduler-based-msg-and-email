package demo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.net.ssl.SSLSocketFactory;

public class SentMail {

String host="smtp.googlemail.com";  
    
	final String sender=" --------- ";//change accordingly  
	final String password=" --------- ";//change accordingly  
    final String cc_receipient="----------";
	  public void sentMail(String receipient, String subject, String content)
	  {
		      
			//Get the session object  
			Properties props = new Properties();
			
			//props.put("mail.transport.protocol", "smtp");
			props.put("mail.user", sender);
			props.put("mail.password", password);
			props.put("mail.smtp.host",host); 
			props.put("mail.smtp.starttls.enable", "587");
			//the above code is used for SSL Protocal only
		   // props.put("mail.smtp.socketFactory.port", "465");   
		  // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		   //end of code
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");   //for tsl only
		   props.put("mail.smtp.port", "587");   //for ssl port is 465
		   
          try{
		   Session sessions = Session.getInstance(props,  
					new javax.mail.Authenticator() {  
				protected PasswordAuthentication getPasswordAuthentication() {  
					return new PasswordAuthentication(sender,password);  
				}  
			}); 
     
		MimeMessage message = new MimeMessage(sessions);  
		message.setFrom(new InternetAddress(sender)); 
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipient));
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(receipient));
		message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc_receipient)); 
		message.setSubject(subject);//Set Subjects

		message.setContent(content,"text/html");

		//send the message  
		Transport.send(message);
		System.out.println("email send successfully");
		System.out.println(content);
          }
          catch(Exception e)
          {
        	  e.printStackTrace();  
          }
		  
	  }
	  
	
}
