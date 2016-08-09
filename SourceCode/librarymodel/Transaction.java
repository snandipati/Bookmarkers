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

/* This class represents the transaction table for the Automated Library Assistant System */
public class Transaction {

    /***    Variables Declaration    ***/
    
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private int transactionID;
        private String itemNumber;
        private String itemTitle;
	private String memberID;
        private String transactionType;
	private Date startDate;
	private Date endDate;
	private double finesPaid;
        private double sum = 0.0;
         
	/* Default Constructor */
        public Transaction() {
	}

	/* A get method */
        public int getTransactionID() {
		return transactionID;
	}
        
        /* A get method */
        public String getItemNumber() {
		return itemNumber;
	}
        
        /* A get method */
        public String getItemTitle() {
		return itemTitle;
	}
        
        /* A get method */
        public String getTransactionType() {
		return transactionType;
	}
        
	/* A get method */
        public String getMemberID() {
		return memberID;
	}

	/* A get method */
        public java.util.Date getStartDate() {
		return startDate;
	}

	/* A get method */
        public java.util.Date getEndDate() {
		return endDate;
	}
        
        /* A get method */
        public double getFinesPaid() {
		return finesPaid;
	}
        
	/* A method to make connection, create the statement and getting result 
         * from database table
         */
        public int selectTransaction(String Query) {
		int size = 0;
		/* A try block */
		try {
                    connection = DBMSConnectivity.getConnection();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(Query);
                    while (resultSet.next()) {
				transactionID = resultSet.getInt(1);
                                memberID = resultSet.getString(2);
                                itemNumber = resultSet.getString(3);
                                itemTitle = resultSet.getString(4);
                                startDate = resultSet.getDate(5);
				endDate = resultSet.getDate(6);
                                transactionType = resultSet.getString(7);
                                finesPaid = resultSet.getDouble(8);
                                size++;
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
		}
		/* A catch block to catch SQL Exception */
                catch (SQLException SQLe) {
                    System.out.println("Transaction.java\n" + SQLe.toString());
                    SQLe.printStackTrace();
                }
                return size;
	}

        
	/* A method to make connection, create the statement and update rows 
         * into database table
         */        
        public void updateTransaction(String Query) {
		/* A try block */
		try {
                    connection = DBMSConnectivity.getConnection();
                    statement = connection.createStatement();
                    statement.executeUpdate(Query);
                    statement.close();
                    connection.close();
		}
		/* A catch block to catch SQL Exception */
                catch (SQLException SQLe) {
			System.out.println("Transaction.java\n" + SQLe.toString());
                        SQLe.printStackTrace();
                }
	}
        
        
        /* A method to make connection, create the statement and find sum of 
         * a column of a database table
         */         
        public double findSum(String Query){
                
                /* A try block */
                try {
                    connection = DBMSConnectivity.getConnection();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(Query);
                    while (resultSet.next()) {
                        sum = resultSet.getDouble(1);
                    }
                    statement.close();
                    connection.close();
                      
		}
		/* A catch block to catch SQL Exception */
                catch (SQLException SQLe) {
			System.out.println("Transaction.java\nError:" + SQLe.toString());
                        SQLe.printStackTrace();
                }
                System.out.println(sum);
                return sum;
        
        }   
    
}
