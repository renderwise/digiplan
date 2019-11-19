package api;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableClass {

	public static void main(String[] args) throws SQLException {
		
		Connection con= ConnectionJDBC.DBconnect();
		Statement s= con.createStatement();
		
		String sql="Create table alignwise_basicinfo(\r\n" + 
				"CaseID nvarchar2(20) primary key, \r\n" + 
				"DoctorName nvarchar2(20),\r\n" + 
				"PhoneNumber nvarchar2(20),\r\n" + 
				"TreatingDoctor nvarchar2(20),\r\n" + 
				"TreatingDoctorPhone nvarchar2(20),\r\n" + 
				"ClinicAddress CLOB,\r\n" + 
				"City nvarchar2(20),\r\n" + 
				"ClinicEmail nvarchar2(50)\r\n" + 
				")";
		
		s.executeUpdate(sql);
		ConnectionJDBC.DBDisconnect(con);
		
	}
	
}
