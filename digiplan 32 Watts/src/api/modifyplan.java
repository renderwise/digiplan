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



@Path("/modifyplan")
public class modifyplan {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject test(String formdata) throws IOException, ParseException {
		System.out.println("aa");
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		
		j= (JSONObject) parser.parse(formdata);
		
		JSONObject returnjson= new JSONObject();
		System.out.println("return "+returnjson);
		Connection con=ConnectionJDBC.DBconnect();
		String caseid=(String) j.get("searchserialnumber1");
		System.out.println("this is the serial number "+j.get("searchserialnumber1"));
		
		try {
		PreparedStatement ps=con.prepareStatement("select * from alignwise_cases where caseid='"+caseid+"'");
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			
			
			
		}
		else {
			
			returnjson.put("name","amit goyal");
			
		}
	
		}
		catch(Exception e) {
			
			
		}
		    
		    
		    System.out.println(returnjson +"ghfgvju");
		return returnjson;
	//return "hello";
	}

	
	
}
