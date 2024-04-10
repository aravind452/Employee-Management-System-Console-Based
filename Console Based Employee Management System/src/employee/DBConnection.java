package employee;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	
	static Connection con;
	
	public static Connection createDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/employee2";
			String userName = "root";
			String password = "aravind";
			con =  DriverManager.getConnection(url, userName, password);
			
					
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}
	
	
	
}
