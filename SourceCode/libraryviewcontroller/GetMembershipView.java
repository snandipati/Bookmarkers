/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
/**
 *
 * @author Silpa
 */
/* This class is used to create membership for a non member */
public class GetMembershipView extends JInternalFrame{
    
    /***    Variables Declaration    ***/ 
           
        /* creating the Panels */
	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
        
        /* creating an internal Panels */
	private JPanel dataLabelPanel = new JPanel();
        private JPanel dataTextFieldPanel = new JPanel();
        private JPanel submitButtonPanel = new JPanel();
        
        /* creating the iamge and Label */
	private ImageIcon image;
        private JLabel northLabel;
                      
	/* creating an array of JLabel */
	private JLabel[] dataLabel = new JLabel[4];
	/* creating an array of String */
	private String[] dataString = {" Name: "," Phone No: "," E-MAIL: ", " Address: "};
	
	/* creating an array of JTextField */
	private JTextField[] dataTextField = new JTextField[4];
	
	/* creating a button */
	private JButton submitButton = new JButton("Submit");

	/* creating a button */
	private JButton exitButton = new JButton("Exit");
        
        /* fields for calculation */
        private double fee = 10.00;
	
	/* creating objects from another classes */
	Member member;
        
	/* creating an array of string to store the data */
	private String[] data;


	/* A method to check the information from the text field */
	public boolean isTextCorrect() {
            data = new String[4];
            for (int i = 0; i < dataLabel.length; i++) {
		
                    if (!dataTextField[i].getText().equals("")) {
                            data[i] = dataTextField[i].getText();
                    }
                    else
                            return false;
              
            }
            return true;
        }
        
	/* A method to set the array of JTextField to null */
	public void clearTextFields() {
            for (int i = 0; i < dataLabel.length; i++) {
                    dataTextField[i].setText(null);
            }
	}
        
       
        /* Constructor of GetMembershipView */
	public GetMembershipView() {
            /* setting the title for the internal frame */
            super("Get a Membership Card", false, true, false, true);
            
            /* setting the icon */
            setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Member12.png")));
            
            /* getting the content Pane */
            Container contentPane = getContentPane();
                                  
            /* setting the layout */
            northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            centerPanel.setLayout(new BorderLayout());
            centerPanel.setBorder(BorderFactory.createTitledBorder("Online Registration :"));
            dataLabelPanel.setLayout(new GridLayout(4, 1, 1, 1));
            dataTextFieldPanel.setLayout(new GridLayout(4, 1, 1, 1));
            
             /* adding the image to the panel */
            image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/MembershipCard.jpg"));
            northLabel = new JLabel(image);
            
            /* adding the label to the panel */
            northPanel.add(northLabel);
            
            /* adding the panel to the container */
            contentPane.add("North", northPanel);

            /* adding the strings to the labels and adding these labels to the panel */
             for (int i = 0; i < dataLabel.length; i++) {
		dataLabelPanel.add(dataLabel[i] = new JLabel(dataString[i]));
		dataLabel[i].setFont(new Font("Arial", Font.BOLD, 12));
            }
            /* adding the panel to the centerPanel */
            centerPanel.add("West", dataLabelPanel);

            /* for adding the fields to the panel and adding the panel to the 
             * centerPanel 
             */
            for (int i = 0; i < dataLabel.length; i++) {
	
		if (i == 0) {
			dataTextFieldPanel.add(dataTextField[i] = new JTextField(25));
			dataTextField[i].setFont(new Font("Arial", Font.PLAIN, 12));
		}
                if (i == 1) {
			dataTextFieldPanel.add(dataTextField[i] = new JTextField(10));
			dataTextField[i].setFont(new Font("Arial", Font.PLAIN, 12));
		}              
		if (i == 2 || i == 3) {
			dataTextFieldPanel.add(dataTextField[i] = new JTextField(20));
                	dataTextField[i].setFont(new Font("Arial", Font.PLAIN, 12));
		}
            }
            centerPanel.add("East", dataTextFieldPanel);

            /* setting the layout for the panel and adding the panel to the 
             * content Pane	
             */
            submitButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            submitButton.setFont(new Font("Arial", Font.BOLD, 12));
            submitButtonPanel.add(submitButton);
            centerPanel.add("South", submitButtonPanel);
            contentPane.add("Center", centerPanel);

            /* setting the layout for the panel and adding the panel to the 
             * content Pane	
             */
            southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            exitButton.setFont(new Font("Arial", Font.BOLD, 12));
            southPanel.add(exitButton);
            southPanel.setBorder(BorderFactory.createEtchedBorder());
            contentPane.add("South", southPanel);
            
            /* adding the action listener to the button */
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                /* checking if there is a missing information */
                    if (isTextCorrect()) {
                           Thread runner = new Thread() {
                                @Override
                                public void run() {
                                    member = new Member();
                                    int noMember = member.selectMember("Select * from member where phoneNo like '" +data[1]+ "'");
                                    if (noMember == 0){
                                            int number = 0;
                                            
                                            //check if the the person is resident of Springfield
                                            if (data[3].equals("Springfield"))
                                                 fee = 0.0;
                                            
                                            //PreparedStatement ps=connection.prepareStatement(sql);
                                            Date date = new Date();
                                            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                            
                                            /* creating an unique member ID from the serial number */
                                            int  serialNo = member.selectMaxMember("SELECT MAX(serialNo) from member");
                                            String memberID = String.format("%05d", (serialNo+1));
                                            
                                            String query = "INSERT INTO Member (memberID, Name,PhoneNo,EMail,Address,StartDate, TotalFine, NoOfFicBooks, NoOfNonFicBooks, NoOfVideos) VALUES ('" + memberID + "','" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "','" +sqlDate + "', " + fee + "," + number + "," + number +"," + number + ")";
                                            try{
                                                if(member.updateMember(query)){
                                                        JOptionPane.showMessageDialog(null, "Membership created successfully ! Name : " + data[0]+" Your Member ID is : " + memberID + ".  Please pay a of fee $" + fee + "  at the front desk.", "Information", JOptionPane.INFORMATION_MESSAGE);  
                                                        clearTextFields();
                                                        
                                                
                                                }
                                                else
                                                        JOptionPane.showMessageDialog(null, "Please enter 10 digit unique Phone No.", "Warning", JOptionPane.WARNING_MESSAGE);
			
                                        }
                                            
                                            
                                        catch(IndexOutOfBoundsException sibe){
                                                JOptionPane.showMessageDialog(null, "Error: Please enter 10 digit unique Phone No.","Error", JOptionPane.ERROR_MESSAGE );  
                                            
                                    }                                         
                                    catch(Exception ex){
                                                JOptionPane.showMessageDialog(null, ex.toString());
                                                clearTextFields();
                                    }
                                }   
                                else
                                        JOptionPane.showMessageDialog(null, "Phone No. already exists in the Library records. Please provide a different 10 digit no. ", "Error", JOptionPane.ERROR_MESSAGE);
			
                            }                                 
                                                                    
                                                                    
                        };
			runner.start();
                    }
                          
                            
				//if there is a missing data, then display Message Dialog
                    else
                            JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
		});
		/* adding the action listener for the button to dispose the frame */
		exitButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		/* setting the visible to true */
		setVisible(true);
		pack();
	}
}
