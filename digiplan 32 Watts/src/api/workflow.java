package api;
import java.sql.*;

public class workflow {

	public static void dataAdd(String caseid,String patientname,String doctorname,String clinicaddress) {
		
		Connection con=null;
		ResultSet rs=null;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");  
	    	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/amit1111","root","root");
			
		
	    	    PreparedStatement ps=null;
		
			
			ps=con.prepareStatement("insert into case_mstr(prerequisite,case_number,clinic_address,gp,city,patient_age,type_of_account,referred_by,channel,corporate_account,kol,discount,no_of_aligners,starter_kit,scanning,pickup,dispatch,dispatch_to,advance_starter,advance_scanning, scanning_service,dispatch_date,person_dispatch,person_scan,bill_to,gst_number,payment_mode,wo_number,wo_date,priority,no_of_stages,current_stage,no_of_aligners_u_a,no_of_aligners_u_p,no_of_aligners_l_a,no_of_aligners_l_p,attachment_applicable,dispatch_grid,data_grid,dispatch_date2,final_amount,status_grid,payment_grid,batch_number,work_date,next_work,next_work_date,case_review,refinement_package,crmname,decision,user_id,stage,next_stage,case_id,initiated_date,created_date,registered_doctor,clinic_name,patient_name,draftflag) values('','"+caseid+"','"+clinicaddress+"','','','','','','','','','','','','','','','','','','','','','','','','','','','5','','','','','','','','','','','','','','','','','','','','','decs22','Initiation Stage','Start Stage','workorder',"+caseid+",sysdate(),sysdate(),'"+doctorname+"','"+clinicaddress+"','"+patientname+"','N')");
			ps.execute();
			
			
			
			//INSERT INTO CASE_MSTR_HISTORY (CASE_ID,USER_ID,initiated_by,inserted_date,initiated_Date,DECISION, STATUS, STAGE, NEXT_STAGE,prerequisite,
			//case_number,registered_doctor,clinic_name,clinic_address,gp,city,patient_name,patient_age,type_of_account,referred_by,channel,corporate_account,
			//kol,discount,no_of_aligners,starter_kit,scanning,pickup,dispatch,dispatch_to,advance_starter,advance_scanning, scanning_service,dispatch_date,
			//person_dispatch,person_scan,bill_to,gst_number,payment_mode,wo_number,wo_date,priority,created_date,no_of_stages,current_stage,
			//no_of_aligners_u_a,no_of_aligners_u_p,no_of_aligners_l_a,no_of_aligners_l_p,attachment_applicable,dispatch_grid,data_grid,dispatch_date2,
			//final_amount,status_grid,payment_grid,batch_number,work_date,next_work,next_work_date,case_review,refinement_package,crmname
			
			
			//)select CASE_ID,USER_ID,initiated_by,sysDate(),initiated_Date,DECISION, STATUS, STAGE, NEXT_STAGE,prerequisite,case_number,registered_doctor,clinic_name,clinic_address,gp,city,patient_name,patient_age,type_of_account,referred_by,channel,corporate_account,kol,discount,no_of_aligners,starter_kit,scanning,pickup,dispatch,dispatch_to,advance_starter,advance_scanning, scanning_service,dispatch_date,person_dispatch,person_scan,bill_to,gst_number,payment_mode,wo_number,wo_date,priority,created_date,no_of_stages,current_stage,no_of_aligners_u_a,no_of_aligners_u_p,no_of_aligners_l_a,no_of_aligners_l_p,attachment_applicable,dispatch_grid,data_grid,dispatch_date2,final_amount,status_grid,payment_grid,batch_number,work_date,next_work,next_work_date,case_review,refinement_package,crmname from CASE_MSTR where CASE_ID="+caseid; 
					
			ps=con.prepareStatement("insert into decision_history values('Case Initiated','Started',sysdate(),'New Case','Digiplan',"+caseid+")");
		
			ps.execute();
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
}
