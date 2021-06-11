package AllinusModel;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection getConnection() throws Exception{
		try{
			String Driver = "com.mysql.jdbc.Driver";
			String url ="jdbc:mysql://localhost:3306/chatproject";
			String admin ="rodriguez";
			String password = "250389";
			Class.forName(Driver);
			Connection conn = DriverManager.getConnection(url,admin,password);
			return conn;
		}catch(Exception ex){
			System.out.println("Error: "+ex);
		}
		return null;
	}
	
}
