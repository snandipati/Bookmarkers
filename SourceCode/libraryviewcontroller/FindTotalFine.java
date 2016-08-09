/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.Transaction;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Silpa
 */
/* This class is used to find total fine in the library */
public class FindTotalFine extends JPanel{
    
    /***    Variables Declaration    ***/

	/* creating Panels */
      	private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
	/* creating the Internal Panels */
	private JPanel centerSelectPanel = new JPanel();
	private JPanel selectPanel = new JPanel();
	private JPanel findButtonPanel = new JPanel();

	/* creating the labels */
	private JLabel jTitle = new JLabel("Find Total Fines");
	private JLabel fineKey = new JLabel(" Total Fines Collected : ");
	
        /* creating the text field */
	private JTextField fineTextField = new JTextField();
	
        /* creating the button */
	private JButton fromDateButton = new JButton("From");
        private JButton toDateButton = new JButton("To");
        private JButton findButton = new JButton("Find");
        private JButton cancelButton = new JButton("Cancel");
       
        /* creating the text field */
	private JTextField fromDateTextField = new JTextField(10);
        private JTextField toDateTextField = new JTextField(10);
        
         /* creating an array of string to store the data */
	private String[] data;
        
	/* creating String */
	private String query;
        private String number;
    
	/* objects from another classes to use them in the ActionListener */
	private Transaction transaction;
     
        
        /* A method to check the data from the text field */
        public boolean isCorrect() {
                data = new String[2];
                if ((!fromDateTextField.getText().equals("")) &&
                        (!toDateTextField.getText().equals("") ) ){
                                data[0] = fromDateTextField.getText();
                                data[1] = toDateTextField.getText();
                }
                else
                                return false;
                    
                return true;
        }
        
        
	/* constructor of FindTotalFine */
	public FindTotalFine() {
		
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
                
		/* to set textField non Editable */
                add(northPanel, BorderLayout.NORTH);

                /* to set textField non Editable */
                fineTextField.setEditable(false);
            
		/* adding the button */
		findButtonPanel.add(findButton);
		
		/* setting the border */
		centerSelectPanel.setBorder(BorderFactory.createTitledBorder("Find count of Items :"));
		
                selectPanel.add(fromDateButton);
                selectPanel.add(fromDateTextField);
                selectPanel.add(toDateButton);
                selectPanel.add(toDateTextField);
               
                
		/* adding the label */
		selectPanel.add(fineKey );
		/* adding the text field */
		selectPanel.add(fineTextField);
		
                /* adding the label */
                centerSelectPanel.add("North", selectPanel);
                centerSelectPanel.add("South", findButtonPanel);
                centerPanel.add("Center", centerSelectPanel);
                
		/* adding the center to the panel */
		add(centerPanel, BorderLayout.CENTER);

		/* setting the font to the labels & buttons */
		fineTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		findButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
             
                /* adding the action listener to the button */ 
                fromDateButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        fromDateTextField.setText(new DatePicker(selectPanel).setPickedDate());
                    }
                });
		
                /* adding the action listener to the button */ 
                toDateButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        toDateTextField.setText(new DatePicker(selectPanel).setPickedDate());
                    }
                });
                 
                 
                
                /* adding the action listener to the button */  
                findButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                                    transaction = new Transaction();
                                    if (isCorrect()){
                                        query = "SELECT SUM(FinesPaid) FROM Transaction WHERE (startDate between '" + data[0]+ "' AND '" + data[1]+ "')  ";
                                        double sum = transaction.findSum(query);
                                        number = Double.toString(sum);
                                        fineTextField.setText(number);
                                       // System.out.println(data[0]); 
                                       // System.out.println(data[1]);
                                    }
                                    else
                                        JOptionPane.showMessageDialog(null, "Please, complete the information for Find Total Fines ", "Warning", JOptionPane.WARNING_MESSAGE);
                               
                    }
                });
				
		/* for setting the visible to true */
		setVisible(true);
	
	}
        /*
        public static void main(String[] args){
            JFrame f = new JFrame();
            FindTotalFine ft = new FindTotalFine();
            f.add(ft);
            f.setVisible(true);
            f.pack();
            
        }*/
}
