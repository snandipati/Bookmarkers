/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Silpa
 */
/* This class is used to show the main frame Library System of our program*/
public class LibrarySystemGUI extends JFrame implements ActionListener {

    /***    Variables Declaration    ***/
    
    /* creating Panels */
    private JPanel searchPanel = new JPanel();
       
    /* creating JDeskTopPane */
    private JDesktopPane desktopPane = new JDesktopPane();
    
    /* creating objects to use them in the ActionListener **/
    private MenuBarView menu;
    private ToolBarView toolbar;
  
    private HomeScreenView homeScreenView;
    private HoursView hoursView;
    
    private SearchScreenView searchScreen;
    
    private CheckOutItem checkOutItem;
    private ReturnItem returnItem;
    private ReserveItem reserveItem;
    private TransactionHistory transactionHistory;
         
    private GetMembershipView getMembership;
    private UsingYourMembershipCardView usingYourMembershipCard;
    
    private Claims claim;
    
    private PayFine payfine;
    private LibraryAdminFunctions findTotalMembers;
    
    private HelpView helpView;

    /* constructor of LibrarySystemGUI */
    public LibrarySystemGUI(){
        /* setting the title for the frame */
        super("Automated Library Assistant");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
         /* setting an icon for the program */
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.getImage(ClassLoader.getSystemResource("libraryimages/computer.png"));
        setIconImage(image);
        
        /* setting the menu bar */
        menu = new MenuBarView();
        setJMenuBar(menu);
        toolbar = new ToolBarView();
        
        /* adding the actionListener to menu items*/
        menu.aboutLibItem.addActionListener(this);
        menu.hoursItem.addActionListener(this);
        menu.searchItem.addActionListener(this);
        menu.checkOutItem.addActionListener(this);
        menu.returnItem.addActionListener(this);
        menu.reserveItem.addActionListener(this);
        menu.payFineItem.addActionListener(this);
        menu.viewTransactionItem.addActionListener(this);
        menu.getMembershipItem.addActionListener(this);
        menu.membershipInformationItem.addActionListener(this);
        menu.claimLostItem.addActionListener(this);
        menu.libraryAdminfunctions.addActionListener(this);
        menu.helpItem.addActionListener(this);
        menu.exitItem.addActionListener(this);
        

        /* get the the conten Pane */
        Container contentPane = getContentPane();
        desktopPane.setBackground(Color.GRAY);
        contentPane.add("Center", desktopPane);
       
        /* setting the layout */
        searchPanel.setLayout(new BorderLayout());
        /* adding the toolBar to the searchPanel */
        searchPanel.add("Center", toolbar);
        
        /* adding the searchPanel to the Container */
        contentPane.add("North", searchPanel);
        

        for (int i = 0; i < toolbar.imagesToolbar.length; i++) {
            //for adding the action to the button
            toolbar.buttonsToolbar[i].addActionListener(this);
        }
        
         homeScreenView = new HomeScreenView();
         desktopPane.add(homeScreenView);
        
        /* adding WindowListener to the program */
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //show the program
        show();
}

       
    @Override
    public void actionPerformed(ActionEvent ae) {     
        /* showing home page */
        if (ae.getSource() == menu.aboutLibItem || ae.getSource() == toolbar.buttonsToolbar[0]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    homeScreenView = new HomeScreenView();
                    desktopPane.add(homeScreenView);
                    try {
                    homeScreenView.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing library hours frame */
        if (ae.getSource() == menu.hoursItem || ae.getSource() == toolbar.buttonsToolbar[1]) {
            Thread t = new Thread() {

                public void run() {
                    hoursView = new HoursView();
                    desktopPane.add(hoursView);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = hoursView.getSize();
                    hoursView.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        hoursView.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing search an item frame */
        if (ae.getSource() == menu.searchItem || ae.getSource() == toolbar.buttonsToolbar[2]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    searchScreen = new SearchScreenView();
                    desktopPane.add(searchScreen);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = searchScreen.getSize();
                    searchScreen.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        searchScreen.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing check out an item frame */
        if (ae.getSource() == menu.checkOutItem || ae.getSource() == toolbar.buttonsToolbar[3]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    checkOutItem = new CheckOutItem();
                    desktopPane.add(checkOutItem);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = checkOutItem.getSize();
                    checkOutItem.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        checkOutItem.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing return an item frame */
        if (ae.getSource() == menu.returnItem || ae.getSource() == toolbar.buttonsToolbar[4]) {
            Thread runner = new Thread() {

                @Override
                public void run() {
                    returnItem = new ReturnItem();
                    desktopPane.add(returnItem);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = returnItem.getSize();
                    returnItem.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        returnItem.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            runner.start();
        }
        
        /* showing reserve an item frame */
        if (ae.getSource() == menu.reserveItem || ae.getSource() == toolbar.buttonsToolbar[5]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    reserveItem = new ReserveItem();
                    desktopPane.add(reserveItem);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = reserveItem.getSize();
                    reserveItem.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        reserveItem.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing pay fine frame */
        if (ae.getSource() == menu.payFineItem || ae.getSource() == toolbar.buttonsToolbar[6]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                  payfine = new PayFine();
                    desktopPane.add(payfine);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = payfine.getSize();
                    payfine.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    
                    try {
                        payfine.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    } 
                }
            };
            t.start();
        }
        
        /* showing transaction history frame */
        if (ae.getSource() == menu.viewTransactionItem || ae.getSource() == toolbar.buttonsToolbar[7]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    transactionHistory = new TransactionHistory();
                    desktopPane.add(transactionHistory);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = transactionHistory.getSize();
                    transactionHistory.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        transactionHistory.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing get membership frame */
        if (ae.getSource() == menu.getMembershipItem || ae.getSource() == toolbar.buttonsToolbar[8]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    getMembership = new GetMembershipView();
                    desktopPane.add(getMembership);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = getMembership.getSize();
                    getMembership.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        getMembership.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing membership information frame */
        if (ae.getSource() == menu.membershipInformationItem|| ae.getSource() == toolbar.buttonsToolbar[9]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    usingYourMembershipCard = new UsingYourMembershipCardView();
                    desktopPane.add(usingYourMembershipCard);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = usingYourMembershipCard.getSize();
                    usingYourMembershipCard.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        usingYourMembershipCard.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing claims frame */
        if (ae.getSource() == menu.claimLostItem || ae.getSource() == toolbar.buttonsToolbar[10]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    claim = new Claims();
                    desktopPane.add(claim);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = claim.getSize();
                    claim.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    
                    try {
                        claim.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing library administrator functions frame */
        if (ae.getSource() == menu.libraryAdminfunctions || ae.getSource() == toolbar.buttonsToolbar[11]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    findTotalMembers = new LibraryAdminFunctions();
                    desktopPane.add(findTotalMembers);
                    
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = findTotalMembers.getSize();
                    findTotalMembers.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    
                    try {
                        findTotalMembers.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        
        /* showing help frame */
        if (ae.getSource() == menu.helpItem || ae.getSource() == toolbar.buttonsToolbar[12]) {
            Thread t = new Thread() {

                @Override
                public void run() {
                    helpView = new HelpView();
                    desktopPane.add(helpView);
                    Dimension desktopSize = desktopPane.getSize();
                    Dimension jInternalFrameSize = helpView.getSize();
                    helpView.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                            (desktopSize.height- jInternalFrameSize.height)/2);
                    try {
                        helpView.setSelected(true);
                    }
                    catch (java.beans.PropertyVetoException e) {
                    }
                }
            };
            t.start();
        }
        /* exiting from the program */
        if (ae.getSource() == menu.exitItem || ae.getSource() == toolbar.buttonsToolbar[13]) {
            dispose();
            System.exit(0);
        }
        
    }
}