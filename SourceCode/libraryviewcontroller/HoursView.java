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
public class HoursView extends JInternalFrame {
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
        
        /* Constructor of HoursView */
        public HoursView(){
                /*  setting the title for the internal frame */
                super("Library Hours", false, true, false, true);
                /* setting the icon */
                setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Clock12.png")));
                /* setting the size pf the internal frame */
                setSize(350,350);

                Container contentPane = getContentPane();

                image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/lib_hours.jpg"));
                northLabel = new JLabel(image);

                /* setting the layout */
                northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new BorderLayout());
                /* setting the layout border */
                centerPanel.setBorder(BorderFactory.createTitledBorder("Hours : "));

                /* adding the label to the panel */
                northPanel.add(northLabel);
                /*for adding the panel to the container */
                contentPane.add("North", northPanel);

                /* setting the text area to non - editable*/
                textArea.setFont(new Font ("Arial", Font.BOLD, 12));
                textArea.setEditable(false);

                /* readind data from a text file */
                try{
                    Scanner sc= new Scanner(HomeScreenView.class.getResourceAsStream("hours.dat"));
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
