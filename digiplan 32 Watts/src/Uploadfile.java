

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;

import javassist.bytecode.ByteArray;


/**
 * Servlet implementation class Uploadfile
 */
@WebServlet("/Uploadfile")
public class Uploadfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploadfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String foldername=createdir(request);
		System.out.println("foldercreated "+foldername);
		String uploadfile=putfile(foldername);
		if(adminname!=null){
			System.out.println("redirect to "+"adminpanel.jsp?refno="+refid+"#!/");
			response.sendRedirect("adminpanel.jsp?refno="+refid+"#!/");
		}
		else if(supportname!=null) {
			System.out.println("redirect to "+"supportform.jsp?refno="+refid+"#!/");
			response.sendRedirect("supportform.jsp?refno="+refid+"#!/");	
		}
		
		else {
			System.out.println("redirect to "+"patientpresicriptionform.jsp?refno="+refid+"#!/");
			response.sendRedirect("patientpresicriptionform.jsp?refno="+refid+"#!/");	
		}
		
		
	}
	
	 public String putfile(String foldername) throws IOException {
		 
		 File folder = new File(tempfolder);
		 File[] listOfFiles = folder.listFiles();

		     for (int i = 0; i < listOfFiles.length; i++) {
		       if (listOfFiles[i].isFile()) {
		         System.out.println("File " + listOfFiles[i].getName());
		         FileUtils.copyFileToDirectory(listOfFiles[i].getAbsoluteFile(), new File(foldername));
		         
		       } else if (listOfFiles[i].isDirectory()) {
		         System.out.println("Directory " + listOfFiles[i].getName());
		       }
		     }
		     
		     FileUtils.deleteDirectory( new File( tempfolder));
		     
		return foldername;
			// TODO Auto-generated method stub		
		}
	 String tempfolder;
	 String adminname=null;
	 String supportname=null;
	 String refid=null;
	 File path ;
	 public String createdir(HttpServletRequest request) throws ServletException, IOException {
			// TODO Auto-generated method stub
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	        if (isMultipart) {
	        	Properties prop = new Properties();
			    InputStream input = null;
			    //File propertyFile = new File("config.properties");
			    input = this.getClass().getResourceAsStream("config.properties");
			    prop.load(input);
			    System.out.println(prop.getProperty("database"));
			   tempfolder=prop.getProperty("tempfolder");
	            // Create a factory for disk-based file items
	            FileItemFactory factory = new DiskFileItemFactory();
	            File uploadedFile = null;
	            // Create a new file upload handler
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            
	            String foldername="";
	            File file=null;
	            try {
	                // Parse the request
	            	List items = upload.parseRequest(new ServletRequestContext(request));
	                Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
	                    if (!item.isFormField()) {
	                    	System.out.println("we are here");
	                    	
	                    	
	                    	String fileName = item.getName();    
	              	      	System.out.println(fileName);
	              	      path = new File(tempfolder);
		              	    if (!path.exists()) {
	                            boolean status = path.mkdir();
	                            System.out.println("this is the status "+status);
	                            
	                        }  
	              	      path = new File(tempfolder+ "/"+fileName);
	              	      FileOutputStream fo=new FileOutputStream(path);
	              	      System.out.println("this is the path "+path);
	              	      
	              	      
	              	      
	              	      
	              	      fo.flush();
	              	      fo.close();
	              	      
	                        System.out.println("transferring");
	                         item.write(path);
	                         System.out.println("transferred");
	                    }
	                    else if(item.isFormField()){
	                    	System.out.println("idhar bhi agya bhai");
	                    
	                    	
	                    	String itemname = item.getFieldName();
	                    	if(itemname.equals("foldername")){
	                    		foldername=item.getString();
	                    		 file =new File(prop.getProperty("appfolder"));
	            			    if(!file.exists()){file.mkdir();}
	            			    file =new File(prop.getProperty("uploadfolder"));
	            			    if(!file.exists()){file.mkdir();}
	            			    file =new File(prop.getProperty("uploadfolder")+"/"+foldername);
	            			    if(!file.exists()){file.mkdir();}
	                    	}
	                    	if(itemname.equals("adminname")){
	                    		adminname=item.getString();
	                    	}
	                    	if(itemname.equals("supportname")){
	                    		supportname=item.getString();
	                    	}
	                    	if(itemname.equals("serialnumber")){
	                    		refid=item.getString();
	                    	}
	                    	
	                    	
	                    }
	                }
	                return file.getAbsolutePath();
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
	        }
			return null;
		}

	
}
