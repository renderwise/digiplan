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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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


@Path("/fetchdoctornames")
public class Fetchdoctornames {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException, SQLException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		j= (JSONObject) parser.parse(formdata);
		JSONArray returnjson= new JSONArray();
		
		Connection con=ConnectionJDBC.DBconnect();
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("Select Firstname,lastname from alignwise_users where typeofuser in ('Doctor','DoctorAdmin')");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String docName=rs.getString("Firstname")+" "+rs.getString("lastname");
				if(docName!=null){
					returnjson.add(docName);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionJDBC.DBDisconnect(con);	
		}
	   
		    System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}

	
	
}
