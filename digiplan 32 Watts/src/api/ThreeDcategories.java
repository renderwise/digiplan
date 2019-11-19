package api;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@javax.ws.rs.Path("3dcategories")
public class ThreeDcategories {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String userform) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerException {

		Properties prop = new Properties();
	    InputStream input = null;
	    //File propertyFile = new File("config.properties");
	    input = this.getClass().getResourceAsStream("config.properties");
	    prop.load(input);
	    
		JSONParser parser= new JSONParser();
		JSONObject userjson=(JSONObject)parser.parse(userform);
		File file =new File(prop.getProperty("stlfilesfolder")+userjson.get("user")+"/stl/");
		System.out.println(file.exists());
		
		System.out.println(file.list());
		JSONArray stlcategories= new JSONArray();
		for(int i=0;i<file.listFiles().length;i++){
			if(file.listFiles()[i].isDirectory()){
				stlcategories.add(file.listFiles()[i].getName());
			}
		}
		userjson.put("categories",stlcategories);
		
		
		
	    
	   
	    System.out.println(userjson.toJSONString());
	  		return Response.status(Status.OK).entity(userjson.toJSONString()).build();
	    
	  
	}
	
}