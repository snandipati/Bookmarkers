/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.*;

/**
 *
 * @author Silpa
 */
/* This class is used to chcek out an item in the Automated Library Assistant System */
public class CheckOutItem extends JInternalFrame{
     
    /***    Variables Declaration    ***/
    
        private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
    
        //for creating the North Panel
	private JPanel northPanel = new JPanel();
        private JPanel center = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        
        /* creating an Internal Panel in the center panel */
        private JPanel informationPanel = new JPanel();
        private JPanel textAreaPanel = new JPanel();
        private JPanel checkOutButtonPanel = new JPanel();
        
	/* creating the North Label */
	private ImageIcon image;
        private JLabel northLabel;
        
	//for creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[3];
	//for creating an array of String
	private String[] informationString = { " Enter the Item Number : "," Enter the Member ID : ",
	                                      " The Current Date : "};
	/* creating an array of JTextField */
	private JTextField[] informationTextField = new JTextField[3];
        private JTextArea textArea = new JTextArea(15, 25);
	
        /* creating the date in the String */
	private java.util.Date todayDate = new java.util.Date();
        private String inDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(todayDate);
        private String dueDate;
        private java.sql.Date now = new java.sql.Date(todayDate.getDate());;
        
        /* creating an array of string to store the data */
	private String[] data;
	
	/* creating the button */
	private JButton checkOutButton = new JButton("Check Out");
        private JButton cancelButton = new JButton("Cancel");
	
	/* creating the objects */
        private Item item;
	private Member member;
	private Transaction transaction;
        private ReserveList reserve;
        
        private int flag;

	/* A method to check the information from the text field */
	public boolean isCorrect() {
		data = new String[3];
		for (int i = 0; i < informationLabel.length; i++) {
			if (!informationTextField[i].getText().equals(""))
				data[i] = informationTextField[i].getText();
			else
				return false;
		}
		return true;
	}

	/* A method to set the array of JTextField to null */
	public void clearTextField() {
		for (int i = 0; i < informationTextField.length - 1; i++)
			if (i != 2)
				informationTextField[i].setText(null);
	}
        
        
        public String calculateDueDate(int days)
        {
            Calendar c=new GregorianCalendar();
            c.add(Calendar.DATE, days);
            Date d = c.getTime();
            dueDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(d);
            return dueDate;
        }
        
        /* A method to display information of items */
        public int displayItem(String query){
             int row = 0;
             textArea.setText("");
                   try {
			connection = DBMSConnectivity.getConnection();     
                        statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
                        
                            textArea.append(":::::::::: Item Checked out today :::::::::\n\n");
                            textArea.append("Member ID: " + "\tItem No.: " +"\tTitle: "+ "\tStart Date: " + "\tDue Date: " + "\n");
                            while (resultSet.next()) {
				textArea.append("\n"+resultSet.getString("MemberID") + 
                                        "\t" + resultSet.getString("ItemNumber")+
                                        "\t" + resultSet.getString("ItemTitle")+
                                        "\t"+ resultSet.getDate("startDate")+ "\t" + 
                                        resultSet.getDate("endDate"));
                                row++;
                            }
                            textArea.append("\n\n::::::::::: End ::::::::::: \n");
                        
                        resultSet.close();
			statement.close();
			connection.close();
                    }
                    catch (SQLException SQLe) {
                            System.out.println(SQLe.toString());
                            SQLe.printStackTrace();
		}
                return row;
        
        }
        

