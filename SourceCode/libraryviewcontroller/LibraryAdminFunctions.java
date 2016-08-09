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
/* This class is used for library administrator functions */
public class LibraryAdminFunctions extends JInternalFrame {
        
        
	/* creating Panels */
        private JPanel loginPanel = new JPanel();
        private JPanel loginNorthPanel = new JPanel();
	private JPanel loginCenterPanel = new JPanel();
        private JPanel loginSouthPanel = new JPanel();
        
        private JPanel westPanel1 = new JPanel();
        private JPanel eastPanel1 = new JPanel();
        
        private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
        
	/* creating an Internal Panel in the center panel */
	private JPanel dataPanel = new JPanel();
        private JPanel submitButtonPanel = new JPanel();
        
        /* creating the North Label */
	private JLabel loginLabel = new JLabel(" Login Information ");
        /* creating the date in the String */
	private String inDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
	
	/* creating an array of JLabel */
	private JLabel[] dataLabel = new JLabel[3];
	/* creating an array of String */
	private String[] informationString = {" Login ID : "," Password : " ," The Current Date : "};
	
	/* creating an array of JTextField */
	private JTextField[] informationTextField = new JTextField[2];
        
        /* creating an array of JTextField */
	private JPasswordField informationPasswordField = new JPasswordField();
	
	/* creating a button */
	private JButton submitButton = new JButton("Submit");
        private JButton logOutButton = new JButton("Log out");
		
        
	/* objects to use them in the ActionListener */
	Member member;
        LibraryAdmin libAdmin; 
        
	/* creating an array of string to store the data */
	private String[] data;
        private Container cp = getContentPane();
        private FindTotalMembers fm = new FindTotalMembers();
        private FindTotalItems ft = new FindTotalItems();
        private FindItemsOut fou = new FindItemsOut();
        private FindItemCheckedOutMost fic = new FindItemCheckedOutMost();
        private FindTotalFine fti = new FindTotalFine();
        
	/* A method to check the information from the text field */
	public boolean isTextCorrect() {
		data = new String[3];
		for (int i = 0; i < dataLabel.length; i++) {
			if (i == 1 ) {
				if (!informationPasswordField.getText().equals(""))
				        data[i] = informationPasswordField.getText();
                                else
                                   return false;      
			}
                    
                        else if (i == 0 ) {
				if (!informationTextField[i].getText().equals("")) {
					data[i] = informationTextField[i].getText();
				}
				else
					return false;
			}
			else if ( i == 2 ) {
				if (!informationTextField[i - 1].getText().equals("")) {
					data[i] = informationTextField[i - 1].getText();
				}
				else
					return false;
			}
			
		}
		return true;
	}
        
	/* A method to set the array of JTextField & JPasswordField to null */
	public void clearTextFields() {
            for (int i = 0; i < dataLabel.length-1; i++) {
		
                        informationTextField[i].setText(null);
                
            }
	} 
        
        
        /* Constructor of LibraryAdminFunctions */
        public LibraryAdminFunctions(){
                /* setting the title for the internal frame */
		super(" Library Administrator Functions ", false, true, false, true);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("LibraryImages/LibraryAdmin12.png")));
		/* getting the content Pane */
		setSize(900, 500);
               
		/* setting the layout  */
                loginPanel.setLayout(new BorderLayout());
		loginNorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		loginSouthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                westPanel1.setLayout(new GridLayout(2, 1, 1, 1));
                eastPanel1.setLayout(new GridLayout(2, 1, 1, 1));
                
                /* setting the font */
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		/* adding the label to the panel */
		loginNorthPanel.add(loginLabel);
		               
                /* adding the panel to the loginPanel */
                loginPanel.add(loginNorthPanel, BorderLayout.NORTH);
                loginPanel.add(loginCenterPanel, BorderLayout.CENTER);
                loginPanel.add(loginSouthPanel, BorderLayout.SOUTH);
                
                 /* adding the panel to the container */
                cp.add("North", loginPanel);
                
                centerPanel.add(ft);
                westPanel1.add(fm); 
                westPanel1.add(fou);
                eastPanel1.add(fic);
                eastPanel1.add(fti); 
                
		/* setting the layout */
		loginCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		/* setting the layout for the internal panel */
		dataPanel.setLayout(new GridLayout(3, 2, 1, 1));

		/* adding the strings to the labels and adding the panel to 
                 * the container						   *
		 */
		for (int i = 0; i < dataLabel.length; i++) {
			dataPanel.add(dataLabel[i] = new JLabel(informationString[i]));
			dataLabel[i].setFont(new Font("Tahoma", Font.BOLD, 12));
                        if(i == 0 ) {
				dataPanel.add(informationTextField[i] = new JTextField());
				informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
                        else if(i == 1 ) {
				dataPanel.add(informationPasswordField = new JPasswordField());
				informationPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
                        else if (i == 2) {
				dataPanel.add(informationTextField[i-1] = new JTextField(inDate));
				informationTextField[i-1].setFont(new Font("Tahoma", Font.PLAIN, 12));
				informationTextField[i-1].setEnabled(false);
			}
                        
                        
		}
		loginCenterPanel.add("Center", dataPanel);
                
		/* setting the layout */
		submitButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* setting the font to the button */
		submitButton.setFont(new Font("Arial", Font.BOLD, 12));
		/* adding the button to the panel */
		submitButtonPanel.add(submitButton);
		/* adding the panel to the center panel */
		loginSouthPanel.add( submitButtonPanel);
		/* setting the border to the panel */
		loginPanel.setBorder(BorderFactory.createTitledBorder(" Enter Login details :"));
			

		/* adding the layout */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/*setting the font to the button */
		logOutButton.setFont(new Font("Arial", Font.BOLD, 12));
		/* adding the button to the panel */
		southPanel.add(logOutButton);
		/*setting the border to the panel */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the panel to the container */
		cp.add("South", southPanel);
            
                        
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                       /* checking if data is missing */
                            
                        if (isTextCorrect()) {
                            Thread runner = new Thread() {
                                @Override
                                public void run() {    
                       
                                libAdmin = new LibraryAdmin();
                                int no = libAdmin.selectLibraryAdmin("SELECT * FROM LibraryAdmin WHERE login like '" + data[0]+"'");
                                // check if data is same in the database
                                if(no!=0)
                                {
                                    String password = libAdmin.getPassword();
                                    //checking if password is correct
                                    if(password.equals(data[1]))
                                    {
                                        cp.remove(loginPanel);
                                        cp.add("West", westPanel1);
                                        cp.add("Center", centerPanel);
                                        cp.add("East", eastPanel1);
                                        revalidate();
                                        repaint();
                                        JOptionPane.showMessageDialog(null, " Login is Successful !", "Information", JOptionPane.INFORMATION_MESSAGE);
                                            
                                    }
                                    // if password is not correct, then display dialog
                                    else
                                        JOptionPane.showMessageDialog(null, " The Password is not correct ! ", "Error", JOptionPane.ERROR_MESSAGE);
                                    
                                }
                                
                                // if data is not same as the database
                                else 
                                    JOptionPane.showMessageDialog(null, " The login ID is invalid ! ", "Error", JOptionPane.ERROR_MESSAGE);

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
                logOutButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		/* setting the visible to true */
		setVisible(true);
				
	}
}
