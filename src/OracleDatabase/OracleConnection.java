package OracleDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection {
	Connection con = null; 
	public Connection Connect()
	{
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.10:1521:erpdb","nx_developer","nx_developer");
		return con;
		}catch(Exception e) {e.printStackTrace();
		return null;}
	}
}
