package com.hexaware.util;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBPropertyUtil {
	public static String getConnectionString(String fileName) {
		Properties p=new Properties();
		FileInputStream fis=null;
		
		try {
			fis = new FileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return p.getProperty("url.prefix") 
				//+p.getProperty("username")+":"
				//+p.getProperty("password")+"@"
			    +p.getProperty("dbserver") + ":" 
				+ p.getProperty("port") + "/"
				+ p.getProperty("dbname");
	}
	
	public static String getClassName(String fileName) {
		Properties p=new Properties();
		FileInputStream fis=null;
		
		try {
			fis = new FileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p.getProperty("driver-class-name");
	}
	
	public static String getUserName(String fileName) {
		Properties p=new Properties();
		FileInputStream fis=null;
		
		try {
			fis = new FileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return p.getProperty("username");
	}
	
	public static String getPassword(String fileName) {
		Properties p=new Properties();
		FileInputStream fis=null;
		
		try {
			fis = new FileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return p.getProperty("password");
	}
}
