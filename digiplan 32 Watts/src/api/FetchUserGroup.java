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


@Path("/fetchUserGroup")
public class FetchUserGroup {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException, SQLException {
	
		Connection con=ConnectionJDBC.DBconnect();
		JSONArray jarray=new JSONArray();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("Select * from alignwise_usergroup");
			ResultSet rs=ps.executeQuery();
		
			while(rs.next()){
				JSONObject jObject=new JSONObject();
				jObject.put("groupId", rs.getString("groupId"));
				jObject.put("groupName", rs.getString("groupName"));
				jarray.add(jObject);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionJDBC.DBDisconnect(con);	
		}
	   
		    System.out.println(jarray.toJSONString());
		return Response.status(Status.OK).entity(jarray.toJSONString()).build();
	//return "hello";
	}

	
	
}
