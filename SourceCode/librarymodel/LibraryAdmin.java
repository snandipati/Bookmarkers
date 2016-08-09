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
/* This class represents the library admin table for the Automated Library Assistant System */
public class LibraryAdmin {
    
    
    /***    Variables Declaration    ***/
    
        private Connection connection = null;
        private Statement statement = null;
        private ResultSet resultSet = null;
        
        private String login;
        private String password;
       
      
        /*  Default Constructor */
        public LibraryAdmin(){

        }
        
        /* A get method */
        public String getLogin(){
            return login;
        }
        
        /* A get method */
        public String getPassword(){
            return password;
        }
        
        /* A method to make connection, create the statement and getting result from
         * database table
         */
        public int selectLibraryAdmin(String Query) {
                    
                    int row = 0;
                    /* A try block */
                    try {
                            connection = DBMSConnectivity.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(Query);
                            while (resultSet.next()) {
                                    login = resultSet.getString(1);
                                    password = resultSet.getString(2);
                                    row++;
                            }

                            resultSet.close();
                            statement.close();
                            connection.close();
                    }
                    /* A catch block to catch SQL Exception */
                    catch (SQLException SQLe) {
                            System.out.println("LibraryAdmin.java\n" + SQLe.toString());
                            SQLe.printStackTrace();
                    }
                    return row;
            }
            
            /* A method to make connection, create the statement and update rows into
            * database table
            */
            public void updateLibraryAdmin(String Query) {

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
                            System.out.println("LibraryAdmin.java\nError:" + SQLe.toString());
                            SQLe.printStackTrace();
                    }
            }
    
    
    
}
