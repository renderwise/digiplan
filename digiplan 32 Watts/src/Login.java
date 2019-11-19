import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.InputStream;
import java.io.PrintWriter;  
  


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
  
  
public class Login extends HttpServlet {  
	private static final long serialVersionUID = 102831973239L;
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
	
	if(request.getParameter("loginButton")!=null){
		    response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		          
		    String n=request.getParameter("username");  
		    String p=request.getParameter("password");  
		    
		    String userName=n;
		    String password=p;
		    
		    String uname = null;
		    String pwd = null;
		    Connection con = null;
		    try{
		    	
		    	
		    	con= ConnectionJDBC.DBconnect();
		    	
		    	String query="Select * from alignwise_users where Username=? and password=?";
		    	PreparedStatement ps=con.prepareStatement(query);
		    	ps.setString(1, userName);
		    	ps.setString(2, password);
		    	ResultSet rs=ps.executeQuery();
		    	JSONObject jObject=new JSONObject(); 
		    	
		    	while(rs.next()){
		    		jObject.put("username", rs.getString("Username"));
		    		jObject.put("password", rs.getString("Password"));
		    		jObject.put("city", rs.getString("city"));
		    		jObject.put("firstName", rs.getString("FirstName"));
		    		jObject.put("lastName", rs.getString("LastName"));
		    		jObject.put("email", rs.getString("Email"));
					jObject.put("phoneNumber", rs.getString("PhoneNumber"));
					jObject.put("typeOfUser", rs.getString("TypeOfUser"));
				}
		    	HttpSession session = request.getSession();
				if (!jObject.isEmpty()) {
					
					session.setAttribute("userjson", jObject);

					if (jObject.get("typeOfUser").toString().equals("Support")) {
						session.setAttribute("supportpassword", jObject.get("password").toString());
						session.setAttribute("supportuser", jObject.get("username").toString());
						response.sendRedirect("supportform.jsp");
					} else if (jObject.get("typeOfUser").toString().equals("Doctor") || jObject.get("typeOfUser").toString().equals("DoctorAdmin") || jObject.get("typeOfUser").toString().equals("caseuser")) {
						session.setAttribute("username", jObject.get("username").toString());
						session.setAttribute("password", jObject.get("password").toString());
						session.setAttribute("typeofuser",jObject.get("typeOfUser").toString());
						response.sendRedirect("patientpresicriptionform.jsp");
					}
					else{
						session.setAttribute("error", "Invalid credentials!");
				    	response.sendRedirect("index.jsp");
					}

				}else{
					session.setAttribute("error", "Invalid credentials!");
			    	response.sendRedirect("index.jsp");
				}
		    	
		    	
		    	
		    	
		    	
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    finally {
		    	ConnectionJDBC.DBDisconnect(con);
			}
    }  
	if(request.getParameter("registerButton")!=null){
		 RequestDispatcher rd=request.getRequestDispatcher("register.html");  
	     rd.include(request,response); 
	}
 }	 

}