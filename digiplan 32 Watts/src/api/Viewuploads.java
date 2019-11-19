package api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

@Path("viewuploads")
public class Viewuploads {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		JSONParser parser= new JSONParser();
		JSONObject jsonObject=(JSONObject) parser.parse(formdata);
		System.out.println(jsonObject.get("name"));
		
		Properties prop= new Properties();
	    InputStream input = null;
	    //File propertyFile = new File("config.properties");
	    input = this.getClass().getResourceAsStream("config.properties");
	    prop.load(input);
	    JSONArray returnjson=new JSONArray();
	    String uploadFolder= prop.getProperty("uploadfolder");
	    
	    File folder=new File(uploadFolder+"/"+jsonObject.get("name"));
	    System.out.println(folder.getAbsolutePath());
	    File[] listofuploads=folder.listFiles();
	    
	    for (int i = 0; i < listofuploads.length; i++) {
		      if (listofuploads[i].isFile()) {
		        System.out.println("File " + listofuploads[i].getName());
		        returnjson.add(listofuploads[i].getName());
		      } 
		    }
	    
		
	    System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	
	
	}
	
}
