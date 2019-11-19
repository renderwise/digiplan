package api;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




@Path("/return")
public class previousform {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException {
		JSONParser parser=new JSONParser();
		
		try {
			JSONObject j= (JSONObject) parser.parse(formdata);
			//String user=j.get("user").toString();
			JSONObject json=(JSONObject) parser.parse(j.get("fetchdate").toString());
			String filepath=(String) json.get("Filepath");
			if(filepath==null){
				 filepath=(String) json.get("Filelocation");
			}
			System.out.println(filepath);
			System.out.println(json.toJSONString());
			//File file = new File("D:\\new.json");
			String s= new String(Files.readAllBytes(Paths.get(filepath)));
			JSONObject jsonObject= (JSONObject) parser.parse(s);
			System.out.println(jsonObject.toJSONString());
			return Response.status(Status.OK).entity(jsonObject.toJSONString()).build();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
		
	//return "hello";
	}

}
