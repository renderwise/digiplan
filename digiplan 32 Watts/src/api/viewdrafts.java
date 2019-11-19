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


@Path("/viewdrafts")
public class viewdrafts {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		System.out.println("this is the form data  "+formdata);
		
		JSONParser parser = new JSONParser();
		JSONObject j = null;
		j = (JSONObject) parser.parse(formdata);
		JSONArray returnjson = new JSONArray();

		String user = j.get("user").toString();
		System.out.println("this is the user "+user);
		
		Connection con = ConnectionJDBC.DBconnect();

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("Select * from alignwise_drafts where savedby='"+user+"'"+ " && isActive = 1 ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JSONObject addobj = new JSONObject();
				String s = rs.getString("formdata");
				System.out.println("this is the value of s : "+s);
				
				JSONObject extractjson = (JSONObject) parser.parse(s);
				System.out.println("extract json "+extractjson);
				
				addobj.put("draftId",rs.getString("Id"));
				addobj.put("PatientName", extractjson.get("PatientName"));
				addobj.put("serialnumber", extractjson.get("serialnumber"));
				/*addobj.put("DOB", extractjson.get("DOB"));*/
				addobj.put("Age", extractjson.get("Age"));
				addobj.put("Filedate", extractjson.get("date"));
				addobj.put("Filelocation", extractjson.get("filelocation"));
				addobj.put("formData", extractjson);
				returnjson.add(addobj);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionJDBC.DBDisconnect(con);
		}
		

		System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
		// return "hello";
	}

}
