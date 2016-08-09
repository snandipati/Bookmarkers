/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Silpa
 */
/* This calss contains the main function */
public class RunLibrarySystem implements Runnable{
    final Frame frame;

	public RunLibrarySystem(Frame frame) {
		this.frame = frame;
	}

        @Override
	public void run() {
		frame.show();
	}

	public static void main(String[] args) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		EventQueue.invokeLater(new RunLibrarySystem(new LibrarySystemGUI()));
	}
}
