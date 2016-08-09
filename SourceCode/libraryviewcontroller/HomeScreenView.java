/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;

/**
 *
 * @author Silpa
 */
public class HomeScreenView extends JInternalFrame{
    
    /***    Variables Declaration    ***/

        /* creating the Panels */
        private JPanel northPanel = new JPanel();
        private JPanel southPanel = new JPanel();
        private JPanel centerPanel = new JPanel();
        private JPanel westPanel = new JPanel();
        private JPanel westPanel1 = new JPanel();
        private JPanel westPanel2 = new JPanel();

        /* creating the Labels */
        private ImageIcon image;
        private JLabel northLabel;
        
        /* creating String and Labels */
        private String filler = "                 ";
        JLabel empty = new JLabel(filler);
        JLabel ac = new JLabel(" New Additions of the Month ");
        
        /* creating the text area */
        private String lines ="";
        private JTextArea textArea = new JTextArea(50, 50);
       
        /* calculating the dimension of the internal frame */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private int height = screenSize.height;
        private int width = screenSize.width;

        int x=(2*width)/9, y=(2*height)/9;
        
        Flash flash = new Flash();
    
        /* A method to create the moving text */
        @Override
        public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                Font font = new Font ("Tahoma", Font.BOLD+Font.PLAIN, 14);
                g2.setFont(font);
                g2.setColor(Color.white);
                g2.drawString("Welcome To BookMarkers Library!", x, y);

                try{
                    Thread.sleep(100);
                    }        
                catch(InterruptedException e){ 
                    e.printStackTrace();
                }
                x-=10;
                if(x<(-350)){
                    x=width;   
                }
                repaint();  

        }
        
        /* Constructor of HomeScreenView */    
        public HomeScreenView(){
                /* setting the title for the internal frame */
                super("Home", false, true, false, true);
                /* setting the size of the internal frame */
                setSize(1020,650);
                /* setting the icon */
                setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("LibraryImages/Home12.png")));

                /* setting the image */
                image= new ImageIcon(ClassLoader.getSystemResource("LibraryImages/Menubar.jpg"));
                northLabel = new JLabel(image);
                /* getting content Pane */
                Container contentPane = getContentPane();

                /* setting the layout */
                northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                westPanel.setLayout(new BorderLayout());
                westPanel1.setLayout(new GridLayout(10, 1, 1, 1));
                westPanel2.setLayout(new GridLayout(10, 1, 1, 1));

                /* adding the label in the North Panel */
                northPanel.add(northLabel);
                textArea.setEditable(false);

                /* adding the north panel to the container */
                contentPane.add("North", northPanel);

                /* creating and setting border */
                Border blackline = BorderFactory.createLineBorder(Color.black);
                TitledBorder bTitle = BorderFactory.createTitledBorder(
                               blackline,  " Acquistions ");
                bTitle.setTitleJustification(TitledBorder.CENTER);
                westPanel.setBorder(bTitle);
                /* setting font of button */
                ac.setFont(new Font("Arial", Font.BOLD, 12));
                
                /* A try block to read from the text file */
                try{
                    Scanner sc= new Scanner(HomeScreenView.class.getResourceAsStream("HomeM.dat"));
                    String str= null;
                    while(sc.hasNext()){
                        str = sc.nextLine();
                        lines = lines + "\n"+ str;
                     }
                }
                /* A catch block */
                catch(Exception ex){ex.printStackTrace();}

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate= new java.sql.Date(date.getTime());

                /* finding the current month and the current year 
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);                             	
                int year = cal.get(Calendar.YEAR);
                month = month +1; */

                /* adding the west panels to the west Panel */
               westPanel.add(ac, BorderLayout.NORTH);

               
                westPanel.add(flash, BorderLayout.CENTER);
                /* setting the font */
                textArea.setFont(new Font ("Arial", Font.BOLD, 12));

                /*setting the text of text area */
                textArea.setText(lines);

                /* adding the text are to the center Panel */
                centerPanel.add( textArea );
                southPanel.add(empty);

                /* adding the panels to the container */
                contentPane.add("Center", centerPanel);
                contentPane.add("West", westPanel);
                contentPane.add("South", southPanel);

                setVisible(true);
               // pack();

        }
}