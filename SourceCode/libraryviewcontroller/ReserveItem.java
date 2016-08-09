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
/* This class reserves an item in the Automated Library Assistant System */
public class ReserveItem extends JInternalFrame{
      
    /***    Variables Declaration    ***/
    
	/* creating the North Panel */
	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        
	/* creating the label */
	private ImageIcon image;
        private JLabel northLabel;

	/* creating an Internal Panel in the center panel */
	private JPanel dataPanel = new JPanel();
        private JPanel reserveButtonPanel = new JPanel();
        
	/* creating an array of JLabel */
	private JLabel[] dataLabel = new JLabel[3];
	/*creating an array of String */
	private String[] dataString = { " Enter the Item Number : "," Enter the Member ID : ",
	                                      " The Current Date : "};
	/* creating an array of JTextField */
	private JTextField[] dataTextField = new JTextField[3];
	/* creating the date in the String */
	private String inDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
	
        /* creating an array of string to store the data */
	private String[] data;
	
	/* creating the button */
	private JButton reserveButton = new JButton("Reserve");
        private JButton cancelButton = new JButton("Cancel");
        
	/* creating an object */
        private Item item;
	private Member member;
	private Transaction transaction;
        private ReserveList reserve;

        
        
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

	/* A method to set the array of JTextField to null */
	public void clearTextField() {
		for (int i = 0; i < dataTextField.length - 1; i++)
			if (i != 2)
				dataTextField[i].setText(null);
	}

	/* constructor of ReserveItem */
	public ReserveItem() {
		/* setting the title for the internal frame */
		super("Reserve an Item", false, true, false, true);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Reserve12.png")));
		/* getting the content Pane */
		Container contentPane = getContentPane();

		/* setting the layout */
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		/* adding the image to the panel */
                image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/reserve_item.jpg"));
                northLabel = new JLabel(image);
            
                /* adding the label to the panel */
                northPanel.add(northLabel);
            
                /* adding the panel to the container */
                contentPane.add("North", northPanel);

		/* setting the layout */
		centerPanel.setLayout(new BorderLayout());
		/* setting the layout for the internal panel */
		dataPanel.setLayout(new GridLayout(4, 2, 1, 1));

		/* adding the strings to the labels and adding these 
                 * labels to the panel.							   *
		 */
		for (int i = 0; i < dataLabel.length; i++) {
			dataPanel.add(dataLabel[i] = new JLabel(dataString[i]));
			dataLabel[i].setFont(new Font("Tahoma", Font.BOLD, 12));
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

		/* setting the layout  */
		reserveButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button  */
		reserveButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		/* adding the button to the panel  */
		reserveButtonPanel.add(reserveButton);
		/* adding the panel to the center panel  */
		centerPanel.add("South", reserveButtonPanel);
		/* setting the border to the panel  */
		centerPanel.setBorder(BorderFactory.createTitledBorder("Check out an Item :"));
		/* adding the panel to the container  */
		contentPane.add("Center", centerPanel);

		/* adding the layout  */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button  */
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		/* adding the button to the panel  */
		southPanel.add(cancelButton);
		/* setting the border to the panel  */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the panel to the container  */
		contentPane.add("South", southPanel);

		/* adding the action listener to the button */
		reserveButton.addActionListener(new ActionListener() {
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
					//int noReserve = item.selectItem("SELECT * FROM ReserveList WHERE itemNumber like '" + data[0]+"'");
                                        
                                        if (noMembers != 0 )
                                        {
                                            String email = member.getEmail();
                                            String itemTitle = item.getTitle();
                                            String status = item.getStatus();
                                            
                                       
					//for checking if there is no same information in the database
                                        if ( status.equals("Checked out")){
                                                    reserve.updateReserve("INSERT INTO reservelist ( ItemNumber, MemberID, Email, startDate) VALUES ('" +
								        data[0].toUpperCase() + "','" + data[1].toUpperCase() + "', '" + email + "' ,'" + data[2] + "')");
                                                    item.updateItem("UPDATE Items SET status ='" + "Reserved" + "' WHERE (ItemNumber like '" + data[0] + "')");
							
                                                    //transaction.updateTransaction("INSERT INTO Transaction ( MemberID, itemNumber, itemTitle, inDate, outDate, TransactionType,  FinesPaid) VALUES ('" +
							//	        data[1].toUpperCase() + "','" + data[0].toUpperCase() + "','" + itemTitle + "','" + data[2] + "','" + data[2] + "','" +"Reserved" + "', " + fine + ")");
                                                    JOptionPane.showMessageDialog(null, " Item Number : " + data[0].toUpperCase()+ " reserved successfully ! " , "Information", JOptionPane.INFORMATION_MESSAGE);
                                                    //for setting the array of JTextField to null
                                                    clearTextField();
                                                    }   
                                                else
                                                    JOptionPane.showMessageDialog(null, "The Item not available for reservation.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            
                                        
                                         }  
                                       else
                                            JOptionPane.showMessageDialog(null, "The MemberID is invalid !", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                };
                            runner.start();
                        }
				/* if data is missing, then display Message Dialog */
                    else
                        JOptionPane.showMessageDialog(null, "Please, complete the information,", "Warning", JOptionPane.WARNING_MESSAGE);
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
