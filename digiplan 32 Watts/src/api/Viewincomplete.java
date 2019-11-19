package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/Viewincomplete")
public class Viewincomplete {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test() throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		
		
		JSONArray returnjson= new JSONArray();
		Connection con=ConnectionJDBC.DBconnect();
		
		try {
			PreparedStatement ps=con.prepareStatement("select * from incompleteform where flag='Y'order by submittedon desc");
			ResultSet rs=ps.executeQuery();
		
			while(rs.next())
			{
		        System.out.println("jefkjdnvdhbckhsbfkdbsbksjshvbjh");
		        	JSONObject addobj=new JSONObject();
		        	String s= rs.getString("formdata");
		        	String formid=rs.getString("form_id");
		        	String submittedon=rs.getString("submittedon");
		        	System.out.println(submittedon);
		        	String submittedby=rs.getString("submittedby");
		        	System.out.println(submittedby);
		        	System.out.println("form id = "+formid);
		        	JSONObject extractjson=(JSONObject) parser.parse(s);
			        
		        	String email=extractjson.get("ClinicEmail").toString();
		        	
		        	addobj.put("PatientName", rs.getString("patientname"));
		        	//addobj.put("serialnumber", extractjson.get("serialnumber"));
		        	//addobj.put("DOB", extractjson.get("DOB"));
		        	//addobj.put("Filedate", extractjson.get("date"));
		        	addobj.put("formid", formid);
		        	addobj.put("email",email);
		        	addobj.put("submittedon", submittedon);
		        	addobj.put("submittedby", submittedby);
			        	returnjson.add(addobj);
		        	
		        	//addobj.put("Filepath", listOfFiles[i].getAbsoluteFile().toString());
		        	
		        
		      } 
			
			
		}
		catch(Exception e) {
			
			
			
		}
		finally {
			ConnectionJDBC.DBDisconnect(con);
		}
		
		    
		    System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();

	}
}