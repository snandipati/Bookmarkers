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
/* This class represents the item table for the Automated Library Assistant System */
public class Item {
    
    /***    Variables Declaration    ***/

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private String itemNumber;
	private String title;
        private String author;
	private String status;
        private String itemType;
	private double price;
        private String category;
	
	private int shelfNumber;
	private Date acquireDate;
              
        /* Default Constructor */   
	public Item() {
	}
        
        /* A get method */
	public String getItemNumber() {
		return itemNumber;
	}
        
        /* A get method */
	public String getTitle() {
		return title;
	}
        
        /* A get method */
	public String getAuthor() {
		return author;
	}
        
        /* A get method */
        public String getStatus() {
		return status;
	}
        
        /* A get method */
        public String getItemType() {
		return itemType;
	}
        
        /* A get method */
        public double getPrice() {
		return price;
	}
        
        /* A get method */	       
        public int getShelfNumber() {
		return shelfNumber;
	}
        
	/* A get method */
        public Date getAcquireDate() {
		return acquireDate;
	}
        
        /* A get method */
        public String getCategory() {
		return category;
	}

        /* A method to make connection, create the statement and getting result 
         * from database table
         */
	public int selectItem(String Query) {
		int row = 0;
		/* A try block */
                try {
			connection = DBMSConnectivity.getConnection();
                    	statement = connection.createStatement();
			resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				itemNumber = resultSet.getString(1);
				title = resultSet.getString(2);
				author = resultSet.getString(3);
				price = resultSet.getDouble(4);
                                status = resultSet.getString(5);
                                itemType = resultSet.getString(6);				
				shelfNumber = resultSet.getInt(7);
                                acquireDate = resultSet.getDate(8);
                                category = resultSet.getString(9);
                                row++;
                        }
			
                        resultSet.close();
			statement.close();
			connection.close();
		}
                /* A catch block to catch SQL Exception */
		catch (SQLException SQLe) {
			System.out.println("Item.java\n" + SQLe.toString());
                        SQLe.printStackTrace();
		}
                return row;
	}

        /* A method to make connection, create the statement and update rows 
         * into database table
         */
	public void updateItem(String Query) {
		
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
			System.out.println("Item.java\nError:" + SQLe.toString());
                        SQLe.printStackTrace();
                }
	}
}
