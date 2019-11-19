package api;

import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

@javax.ws.rs.Path("Adddoctor")
public class Adddoctor {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerException, SQLException {

		JSONArray returnjson=new JSONArray();
		
		JSONParser parser= new JSONParser();
		JSONObject userjson=(JSONObject)parser.parse(formdata);
		JSONObject j = null;
		j= (JSONObject) parser.parse(formdata);
		Connection con=ConnectionJDBC.DBconnect();
		JSONObject jObj=new JSONObject();
		String x="1";
		
		try {
			PreparedStatement ps=con.prepareStatement("select * from alignwise_users where username='"+j.get("addoctorname").toString()+"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				x="2";
				jObj.put("show","Already Exists with this username");
				
			}
			
		if(!x.contentEquals("2")) {	
		PreparedStatement ps2=con.prepareStatement("insert into alignwise_users values('"+j.get("addoctorname").toString()+"','render123#','"+j.get("adcity").toString()+"','"+j.get("adfirstname").toString()+"','"+j.get("adlastname").toString()+"','"+j.get("addoctoremail").toString()+"','"+j.get("adphonenumber").toString()+"','Doctor','"+j.get("adcreatedby").toString()+"','100')");
		
		jObj.put("show","Doctor Added");
		
		
		ps2.execute();
			/*
			 * not working now ps2=con.
			 * prepareStatement("insert into alignwise_cases values(\"LIE01\",\"2019-02-02\",\"e\",\"e\",\"{'date':'2018-01-19','DoctorName':'Alignwise Smile Technologies','Retroclined':'No','OverJetCorrection':'Correct','Overbite':'inc','ArchFormCorrection':'Improve','CorrectSpacing':'CloseAllSpaces','Overjet':'inc','MidlinesLower':'Normal','Gender':'Male','CrossBite':{'No':true},'password':'render123#','CanineRelationCorrection':'Improve','LMalformationOfTeeth':'No','Gingival':'Good','Anyotherconditionradio':'No','serialnumber':'LIE01','ArchForm':'Normal','LCaninRelation':'2','City':'01','PatientName':'Posterior Open Bite','Crowding':'No','LRotation':'No','Treat':'BothArches','MolarRelation':'2','DentalStructures':'No','LMolarRelation':'2','Proclined':'Yes','patientCategoryCode':'1','CorrectCrowdingby':{'Proclination':true,'IPR':true,'Extraction':true,'Expansion':true},'ExtractionSpecify':'Not Applicable','MolarRelationCorrection':'Improve','SpecialInstruction':'.','ClinicAddress':'A-1/51, Keshavpuram, Delhi','CaninRelation':'2','PosteriorCrossBiteCorrection':'NA','IncisorRelationCorrection':'Improve','CorrectRotation':{'Posterior':true,'Anterior':true},'treatingDoctorPhone':'01127371074','OverbiteCorrection':'Correct','filelocation':'/APPDATA/savejson/drvortho_14-05-2018-07-33-45.txt','MidlinesUpper':'Normal','MalformationOfTeeth':'No','Age':'29','timestamp':'14-05-2018-07-35-13','treatingDoctor':'N/A','DentalTreatment':'H/O Extraction = 14, 24, 34, 44','LArchForm':'Normal','DoctorPhoneNumber':'01127371074','Spacing':'Yes','LCrowding':'No','Rotation':'Yes','LCrossBite':{'No':true},'ClinicEmail':'info@alignwisesmile.com','CorrectCrowdingbyonlyifneeded':{'Expansiononlyifneeded':true,'Extractiononlyifneeded':true,'Proclinationonlyifneeded':true,'IPRonlyifneeded':true},'LSpacing':'Yes','ChiefComplaint':'Spacing in Upper & Lower arch and posterior Bite is open','fileurl':'http://103.50.161.184:8080/new/viewuploads.html?name=Posterior Open Bite_14-05-2018-07-35-13','user':"
			 * +"'"+j.get("addoctorname").toString()+
			 * "'"+",'SensitivityofTeeth':'No'}\",\"alignwise smile\",\"e\");\r\n" + "");
			 */

		
		}
		
		System.out.println("vakue of x "+x);
		}
		catch(Exception e) {
			String notadded="Error";
			userjson=(JSONObject)parser.parse(notadded);
			
			
		}
		finally {
			con.close();
			
			
		}
		returnjson.add(jObj);
	    System.out.println(userjson.toJSONString());
	  		return Response.status(Status.OK).entity(x).build();
	    
	  
	}
	
}
