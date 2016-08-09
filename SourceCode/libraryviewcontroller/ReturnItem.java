/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Locale;
import javax.swing.*;

/**
 *
 * @author Silpa
 */
/* This class is use to return an item in the Automated Library Assistant System */
public class ReturnItem extends JInternalFrame{
    
    /***    Variables Declaration    ***/
       
        /* creating the Panels */
        private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel southPanel = new JPanel();

        /* creating an Internal Panel in the center panel */
        private JPanel dataPanel = new JPanel();
        private JPanel returnButtonPanel = new JPanel();

        /* creating the label */
        private ImageIcon image;
        private JLabel northLabel;
        
        /* creating the North Label */
        private JLabel[] dataLabel = new JLabel[3];

        /* creating an array of String */
        private String[] dataString = { " Enter the Item Number : "," Enter the Member ID : ",
                                                  " The Current Date : "};
        /* creating an array of JTextField */
        private JTextField[] dataTextField = new JTextField[3];

        /* creating an array of string to store the data */
        private String[] data;
        
        /* creating the date in the String */
        private java.util.Date todayDate = new java.util.Date();
        private String inDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(todayDate);
        
        /* creating the buttons */
        private JButton returnButton = new JButton("Return");
        private JButton cancelButton = new JButton("Cancel");

        /* creating objects */
        private Item item;
        private Member member;
        private Transaction transaction;
        private ReserveList reserve;

        private double totalFine;
        
        /* A method to check the data from the text field */
        public boolean isDataCorrect() {
            data = new String[3];
            for (int i = 0; i < dataLabel.length; i++) {
                if (!dataTextField[i].getText().equals("")) {
                    data[i] = dataTextField[i].getText();
                } else {
                    return false;
                }
            }
            return true;
        }

        /* A method to set the array of JTextField to null */
        public void clearTextFields() {
            for (int i = 0; i < dataTextField.length - 1; i++) {
                dataTextField[i].setText(null);
            }
        }
             
