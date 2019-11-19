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
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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


@Path("/Previousdatesforuser")
public class Previousdatesforuser {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		j= (JSONObject) parser.parse(formdata);
		JSONArray returnjson= new JSONArray();
		String loggedinuser=(String) j.get("user");
		System.out.println(loggedinuser);
		//String user=j.get("PatientName").toString();
Connection con=ConnectionJDBC.DBconnect();
		
		try{
			
			PreparedStatement ps=con.prepareStatement("select * from alignwise_cases");
			ResultSet rs=ps.executeQuery();
			PreparedStatement ps1=con.prepareStatement("select * from alignwise_users where username='"+loggedinuser+"'");
			ResultSet rs1=ps1.executeQuery();
			System.out.println("abc");
			System.out.println(loggedinuser);
			Boolean doctorAdminCheck = false;
			String doctorAdminGroupId = "";
			if(rs1.next()){
				if(rs1.getString("TypeOfUser").equals("DoctorAdmin")){
					doctorAdminCheck=true;
					doctorAdminGroupId=rs1.getString("groupId");
					System.out.println(doctorAdminGroupId);
				}
			}
			ArrayList groupUsers=new ArrayList();
			if(doctorAdminCheck){
			PreparedStatement ps2=con.prepareStatement("select * from alignwise_users where groupId='"+doctorAdminGroupId+"'");
			ResultSet rs2=ps2.executeQuery();
			
			while(rs2.next()){
				groupUsers.add(rs2.getString("username"));
			}
			
			}
			
			while(rs.next())
			{
		        
					try {
		        	JSONObject addobj=new JSONObject();
		        	String s= rs.getString("formdata");
		        	System.out.println(s);
		        	JSONObject extractjson=(JSONObject) parser.parse(s);
		        	String user=extractjson.get("user").toString();
		        	
		        	if(user!=null&& (user.toString().equals(loggedinuser) || (doctorAdminCheck && groupUsers.contains(user)) )   ){
		        	addobj.put("PatientName", extractjson.get("PatientName"));
		        	addobj.put("serialnumber", extractjson.get("serialnumber"));
		        	addobj.put("DOB", extractjson.get("DOB"));
		        	addobj.put("Filedate", extractjson.get("date"));
		        	addobj.put("data", extractjson);
		        	returnjson.add(addobj);
		        	}
					}
					catch(Exception e) {
						
					}
		        	//addobj.put("Filepath", listOfFiles[i].getAbsoluteFile().toString());
		        	
		        
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
