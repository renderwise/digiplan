package api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Serialnumber implements Serializable {

	public String serialnumber() throws ParserConfigurationException, IOException{
		
		Properties prop = new Properties();
	    InputStream input = null;
	    //File propertyFile = new File("config.properties");
	    input = this.getClass().getResourceAsStream("config.properties");
	    prop.load(input);
	    System.out.println(prop.getProperty("database"));
	    File file =new File(prop.getProperty("appfolder"));
	    if(!file.exists()){file.mkdir();}
	   
	    String filepath = file + System.getProperty("file.separator") +"Serial.xml";
	    System.out.println("filepath: " +filepath);
	    file = new File(filepath);
	    Path path = Paths.get(filepath);
	    int i = 0;
	    if(Files.exists(path)){
	    	System.out.println("File!");
	    	
	    try {
	    	
	    	File fXmlFile = new File(filepath);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	
	    	doc.getDocumentElement().normalize();
	
	    	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
	    	NodeList nList = doc.getElementsByTagName("serial");
	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("Serial : " + eElement.getElementsByTagName("serialnumber").item(0).getTextContent());
				i=Integer.parseInt(eElement.getElementsByTagName("serialnumber").item(0).getTextContent());
				System.out.println(i);
	    			//jeElement.getElementsByTagName("password").item(0).setTextContent(newpassword);
	    		eElement.getElementsByTagName("serialnumber").item(0).setTextContent( (i+1)+"");
		    	System.out.println("Serial : " + eElement.getElementsByTagName("serialnumber").item(0).getTextContent());
	    		}
	    	}
	        
	    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");

	   
	    
	    
		
		
	}catch(Exception e){}
	
	    }
		return i+"";
	}
	
	
public static void main(String[] args) throws ParserConfigurationException, IOException {
	System.out.println(new Serialnumber().serialnumber());
}
	}