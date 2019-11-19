package api;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;



//import com.cnp.uw.covernote.vo.MailManagerVO;



public class MailSender {
	
	
    public MailSender() {
    }
    
   

    public String sendMail(String host, String port, String from, String pwd, String emailSubject, String emailBody, String emailTo, String filename, String contentType) {
        
    	//MailVO mailVO = new MailVO();
    	String method_Name=" :: MailSender :: sendMail : ";
		//logger.info(method_Name+"Start...!!!!!!");

		
    	String message = "";
        int returnValue=0;
        String to = emailTo;
         
        
        String emailMsgMimeType = "text/text";
        if(contentType.equalsIgnoreCase("html")) {
        	emailMsgMimeType = "text/html";
        }
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.starttls.required", "false");
        props.put("mail.debug", "true");
        props.put("mail.transport.protocol", "smtp");
        
        Authenticator auth = new AccountAuthenticator(from,pwd); 
        Session session = Session.getInstance(props, auth);
       
//        Session session = Session.getInstance(props, null); //Commented on 24/7/2015 for changing mail ID

        try {
            // create a message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address_to = { new InternetAddress(to) };
            msg.setRecipients(Message.RecipientType.TO, address_to);
            
            msg.setSubject(emailSubject);
            msg.setSentDate(new Date());
            
            // If the desired charset is known, you can use
            // setText(text, charset)
            //msg.setText();
            
         // create and fill the first message part        
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(emailBody);
         // create the Multipart and add its parts to it
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            
            if(filename!=null){
            // create the second message part
            MimeBodyPart mbp2 = new MimeBodyPart();    
            // attach the file to the message
            FileDataSource fds = new FileDataSource(filename);
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
            mp.addBodyPart(mbp2);
            }
            // add the Multipart to the message
            msg.setContent(mp);

            Transport transport = session.getTransport("smtp");
            transport.send(msg);
            message = "success";
           // logger.info(method_Name+" "+message);
        	returnValue = 1;
        	//return null;
        	return "Send to "+emailTo;
            }
        	catch (MessagingException ex) {
    	    //logger.error("MailSender :: MessagingException :  "+ex.getMessage());
    	    returnValue = -1;
    	    //mailVO.setMailStatus(returnValue);
    	    ex.printStackTrace();
    	    //mailVO.setException_message(ex.getMessage());
    	}catch (Exception ex2) {
    		//logger.error("MailSender :: Exception :  "+ex2.getMessage());
    	    returnValue = -1;
    	   // mailVO.setMailStatus(returnValue);
    	    ex2.printStackTrace();
    	    //mailVO.setException_message(ex2.getMessage());
    	}catch (Throwable ex3) {
    		//logger.error("MailSender :: Throwable :  "+ex3.getMessage());
    	    returnValue = -1;
    	   // mailVO.setMailStatus(returnValue);
    	    ex3.printStackTrace();
    	  //  mailVO.setException_message(ex3.getMessage());
    	}
    	//logger.error(" MailSender :: returnValue  "+returnValue);
    	//return mailVO;
		return null;
        
    }
    
    
    public static class AccountAuthenticator extends Authenticator    
       {
           private String username;
           private String password;
           AccountAuthenticator(String username, String password) {
               this.username = username;
               this.password = password;
           }
           public PasswordAuthentication getPasswordAuthentication() { 
                   return new PasswordAuthentication(username, password);   
           }   
       } 
}
