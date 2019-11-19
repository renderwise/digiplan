package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import com.mysql.jdbc.Statement;

@Path("/Mail1")
public class Mail1 {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formid) throws IOException, ParseException {
		
		
		
		
		
JSONParser parser=new JSONParser();
		
System.out.println("reached second mail");
		
		Connection con=ConnectionJDBC.DBconnect();
		
		System.out.println(formid);
		String arr[]=formid.split("~");
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		//String index="2";
		String index1="3";
		
		//Connection con=ConnectionJDBC.DBconnect();
		try {
		
	
			PreparedStatement ps1=con.prepareStatement("select * from incompleteform where form_id='"+arr[0]+"'");
			ResultSet rs1=ps1.executeQuery();
			
			if(rs1.next()) {
				
				String formdata=rs1.getString("formdata");
			
				JSONObject extractjson=(JSONObject) parser.parse(formdata);
	        	
			
				
				
				Mail mail=new Mail();
				
				System.out.println("hsyfhsjf");
				mail.sendMail(extractjson.get("ClinicEmail").toString(),arr[1]);
				

				
				
		
			
			
				
			}
			
	
		return Response.status(Status.OK).entity(index1).build();
		
		
		
		
		}
		catch(Exception e) {
			return Response.status(Status.OK).entity(index1).build();
			
		}
		
		
		
		
		
		
		
		
		
		

	}
}