package api;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@Path("/hello")
public class Webapi {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(String formdata) throws IOException, ParserConfigurationException {
		JSONParser parser=new JSONParser();
		JSONObject j = null;
		try {
			
			System.out.println("clicked "+formdata );
			j=(JSONObject) parser.parse(formdata) ;
			
			
			if(j.get("filelocation")!=null){
				File deletefile=new File(j.get("filelocation").toString());
				deletefile.delete();
			}
			
			String user=j.get("PatientName").toString();
			System.out.println(j.get("PatientName").toString());
			System.out.println(j.toJSONString());
			Properties prop = new Properties();
		    InputStream input = null;
		    //File propertyFile = new File("config.properties");
		    input = this.getClass().getResourceAsStream("config.properties");
		    prop.load(input);
		    
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
			Date dateobj = new Date();
			
			String serverip=prop.getProperty("serverip");
			j.put("timestamp", ""+df.format(dateobj));
			Serialnumber sclass=new Serialnumber();
			j.put("fileurl", "http://"+serverip+"/digiplan32watts/viewuploads.html?name="+j.get("PatientName")+"_"+df.format(dateobj) );
			//String serialnumber=sclass.serialnumber();
		
			df = new SimpleDateFormat("yyyy");
			
			String serial1=df.format(dateobj);
			String serial="";
			/*for(int m=0;m<serial1.length();m++){
				serial=serial+serial1.charAt(serial1.length()-1-m);
			}*/
			
			serial+=serial1.split("")[2]+serial1.split("")[3];
			
			//df = new SimpleDateFormat("yyyy");
			
			
			df = new SimpleDateFormat("MM");
			serial+=df.format(dateobj) ;
			serial+=j.get("City");
			serial+=j.get("patientCategoryCode");
			/*int looplength=3-serialnumber.length();
			for(int k=0;k<looplength;k++){
				serialnumber="0"+serialnumber;
			}*/
			String remarks="";
			GetSerialNumber getSerialnumber=new GetSerialNumber();
		//	String serialNumber=getSerialnumber.generateSerial((String) j.get("City"));
			int serialNumber=0;
			//j.put("serialnumber", serial+serialNumber);
			
			Connection con=ConnectionJDBC.DBconnect();
			ResultSet rs;
			try{
			
				if(j.containsKey("remarks")) {
					
					remarks=j.get("remarks").toString();
					
				}
				
				PreparedStatement ps=con.prepareStatement("insert into incompleteform(submittedon,formdata,submittedby,remarks,patientname,flag) values(?,?,?,'"+remarks+"','"+j.get("PatientName").toString()+"','Y')");
				
				
				//PreparedStatement ps=con.prepareStatement("insert into alignwise_cases values('"+j.get("serialnumber")+"',?,'No link','No link',?,'"+j.get("user")+"','"+j.get("remarks")+"')");
				
				ps.setTimestamp(1, new Timestamp(dateobj.getTime()));
				ps.setString(2,j.toJSONString());
				System.out.println("thisistheuser "+j.get("user").toString());
				ps.setString(3,j.get("user").toString());
				ps.execute();
				String query="Select * from incompleteform order by form_id desc limit 1";
		    	PreparedStatement ps3=con.prepareStatement(query);
		    	 rs=ps3.executeQuery();
		    	if(rs.next()) {
		    		serialNumber=rs.getInt("form_id");
		    	}
		    	System.out.println(serialNumber);
		    	j.put("serialnumber", serialNumber);
				
				
				
				System.out.println("remarks Add Sucessfully");
				
				//System.out.println(j.get("remarks").toString());
				
				
			
/*				CaseID nvarchar2(20) primary key, 
PatientName nvarchar2(20),
Gender nvarchar2(20),
DateOfBirth date,
Age nvarchar2(20),
PatientCategory nvarchar2(20),
ChiefComplaint CLOB)*/
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				ConnectionJDBC.DBDisconnect(con);
			}
			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(Status.OK).entity(j.toJSONString()).build();
	//return "hello";
	}

}
