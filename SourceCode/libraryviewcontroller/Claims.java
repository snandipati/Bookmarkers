/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.*;


/**
 *
 * @author Silpa
 */
/* This class is used to claim an item as lost in the Automated Library Assistant System */
public class Claims extends JInternalFrame {
    
    /***    Variables Declaration    ***/
	
        /* creating the Panels */
	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        
	/* creating the Label */
	private JLabel jTitle = new JLabel("Claims");
		
	/* creating an Internal Panel in the center panel */
	private JPanel dataPanel = new JPanel();
        private JPanel submitButtonPanel = new JPanel();
        
	/* creating an array of JLabel */
	private JLabel[] dataLabel = new JLabel[3];
        
	/* creating an array of Strings */
	private String[] dataString = { " Enter the Item Number : "," Enter the Member ID : ",
	                                      " The Current Date : "};
	/* creating an array of JTextField */
	private JTextField[] dataTextField = new JTextField[3];
        
	/* creating the date in the String */
	private String inDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
	
        /* creating an array of string to store the data */
	private String[] data;

	/* creating the buttons */
	private JButton submitButton = new JButton("Submit");
	private JButton cancelButton = new JButton("Cancel");

	/* for creating an object */
        private Item item;
	private Member member;
	private Transaction transaction;
        private ReserveList reserve;

	/* A method to check the dat from the textfields */
	public boolean isCorrect() {
		data = new String[3];
		for (int i = 0; i < dataLabel .length; i++) {
			if (!dataTextField[i].getText().equals(""))
				data[i] = dataTextField[i].getText();
			else
				return false;
		}
		return true;
	}

	/* A method to set the array of JTextField to null */
	public void clearTextField() {
		for (int i = 0; i < dataTextField.length - 1; i++)
			if (i != 2)
				dataTextField[i].setText(null);
	}

	/* Constructor of Claims */
	public Claims() {
		/* setting the title for the internal frame */
		super("Claim as Lost", false, true, false, true);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Claim12.png")));
		
                /*getting the content Pane */
		Container contentPane = getContentPane();

		/* setting the layout */
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                dataPanel.setLayout(new GridLayout(4, 2, 1, 1));
                
		/* setting the font */
                Font f = new Font("Arial", Font.BOLD, 14);
		jTitle.setFont(f);
                
		/* adding the label to the panel */
		northPanel.add(jTitle);
                
		/* adding the panel to the container */
		contentPane.add("North", northPanel);


		/* adding the strings to the labels and adding the panel 
                 * to the container
                 */
		for (int i = 0; i < dataLabel .length; i++) {
			dataPanel.add(dataLabel [i] = new JLabel(dataString[i]));
			dataLabel [i].setFont(new Font("Tahoma", Font.BOLD, 12));
			if (i == 2) {
				dataPanel.add(dataTextField[i] = new JTextField(inDate));
				dataTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
				dataTextField[i].setEnabled(false);
			}
			else {
				dataPanel.add(dataTextField[i] = new JTextField());
				dataTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
		}
		centerPanel.add("Center", dataPanel);

		/* setting the layout */
		submitButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button */
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		/* adding the button to the panel */
		submitButtonPanel.add(submitButton);
		/* adding the panel to the center panel */
		centerPanel.add("South", submitButtonPanel);
		/* setting the border to the panel */
		centerPanel.setBorder(BorderFactory.createTitledBorder("Check out an Item :"));
		/* adding the panel to the container */
		contentPane.add("Center", centerPanel);

		/* adding the layout */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button */
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		/* adding the button to the panel */
		southPanel.add(cancelButton);
		/* setting the border to the panel */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the panel to the container */
		contentPane.add("South", southPanel);

		/* adding the action listener to the button */
		submitButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
                            /*  checking if there is a missing information */
                            if (isCorrect()) {
				Thread runner = new Thread() {
                                    @Override
                                    public void run() {
                                        item = new Item();
					member = new Member();
					transaction = new Transaction();
					reserve = new ReserveList();
                                        int noItems = item.selectItem("SELECT * FROM Items WHERE itemNumber like '" + data[0]+"'");
					int noMembers = member.selectMember("SELECT * FROM Member WHERE MemberID like '" + data[1]+"'");
					int tranNo = transaction.selectTransaction("SELECT * FROM Transaction WHERE (itemNumber like '" + data[0]+ "' AND (MemberID like '" + data[1]+ "' AND TransactionType like 'Checked out' ) )");
                                   
                                        //int noReserve = item.selectItem("SELECT * FROM ReserveList WHERE itemNumber like '" + data[0]+"'");
                                        if (noItems  != 0 ){
                                            if (noMembers != 0 )
                                            {
                                                if (tranNo != 0 ){
                                                    String itemTitle = item.getTitle();
                                                    String status = item.getStatus();
                                                    double price = item.getPrice();
                                                    double fine = member.getFine();
                                                    fine = fine + price;

                                                    //for checking if there is no same information in the database
                                                    if ( status.equals("Checked out") || status.equals("Reserved")){
                                                        item.updateItem("UPDATE Items SET status ='" + "Lost" + "' WHERE (ItemNumber like '" + data[0]+ "')");

                                                        member.updateMember("UPDATE Member SET TotalFine = " + fine + " WHERE (MemberID like '" + data[1]+"')");


                                                        reserve.updateReserve("DELETE FROM reservelist WHERE (ItemNumber like '" + data[0]+ "')");

                                                        transaction.updateTransaction("UPDATE Transaction SET transactionType ='" + "Check out/Lost" + "' , endDate = '" + data[2] + "' WHERE (itemNumber like '" + data[0]+"' AND (MemberID like '" + data[1]+"' AND TransactionType like 'Checked out'))");


                                                        //transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, startDate, endDate, TransactionType) VALUES ('" +
                                                                    //        data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + itemTitle+ "','" + data[2] + "','" + data[2] + "','" +"Lost" + "')");
                                                        JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " is claimed as Lost. A fine $" + price+  " of is charged on your account. " , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                        //for setting the array of JTextField to null
                                                        clearTextField();
                                                        }   
                                                     else
                                                            JOptionPane.showMessageDialog(null, "The Item has not been checked out yet", "Warning", JOptionPane.WARNING_MESSAGE);
                                                } 
                                                else
                                                    JOptionPane.showMessageDialog(null, "The Item has not been checked out by this member ID", "Warning", JOptionPane.WARNING_MESSAGE);
                                            
                                            }  
                                            else
                                                JOptionPane.showMessageDialog(null, "The MemberID is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
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
		/* adding the action listener for the button to dispose the frame */
            cancelButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		/* setting the visible to true */
		setVisible(true);
		/* showing the internal frame */
		pack();
	}
}
