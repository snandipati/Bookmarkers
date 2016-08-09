/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

/**
 *
 * @author Silpa
 */
/* This class is use for transaction history in the Automated Library Assistant System */
public class TransactionHistory extends JInternalFrame{
    
    /***    Variables Declaration    ***/
   
        private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
        
	/* creating Panels */
	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        
	 /* creating the North Label */
	private ImageIcon image;
        private JLabel northLabel;

	/* creating the center Panel */
	private JPanel centerSelectPanel = new JPanel();
	/* creating the Internal Panels in the center panel */
	private JPanel selectMonthsPanel = new JPanel();
	private JPanel viewButtonPanel = new JPanel();
        private JPanel selectYearsPanel = new JPanel();
	private JPanel textAreaPanel = new JPanel();

	/* creating the Label */
	private JLabel selectMonthsLabel = new JLabel(" Select Month : ");
        private JLabel selectYearsLabel = new JLabel(" Select Year : ");
        
	/* for JComboBox */
	private JComboBox selectMonthsTypes;
        private JComboBox selectYearsTypes;
        
	/* creating String[] */
	private String[] monthsTypes = {"All", "January", "February", "March", "April", "May", "June",
                                        "July", "August", "September", "October", "November", "December"};
	private String[] yearsTypes = {"2013", "2012", "2011", "2010", "2009"};
	
        /* creating the label */
	private JLabel memberIDKey = new JLabel(" Enter your Member ID : ");
	/* creating the text field */
	private JTextField memberIDTextField = new JTextField();
	
	/* creating the text area */
	private JTextArea textArea = new JTextArea(15,25);
        
        /* creating the button */
	private JButton viewButton = new JButton("View");
        private JButton cancelButton = new JButton("Cancel");

	/* creating an array of string to store the data */
	private String data;
	
	/* creating objects from another classes for using them in the ActionListener */
	private int indexMonths, indexYears;
        private int year;
        private String monthsQuery;
	private Member member;   

	/* A method to check the data from the text field */
	public boolean isDataCorrect() {
		data = new String();
		data = selectMonthsTypes.getSelectedItem().toString();
		if (!memberIDTextField.getText().equals("")) {
				data = memberIDTextField.getText();
				
		}
                else{
			return false;
		}
		return true;
	}
        
        /* A method to display the data in the text area */
        public void displayTransaction(String query){
             int row = 0;
             textArea.setText("");
                   try {
			connection = DBMSConnectivity.getConnection();     
                        statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
                        
                            textArea.append("::::::: Transaction History :::::::\n\n");
                            while (resultSet.next()) {
				textArea.append("Member ID : " + resultSet.getString("MemberID") + "\n" +
				        "Item Number : " + resultSet.getString("ItemNumber") + "\n" +
				        "Title : " + resultSet.getString("ItemTitle") + "\n" +
				        "Start Date : " + resultSet.getString("startDate") + "\n" +
                                        "End Date : " + resultSet.getString("endDate") + "\n" +
				        "Transaction Type : " + resultSet.getString("TransactionType") + "\n" +
				        "Fines Paid : " + resultSet.getDouble("FinesPaid") + "\n\n");
                                        row++;
                            }
                            textArea.append(":::::::: END ::::::::\n");
                        if(row == 0)
                            textArea.setText(" No records ");
                        
                        resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println(SQLe.toString());
                        SQLe.printStackTrace();
		}
        //return row;
        
        }
        

