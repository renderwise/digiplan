package api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetSerialNumber {

protected  String generateSerial(String cityCode) throws IOException, ParseException {
	// TODO Auto-generated method stub
	 Properties prop = new Properties();
	    InputStream input = null;
	    //File propertyFile = new File("config.properties");
	    input = this.getClass().getResourceAsStream("config.properties");
	    prop.load(input);
	    System.out.println(prop.getProperty("appfolder"));
	    File file =new File(prop.getProperty("appfolder")+"/GetSerial.txt");
	    System.out.println(file.getAbsolutePath());
	    
	    JSONParser jParse= new JSONParser();
	    String jsonString= new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
	    JSONObject serialJson= (JSONObject)jParse.parse(jsonString);
	    System.out.println(serialJson);
	    
	    Calendar today=Calendar.getInstance();
	    String currentMonth=null;
	    int currentMonthInt=today.get(Calendar.MONTH);
	    
	    if(currentMonthInt<10){
	    	currentMonth="0"+(currentMonthInt+1);
	    }
	    else{
	    	currentMonth=""+(currentMonthInt+1);
	    }
	    System.out.println(currentMonth);
	    
	    String monthInSerialJson=(String)serialJson.get("month");
	    
	    if(monthInSerialJson.equals(currentMonth)){
	    
	    JSONObject getSerialPerCityCode=(JSONObject) serialJson.get("serialPerCityCode");
	    String serialPerCityCode= (String) getSerialPerCityCode.get(cityCode);
	   
	    if(serialPerCityCode==null){
	    	getSerialPerCityCode.put(cityCode, "901");
	    	serialJson.put("serialPerCityCode", getSerialPerCityCode);
	    }
	    else{
	    	Integer serialPerCityCodeInt=Integer.parseInt(serialPerCityCode);
	    	System.out.println(serialPerCityCodeInt);
	    	String generatedSerialPerCityCode=(serialPerCityCodeInt+1)+"";
	    	int looplength=3-(generatedSerialPerCityCode).length();
			for(int k=0;k<looplength;k++){
					generatedSerialPerCityCode="0"+generatedSerialPerCityCode;
			}
			
			getSerialPerCityCode.put(cityCode, generatedSerialPerCityCode);
			serialJson.put("serialPerCityCode", getSerialPerCityCode);
	    	//getSerialPerCityCode.put(cityCode, );
	    }
	    }
	    else {
	    	JSONObject newMonthJson=new JSONObject();
	    	newMonthJson.put(cityCode, "901");
			serialJson.put("serialPerCityCode", newMonthJson);
			serialJson.put("month", currentMonth);
		}
	    System.out.println(serialJson);
	    
	    
	    FileWriter fw= new FileWriter(file);
		fw.write(serialJson.toJSONString());
		fw.flush();
		fw.close();
		return (String) ((JSONObject)serialJson.get("serialPerCityCode")).get(cityCode);
	    
}
	
}
