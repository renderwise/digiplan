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
import java.sql.Timestamp;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/lastcaseforuser")
public class Lastcaseforuser {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		j= (JSONObject) parser.parse(formdata);
		JSONObject returnjson= new JSONObject();
		String loggedinuser=(String) j.get("user");
		//String user=j.get("PatientName").toString();
		
		//select * from AlignWise_Cases where Submittedon=(Select  max(Submittedon) from alignwise_cases )
		Connection con=ConnectionJDBC.DBconnect();
		
		try{
			String query="select * from alignwise_cases where Submittedon=(Select  max(Submittedon) from alignwise_cases where submittedby='"+loggedinuser+"')";
			System.out.println(query);
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){
				String s= rs.getString("formdata");
	        	JSONObject extractjson=(JSONObject) parser.parse(s);
				returnjson=extractjson;
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
