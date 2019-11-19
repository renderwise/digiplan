package api;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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


@Path("/viewtreatmentforuser")
public class viewtreatmentforuser {

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException {
		
		System.out.println("this is me");
		
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		j= (JSONObject) parser.parse(formdata);
		Object serialnumber=j.get("searchserialnumber");
		Object patientname=j.get("searchpatientname");
		JSONArray returnjson= new JSONArray();
		String loggedinuser=(String) j.get("user");
		//String user=j.get("PatientName").toString();
		InputStream propfile = this.getClass().getResourceAsStream("config.properties");
		Properties prop=new Properties();
		prop.load(propfile);
		File folder = new File(prop.getProperty("jsonfolder"));
		File[] listOfFiles = folder.listFiles();

		System.out.println("ajdbhjs  "+listOfFiles.length);
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        	
		        	JSONObject addobj=new JSONObject();
		        	String s= new String(Files.readAllBytes(Paths.get(listOfFiles[i].getAbsolutePath())));
		        	JSONObject extractjson=(JSONObject) parser.parse(s);
		        	Object user=extractjson.get("user");
		        	if(user!=null&&user.toString().equals(loggedinuser)){
		        		
		        		if(serialnumber==null){
		        			System.out.println("serialnumbernull");
		        			
		        			if(extractjson.get("PatientName").toString().equals(patientname.toString())){
		        				
		        				addobj.put("PatientName", extractjson.get("PatientName"));
		    		        	addobj.put("serialnumber", extractjson.get("serialnumber"));
		    		        	addobj.put("DOB", extractjson.get("DOB"));
		    		        	addobj.put("Filedate", extractjson.get("date"));
		    		        	addobj.put("Filepath", listOfFiles[i].getAbsoluteFile().toString());
		    		        	returnjson.add(addobj);
		        			}
		        			
		        		}
		        		else{ System.out.println("serialnumbernotnull");
		        		if(extractjson.get("serialnumber")!=null){
		        		if(extractjson.get("serialnumber").equals(serialnumber.toString())){
			        	addobj.put("PatientName", extractjson.get("PatientName"));
			        	addobj.put("serialnumber", extractjson.get("serialnumber"));
			        	addobj.put("DOB", extractjson.get("DOB"));
			        	addobj.put("Filedate", extractjson.get("date"));
			        	addobj.put("Filepath", listOfFiles[i].getAbsoluteFile().toString());
			        	returnjson.add(addobj);
			        		}
		        		}
		        		}
		        	}
		      } 
		    }
		    
		    System.out.println(returnjson.toJSONString());
		return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	//return "hello";
	}

	
	
}
