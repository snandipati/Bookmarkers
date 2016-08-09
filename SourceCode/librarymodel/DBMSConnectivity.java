/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymodel;

import java.sql.*;
/**
 *
 * @author Silpa
 */
/* This class is used to connect to mysql database */
public class DBMSConnectivity {
      
    public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Pallavi@21");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DBMSConnectivity.java\n" + cnfe.toString());
		}
		catch (Exception e) {
			System.out.println("DBMSConnectivity.java\n" + e.toString());
		}
		return conn;
        }  
}