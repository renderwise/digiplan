package api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@Path("/Sendemail")
public class Sendemail {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		try {
			System.out.println("clicked");
			j=(JSONObject) parser.parse(formdata) ;
			String user=j.get("PatientName").toString();
			System.out.println(j.get("PatientName").toString());
			System.out.println(j.toJSONString());
			JSONObject form=(JSONObject) j.get("form");
			String html=form.get("html").toString();
			Properties prop = new Properties();
		    InputStream input = null;
		    //File propertyFile = new File("config.properties");
		    input = this.getClass().getResourceAsStream("config.properties");
		    prop.load(input);
		    File directory=new File(prop.getProperty("htmlfolder"));
			if(!directory.exists()){directory.mkdir();}
			
			File file=new File(directory+"/"+user+"_"+j.get("timestamp")+".html");
			FileWriter fw= new FileWriter(file);
			System.out.println(file.getAbsolutePath());
			fw.write(html);
			fw.flush();
			fw.close();
			MailSender mailsender=new MailSender();
			String from=prop.getProperty("emailsendfrom");
		    String emailTo=prop.getProperty("emailsendtohtml");
		    String pwd=prop.getProperty("senderpassword");
			String smtpserver=prop.getProperty("smtpserver");
			String smtpport=prop.getProperty("smtpport");
			
			
			
			
			String emailsentornot=mailsender.sendMail(smtpserver, smtpport, from, pwd, "Patient prescription form", "<h3>PFA Html document</h3>", emailTo, file.getAbsolutePath(), "html");
			if(emailsentornot==null){
				return Response.status(Status.SERVICE_UNAVAILABLE).entity(form.toJSONString()).build();
			}
			return Response.status(Status.OK).entity(j.toJSONString()).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(Status.SERVICE_UNAVAILABLE).build();
	//return "hello";
	}

}
