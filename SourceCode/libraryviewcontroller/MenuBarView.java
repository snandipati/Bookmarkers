/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;;

import javax.swing.*;

/**
 *
 * @author Silpa
 */

/* This class represents the menubar for the Automated Library Assistant System */
public class MenuBarView extends JMenuBar {

    /***    Variables Declaration    ***/
    
        /* creating menus for the menubar */
        public JMenu aboutMenu, searchMenu, myAccountMenu,  usingLibraryMenu, 
                 claimsMenu, libAdminMenu,helpMenu;
    
        /* creating the menu items for the menus */
        public JMenuItem aboutLibItem, hoursItem;
        public JMenuItem searchItem;
        public JMenuItem checkOutItem,  returnItem, reserveItem,  payFineItem,  
                         viewTransactionItem;
        public JMenuItem  getMembershipItem,  membershipInformationItem;
        public JMenuItem claimLostItem;
        public JMenuItem libraryAdminfunctions;
        public JMenuItem helpItem, exitItem;

        /* creating an imageIcon */
        public ImageIcon[] icons;
        /* creating the name of the image file */
        public String[] imagefiles12 = {"libraryimages/Home12.png", "libraryimages/Clock12.png", 
                                        "libraryimages/Search12.png", "libraryimages/CheckOut12.png",
                                        "libraryimages/Return12.png",
                                        "libraryimages/Reserve12.png", "libraryimages/Fine12.png",
                                        "libraryimages/Transaction12.png", "libraryimages/Member12.png",
                                        "libraryimages/Info12.png", "libraryimages/Claim12.png",
                                        "libraryimages/LibraryAdmin12.png",
                                        "libraryimages/Help12.png", "libraryimages/Exit12.png"};

        /* Constructor of MenuBarView */
        public MenuBarView() {
            
            /* adding menus to the menubar */
            this.add(aboutMenu = new JMenu("About"));
            this.add(searchMenu = new JMenu("Search"));
            this.add(myAccountMenu = new JMenu("My Account"));
            this.add(usingLibraryMenu = new JMenu("Using the Library"));
            this.add(claimsMenu = new JMenu("Claims"));
            this.add(libAdminMenu = new JMenu("Library Admin"));
            this.add(helpMenu = new JMenu("Help"));

            aboutMenu.setMnemonic('A');
            searchMenu.setMnemonic('S');
            myAccountMenu.setMnemonic('M');
            usingLibraryMenu.setMnemonic('U');
            claimsMenu.setMnemonic('C');
            libAdminMenu.setMnemonic('L');
            helpMenu.setMnemonic('H');

            /* setting the image icons */
            icons = new ImageIcon[14];
            for (int i = 0; i < imagefiles12.length; i++) {
                icons[i] = new ImageIcon(ClassLoader.getSystemResource(imagefiles12[i]));
            }

            /* adding home page and library information */
            aboutMenu.add(aboutLibItem = new JMenuItem("Home Page", icons [0]));
            aboutMenu.add(hoursItem = new JMenuItem("Library Hours", icons [1]));

            /* adding menu item to the Search Menu */
            searchMenu.add(searchItem = new JMenuItem("Search an Item", icons[2]));

            /*adding check out, return and reserve to the myAccount Menu */
            myAccountMenu.add(checkOutItem = new JMenuItem("Check out an Item", icons[3]));
            myAccountMenu.add(returnItem = new JMenuItem("Return an Item", icons[4]));
            myAccountMenu.addSeparator();
            myAccountMenu.add(reserveItem = new JMenuItem("Reserve an Item", icons[5]));
            myAccountMenu.addSeparator();
            myAccountMenu.add(payFineItem = new JMenuItem("Pay Fine", icons[6]));
            myAccountMenu.add(viewTransactionItem = new JMenuItem("Transaction History", icons[7]));


            /* adding menu items to Using the Library Menu */
            usingLibraryMenu.add(getMembershipItem = new JMenuItem("Get a Memebership Card", icons[8]));
            usingLibraryMenu.addSeparator();
            usingLibraryMenu.add(membershipInformationItem = new JMenuItem("Using your Membership Card", icons[9]));

            /* adding menu items to the Claims Menu */
            claimsMenu.add(claimLostItem = new JMenuItem("Claim Lost Item", icons[10]));

            /* adding menu items to the Library Admin Menu */
            libAdminMenu.add(libraryAdminfunctions = new JMenuItem("Library Administrator Functions", icons[11]));
           
            /* adding menu items to the help Menu */
            helpMenu.add(helpItem = new JMenuItem("Help", icons[12]));
            helpMenu.addSeparator();
            helpMenu.add(exitItem = new JMenuItem("Exit", icons[13]));

        }
        
        
}