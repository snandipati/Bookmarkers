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
/* This class is used to display help information */
public class HelpView extends JInternalFrame{
    
    /***    Variables Declaration    ***/   
    
        /* creating Panels */    
        private JPanel northPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        
        /* creating the text field */
        private JTextArea textArea = new JTextArea(20, 25);
        private String line ="";
        private String filler = "                 ";
        JLabel empty = new JLabel(filler);
        
             
        
        /* Constructor of HelpView */
        public HelpView(){
                /*  setting the title for the internal frame */
                super("Help", false, true, false, true);
                /* setting the icon */
                setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Help12.png")));
                /* setting the size pf the internal frame */
                setSize(500,500);
                /* getting the content Pane */
                Container contentPane = getContentPane();

                /* setting the layout */
                northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                               
                /* setting the text area to non - editable*/
                textArea.setFont(new Font ("Arial", Font.BOLD, 12));
                textArea.setEditable(false);

                /* reading the file and setting the text */
                try{
                    Scanner sc= new Scanner(HomeScreenView.class.getResourceAsStream("Help.dat"));
                    String str;
                    while(sc.hasNext()){
                        str = sc.nextLine();
                        line = line + "\n"+ str;
                     }
                }catch(Exception ex){ex.printStackTrace();}


                textArea.setText(line);
                //System.out.println(line);
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
                /* adding the textarea in to center panel */
                centerPanel.add ( scroll );

                //centerPanel.add( empty, BorderLayout.NORTH);
                //centerPanel.add( textArea);
                contentPane.add("Center", centerPanel);
                setVisible(true);

            }    
}
