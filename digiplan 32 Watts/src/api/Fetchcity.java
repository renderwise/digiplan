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



@Path("/fetchcities")
public class Fetchcity {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject j = null;
		j = (JSONObject) parser.parse(formdata);
		JSONArray returnjson = new JSONArray();
		Connection con = ConnectionJDBC.DBconnect();

		try {

			PreparedStatement ps = con.prepareStatement("select * from alignwise_citymaster");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				JSONObject extractjson = new JSONObject();
				extractjson.put("id", rs.getString("id"));
				extractjson.put("name", rs.getString("name"));
				returnjson.add(extractjson);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionJDBC.DBDisconnect(con);
		}

		System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
		// return "hello";
	}

}
