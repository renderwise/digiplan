package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createcaseid
 */
@WebServlet("/createcaseid")
public class createcaseid extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String caseid=request.getParameter("caseid");
		PrintWriter out=response.getWriter();
		System.out.println(caseid);
		String formid=request.getParameter("form_id");
		
		System.out.println(formid);
		Connection con=ConnectionJDBC.DBconnect();
		try {
		PreparedStatement ps=con.prepareStatement("select * from alignwise_cases where caseid='"+caseid+"'");
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			
			out.println("no");
			System.out.println("inside");
			
			
		}
		else {
				/*
				 * PreparedStatement
				 * ps=con.prepareStatement("select * from alignwise_cases where caseid='"+caseid
				 * +"'"); ResultSet rs=ps.executeQuery();
				 */
			
		}
		
		}
		catch(Exception e) {
			
			
			
		} 
		finally {
			
			
		}
		
		
	}

}