        /* constructor of ReturnItem */
        public ReturnItem(){
            /* setting the title for the internal frame */
            super("Return an Item", false, true, false, true);
            /* setting the icon */
            setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Return12.png")));
            /* getting the content Pane */
            Container contentPane = getContentPane();

            /* setting the layout of Panels */
            northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            centerPanel.setLayout(new BorderLayout());
            dataPanel.setLayout(new GridLayout(4, 2, 1, 1));

             /* adding the image to the panel */
            image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/return_item.jpg"));
            northLabel = new JLabel(image);
            
            /* adding the label to the panel */
            northPanel.add(northLabel);
            
            /* adding the panel to the container */
            contentPane.add("North", northPanel);

            /* adding the strings to the labels and adding these labels to the panel */
            for (int i = 0; i < dataLabel.length; i++) {
                    dataPanel.add(dataLabel[i] = new JLabel(dataString[i]));
                    dataLabel[i].setFont(new Font("Arial", Font.BOLD, 12));
                    if (i == 2) {
                            dataPanel.add(dataTextField[i] = new JTextField(inDate));
                            dataTextField[i].setFont(new Font("Arial", Font.PLAIN, 12));
                            dataTextField[i].setEnabled(false);
                    }
                    else {
                            dataPanel.add(dataTextField[i] = new JTextField());
                            dataTextField[i].setFont(new Font("Arial", Font.PLAIN, 12));
                    }
            }

            centerPanel.add("Center", dataPanel);
            /* setting the layout */
            returnButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            /* adding the button */
            returnButtonPanel.add(returnButton);
            /* setting the font to the button */
            returnButton.setFont(new Font("Arial", Font.BOLD, 12));
            /* adding the internal panel to the panel */
            centerPanel.add("South", returnButtonPanel);
            /* setting the border */
            centerPanel.setBorder(BorderFactory.createTitledBorder("Return an Item:"));
            /* adding the center panel to the container */
            contentPane.add("Center", centerPanel);

            /* setting the layout */
            southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            /* adding the button */

            southPanel.add(cancelButton);
            /* setting the font to the button */
            cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
            /* setting the border */
            southPanel.setBorder(BorderFactory.createEtchedBorder());
            /* adding the south panel to the container */
            contentPane.add("South", southPanel);

            /* adding the action listener to the button */
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    //for checking if there is a missing information
                    if (isDataCorrect()) {
                        Thread runner = new Thread() {
                            @Override
                            public void run() {
                                    item = new Item();
                                    member = new Member();
                                    transaction = new Transaction();
                                    reserve = new ReserveList();
                                    
                                    int noItems = item.selectItem("SELECT * FROM Items WHERE itemNumber like '" + data[0]+"'");
                                    int noMembers = member.selectMember("SELECT * FROM Member WHERE MemberID like '" + data[1]+"'");
                                    int noReserveItems = reserve.selectReserve("SELECT * FROM ReserveList WHERE itemNumber like '" + data[0]+"'");
                                    int tranNo = transaction.selectTransaction("SELECT * FROM Transaction WHERE (itemNumber like '" + data[0]+"' AND (MemberID like '" + data[1]+"' AND TransactionType like 'Checked out'))");
                                   
                                  
                                    /* for checking if there same item in the database */
                                    if(noItems != 0)
                                    {
                                        // checking member id valid and transaction is done by the member
                                        if (noMembers != 0  && tranNo != 0 ){

                                            String currentStatus = item.getStatus();
                                            String email = reserve.getMemberEmail();
                                            java.util.Date dueDate = transaction.getEndDate();
                                            
                                            double price = item. getPrice();
                                            String type = item.getItemType();
                                            int noOfFicBooks = member.getNoOfFicBooks();    
                                            int noOfNonFicBooks = member.getNoOfNonFicBooks();
                                            int noOfVideos = member.getNoOfVideos();
                                            String title = item.getTitle();
                                            double fine = member.getFine();
                                            String message = " Dear Member," + "\n\nThe Item Number " + data[0] + " you have reserved is now available for pick up." + "\n\n Library Team";


                                            String newStatus = null;
                                            int flag = 0;   
                                            /*changing the new status to Available when it is checked out */
                                            if(currentStatus.equals("Checked out") ){
                                                     newStatus = "Available";  
                                                     flag = 1;
                                            }
                                            /*changing the new status to AvailableForReserved when reserved */
                                            else if (currentStatus.equals("Reserved") ){
                                                  newStatus = "On Hold";
                                                  flag = 2;
                                                  String[] to = {email};
                                                  EmailSender em = new EmailSender("bookmarkerslibrary@gmail.com", 
                                                                            "happyReading", message, to);
                                                  em.start();
                                                      // System.out.println("email sent");
                                                  //else
                                                      // System.out.println("error");
                                            }
                                            else{
                                                    JOptionPane.showMessageDialog(null, "The Item has not been checked out", "Warning", JOptionPane.WARNING_MESSAGE);

                                            }
                                            
                                            
                                            /* Return only when the book is checked out and/or reserved by other member */
                                            if(flag != 0){
                                                if(type.equals("Fiction")){	
                                                   noOfFicBooks = noOfFicBooks - 1 ;
                                                   
                                                   int days = DaysBetween.calculateDaysBetween(dueDate, todayDate);
                                                   //System.out.println("Days" + days);
                                                   if (days != 0)
                                                            totalFine = fine + (0.15 * (days - 1));
                                                   if (price < totalFine){
                                                                totalFine = price;
                                                   }
                                                   item.updateItem("UPDATE Items SET status ='" + newStatus + "' WHERE (ItemNumber like '" + data[0]+ "')");
                                                   member.updateMember("UPDATE Member SET NoOfFicBooks = " + noOfFicBooks + " WHERE (MemberID like '" + data[1]+ "')");
                                                   member.updateMember("UPDATE Member SET TotalFine = " + totalFine + " WHERE (MemberID like '" + data[1]+"')");
                                                        
                                                   transaction.updateTransaction("UPDATE Transaction SET transactionType ='" + "Check out/Return" + "' , endDate = '" + data[2] + "' WHERE (itemNumber like '" + data[0]+"' AND (MemberID like '" + data[1]+"' AND TransactionType like 'Checked out'))");

                                                   //transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, inDate, outDate, TransactionType) VALUES ('" +
                                                    //                            data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + data[2] + "','" +"Returned" + "')");
                                                   JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " returned successfully ! Your total fines are $" + totalFine , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                   /* for setting the array of JTextField to null */
                                                   clearTextFields();
                                                   }   

                                                    else if (type.equals("Non Fiction")) {
                                                        noOfNonFicBooks = noOfNonFicBooks - 1;
                                                        
                                                        int days = DaysBetween.calculateDaysBetween(dueDate, todayDate);
                                                        //System.out.println("Days = " + days);
                                                        if (days != 0)
                                                            totalFine = fine + (0.30 * (days - 1));
                                                        if (price < totalFine){
                                                                totalFine = price;
                                                        }

                                                        item.updateItem("UPDATE Items SET status ='" + newStatus + "' WHERE (ItemNumber like '" + data[0]+ "')");
                                                        member.updateMember("UPDATE Member SET NoOfNonFicBooks = " + noOfNonFicBooks + " WHERE (MemberID like '" + data[1]+"')");
                                                        member.updateMember("UPDATE Member SET TotalFine = " + totalFine + " WHERE (MemberID like '" + data[1]+"')");
                                                        
                                                        transaction.updateTransaction("UPDATE Transaction SET transactionType ='" + "Check out/Return" + "' , endDate = '" + data[2] + "' WHERE (itemNumber like '" + data[0]+"' AND (MemberID like '" + data[1]+"' AND TransactionType like 'Checked out'))");
                                   
                                                
                                                        // transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, inDate, outDate, TransactionType) VALUES ('" +
                                                          //                           data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + data[2] + "','" +"Returned" + "')");
                                                         JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " returned successfully ! You now owe the fine of $" + totalFine , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                         /* for setting the array of JTextField to null */
                                                         clearTextFields();
                                                     }   
                                                    else if (type.equals("Video")) {

                                                        noOfVideos = noOfVideos - 1;
                                                        
                                                        int days = DaysBetween.calculateDaysBetween(dueDate, todayDate);
                                                        if (days != 0)
                                                            totalFine = fine + (0.50 * (days - 1));
                                                        if (price < totalFine){
                                                                totalFine = price;
                                                        }
                                                        item.updateItem("UPDATE Items SET status ='" + newStatus + "' WHERE (ItemNumber like '" + data[0]+ "')");
                                                        member.updateMember("UPDATE Member SET NoOfVideos = " + noOfVideos+ " WHERE (MemberID like '" + data[1]+"')");
                                                        member.updateMember("UPDATE Member SET TotalFine = " + totalFine + " WHERE (MemberID like '" + data[1]+"')");
                                                        
                                                        transaction.updateTransaction("UPDATE Transaction SET transactionType ='" + "Check out/Return" + "' , endDate = '" + data[2] + "' WHERE (itemNumber like '" + data[0]+"' AND (MemberID like '" + data[1]+"' AND TransactionType like 'Checked out'))");


                                                       // transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, inDate, outDate, TransactionType) VALUES ('" +
                                                         //                           data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + data[2] + "','" +"Returned" + "')");
                                                        JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " returned successfully ! You now owe the fine of $" + totalFine   , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                        /* for setting the array of JTextField to null */
                                                        clearTextFields();
                                                    }   
                                                
                                            }
                                            
                                        }// End of checking member id valid and transaction is done by the member
                                        
                                        else
                                            JOptionPane.showMessageDialog(null, "The Item Number AND Member ID mismatch/invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }/* End of checking if there same item in the database */
                                    
                                    else
                                        JOptionPane.showMessageDialog(null, "The Item Number is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            
                                }  
                        };
                        runner.start();
                    }
                    /* if data is missing, then display Message Dialog */
                    else
                            JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            });

            /* for adding the action listener for the button to dispose the frame */
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                            dispose();
                    }
            });
                    /* for setting the visible to true */
            setVisible(true);
            /* show the internal frame */
            pack();
        }
}


