/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 *
 * @author Silpa
 */

/* This class finds the total count of members in the library */
public class FindTotalMembers extends JPanel {
    
    /***    Variables Declaration    ***/
    
	/* creating Panels */
      	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
	/* creating the Internal Panels */
	private JPanel centerSelectPanel = new JPanel();
	private JPanel selectYearsPanel = new JPanel();
	private JPanel viewButtonPanel = new JPanel();

	/* creating the labels */
	private JLabel jTitle = new JLabel("Total Number of Members");
	private JLabel memberKey = new JLabel(" Total No. of Members : ");
	
        /* creating the text field */
	private JTextField memberTextField = new JTextField();
	
        /* creating the button */
	private JButton findButton = new JButton("Find");
        private JButton cancelButton = new JButton("Cancel");
       
	/* creating the JComboBox and its labels */
	private JComboBox selectYearsTypes;
        private JLabel selectYearsLabel = new JLabel(" Select Year : ");
	private String[] yearsTypes = {"All" , "2013", "2012", "2011", "2010", "2009"};
	
	
	/* create objects from another classes for using them in the ActionListener */
	private int indexYears;
        private int year;
        private String monthsQuery;
	private Member member;
        
	/* constructor of FindTotalMembers */
	public FindTotalMembers() {
		
		/* setting the layout */
                setLayout(new BorderLayout());
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                centerSelectPanel.setLayout(new BorderLayout());
                viewButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		selectYearsPanel.setLayout(new GridLayout(2, 2, 1, 1));
                
                /* creating and setting border */
                Border compound;
                Border blackline = BorderFactory.createLineBorder(Color.black);
                Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                compound = BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel);
                compound = BorderFactory.createCompoundBorder(
                          blackline, compound);
                setBorder ( compound );

                /* setting the font */
		jTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
                
		/* adding the label */
		northPanel.add(jTitle);
		
                add(northPanel, BorderLayout.NORTH);

                /* to set textField non Editable */
                memberTextField.setEditable(false);
            
		/* adding the button */
		viewButtonPanel.add(findButton);
		
		/* setting the border */
		centerSelectPanel.setBorder(BorderFactory.createTitledBorder("Find count of Members :"));
		
		/* adding the label */
		selectYearsPanel.add(selectYearsLabel);
		//for adding the JComboBos[]
		selectYearsPanel.add(selectYearsTypes = new JComboBox(yearsTypes));
		
		/* adding the label */
		selectYearsPanel.add(memberKey);
		/* adding the text field */
		selectYearsPanel.add(memberTextField);
		
                /* adding the label */
                centerSelectPanel.add("North", selectYearsPanel);
                centerSelectPanel.add("South", viewButtonPanel);
                centerPanel.add("Center", centerSelectPanel);
                
		/* adding the center to the panel */
		add(centerPanel, BorderLayout.CENTER);

		/* setting the font to the labels & buttons */
		
		memberTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		findButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		selectYearsLabel.setFont(new Font("Arial", Font.BOLD, 12));
		selectYearsTypes.setFont(new Font("Arial", Font.BOLD, 12));
		
		/* adding the action listener to the button */  
                findButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                         
                                   
                                    member = new Member();
                                    indexYears = selectYearsTypes.getSelectedIndex();
                                    //checking the index of JComboBox
                                    if(indexYears==0)
                                    {      
                                         monthsQuery = "SELECT * FROM Member ";
                                         int no = member.selectMember(monthsQuery);
                                         String number = Integer.toString(no);
                                         memberTextField.setText(number);
                                    }
                                    
                                  else if(indexYears == 1 )
                                    {
                                      year = 2013;
                                      String yearQuery = "SELECT * FROM Member WHERE  Year(startDate) = " + year ;
                                      int no = member.selectMember(yearQuery);
                                      String number = Integer.toString(no);
                                      memberTextField.setText(number);
                                    }
                                     
                                  else if(indexYears == 2)
                                  {
                                      year = 2012;
                                      String yearQuery = "SELECT * FROM Member WHERE  Year(startDate) = " + year ;
                                      int no = member.selectMember(yearQuery);
                                      String number = Integer.toString(no);
                                      memberTextField.setText(number);
                                       
                                   }  
                                      
                                  else if(indexYears == 3 )
                                  {
                                    year = 2011;
                                      String yearQuery = "SELECT * FROM Member WHERE  Year(startDate) = " + year ;
                                      int no = member.selectMember(yearQuery);
                                      String number = Integer.toString(no);
                                      memberTextField.setText(number);
                                    
                                  }
                                    else if(indexYears == 4 )
                                  {
                                    year = 2010;
                                      
                                      String yearQuery = "SELECT * FROM Member WHERE  Year(startDate) = " + year ;
                                      int no = member.selectMember(yearQuery);
                                      String number = Integer.toString(no);
                                      memberTextField.setText(number);
                                    
                                  }
                                    else if(indexYears == 5 )
                                  {
                                    year = 2009;
                                      
                                      String yearQuery = "SELECT * FROM Member WHERE  Year(startDate) = " + year ;
                                      int no = member.selectMember(yearQuery);
                                      String number = Integer.toString(no);
                                      memberTextField.setText(number);
                                    
                                  }
                       
                                }            
		
				
                        });
		
		
		/* setting the visible to true */
		setVisible(true);
	
	}
        
        
}