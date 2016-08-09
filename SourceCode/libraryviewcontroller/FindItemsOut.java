/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.Item;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Silpa
 */
/* This class is used to find out items that are chcekd out in the library */
public class FindItemsOut extends JPanel {
    
    /***    Variables Declaration    ***/

	/* creating Panels */
      	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
	/* creating the Internal Panels */
	private JPanel centerSelectPanel = new JPanel();
	private JPanel selectItemsPanel = new JPanel();
	private JPanel findButtonPanel = new JPanel();

	/* creating the labels */
	private JLabel jTitle = new JLabel("Find Number Items Currently Out");
	private JLabel itemKey = new JLabel(" Total No. of Items : ");
	
        /* creating the text field */
	private JTextField itemTextField = new JTextField();
	
        /* creating the button */
	private JButton findButton = new JButton("Find");
        private JButton cancelButton = new JButton("Cancel");
       
	/* creating the JComboBox and its labels */
	private JComboBox selectItemsTypes;
        private JLabel selectItemTypesLabel = new JLabel(" Select Type : ");
	private String[] itemsTypes = {"All" , "Fiction Books", "Non Fiction Books", "Videos"};
	
	private int indexItems;
        private String query;
        private String number;
	/* create objects from another classes for using them in the ActionListener */
	private Item item;
        
	/* constructor of FindTotalItems */
	public FindItemsOut() {
		
		/* for setting the layout */
                setLayout(new BorderLayout());
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                centerSelectPanel.setLayout(new BorderLayout());
                findButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		selectItemsPanel.setLayout(new GridLayout(2, 2, 1, 1));
                
                /* for creating and setting border */
                Border compound;
                Border blackline = BorderFactory.createLineBorder(Color.black);
                Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                compound = BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel);
                compound = BorderFactory.createCompoundBorder(
                          blackline, compound);
                setBorder ( compound );

                /* for setting the font */
		jTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
                
		/* for adding the label */
		northPanel.add(jTitle);
		
                add(northPanel, BorderLayout.NORTH);

                /* to set textField non Editable */
                itemTextField.setEditable(false);
            
		/* for adding the button */
		findButtonPanel.add(findButton);
		
		/* for setting the border */
		centerSelectPanel.setBorder(BorderFactory.createTitledBorder("Find count of Items :"));
		
		/* for adding the label */
		selectItemsPanel.add(selectItemTypesLabel);
		//for adding the JComboBos[]
		selectItemsPanel.add(selectItemsTypes = new JComboBox(itemsTypes));
		
		/* for adding the label */
		selectItemsPanel.add(itemKey);
		/* for adding the text field */
		selectItemsPanel.add(itemTextField);
		
                /* for adding the label */
                centerSelectPanel.add("North", selectItemsPanel);
                centerSelectPanel.add("South", findButtonPanel);
                centerPanel.add("Center", centerSelectPanel);
                
		/* for adding the center to the panel */
		add(centerPanel, BorderLayout.CENTER);

		/* for setting the font to the labels & buttons */
		itemTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		findButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		selectItemTypesLabel.setFont(new Font("Arial", Font.BOLD, 12));
		selectItemsTypes.setFont(new Font("Arial", Font.BOLD, 12));
		
		/* for adding the action listener to the button */  
                findButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                       
                                    item = new Item();
                                    indexItems = selectItemsTypes.getSelectedIndex();
                                    
                                    if(indexItems==0)
                                    {      
                                         query = "SELECT * FROM Items WHERE status like 'Checked out' ";
                                         int no = item.selectItem(query);
                                         number = Integer.toString(no);
                                         itemTextField.setText(number);
                                    }
                                    
                                  else if(indexItems == 1 )
                                    {
                                     
                                      query = "SELECT * FROM Items WHERE (itemType like 'Fiction' AND status like 'Checked out') " ;
                                      int no = item.selectItem(query);
                                      number = Integer.toString(no);
                                      itemTextField.setText(number);
                                    }
                                     
                                  else if(indexItems == 2)
                                  {
                                      query = "SELECT * FROM Items WHERE (itemType like 'Non Fiction' AND status like 'Checked out')  " ;
                                      int no = item.selectItem(query);
                                      number = Integer.toString(no);
                                      itemTextField.setText(number);
                                       
                                   }  
                                      
                                  else if(indexItems == 3 )
                                  {
                                      query = "SELECT * FROM Items WHERE (itemType like 'Video' AND status like 'Checked out')  " ;
                                      int no = item.selectItem(query);
                                      number = Integer.toString(no);
                                      itemTextField.setText(number);
                                    
                                  }
                                    
                                }            
			
                        });
		
		
		/* for setting the visible to true */
		setVisible(true);
	
	}
        /*
        public static void main(String[] args){
            JFrame f = new JFrame();
            FindItemsOut ft = new FindItemsOut();
            f.add(ft);
            f.setVisible(true);
            f.pack();
            
        }*/
}
