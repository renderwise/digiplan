package api;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

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



@Path("/Samples")
public class Samples {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		j= (JSONObject) parser.parse(formdata);
		JSONArray returnjson= new JSONArray();
		String loggedinuser=(String) j.get("user");
		//String user=j.get("PatientName").toString();
Connection con=ConnectionJDBC.DBconnect();
		
		try{
			
			PreparedStatement ps=con.prepareStatement("select * from gallery");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
		        
		        	JSONObject addobj=new JSONObject();
		        	String s= rs.getString("formdata");
		        	JSONObject extractjson=(JSONObject) parser.parse(s);
		        	String user=extractjson.get("user").toString();
		       
		        	addobj.put("PatientName", extractjson.get("PatientName"));
		        	addobj.put("serialnumber", extractjson.get("serialnumber"));
		        	addobj.put("DOB", extractjson.get("DOB"));
		        	addobj.put("Filedate", extractjson.get("date"));
		        	addobj.put("data", extractjson);
		        	
		        	//addobj.put("Filepath", listOfFiles[i].getAbsoluteFile().toString());
		        	returnjson.add(addobj);
		        
		      } 
		
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			ConnectionJDBC.DBDisconnect(con);
		}
		
		    
		    System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}

	
	
}
