/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.Transaction;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Silpa
 */
public class FindItemCheckedOutMost extends JPanel{
     
    /***    Variables Declaration    ***/

	/* creating Panels */
      	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
	/* creating the Internal Panels */
	private JPanel centerSelectPanel = new JPanel();
	private JPanel selectPanel = new JPanel();
	private JPanel findButtonPanel = new JPanel();

	/* creating the labels */
	private JLabel jTitle = new JLabel("Find Item Checked out the Most");
	private JLabel itemKey = new JLabel(" Item Title : ");
	
        /* cearting the text field */
	private JTextField fineTextField = new JTextField();
	
        /* creating the button */
	private JButton findButton = new JButton("Find");
        private JButton cancelButton = new JButton("Cancel");
       
	/* creating the Lable */
	private JLabel selectMonthsLabel = new JLabel(" Select Month : ");
        private JLabel selectYearsLabel = new JLabel(" Select Year : ");
        private JLabel selectItemTypesLabel = new JLabel(" Select Type : ");
        
	/* creating JComboBox */
	private JComboBox selectMonthsTypes;
        private JComboBox selectYearsTypes;
        private JComboBox selectItemsTypes;
        
	/* creating String[] */
	private String[] monthsTypes = {"All", "January", "February", "March", "April", "May", "June",
                                        "July", "August", "September", "October", "November", "December"};
	private String[] yearsTypes = {"2013", "2012", "2011", "2010", "2009"};
	private String[] itemsTypes = {"Fiction Books", "Non Fiction Books", "Videos"};
	
        
	private int indexMonths, indexYears, indexItems;
        private String query;
        private String itemTitle;
	/* create objects from another classes for using them in the ActionListener */
	private Transaction transaction;
        private int year;
        
	/* constructor of FindTotalItems */
	public FindItemCheckedOutMost() {
		
		/* setting the layout */
                setLayout(new BorderLayout());
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                centerSelectPanel.setLayout(new BorderLayout());
                findButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		selectPanel.setLayout(new GridLayout(4, 2, 1, 1));
                
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

                /* setting textField non Editable */
                fineTextField.setEditable(false);
            
		/* adding the button */
		findButtonPanel.add(findButton);
		
		/* setting the border */
		centerSelectPanel.setBorder(BorderFactory.createTitledBorder("Find count of Items :"));
		
                /* adding the label */
                selectPanel.add(selectYearsLabel);
                /* for adding the JComboBos[] */
                selectPanel.add(selectYearsTypes = new JComboBox(yearsTypes));
		/* for adding the label */
		selectPanel.add(selectMonthsLabel);
		/* adding the JComboBos[] */
		selectPanel.add(selectMonthsTypes = new JComboBox(monthsTypes));
		
                selectPanel.add(selectItemTypesLabel);
		/* for adding the JComboBos[] */
		selectPanel.add(selectItemsTypes = new JComboBox(itemsTypes));
		
                
		/* for adding the label */
		selectPanel.add(itemKey );
		/* for adding the text field */
		selectPanel.add(fineTextField);
		
                /* for adding the label */
                centerSelectPanel.add("North", selectPanel);
                centerSelectPanel.add("South", findButtonPanel);
                centerPanel.add("Center", centerSelectPanel);
                
		/* adding the center to the panel */
		add(centerPanel, BorderLayout.CENTER);

		/* setting the font to the labels & buttons */
		
		fineTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		findButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		selectMonthsLabel.setFont(new Font("Arial", Font.BOLD, 12));
                selectYearsLabel.setFont(new Font("Arial", Font.BOLD, 12));
		selectItemTypesLabel.setFont(new Font("Arial", Font.BOLD, 12));
		selectYearsTypes.setFont(new Font("Arial", Font.BOLD, 12));
		
                /* adding the action listener to the button */  
                findButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                                    Transaction transaction = new Transaction();
                                    indexYears = selectYearsTypes.getSelectedIndex();
                                    indexMonths = selectMonthsTypes.getSelectedIndex();
                                    indexItems = selectItemsTypes.getSelectedIndex();
                                    String type = null;
                                    /* checking the index of itemtypes comboBox */
                                    if(indexItems == 0)
                                    {
                                        type = "Fiction";
                                        
                                    }
                                    else if(indexItems == 1)
                                    {
                                        type = "Non Fiction";
                                        
                                    }
                                    else if(indexItems == 2)
                                    {
                                        type = "Video";
                                    }
                                    
                                    /* checking the index of yeartypes comboBox */
                                    
                                    if (indexYears == 0)
                                        year = 2013;
                                    else if(indexYears ==1)
                                        year = 2012;
                                    else if(indexYears ==2)
                                        year = 2011;
                                    else if(indexYears ==3)
                                        year = 2010;
                                    else 
                                        year = 2009;
                                    
                                    /* checking the index of monthtypes comboBox */
                                    if (indexMonths == 0){
                                            
                                           query = "SELECT *, count(TransactionType) as score FROM Transaction WHERE (Year(startDate) = " + year + " AND ItemType like '" + type + "') order by score desc limit 1";
                                           int no = transaction.selectTransaction(query);
                                           if(no != 0)
                                                itemTitle = transaction.getItemTitle();
                                           else
                                                itemTitle = "NoRecord";
                                           fineTextField.setText(itemTitle);
                                           
                                        }
                                        else{
                                            query = "SELECT *, count(TransactionType) as score FROM Transaction WHERE (ItemType like '" + type + "' AND (Year(startDate) = " + year + " AND Month (startDate) = " + indexMonths + ")) order by score desc limit 1";
                                           int no = transaction.selectTransaction(query);
                                           if(no != 0)
                                                itemTitle = transaction.getItemTitle();
                                           else
                                                itemTitle = "NoRecord";
                                           fineTextField.setText(itemTitle);
                                        }
                    }
                        });
		
		
		/* for setting the visible to true */
		setVisible(true);
	
	}
        /*
        public static void main(String[] args){
            JFrame f = new JFrame();
            FindItemCheckedOutMost ft = new FindItemCheckedOutMost();
            f.add(ft);
            f.setVisible(true);
            f.pack();
            
        }*/
}
