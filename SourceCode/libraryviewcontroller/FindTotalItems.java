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
/* This class is used to find total items in the library */
public class FindTotalItems extends JPanel{
    
    /***    Variables Declaration    ***/

	/* creating Panels */
      	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
	/* creating the internal Panels */
	private JPanel centerSelectPanel = new JPanel();
	private JPanel informationPanel = new JPanel();
	private JPanel viewButtonPanel = new JPanel();
        private JPanel pieChartPanel = new JPanel();
        
	/* creating the label */
	private JLabel jTitle = new JLabel("Total Number of Items");
	
        /* creating the text fields */
	private JTextField[] itemTextFields = new JTextField[4];
	
        /* creating the buttons */
	private JButton findButton = new JButton("Find");
        private JButton cancelButton = new JButton("Cancel");
       
	
        /* creating an array of JLabel */
	private JLabel[] itemLabel = new JLabel[4];
	/* creating an array of String */
	private String[] itemString = { "No. of Fiction Books : ", "No. of Non Fiction Books : ", "No. of Videos : ", "Total : "};
	private String[] query = new String[4];
	
        /* objects from another classes */
        Item item;       
        ItemsPieChartView pieChartView = new ItemsPieChartView();
        
	/* constructor of FindTotalItems */
	public FindTotalItems() {
		
		/* setting the layout */
                setLayout(new BorderLayout());
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                centerSelectPanel.setLayout(new BorderLayout());
                viewButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		informationPanel.setLayout(new GridLayout(4, 2, 1, 1));
                
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
                               
                                
                 /* setting the border */
		centerSelectPanel.setBorder(BorderFactory.createTitledBorder("Find out number of Items :"));
		
                for (int i = 0; i < itemLabel.length; i++) {
			informationPanel.add(itemLabel[i] = new JLabel(itemString[i]));
			itemLabel[i].setFont(new Font("Arial", Font.BOLD, 12));
			informationPanel.add(itemTextFields[i] = new JTextField());
				itemTextFields[i].setFont(new Font("Arial", Font.PLAIN, 12));
			
		}
		
                /* to set textField non Editable */
                for (int i =0; i < itemTextFields.length; i++ ){
                    itemTextFields[i].setEditable(false);
                    itemTextFields[i].setFont(new Font("Arial", Font.PLAIN, 12));
                }
                
                /* adding the button */
		viewButtonPanel.add(findButton);
		
                /* adding the label */
                centerSelectPanel.add("North", informationPanel);
                centerSelectPanel.add("South", viewButtonPanel);
                centerPanel.add("Center", centerSelectPanel);
                centerPanel.add(pieChartPanel, BorderLayout.SOUTH);
                
                pieChartPanel.setBorder(new TitledBorder("Items Chart"));
                // add Items to ItemsPieChartView
                pieChartView.addItem(0, " Fiction Books", 0, Color.RED);
                pieChartView.addItem(1, " Non Fiction Books", 0, Color.GREEN);
                pieChartView.addItem(2, " Videos", 0, Color.BLUE);

                pieChartPanel.add(pieChartView);
		/* adding the center to the panel */
		add(centerPanel, BorderLayout.CENTER);

		/* setting the font to the labels & buttons */
		findButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		
		/* adding the action listener to the button */  
                findButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                            Thread runner = new Thread() {
                                 @Override
                                public void run() {
                                    item = new Item();
                                    /* queries for finding total items by type */
                                    query[0] = "SELECT * FROM Items WHERE (itemType like 'Fiction' AND Status NOT like 'Lost' )" ;
                                    query[1] = "SELECT * FROM Items WHERE (itemType like 'Non Fiction') AND (Status NOT like 'Lost' )" ;
                                    query[2] = "SELECT * FROM Items WHERE (itemType like 'Video') AND (Status NOT like 'Lost' ) " ;
                                    query[3] = "SELECT * FROM Items WHERE Status NOT like 'Lost' " ;
                                    int noItems;
                                    for (int i =0; i < itemTextFields.length; i++ ){
                                            noItems  = item.selectItem(query[i]);
                                            String number = Integer.toString(noItems );
                                            itemTextFields[i].setText(number);
                                            pieChartView.setCount(i, noItems);
                                            pieChartView.repaint();
                                    
                                }
                            }
                        }; 
                        runner.start();
                    }            
				
            });
		
		/* for setting the visible to true */
		setVisible(true);
	
	}
        /*
        public static void main(String[] args){
            JFrame f = new JFrame();
            FindTotalItems ft = new FindTotalItems();
            f.add(ft);
            f.setVisible(true);
            f.pack();
            
        }*/
}