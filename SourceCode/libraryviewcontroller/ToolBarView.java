/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import javax.swing.*;

/**
 *
 * @author Silpa
 */

/* This class represents the toolbar for the Automated Library Assistant System */
public class ToolBarView extends JToolBar {
    
    /***    Variables Declaration    ***/
	
	/* creating the buttons to use them in ToolBar */
	public JButton[] buttonsToolbar;
        
	/* creating the name of the image file */
	public String[] imagesToolbar = {"libraryimages/home.png",
                                         "libraryimages/clock.png","libraryimages/search.png",
	                                 "libraryimages/checkout.png", "libraryimages/return.png",
	                                 "libraryimages/reserve.png", "libraryimages/fine.png",
	                                 "libraryimages/transaction.png", "libraryimages/member.png",
	                                 "libraryimages/info.png", "libraryimages/claim.png",
	                                 "libraryimages/libraryAdmin.png",
	                                 "libraryimages/help.png",  "libraryimages/exit.png"};                            
	                              
        
	/* creating the tipText for the toolbar buttons */
	public String[] tipText = {"Home", "Library Hours and Contact", "Search", "Check out an Item",
	                           "Return an Item", "Reserve an Item", "Pay Fine", "Transaction History",
	                           "Get a Membership Card", "Using your Membership Card", "Claim as Lost",  
                                   "Library Administrator Functions","Help",  "Exit"};

	/* Constructor of ToolBarView */
        public ToolBarView() {
		buttonsToolbar = new JButton[14];
		for (int i = 0; i < imagesToolbar.length; i++) {
			if (i == 2 || i == 3 || i == 8 || i == 10 || i == 11 || i == 12 ) {
                                /* adding separator to the toolbar */
				addSeparator();
                        }        
                        /* adding the buttons to toolbar */
			add(buttonsToolbar[i] = new JButton(new ImageIcon(ClassLoader.getSystemResource(imagesToolbar[i]))));
			/* setting the ToolTipText to the buttons of toolbar */
			buttonsToolbar[i].setToolTipText(tipText[i]);
		}
	}
}