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

@Path("/Showform")
public class Showform {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String x) throws IOException, ParseException {
		
		
		System.out.println(x);
		JSONArray returnjson= new JSONArray();
		
		JSONObject addobj=new JSONObject();
		Connection con=ConnectionJDBC.DBconnect();
		String formdata="";
		JSONParser parser=new JSONParser();
		//JSONObject addobj=new JSONObject();
    	
		try {
			
			PreparedStatement ps=con.prepareStatement("select * from incompleteform where form_id='"+x+"'");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				
				formdata=rs.getString("formdata");
				System.out.println("bfdujs fkdbf");
				
			}
			addobj=(JSONObject) parser.parse(formdata);
			//JSONObject extractjson=(JSONObject) parser.parse(s);
        	
			
			
		}
		
		
		catch(Exception e) {
			
			
			
		}
		finally {
			
			ConnectionJDBC.DBDisconnect(con);
		}
		System.out.println("addobj   =  "+addobj.toJSONString());
		
		return Response.status(Status.OK).entity(addobj.toJSONString()).build();
		
	}
}