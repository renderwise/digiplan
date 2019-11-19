package api;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/contactus")
public class Contactusquery {

	/*
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
			
		JSONParser parser= new JSONParser();
		JSONObject formdatajson=  (JSONObject) parser.parse(formdata);
		MailSender mailsender= new MailSender();
		String emailBody="Name : "+ formdatajson.get("name")+"\nEmail : "+formdatajson.get("email")+"\nContact : "+formdatajson.get("contact")+"\nQuery : "+formdatajson.get("query"); 
		
		Properties prop = new Properties();
	    InputStream input = null;
	    //File propertyFile = new File("config.properties");
	    input = this.getClass().getResourceAsStream("config.properties");
	    prop.load(input);
	    String from=prop.getProperty("emailsendfrom");
	    String emailTo=prop.getProperty("emailsendtoquery");
	    String pwd=prop.getProperty("senderpassword");
		String smtpserver=prop.getProperty("smtpserver");
		String smtpport=prop.getProperty("smtpport");
		System.out.println(mailsender.sendMail(smtpserver, smtpport, from, pwd, "Query", emailBody, emailTo, null, "text"));
		return null;
	
	}*/
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException, SQLException {
			
		JSONParser parser= new JSONParser();
		JSONObject formdatajson=  (JSONObject) parser.parse(formdata);
		MailSender mailsender= new MailSender();
		
		String QueryId=null;
		String CustomerName=(String) formdatajson.get("name");
		String email=(String) formdatajson.get("email");
		String phoneNumber=(String)formdatajson.get("contact");
		String queryText=(String)formdatajson.get("query");
		
		Date date= new Date();
		QueryId= date.getTime()+"";
		
		Connection con=ConnectionJDBC.DBconnect();
		
		PreparedStatement ps=con.prepareStatement("insert into alignwise_query values ('"+QueryId+"','"+CustomerName+"','"+phoneNumber+"','"+email+"','"+queryText+"')");
		ps.execute();
		
		ConnectionJDBC.DBDisconnect(con);
		return Response.status(Status.OK).entity("{\"queryId\":\""+QueryId+"\"}").build();
		
		
		
	}
	
}
