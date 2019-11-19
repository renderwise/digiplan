package api;

import java.io.UnsupportedEncodingException;
import java.util.Properties; 
import javax.mail.*; 
import javax.mail.internet.*; 

public class Mail{ 
public  void sendMail(String to1,String caseid) throws UnsupportedEncodingException { 

	
	System.out.println("mail reached   "+to1);
//change accordingly 
	//String to="amit.goyal4663@gmail.com";
String from="rwisequality@gmail.com";//change accordingly 
String password="KARAN@258";//change accordingly 
String message1 = "<i>Case Submitted And Initialised</i><br>";
message1 += "<b>Your Case Has been Submitted and initialised at Alignwise Smile Technologies<br>Your Case Id is : "+caseid+"</b><br>";


//Get the session object 
Properties props = new Properties(); 
props.put("mail.smtp.host", "smtp.gmail.com"); 
props.put("mail.smtp.socketFactory.port", "465"); 
props.put("mail.smtp.socketFactory.class", 
"javax.net.ssl.SSLSocketFactory"); 
props.put("mail.smtp.auth", "true"); 
props.put("mail.smtp.port", "465"); 

Session session = Session.getDefaultInstance(props, 
new javax.mail.Authenticator() { 
protected PasswordAuthentication getPasswordAuthentication() { 
return new PasswordAuthentication("rwisequality@gmail.com","KARAN@258");
} 
}); 

//compose message 
try { 
MimeMessage message = new MimeMessage(session); 
message.setFrom(new InternetAddress(from,"Alignwise")); 

message.addRecipient(Message.RecipientType.TO,new InternetAddress(to1)); 
message.addRecipient(Message.RecipientType.TO,new InternetAddress("dall46635894@gmail.com")); 

message.setSubject("Case Submitted"); 
message.setContent(message1, "text/html");

//send message 
Transport.send(message); 

System.out.println("message sent successfully"); 

} catch (MessagingException e) {throw new RuntimeException(e);} 

} 
}