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
/* This class represents the reserveList table for the Automated Library Assistant System */
public class ReserveList {
     
    /***    Variables Declaration    ***/   
    
        private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private String itemNumber;
	private String memberID;
        private String memberEmail;
        private Date startDate;
	
                
        /* Default Constructor */
	public ReserveList() {
	}
        
        /* A get method */
	public String getItemNumber() {
		return itemNumber;
	}
        
        /* A get method */
	public String getMemberID() {
		return memberID;
	}
        
        /* A get method */
        public String getMemberEmail() {
		return memberEmail;
	}
        
        /* A get method */
	public Date getstartDate() {
		return startDate;
	}
        
       
        
        /* A method to make connection, create the statement and getting result 
        * from database table
        */ 
	public int selectReserve(String Query) {
		int row = 0;
	        /* A try block */
                try {
			connection = DBMSConnectivity.getConnection();
                    	statement = connection.createStatement();
			resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				itemNumber = resultSet.getString(1);
				memberID = resultSet.getString(2);
				memberEmail = resultSet.getString(3);
                                startDate = resultSet.getDate(4);
				row++;
                        }
			
                        resultSet.close();
			statement.close();
			connection.close();
		}
                /* A catch block to catch SQL Exception */
		catch (SQLException SQLe) {
			System.out.println("Reserve.java\n" + SQLe.toString());
                        SQLe.printStackTrace();
		}
                return row;
	}

        /* A method to make connection, create the statement and update rows 
         * into database table
         */
	public void updateReserve(String Query) {
		/* A try block */
		try {
			connection = connection = DBMSConnectivity.getConnection();	
                    	statement = connection.createStatement();
			statement.executeUpdate(Query);
                        statement.close();
			connection.close();
		}
                /* A catch block to catch SQL Exception */
		catch (SQLException SQLe) {
			System.out.println("Reserve.java\nError:" + SQLe.toString());
                        SQLe.printStackTrace();
                }
	}
}
