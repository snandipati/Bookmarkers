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
public class PayFine extends JInternalFrame{
     
    /***    Variables Declaration    ***/
	
        /* creating the Panels */
	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        
	/* creating an Internal Panel in the center panel */
	private JPanel dataPanel = new JPanel();
        private JPanel payButtonPanel = new JPanel();
        
        /* creating the label */
	private JLabel jTitle = new JLabel(" Pay Fines ");

	/* creating an array of JLabel */
	private JLabel[] dataLabel = new JLabel[3];
        
	/* creating an array of String */
	private String[] dataString = { " Enter the Member ID : ", " Enter Amount : ",
	                                      " The Current Date : "};
	/* creating an array of JTextField */
	private JTextField[] dataTextField = new JTextField[3];
        
	/* creating the date in the String */
	private String inDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
	
        /* creating an array of string to store the data */
	private String[] data;

	/* creating the buttons */
	private JButton payButton = new JButton("Pay");
        private JButton cancelButton = new JButton("Cancel");
         private JButton showButton = new JButton("Show Balance");
       
        private double amount;
	private String mem;
        /* creating an object */
        private Item item;
	private Member member, m1;
	private Transaction transaction;

	/* A method to check the data from the text field */
	public boolean isDataCorrect() {
		data = new String[3];
		for (int i = 0; i < dataLabel.length; i++) {
                    
                        if (!dataTextField[i].getText().equals(""))
				data[i] = dataTextField[i].getText();
			else
				return false;
                   
                }
		return true;
	}
        /* A method to check the member from the text field */
        public boolean isMemberCorrect() {
		mem = new String();
		if (!dataTextField[0].getText().equals(""))
			mem = dataTextField[0].getText();
		else
			return false;
                return true;  
                
	}
        

	/* A method to set the array of JTextField to null */
	public void clearTextFields() {
		for (int i = 0; i < dataTextField.length; i++)
			if (i != 2)
				dataTextField[i].setText(null);
	}

	/* Constructor of PayFine */
	public PayFine() {
		/* setting the title for the internal frame */
		super("Pay Fine", false, true, false, true);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Fine12.png")));
		/* getting  content Pane*/
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


		/* adding the strings to the labels adding the panel to the 
                 * container	
                 */
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
		payButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button */
		payButton.setFont(new Font("Arial", Font.BOLD, 12));
                showButton.setFont(new Font("Arial", Font.BOLD, 12));
		/* adding the button to the panel */
                payButtonPanel.add(showButton);
		payButtonPanel.add(payButton);
		/* adding the panel to the center panel */
		centerPanel.add("South", payButtonPanel);
		/* setting the border to the panel */
		centerPanel.setBorder(BorderFactory.createTitledBorder("Pay Fine :"));
		/* adding the panel to the container */
		contentPane.add("Center", centerPanel);

		/* adding the layout */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button */
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		
                /* adding the button to the panel */
                southPanel.add(cancelButton);
		/* setting the border to the panel */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the panel to the container */
		contentPane.add("South", southPanel);
                
		showButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
                            if (isMemberCorrect()) {
                                    m1 = new Member();
					int noMembers = m1.selectMember("SELECT * FROM Member WHERE MemberID like '" + mem+"'");
					//System.out.println(noMembers);
                                        
                                        if (noMembers != 0 )
                                        {
                                         Double fine = m1.getFine();  
                                         JOptionPane.showMessageDialog(null, "You owe a fine of $"+fine+" as of the date "+ inDate, "Information", JOptionPane.INFORMATION_MESSAGE);
                                         clearTextFields();  
                                        }
                                       else
                                            JOptionPane.showMessageDialog(null,"The Member ID is invalid !" , "Error", JOptionPane.ERROR_MESSAGE);
                                    
                            }
                        }
                });

		/* adding the action listener to the button  */
		payButton.addActionListener(new ActionListener() {
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
					
                                        int noMembers = member.selectMember("SELECT * FROM Member WHERE MemberID like '" + data[0]+"'");
					
                                        System.out.println(noMembers);
                                        
                                        if (noMembers != 0 )
                                        {
                                         Double fine = member.getFine();  
                                         
                                         try {
                                                // deposit amount entered in amountTextField
                                                amount = Double.parseDouble(data[1] );
                                             }

                                             catch ( NumberFormatException exception ) {
                                                JOptionPane.showMessageDialog (null,"Please enter a valid amount", "Error",JOptionPane.ERROR_MESSAGE );
                                              }
                                                                                  
                                            amount =  Double.parseDouble(data[1]);
                                            //for checking if there is no same information in the database
                                            fine = fine - amount;
                                            member.updateMember("UPDATE Member SET TotalFine = " + fine + " WHERE MemberID like '" + data[0]+"'");
                                            transaction.updateTransaction("INSERT INTO Transaction ( MemberID, startDate, endDate,TransactionType, FinesPaid) VALUES ('" +
								        data[0].toUpperCase() + "','" + data[2] + "','" + data[2] + "','" + "Paid Fine" + "', " + amount + ")");
							
                                            JOptionPane.showMessageDialog(null, "Fine of $"+amount+" paid successfully ! Your Balance is $" +fine, "Information", JOptionPane.INFORMATION_MESSAGE);
                                            clearTextFields();
                                        }
                                       else
                                            JOptionPane.showMessageDialog(null,"The Member ID is invalid!" , "Error", JOptionPane.ERROR_MESSAGE);
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
		/* setting the visible to true */
		setVisible(true);
		/* showing the internal frame */
		pack();
	}
}
