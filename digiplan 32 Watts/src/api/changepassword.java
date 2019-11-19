package api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@javax.ws.rs.Path("changepassword")
public class changepassword {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String userform) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerException {
		
		Properties prop = new Properties();
	    InputStream input = null;
	    //File propertyFile = new File("config.properties");
	    input = this.getClass().getResourceAsStream("config.properties");
	    prop.load(input);
	    File file =new File(prop.getProperty("userfolder"));
		JSONParser parser= new JSONParser();
		JSONObject changepasswordjson=(JSONObject)parser.parse(userform);
		System.out.println(changepasswordjson.toJSONString());
		
	    
	    String username=changepasswordjson.get("username").toString();
	    String oldpassword=changepasswordjson.get("oldpassword").toString();
	    String newpassword=changepasswordjson.get("newpassword").toString();
	    String confirmnewpassword=changepasswordjson.get("confirmnewpassword").toString();
	    System.out.println(confirmnewpassword);
	    System.out.println(newpassword);
	    

		Connection con=ConnectionJDBC.DBconnect();
		
		PreparedStatement ps;
		try {
			if(newpassword.equals(confirmnewpassword)){
				ps = con.prepareStatement("update alignwise_users set password='"+newpassword+"' where username='"+username+"' and password='"+oldpassword+"'");	
				ps.execute();			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionJDBC.DBDisconnect(con);	
		}
	   
	   
	    System.out.println(changepasswordjson.toJSONString());
	  		return Response.status(Status.OK).entity(changepasswordjson.toJSONString()).build();
	    
	  
	}
	
}