	/* constructor of checkOutItem */
	public CheckOutItem() {
		/* setting the title for the internal frame */
		super("Check out an Item", false, true, false, true);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/CheckOut12.png")));
		/* getting the graphical user interface components display area */
		Container cp = getContentPane();

		/* setting the layout */
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                center.setLayout(new BorderLayout());
                centerPanel.setLayout(new BorderLayout());
                informationPanel.setLayout(new GridLayout(4, 2, 1, 1));
                
		 /* adding the image to the panel */
                image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/check_out.jpg"));
                northLabel = new JLabel(image);
            
                /* adding the label to the panel */
                northPanel.add(northLabel);
            
                /* adding the panel to the container */
                cp.add("North", northPanel);
                
                textAreaPanel.setBorder ( BorderFactory.createTitledBorder("Display Area" ) );

                /* setting textArea non Editable */
                textArea.setEditable(false);
                textAreaPanel.add(textArea);
                
                /* creating the scrollPane */
                JScrollPane scroll = new JScrollPane ( textArea );
                scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

                /* adding scroll to textAreaPanel */
                textAreaPanel.add ( scroll );
		
		/* adding the strings to the labels, and adding the labels to panel */
		for (int i = 0; i < informationLabel.length; i++) {
			informationPanel.add(informationLabel[i] = new JLabel(informationString[i]));
			informationLabel[i].setFont(new Font("Tahoma", Font.BOLD, 12));
			if (i == 2) {
				informationPanel.add(informationTextField[i] = new JTextField(inDate));
				informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
				informationTextField[i].setEnabled(false);
			}
			else {
				informationPanel.add(informationTextField[i] = new JTextField());
				informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		}
		centerPanel.add("Center", informationPanel);

		/* setting the layout */
		checkOutButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button  */
		checkOutButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		/* adding the button to the panel */
		checkOutButtonPanel.add(checkOutButton);
		/* adding the panel to the center panel */
		centerPanel.add("South", checkOutButtonPanel);
		/* setting the border to the panel */
		centerPanel.setBorder(BorderFactory.createTitledBorder("Check out an Item :"));
		/* adding the panel to the container */
		center.add("Center", centerPanel);
                center.add("South", textAreaPanel);
                cp.add("Center", center);
                

		/* adding the layout */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button */
		cancelButton.setFont(new Font("Arial", Font.BOLD, 11));
		/* adding the button to the panel */
		southPanel.add(cancelButton);
		/* setting the border to the panel */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the panel to the container */
		cp.add("South", southPanel);

		/* for adding the action listener to the button and updating
		 * the table in the database with the new value 
		 */
		checkOutButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        /* checking if there is  data is missing */
                            
                        if (isCorrect()) {
                            Thread runner = new Thread() {
                                @Override
                                public void run() {
                                    item = new Item();
                                    member = new Member();
                                    transaction = new Transaction();
                                    reserve = new ReserveList();
                                    int noItem = item.selectItem("SELECT * FROM Items WHERE itemNumber like '" + data[0]+"'");
                                       
                                       // JOptionPane.showMessageDialog(null, "The Item Number is invalid!", "Warning", JOptionPane.WARNING_MESSAGE);
                                    int noMembers = member.selectMember("SELECT * FROM Member WHERE MemberID like '" + data[1]+"'");
                                    int noReserveItems = reserve.selectReserve("SELECT * FROM ReserveList WHERE itemNumber like '" + data[0]+"'");
                                    
                                    
                                    //System.out.println(noMembers);
                                    /* checking if the item Number is valid */
                                    if (noItem!= 0 ){
                                        /* checking if member ID is valid */
                                        if (noMembers != 0 )
                                        {
                                         
                                            double totalfine = member.getFine(); 
                                            System.out.println(totalfine);
                                            
                                            /* checking if the fine is not paid by the member, if fine is zero then allowed to check out an item */
                                            if (totalfine == 0){
                                                String status = item.getStatus();
                                                String type = item.getItemType();
                                                String title = item.getTitle();

                                                int noOfFicBooks = member.getNoOfFicBooks();    
                                                int noOfNonFicBooks = member.getNoOfNonFicBooks();
                                                int noOfVideos = member.getNoOfVideos();
                                                
                                                String reserveMemberID = reserve.getMemberID();
                                                
                                                /* checking if the item is on hold */
                                                if(status.equals("On Hold")){
                                                    /* checking if the item is reserved by the same Member ID */
                                                    if(reserveMemberID.equals(data[1])){
                                                        if(type.equals("Fiction")){	
                                                            if (noOfFicBooks < 3) {
                                                                noOfFicBooks = 1 + noOfFicBooks;
                                                                calculateDueDate(13);
                                                                item.updateItem("UPDATE Items SET status ='" + "Checked out" + "' WHERE ItemNumber like '" + data[0]+ "'");

                                                                member.updateMember("UPDATE Member SET NoOfFicBooks = " + noOfFicBooks + " WHERE (MemberID like '" + data[1]+"')");
                                                                transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate,  TransactionType,  ItemType) VALUES ('" +
                                                                                    data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + dueDate + "','" +"Checked out" + "', '" + "Fiction"+ "')");
                                                                
                                                                reserve.updateReserve("DELETE FROM ReserveList WHERE ItemNumber like '" + data[0]+ "'");
                                                                JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " checked out successfully!" , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                                //for setting the array of JTextField to null
                                                                displayItem("SELECT * from Transaction WHERE (TransactionType  ='" + "Checked out" + "' AND (MemberID like '" + data[1]+ "' AND startDate like '" + data[2] + "'))");
                                                                clearTextField();
                                                                }   
                                                            else
                                                                JOptionPane.showMessageDialog(null, "Fiction Book check out limit is over", "Warning", JOptionPane.WARNING_MESSAGE);
                                                        }

                                                        else if (type.equals("Non Fiction")) {
                                                            if (noOfNonFicBooks < 2) {
                                                                    noOfNonFicBooks = noOfNonFicBooks + 1;
                                                                    calculateDueDate(13);
                                                                    
                                                                    item.updateItem("UPDATE Items SET status ='" + "Checked out" + "' WHERE (ItemNumber like '" + data[0] + "')");

                                                                    member.updateMember("UPDATE Member SET NoOfNonFicBooks = " + noOfNonFicBooks + " WHERE (MemberID like '" + data[1]+ "')");
                                                                    transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate, TransactionType, ItemType) VALUES ('" +
                                                                                    data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + dueDate + "','" +"Checked out" + "', '" + "Non Fiction" + "')");
                                                                    
                                                                    reserve.updateReserve("DELETE FROM ReserveList WHERE ItemNumber like '" + data[0]+ "'");
                                                                                                                                    
                                                                    JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " checked out successfully!" , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                                    displayItem("SELECT * from Transaction WHERE (TransactionType ='" + "Checked out" + "' AND (MemberID like '" + data[1]+ "' AND startDate like '" + data[2] + "'))");

                                                                    //for setting the array of JTextField to null
                                                                    clearTextField();
                                                                    }   
                                                            else
                                                                JOptionPane.showMessageDialog(null, "Non Fiction Books check out limit is over", "Warning", JOptionPane.WARNING_MESSAGE);
                                                        }
                                                        else if (type.equals("Video")) {
                                                            if (noOfVideos < 2) {
                                                                    noOfVideos = noOfVideos + 1;
                                                                    calculateDueDate(6);
                                                                    item.updateItem("UPDATE Items SET status ='" + "Checked out" + "' WHERE (ItemNumber like '" + data[0] + "')");

                                                                    member.updateMember("UPDATE Member SET NoOfVideos = " + noOfVideos + " WHERE (MemberID like '" + data[1]+ "')");
                                                                    transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate, TransactionType, ItemType) VALUES ('" +
                                                                                    data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + dueDate + "','" +"Checked out" + "',  '" + "Video" + "')");
                                                                    
                                                                    reserve.updateReserve("DELETE FROM ReserveList WHERE ItemNumber like '" + data[0]+ "'");
                                                                
                                                                    JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " checked out successfully!" , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                                    displayItem("SELECT * from Transaction WHERE (TransactionType ='" + "Checked out" + "' AND (MemberID like '" + data[1]+ "' AND startDate like '" + data[2] + "'))");

                                                                    //for setting the array of JTextField to null
                                                                    clearTextField();
                                                                    }   
                                                              else
                                                                JOptionPane.showMessageDialog(null, "Videos check out limit is over", "Warning", JOptionPane.WARNING_MESSAGE);
                                                            }
                                                
                                                        }
                                                        else
                                                            JOptionPane.showMessageDialog(null, "This item is on hold by some other Member ID", "Warning", JOptionPane.WARNING_MESSAGE);
                                                    
                                                }     // End of checking if item is on hold
                                                
                                                
                                                
                                                /* checking if item is available for checking out */
                                                else if(status.equals("Available")){

                                                        if(type.equals("Fiction")){	
                                                            if (noOfFicBooks < 3) {
                                                                noOfFicBooks = 1 + noOfFicBooks;
                                                                calculateDueDate(13);
                                                                item.updateItem("UPDATE Items SET status ='" + "Checked out" + "' WHERE ItemNumber like '" + data[0]+ "'");

                                                                member.updateMember("UPDATE Member SET NoOfFicBooks = " + noOfFicBooks + " WHERE (MemberID like '" + data[1]+"')");
                                                                transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate,  TransactionType,  ItemType) VALUES ('" +
                                                                                    data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + dueDate + "','" +"Checked out" + "', '" + "Fiction"+ "')");
                                                                JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " checked out successfully!" , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                                //for setting the array of JTextField to null
                                                                displayItem("SELECT * from Transaction WHERE (TransactionType  ='" + "Checked out" + "' AND (MemberID like '" + data[1]+ "' AND startDate like '" + data[2] + "'))");
                                                                clearTextField();
                                                            }   
                                                            else
                                                                JOptionPane.showMessageDialog(null, "Fiction Book check out limit is over !", "Warning", JOptionPane.WARNING_MESSAGE);
                                                        }

                                                        else if (type.equals("Non Fiction")) {
                                                            if (noOfNonFicBooks < 2) {
                                                                    noOfNonFicBooks = noOfNonFicBooks + 1;
                                                                    calculateDueDate(13);

                                                                    item.updateItem("UPDATE Items SET status ='" + "Checked out" + "' WHERE (ItemNumber like '" + data[0] + "')");

                                                                    member.updateMember("UPDATE Member SET NoOfNonFicBooks = " + noOfNonFicBooks + " WHERE (MemberID like '" + data[1]+ "')");
                                                                    transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate, TransactionType, ItemType) VALUES ('" +
                                                                                    data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + dueDate + "','" +"Checked out" + "', '" + "Non Fiction" + "')");
                                                                    JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " checked out successfully!" , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                                    displayItem("SELECT * from Transaction WHERE (TransactionType ='" + "Checked out" + "' AND (MemberID like '" + data[1]+ "' AND startDate like '" + data[2] + "'))");

                                                                    //for setting the array of JTextField to null
                                                                    clearTextField();
                                                            }   
                                                            else
                                                                JOptionPane.showMessageDialog(null, "Non Fiction Books check out limit is over !", "Warning", JOptionPane.WARNING_MESSAGE);
                                                        }
                                                        else if (type.equals("Video")) {
                                                            if (noOfVideos < 2) {
                                                                    noOfVideos = noOfVideos + 1;
                                                                    calculateDueDate(6);
                                                                    item.updateItem("UPDATE Items SET status ='" + "Checked out" + "' WHERE (ItemNumber like '" + data[0] + "')");

                                                                    member.updateMember("UPDATE Member SET NoOfVideos = " + noOfVideos + " WHERE (MemberID like '" + data[1]+ "')");
                                                                    transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate, TransactionType, ItemType) VALUES ('" +
                                                                                    data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + title + "','" + data[2] + "','" + dueDate + "','" +"Checked out" + "',  '" + "Video" + "')");
                                                                    JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " checked out successfully!" , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                                    displayItem("SELECT * from Transaction WHERE (TransactionType ='" + "Checked out" + "' AND (MemberID like '" + data[1]+ "' AND startDate like '" + data[2] + "'))");

                                                                    //for setting the array of JTextField to null
                                                                    clearTextField();
                                                            }   
                                                            else
                                                                JOptionPane.showMessageDialog(null, "Videos check out limit is over !", "Warning", JOptionPane.WARNING_MESSAGE);
                                                    }
                                                
                                                } /* End of checking if item is available for checking out */
                                                  
                                                else
                                                        JOptionPane.showMessageDialog(null, "The Item is not available for check out !", "Warning", JOptionPane.WARNING_MESSAGE);
                                        
                                            }/* End checking if the fine is not paid by the member */
                                        
                                            /* if the fine is not paid by the member, then show him/her how much he/she owes */
                                            else
                                                    JOptionPane.showMessageDialog(null, "Fine is not paid. You owe " + totalfine, "Warning", JOptionPane.WARNING_MESSAGE);
                                        
                                        } /* End of checking if member ID is valid */
                                    
                                        /* if member ID is valid, then show member ID is invalid */
                                        else
                                                JOptionPane.showMessageDialog(null, "The Member ID is invalid !", "Error", JOptionPane.ERROR_MESSAGE );
                                
                                    }/* End of checking if the item Number is valid */
                                
                                    else
                                            JOptionPane.showMessageDialog(null, "The Item Number is invalid !", "Error", JOptionPane.ERROR_MESSAGE );
                                    
                                }
                            };
                            runner.start();
                        }
                        /* if there is a missing data, then display Message Dialog */
                        else
                        JOptionPane.showMessageDialog(null, "Please, complete the information !", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                });
		/* adding the action listener for the button to dispose the frame */
            cancelButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		/* setting the visible to true */
		setVisible(true);
		/* show the internal frame */
		pack();
	}
}
