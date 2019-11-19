package api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@javax.ws.rs.Path("fetchusers")
public class Fetchuserdetail {
	
	public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerException {
		Fetchuserdetail f=new Fetchuserdetail();
		f.test("a");
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String userform) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerException {
		
		JSONArray returnjson=new JSONArray();
		Connection con=ConnectionJDBC.DBconnect();
		System.out.println(userform);
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement("select * from alignwise_users");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				JSONObject jObj=new JSONObject();
				jObj.put("username", rs.getString("username"));
				jObj.put("password", rs.getString("password"));
				jObj.put("firstName", rs.getString("firstName"));
				jObj.put("lastName", rs.getString("lastName"));
				jObj.put("email", rs.getString("email"));
				if(!jObj.get("username").toString().equals( ((JSONObject)(new JSONParser()).parse(userform)).get("adminuser").toString() ))
				returnjson.add(jObj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionJDBC.DBDisconnect(con);	
		}
	   System.out.println(returnjson.toJSONString());
	   
	return Response.status(Status.OK).entity(returnjson.toJSONString()).build();
	}
}
