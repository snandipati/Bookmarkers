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
/* This class is used to flash the latest acquired items on the home page of library system*/
public class Flash extends JPanel{
    
    /***    Variables Declaration    ***/ 
    
        private ImageIcon image;
        private int width, height;
        private boolean flash;
        private Timer timer;

        /* Constructor of Flash */
        public Flash(){
        
            setSize(100, 100);
            flash = true;
            setBackground( Color.gray );
            image = new ImageIcon(getClass().getResource("new_books_videos.jpg"));
            width=image.getIconWidth();
            height=image.getIconHeight();

            timer=new Timer(1000,new Flash.TimerHandler());
            timer.start();
        }

        @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            if(flash==true){
                image.paintIcon(this,g,0,0);
            }
            else{
                g.fillRect(0,0,width,height);
            }
        }
    
        private class TimerHandler implements ActionListener{

            // respond to Timer's event
            public void actionPerformed( ActionEvent actionEvent )
            {
                flash = !flash;
                repaint(); /* repaint animator */
            } /* end method actionPerformed */
        } /* end class TimerHandler */

}  