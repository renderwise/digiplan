import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");

		String typeofuser = request.getParameter("typeofuser");
		String createdBy = request.getParameter("createdBy");
		String groupId = request.getParameter("groupId");
		Connection con=null;
		con= ConnectionJDBC.DBconnect();
    	
    	String query="insert into alignwise_users values('"+uname+"','"+pwd+"','"+city+"','"+fname+"','"+lname+"','"+email+"','"+phone+"','"+typeofuser+"','"+createdBy+"','"+groupId+"')";
    	Boolean check=null;
    	try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.execute();
			out.println(" <script> alert( 'User Created Successfully') </script>");

			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			out.println(" <script> alert( 'User was not created due to some error') </script>");
		}
    	RequestDispatcher rd = request.getRequestDispatcher("adminpanel.jsp");
		rd.include(request, response);
		
		
	}

}