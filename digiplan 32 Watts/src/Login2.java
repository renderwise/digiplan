import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.InputStream;
import java.io.PrintWriter;  
  


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
  
  
public class Login2 extends HttpServlet {  
	private static final long serialVersionUID = 102831973239L;
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
	
	if(request.getParameter("loginButton")!=null){
		    response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		          
		    String n=request.getParameter("username");  
		    String p=request.getParameter("password");  
		    
		    String uname = null;
		    String pwd = null;
		    
		    try{
		    	
		    	Properties prop = new Properties();
			    InputStream input = null;
			    //File propertyFile = new File("config.properties");
			    input = this.getClass().getResourceAsStream("config.properties");
			    prop.load(input);
			    System.out.println(prop.getProperty("database"));
			    File file =new File(prop.getProperty("appfolder"));
			    if(!file.exists()){file.mkdir();}
			    file =new File(prop.getProperty("userfolder"));
			    if(!file.exists()){file.mkdir();}
			    
			    
			    //String filepath = "D:\\SampleWorkspace" + System.getProperty("file.separator")+ n + ".xml";
			    String filepath = file + System.getProperty("file.separator") + n + ".xml";
			    System.out.println("filepath: " +filepath);
			    file = new File(filepath);
			    Path path = Paths.get(filepath);
			    
				
	    	if(Files.exists(path)){
		    	System.out.println("User already exists!");
		    	
		    try {
		    	
		    	File fXmlFile = new File(filepath);
		    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    	Document doc = dBuilder.parse(fXmlFile);
		
		    	doc.getDocumentElement().normalize();
		
		    	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		    	NodeList nList = doc.getElementsByTagName("node");
		    	JSONObject jobj=new JSONObject();
		    	for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
	    			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
	    			System.out.println("User Name : " + eElement.getElementsByTagName("username").item(0).getTextContent());
	    			System.out.println("Password : " + eElement.getElementsByTagName("password").item(0).getTextContent());
	    			System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
		    		jobj.put("firstname", eElement.getElementsByTagName("firstname").item(0).getTextContent());
		    		jobj.put("lastname", eElement.getElementsByTagName("lastname").item(0).getTextContent());
		    		jobj.put("username", eElement.getElementsByTagName("username").item(0).getTextContent());
		    		jobj.put("password", eElement.getElementsByTagName("password").item(0).getTextContent());
		    		jobj.put("email", eElement.getElementsByTagName("email").item(0).getTextContent());
		    		if(eElement.getElementsByTagName("phone").item(0)!=null)
		    		jobj.put("phoneNumber", eElement.getElementsByTagName("phone").item(0).getTextContent());
		    		
		    			
	    			uname = eElement.getElementsByTagName("username").item(0).getTextContent();
	    			pwd = eElement.getElementsByTagName("password").item(0).getTextContent();
		    		}
		    	}
		        
		    System.out.println(jobj);
		    System.out.println("Uname is: " + uname);
		    System.out.println("Pwd: " + pwd);
		    System.out.println("Username is: " + n);
		    System.out.println("Password is : " + p);
		    
		    
			    if(pwd.equals(p)){
			    	/*RequestDispatcher rd=request.getRequestDispatcher("welcome");  
			        rd.forward(request,response);
			        
			       */
			    	HttpSession session=request.getSession();
			    	session.setAttribute("userjson", jobj);
			    	session.setAttribute("username", uname);
			    	session.setAttribute("password", pwd);
			    	response.sendRedirect("patientpresicriptionform.jsp");
			    	}
			    else{
			    	HttpSession session=request.getSession();
			    	/*out.println(" <script> alert( 'Invalid credentials!') </script>");
			        
			        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			        rd.include(request,response);  
			       */
					session.setAttribute("error", "Invalid credentials!");
			    	response.sendRedirect("index.jsp");
			    }
		    } catch (Exception e) {
		    	e.printStackTrace();
		        }
		    }
		    else{
		    	
		    		file =new File(prop.getProperty("supportfolder"));
				    if(!file.exists()){file.mkdir();}
				    
				    
				    //String filepath = "D:\\SampleWorkspace" + System.getProperty("file.separator")+ n + ".xml";
				     filepath = file + System.getProperty("file.separator") + n + ".xml";
				    System.out.println("filepath: " +filepath);
				    file = new File(filepath);
				     path = Paths.get(filepath);
				   
				     
				     if(Files.exists(path)){
					    	System.out.println("Support already exists!");
					    	
					    try {
					    	
					    	File fXmlFile = new File(filepath);
					    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					    	Document doc = dBuilder.parse(fXmlFile);
					
					    	doc.getDocumentElement().normalize();
					
					    	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
					    	NodeList nList = doc.getElementsByTagName("node");
					    	JSONObject jobj=new JSONObject();
					    	for (int temp = 0; temp < nList.getLength(); temp++) {
								Node nNode = nList.item(temp);
								System.out.println("\nCurrent Element :" + nNode.getNodeName());
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
				    			System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
				    			System.out.println("User Name : " + eElement.getElementsByTagName("username").item(0).getTextContent());
				    			System.out.println("Password : " + eElement.getElementsByTagName("password").item(0).getTextContent());
				    			System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
				    			jobj.put("firstname", eElement.getElementsByTagName("firstname").item(0).getTextContent());
					    		jobj.put("lastname", eElement.getElementsByTagName("lastname").item(0).getTextContent());
					    		jobj.put("username", eElement.getElementsByTagName("username").item(0).getTextContent());
					    		jobj.put("password", eElement.getElementsByTagName("password").item(0).getTextContent());
					    		jobj.put("email", eElement.getElementsByTagName("email").item(0).getTextContent());
					    		
					    		
					    			
				    			uname = eElement.getElementsByTagName("username").item(0).getTextContent();
				    			pwd = eElement.getElementsByTagName("password").item(0).getTextContent();
					    		}
					    	}
					        
					    System.out.println(jobj);
					    System.out.println("Uname is: " + uname);
					    System.out.println("Pwd: " + pwd);
					    System.out.println("Username is: " + n);
					    System.out.println("Password is : " + p);
					    
					    
						    if(pwd.equals(p)){
						    	/*RequestDispatcher rd=request.getRequestDispatcher("welcome");  
						        rd.forward(request,response);
						        
						       */
						    	HttpSession session=request.getSession();
						    	session.setAttribute("userjson", jobj);
						    	session.setAttribute("supportpassword", pwd);
						    	session.setAttribute("supportuser", uname);
						    	response.sendRedirect("supportform.jsp");
						    	}
						    else{
						    	
						    	 HttpSession session=request.getSession();
				    	/*out.println(" <script> alert( 'Invalid credentials!') </script>");
				        
				        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
				        rd.include(request,response);  
				       */
						session.setAttribute("error", "Invalid credentials!");
				    	response.sendRedirect("index.jsp");
				    	
				        }
					    } catch (Exception e) {
					    	e.printStackTrace();
					        }
					    }
		    	
				   
		    }  
		          
		    out.close();
		    } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }  
	if(request.getParameter("registerButton")!=null){
		 RequestDispatcher rd=request.getRequestDispatcher("register.html");  
	     rd.include(request,response); 
	}
 }	 

}