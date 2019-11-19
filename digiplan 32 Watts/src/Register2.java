import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Register2 extends HttpServlet {
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

		Properties prop = new Properties();
		InputStream input = null;
		// File propertyFile = new File("config.properties");
		input = this.getClass().getResourceAsStream("config.properties");
		prop.load(input);
		System.out.println(prop.getProperty("database"));
		File file = new File(prop.getProperty("appfolder"));
		if (!file.exists()) {
			file.mkdir();
		}

		file = new File(prop.getProperty("userfolder"));
		String filepath = file + System.getProperty("file.separator") + uname + ".xml";
		Path path = Paths.get(filepath);

		if (Files.exists(path) || (uname.equals("")) || (uname.equals(null))) {
			out.println(" <script> alert( 'Username is invalid or the user already exists!') </script>");
			RequestDispatcher rd = request.getRequestDispatcher("adminpanel.jsp");
			rd.include(request, response);
			return;
		}

		file = new File(prop.getProperty("adminfolder"));
		filepath = file + System.getProperty("file.separator") + uname + ".xml";
		path = Paths.get(filepath);

		if (Files.exists(path) || (uname.equals("")) || (uname.equals(null))) {
			out.println(" <script> alert( 'Username is invalid or the user already exists!') </script>");
			RequestDispatcher rd = request.getRequestDispatcher("adminpanel.jsp");
			rd.include(request, response);
			return;
		}

		file = new File(prop.getProperty("supportfolder"));
		filepath = file + System.getProperty("file.separator") + uname + ".xml";
		path = Paths.get(filepath);

		if (Files.exists(path) || (uname.equals("")) || (uname.equals(null))) {
			out.println(" <script> alert( 'Username is invalid or the user already exists!') </script>");
			RequestDispatcher rd = request.getRequestDispatcher("adminpanel.jsp");
			rd.include(request, response);
			return;
		}

		if (typeofuser.equals("normal"))
			file = new File(prop.getProperty("userfolder"));
		else if (typeofuser.equals("admin"))
			file = new File(prop.getProperty("adminfolder"));
		else if (typeofuser.equals("support"))
			file = new File(prop.getProperty("supportfolder"));
		if (!file.exists()) {
			file.mkdir();
		}

		filepath = file + System.getProperty("file.separator") + uname + ".xml";
		System.out.println("filepath: " + filepath);
		file = new File(filepath);
		// File file = new File(filepath);

		path = Paths.get(filepath);

		if (Files.exists(path) || (uname.equals("")) || (uname.equals(null))) {
			out.println(" <script> alert( 'Username is invalid or the user already exists!') </script>");
			RequestDispatcher rd = request.getRequestDispatcher("adminpanel.jsp");
			rd.include(request, response);
		}

		if ((Files.notExists(path)) && (!uname.equals("")) && (!uname.equals(null))) {
			System.out.println("uname -> " + uname);

			try {
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
				// creating a new instance of a DOM to build a DOM tree.
				Document doc = (Document) docBuilder.newDocument();

				new Register2().createXmlTree(doc, filepath, file, fname, lname, uname, pwd, email, city, phone);

				try {
					if (typeofuser.equals("normal"))
						Files.write(Paths.get(prop.getProperty("appfolder") + "/Doctorlist.txt"),
								new String("," + fname + " " + lname).getBytes(), StandardOpenOption.APPEND);
				} catch (IOException e) {
					// exception handling left as an exercise for the reader
				}

				out.println(" <script> alert( 'Xml File Created Successfully') </script>");

				RequestDispatcher rd = request.getRequestDispatcher("adminpanel.jsp");
				rd.include(request, response);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public void createXmlTree(Document doc, String filepath, File file, String fname, String lname, String uname,
			String pwd, String email, String city, String phone) throws Exception {

		// This method creates an element node
		Element root = doc.createElement("root");
		// adding a node after the last child node of the specified node.
		doc.appendChild(root);

		Element child = doc.createElement("node");
		root.appendChild(child);

		Element element1 = doc.createElement("firstname");
		child.appendChild(element1);

		Text text = doc.createTextNode(fname);
		element1.appendChild(text);

		Element element2 = doc.createElement("lastname");
		child.appendChild(element2);

		Text text1 = doc.createTextNode(lname);
		element2.appendChild(text1);

		Element element3 = doc.createElement("username");
		child.appendChild(element3);

		Text text2 = doc.createTextNode(uname);
		element3.appendChild(text2);

		Element element4 = doc.createElement("password");
		child.appendChild(element4);

		Text text3 = doc.createTextNode(pwd);
		element4.appendChild(text3);

		Element element5 = doc.createElement("email");
		child.appendChild(element5);

		Text text4 = doc.createTextNode(email);
		element5.appendChild(text4);

		Element element6 = doc.createElement("city");
		child.appendChild(element6);

		Text text5 = doc.createTextNode(city);
		element6.appendChild(text5);

		Element element7 = doc.createElement("phone");
		child.appendChild(element7);

		Text text6 = doc.createTextNode(phone);
		element7.appendChild(text6);

		/*
		 * Element chilE = doc.createElement("Id"); chilE.setAttribute("name",
		 * "Vineet"); root.appendChild(chilE);
		 * 
		 * 
		 * Text text12 = doc.createTextNode("status");
		 * chilE.appendChild(text12);
		 */

		// TransformerFactory instance is used to create Transformer objects.
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = sw.toString();

		/*
		 * String filepath = "D:\\SampleWorkspace" +
		 * System.getProperty("file.separator")+ uname + ".xml";
		 * System.out.println("filepath: " +filepath); File file = new
		 * File("D:\\SampleWorkspace\\newxml.xml"); //File file = new
		 * File(filepath);
		 * 
		 * Path path = Paths.get(filepath); if(Files.exists(path)){
		 * System.out.println("User already exists!"); }
		 */

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		bw.write(xmlString);
		bw.flush();
		bw.close();
		System.out.println("User created!");

	}

}