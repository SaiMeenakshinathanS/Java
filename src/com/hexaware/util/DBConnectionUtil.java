package com.hexaware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

	public static Connection getDBConnection(String cString,String userName,String password) {
		Connection con=null;
		
		try {
			String cName=DBPropertyUtil.getClassName("resources\\Application.properties");
			Class.forName(cName);
			con=DriverManager.getConnection(cString,userName,password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