	/* constructor of TransactionHistory */
	public TransactionHistory () {
		/* setting the title for the internal frame */
		super("Transaction History", false, true, false, true);
                // setSize(500, 500);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Transaction12.png")));
		/* getting the graphical user interface components display area */
		Container contentPane = getContentPane();

		/* setting the layout */
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
		centerSelectPanel.setLayout(new BorderLayout());
		selectMonthsPanel.setLayout(new GridLayout(2, 2, 1, 1));
		
                image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/TransactionHistory.jpg"));
                northLabel = new JLabel(image);


                /* for adding the label to the panel */
                northPanel.add(northLabel);
                /*for adding the panel to the container */
                contentPane.add("North", northPanel);
                
                /* setting the border */
                textAreaPanel.setBorder ( BorderFactory.createTitledBorder("Display Area" ) );

                /* to set textArea non Editable */
                textArea.setEditable(false);
                textAreaPanel.add(textArea);
                
                /* creating scrollPane */
                JScrollPane scroll = new JScrollPane ( textArea );
                scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

                /* Adding scroll to textAreapanel */
                textAreaPanel.add ( scroll );
                             
		
		/* adding the label */
		selectMonthsPanel.add(selectMonthsLabel);
		/* adding the JComboBos[] */
		selectMonthsPanel.add(selectMonthsTypes = new JComboBox(monthsTypes));
		/* adding the label */
		selectMonthsPanel.add(memberIDKey);
		/* adding the text field */
		selectMonthsPanel.add(memberIDTextField);
		/* adding the internal panel to the panel
		

		/* setting the layout */
		viewButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* adding the button */
		viewButtonPanel.add(viewButton);
		/* adding the internal panel to the center panel */
		              
                
		/* setting the border */
		centerSelectPanel.setBorder(BorderFactory.createTitledBorder("View Transaction History :"));
        
                centerPanel.add("South", textAreaPanel);
		

                selectYearsPanel.setLayout(new GridLayout(1, 2, 1, 1));
		/* adding the label */
		selectYearsPanel.add(selectYearsLabel);
		/* adding the JComboBos[] */
		selectYearsPanel.add(selectYearsTypes = new JComboBox(yearsTypes));
		
                centerSelectPanel.add("North", selectYearsPanel);
                centerSelectPanel.add("Center", selectMonthsPanel);
                centerSelectPanel.add("South", viewButtonPanel);
              
                centerPanel.add("Center", centerSelectPanel);
                
		/* adding the center to the container */
		contentPane.add("Center", centerPanel);

		/* setting the font to the labels & buttons */
		selectMonthsLabel.setFont(new Font("Arial", Font.BOLD, 12));
		selectMonthsTypes.setFont(new Font("Arial", Font.BOLD, 12));
		
		memberIDTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		viewButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		selectYearsLabel.setFont(new Font("Arial", Font.BOLD, 12));
		selectYearsTypes.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));

		/* setting the layout */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* adding the button */
		southPanel.add(cancelButton);
		/* setting the border */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the south panel to the container */
		contentPane.add("South", southPanel);
                
		 /* for adding the action listener to the button */ 
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isDataCorrect()) {
					
                                    Thread runner = new Thread() {
                                    @Override
                                    public void run() {
                                    member = new Member();
                                    int noMembers = member.selectMember("SELECT * FROM Member WHERE MemberID like '" + data+"'");
                                    if(noMembers != 0){
                                        indexYears = selectYearsTypes.getSelectedIndex();
                                        indexMonths = selectMonthsTypes.getSelectedIndex();
                                        System.out.println(indexYears);                                                                    

                                        if(indexYears==0)
                                        {      
                                         year = 2013;
                                            if (indexMonths == 0){

                                               String yearQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  Year(startDate) = " + year ;
                                               displayTransaction(yearQuery);
                                            }
                                            else{
                                                monthsQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  (Year(startDate) = " + year + " AND Month (startDate) = " + indexMonths + ")";
                                                displayTransaction(monthsQuery);
                                                System.out.println(monthsQuery);

                                            }
                                        }
                                    
                                    else if(indexYears ==1)
                                    {
                                        year = 2012;
                                          if (indexMonths == 0){

                                              String yearQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  Year(startDate) = " + year ;
                                              displayTransaction(yearQuery);
                                          }
                                          else
                                          {
                                             monthsQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  (Year(startDate) = " + year + " AND Month (startDate) = " + indexMonths + ")";
                                              displayTransaction(monthsQuery);
                                              /*
                                              if (no == 0)
                                              {
                                                  textArea.setText("No Records");
                                              }*/
                                          }
                                     /*          
					int no = book.selectItem(bookQuery);
                                                                               
                                        if (no != 0 ){
                                        String itemNumber = book.getItemNumber();
                                        String title = book.getTitle();
                                        String author = book.getAuthor();
                                        String itemType = book.getItemType();
                                        int shelfNo =  book.getShelfNumber();  
                                        System.out.println(itemNumber + " " +title +  " " + itemType+  " " + shelfNo);
                                        JOptionPane.showMessageDialog(null, " Item Number : " + itemNumber + ", Title : "+ title +  "\n Author : " + author + ", Type : " + itemType + ", Shelf Number : " + shelfNo, "Information", JOptionPane.INFORMATION_MESSAGE);  
                                        }*/
                                    }
                                    else if(indexYears == 2)
                                    {
                                        year = 2011;
                                          if (indexMonths == 0){

                                              String yearQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  Year(startDate) = " + year ;
                                              displayTransaction(yearQuery);
                                          }
                                          else{
                                              monthsQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  (Year(startDate) = " + year + " AND Month (startDate) = " + indexMonths + ")";
                                              displayTransaction(monthsQuery);

                                          }  
                                      /*           
					int no = book.selectItem(bookQuery);
                                        if (no != 0 ){
                                        String itemNumber = book.getItemNumber();
                                        String title = book.getTitle();
                                        String author = book.getAuthor();
                                        String itemType = book.getItemType();
                                        int shelfNo =  book.getShelfNumber();  
                                        System.out.println(itemNumber + " " +title +  " " + itemType+  " " + shelfNo);
                                        JOptionPane.showMessageDialog(null, " Item Number : " + itemNumber + ", Title : "+ title +  "\n Author : " + author + ", Type : " + itemType + ", Shelf Number : " + shelfNo, "Information", JOptionPane.INFORMATION_MESSAGE);  
                                        }*/
                                    }
                                    else if(indexYears ==3 )
                                    {
                                      year = 2010;
                                          if (indexMonths == 0){

                                              String yearQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  Year(startDate) = " + year ;
                                              displayTransaction(yearQuery);
                                          }
                                          else{
                                              monthsQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  (Year(startDate) = " + year + " AND Month (startDate) = " + indexMonths + ")";
                                               displayTransaction(monthsQuery);

                                          }
                                    }
                                        else if(indexYears ==4 )
                                      {
                                        year = 2009;
                                            if (indexMonths == 0){

                                                String yearQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  Year(startDate) = " + year ;
                                                displayTransaction(yearQuery);
                                            }
                                            else{
                                                monthsQuery = "SELECT * FROM Transaction WHERE MemberID LIKE '" + data+ "' AND  (Year(startDate) = " + year + " AND Month (startDate) = " + indexMonths + ")";
                                                displayTransaction(monthsQuery);

                                            }
                                      }

                                        }
                                        else 
                                            JOptionPane.showMessageDialog(null, "Member ID is invalid ! Please enter a valid member ID", "Error", JOptionPane.ERROR_MESSAGE);
			
                                }            
		
                            };
                            runner.start();
                           }
				
				else
					JOptionPane.showMessageDialog(null, "Please, enter your member ID", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		
		/* for adding the action listener for the button to dispose the frame */
		cancelButton.addActionListener(new ActionListener() {
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
