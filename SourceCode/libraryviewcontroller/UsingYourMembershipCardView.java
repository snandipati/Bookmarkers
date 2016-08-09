/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Silpa
 */
public class UsingYourMembershipCardView extends JInternalFrame{
    
    /***    Variables Declaration    ***/   
    
        /* creating Panels */    
        private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
        /* creating the text field */
        private JTextArea textArea = new JTextArea(20, 25);
        private String line ="";
        private String filler = "                 ";
        JLabel empty = new JLabel(filler);
        
        /* for creating the image and north Label */
	private ImageIcon image;
        private JLabel northLabel;
        
        /* Constructor of UsingYourMembershipCardView */
        public UsingYourMembershipCardView(){
                /*  setting the title for the internal frame */
                super("Using Your Membership Card", false, true, false, true);
                
                /* setting the icon */
                setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Info12.png")));
                /* setting the size pf the internal frame */
                setSize(500,500);
                
                /* getting the content Pane */
                Container cp = getContentPane();
                
                /* setting the image icon */
                image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/MembershipInfo.jpg"));
                northLabel = new JLabel(image);

                /* setting the layout */
                northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                
                /* setting the layout border */
                centerPanel.setBorder(BorderFactory.createTitledBorder("Membership Card Information : "));

                /* adding the label to the panel */
                northPanel.add(northLabel);
                /*for adding the panel to the container */
                cp.add("North", northPanel);

                /* setting the text area to non - editable*/
                textArea.setFont(new Font ("Arial", Font.BOLD, 12));
                textArea.setEditable(false);

                /* reading from text file */
                try{
                    Scanner sc= new Scanner(HomeScreenView.class.getResourceAsStream("UsingYourMembershipCard.dat"));
                    String str;
                    while(sc.hasNext()){
                        str = sc.nextLine();
                        line = line + "\n"+ str;
                     }
                }catch(Exception ex){ex.printStackTrace();}

                /* setting text */
                textArea.setText(line);
                //System.out.println(line);
                
                /* adding scrollbar */
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
                
                /* adding the textarea in to center panel */
                centerPanel.add ( scroll );

                //centerPanel.add( empty, BorderLayout.NORTH);
                //centerPanel.add( textArea);
                cp.add("Center", centerPanel);
                setVisible(true);

        }   
}
