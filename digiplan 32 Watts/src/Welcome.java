import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
public class Welcome extends HttpServlet {  
	private static final long serialVersionUID = 102831973239L;
public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("username");  
    out.print("Welcome, "+n);  
          
    out.close();  
    }  
public static void main(String[] args) {
	
	ArrayList al=new ArrayList<Object>();
	
	al.add("true");
	System.out.println(al.size());
	al.add("true");
	 
	
	
	Iterator i= al.iterator();
	System.out.println(al.size());
	while(i.hasNext()){
		
		System.out.println(i.next());
		System.out.println(al.size());
		
	}
	
	
	
	
	
	
}

}
