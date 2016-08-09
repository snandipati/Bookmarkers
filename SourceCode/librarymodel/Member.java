/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymodel;

/*import the packages for using the classes in them into the program*/
import java.sql.*;

/**
 *
 * @author Silpa
 */
/* This class represents the member table for the Automated Library Assistant System */
public class Member {
    
    /***    Variables Declaration    ***/
        private Connection connection = null;
        private Statement statement = null;
        private ResultSet resultSet = null;

        private String memberID;
        private String name;
        private String phoneNo;
        private String email;
        private String password;
        private String address;
        private java.sql.Date startDate;
        private double fine;
        private int noOfFicBooks, noOfNonFicBooks, noOfVideos;

        //for creating an object

        //private Transaction transaction;


        public Member() {
            }

        public String getMemberID(){
            return this.memberID;
        }

        public String getName(){
            return this.name;
        }

        public String getPhoneNo(){
            return this.phoneNo;
        }
    
        public String getPassword(){
            return this.password;
        }

       public String getAddress(){
            return this.address;
        }

       public String getEmail(){
            return this.email;
        }

        public Date getStartDate(){
            return this.startDate;
        }

        public double getFine(){
            return this.fine;
        }

        public int getNoOfFicBooks(){
            return this.noOfFicBooks;
        }

        public int getNoOfNonFicBooks(){
            return this.noOfNonFicBooks;
        }

        public int getNoOfVideos(){
            return this.noOfVideos;
        }

        public int selectMember(String Query) {
                int size = 0;
                    /***************************************************************
                     * for making the connection,creating the statement and update *
                     * the table in the database. After that,closing the statement *
                     * and connection. There is catch block SQLException for error *
                     ***************************************************************/
		try {
                        connection = DBMSConnectivity.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				memberID = resultSet.getString(2);
				name = resultSet.getString(3);
				phoneNo = resultSet.getString(4);
				email = resultSet.getString(5);
				address = resultSet.getString(6);
                                startDate = resultSet.getDate(7);
				fine = resultSet.getDouble(8);
                                noOfFicBooks = resultSet.getInt(9);
                                noOfNonFicBooks = resultSet.getInt(10);
                                noOfVideos = resultSet.getInt(11);
                                size++;
			}
			                        
                        resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("Member.java\n" + SQLe.toString());
                        SQLe.printStackTrace();
                }
                return size;
        }

        public boolean updateMember(String Query) {

                    /***************************************************************
                     * for making the connection,creating the statement and update *
                     * the table in the database. After that,closing the statement *
                     * and connection. There is catch block SQLException for error *
                     **************************************************************/
                try {
                        connection = DBMSConnectivity.getConnection();
                        statement = connection.createStatement();
                        statement.executeUpdate(Query);
                        statement.close();
                        connection.close();
                        return true;
		}
		               
                catch (SQLException SQLe) {
			System.out.println("Member.java\n" + SQLe.toString());
                        SQLe.printStackTrace();
                        return false;
                }
                
	}
        
        public int selectMaxMember(String Query) {
		int memberID = 0;
                try {
                    connection = DBMSConnectivity.getConnection();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(Query);
                    while (resultSet.next()) {
                        memberID = resultSet.getInt(1);
                    }
                    statement.close();
                    connection.close();
                      
		}
		/* A catch block to catch SQL Exception */
                catch (SQLException SQLe) {
			System.out.println("Member.java\nError:" + SQLe.toString());
                        SQLe.printStackTrace();
                }
                System.out.println(memberID);
                return memberID;
        }
        
        
}
