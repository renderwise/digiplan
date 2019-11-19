package api;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/saveapiupdate")
public class saveapiupdate {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParserConfigurationException {
		JSONParser parser = new JSONParser();
		JSONObject j = null;
		try {
			System.out.println("clicked");
			j = (JSONObject) parser.parse(formdata);
			String user = j.get("user").toString();
			String drafttt = j.get("draftId").toString();
			System.out.println(j.get("user").toString());
			System.out.println(j.toJSONString());
			System.out.println("Sandeepatelnitrr");
		String filepath = null;
			if (j.get("filelocation") != null) {
				filepath = j.get("filelocation").toString();
			}
			Date dateobj = new Date();
			

		
			Connection con = ConnectionJDBC.DBconnect();
			try {
				int draftint=Integer.parseInt(drafttt);
				Timestamp ttt=new Timestamp(dateobj.getTime());
String updatequery = "UPDATE alignwise_drafts SET FORMDATA ='"+j.toJSONString() + "' WHERE id ="+draftint+";";
System.out.println(updatequery);				
PreparedStatement ps = con.prepareStatement(updatequery);
				ps.execute();
				System.out.println(updatequery);
   
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionJDBC.DBDisconnect(con);
			}

			// df = new SimpleDateFormat("yyyy");

	

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(j.toJSONString()).build();
		// return "hello";
	}
	/*public Response test(String formdata) throws IOException, ParserConfigurationException {
		JSONParser parser = new JSONParser();
		JSONObject j = null;
		try {
			System.out.println("clicked");
			j = (JSONObject) parser.parse(formdata);
			String user = j.get("user").toString();
			System.out.println(j.get("user").toString());
			System.out.println(j.toJSONString());
		String filepath = null;
			if (j.get("filelocation") != null) {
				filepath = j.get("filelocation").toString();
			}
			Date dateobj = new Date();
			

		
			Connection con = ConnectionJDBC.DBconnect();

			try {

				PreparedStatement ps = con
						.prepareStatement("insert into alignwise_drafts (SAVEDON,FORMDATA, SAVEDBY) values(?,'" + j.toJSONString() + "','" + j.get("user") + "')");
				ps.setTimestamp(1, new Timestamp(dateobj.getTime()));
				ps.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionJDBC.DBDisconnect(con);
			}

			// df = new SimpleDateFormat("yyyy");

	

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(j.toJSONString()).build();
		// return "hello";
*/
}
