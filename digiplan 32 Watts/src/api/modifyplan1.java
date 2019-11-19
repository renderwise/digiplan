package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class modifyplan1
 */
@WebServlet("/modifyplan1")
public class modifyplan1 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONParser parser=new JSONParser();
		PrintWriter out=response.getWriter();
		int plannumber=Integer.parseInt(request.getParameter("plannumber"));
		String caseid=request.getParameter("caseid");
		System.out.println("plan number  "+plannumber + "  "+caseid);
		String returnpart="";
		Connection con=ConnectionJDBC.DBconnect();
		int tablerowcount;
		
		
		try {
			PreparedStatement ps=con.prepareStatement("select * from alignwise_cases where caseid='"+caseid+"'");
			ResultSet rs=ps.executeQuery();
			ResultSet rs2;
			int rs3;
			if(rs.next()) {
				
				ps=con.prepareStatement("select count(*) from alignwise_cases where caseid='"+caseid+"'");
				
				rs2=ps.executeQuery();
				
				if(rs2.next()) {
					
					
					
					tablerowcount=rs2.getInt("count(*)");
					
					if(plannumber<=tablerowcount) {
						
						out.print("plan "+plannumber+" already exists");
						
						
						
						
					}
					else {
						
						
						
						String formdata=rs.getString("formdata");
						System.out.println(formdata);
						String formdata1=formdata;
						JSONObject extractjson=(JSONObject) parser.parse(formdata);
						
						System.out.println("serial number = "+extractjson.get("serialnumber").toString());
						
						JSONObject extractjson1=(JSONObject) parser.parse(formdata1);
						
						extractjson1.replace("serialnumber",extractjson.get("serialnumber").toString()+"-"+plannumber);
						
						 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					       Date dateobj = new Date();
					       
					       System.out.println(df.format(dateobj));
						
						//extractjson1.replace("Filedate",);
						
					
						extractjson1.replace("date",df.format(dateobj));
						
						formdata1=extractjson1.toJSONString();
						System.out.println("new form details are : "+formdata1);
						
						System.out.println("new form details are : "+extractjson1.get("date"));
						
						System.out.println("insert into alignwise_cases(caseid,submittedon,formdata,submittedby) values('"+caseid+"','"+df.format(dateobj)+"','"+formdata1+"','"+extractjson1.get("user")+"','"+extractjson1.get("remarks")+"')");
						
						ps=con.prepareStatement("insert into alignwise_cases values('"+caseid+"','"+df.format(dateobj)+"','No link','No link','"+formdata1+"','"+extractjson1.get("user")+"','"+extractjson1.get("remarks")+"')");
						ps.execute();
						out.print("New Plan Uploaded ");
						
						
						
					}
					
					
					
					
				}
				
			}
			else {
				
			returnpart="No Such Case Id Exists";
			out.print(returnpart);
				
			}
		
			}
			catch(Exception e) {
				
				
			}
		
		
		
		
		
	}

}
