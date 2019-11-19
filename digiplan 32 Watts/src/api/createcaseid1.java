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

@Path("/createcaseid1")
public class createcaseid1 {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formid) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		
		
		JSONArray returnjson= new JSONArray();
		Connection con=ConnectionJDBC.DBconnect();
		
		System.out.println(formid);
		String arr[]=formid.split("~");
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		String query="update incompleteform set flag='N' where form_id='"+arr[0]+"'";
		System.out.println(query);
		String index="2";
		String index1="3";
		 Statement stmt = null;
		
		//Connection con=ConnectionJDBC.DBconnect();
		try {
		PreparedStatement ps=con.prepareStatement("select * from alignwise_cases where caseid='"+arr[1]+"'");
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			
			return Response.status(Status.OK).entity(index).build();

			
		}
		else {
			
			 stmt = (Statement) con.createStatement();
				
		stmt.executeUpdate(query);
			
			PreparedStatement ps1=con.prepareStatement("select * from incompleteform where form_id='"+arr[0]+"'");
			ResultSet rs1=ps1.executeQuery();
			
			if(rs1.next()) {
				
				
				String formdata=rs1.getString("formdata");
				String submittedby=rs1.getString("submittedby");
				String submittedon=rs1.getString("submittedon");
				String remarks=rs1.getString("remarks");
				
				JSONObject extractjson=(JSONObject) parser.parse(formdata);
	        	extractjson.put("serialnumber",arr[1]);
	        	
	        	workflow.dataAdd(arr[1],extractjson.get("PatientName").toString(),extractjson.get("treatingDoctorPhone").toString(),extractjson.get("ClinicAddress").toString());
				
				PreparedStatement ps2=con.prepareStatement("insert into alignwise_cases values('"+arr[1]+"','"+submittedon+"','','','"+extractjson.toJSONString()+"','"+submittedby+"','"+remarks+"')");
				ps2.execute();
				
				
								PreparedStatement ps4=con.prepareStatement("insert into alignwise_basicinfo values('"+arr[1]+"',?,?,?,?,?,?,?)");
				ps4.setString(1, submittedby);
				ps4.setString(2, extractjson.get("DoctorPhoneNumber").toString());
				ps4.setString(3, extractjson.get("treatingDoctor").toString());
				ps4.setString(4, extractjson.get("treatingDoctorPhone").toString());
				ps4.setString(5, extractjson.get("ClinicAddress").toString());
				ps1.setString(6, extractjson.get("City").toString());
				ps4.setString(7, extractjson.get("ClinicEmail").toString());
				/*ps1.setString(8, j.get("remarks").toString());*/
				ps4.execute();
				
				
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedTimeStamp = dateFormat.parse(extractjson.get("DOB").toString());
				Timestamp timestampDOB = new Timestamp(parsedTimeStamp.getTime());		 
				
				PreparedStatement ps3=con.prepareStatement("insert into alignwise_basicinfopatient values('"+arr[1]+"',?,?,?,?,?,?)");
				ps3.setString(1, extractjson.get("PatientName").toString());
				ps3.setString(2, extractjson.get("Gender").toString());
				ps3.setTimestamp(3, timestampDOB);
				ps3.setString(4, extractjson.get("Age").toString());
				ps3.setString(5, extractjson.get("patientCategoryCode").toString());
				ps3.setString(6, extractjson.get("ChiefComplaint").toString());
				ps3.execute();
				
				System.out.println("u are here");
			
				
				
			
				
			}
			
	
		return Response.status(Status.OK).entity(index1).build();
		}
		
		
		
		}
		catch(Exception e) {
			return Response.status(Status.OK).entity(index1).build();
			
		}
	}
}
