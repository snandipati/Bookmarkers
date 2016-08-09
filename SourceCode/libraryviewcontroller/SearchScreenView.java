/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import librarymodel.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


/**
 *
 * @author Silpa
 */
/* This class is use for search an item in the Automated Library Assistant System */
public class SearchScreenView extends JInternalFrame{
     
    /***    Variables Declaration    ***/
    
        private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
        
	/* creating Panels */
	private JPanel northPanel = new JPanel();
        private JPanel center = new JPanel();
        private JPanel southPanel = new JPanel();
        
	/* creating the Center Panel */
	private JPanel centerBooksPanel = new JPanel();
	/* creating the Internal Panels in the center panel */
	private JPanel searchBooksPanel = new JPanel();
	private JPanel searchBooksButtonPanel = new JPanel();
        
        /* creating the North Label */
	private ImageIcon image;
        private JLabel northLabel;

	/* creating the table */
	private JLabel searchBooksLabel = new JLabel(" Search by: ");
	/* for JComboBox */
	private JComboBox searchBooksTypes;
	/* creating String[] */
	private String[] booksTypes = {"Item Number", "Title", "Author"};
	/* creating the label */
	private JLabel booksKey = new JLabel(" Enter the Keyword : ");
	/* cearting the text field */
	private JTextField booksKeyTextField = new JTextField();
	/* creating the button */
	private JButton searchBooksButton = new JButton("Search");

	/* creating the Center Panel */
	private JPanel centerVideosPanel = new JPanel();
        
	/* creating the Internal Panels in the center panel */
	private JPanel searchVideosPanel = new JPanel();
	private JPanel searchVideosButtonPanel = new JPanel();
        
        private JPanel textAreaPanel = new JPanel();
        private JTextArea textArea = new JTextArea(15,40);
        
        /* creating the label */
	private JLabel searchVideosLabel = new JLabel(" Search by : ");
	//* for JComboBox */
	private JComboBox searchVideosTypes;
	/* creating String[] */
	private String[] videosTypes = {"Item Number", "Title", "Director"};
	/* creating the label */
	private JLabel videosKey = new JLabel(" Enter the Keyword : ");
	/* creating the text field */
	private JTextField videosKeyTextField = new JTextField();
	
        /* creating the buttons */
	private JButton searchVideosButton = new JButton("Search");
	private JButton cancelButton = new JButton("Cancel");

	/* creating an array of string to store the data */
	private String[] booksData;
	private String[] videosData;
	
        /* objects from another classes for using them in the ActionListener */
	private int index;
        
	private Item book;
	private Item video;
        

	/* A method to check the information from the text field */
	public boolean isBooksDataCorrect() {
		booksData = new String[2];
		booksData[0] = searchBooksTypes.getSelectedItem().toString();
		for (int i = 1; i < booksData.length; i++) {
			if (!booksKeyTextField.getText().equals("")) {
				if (searchBooksTypes.getSelectedItem().toString().equals("Item Number")) {
					booksData[i] = booksKeyTextField.getText();
				}
				else
                                    booksData[i] = booksKeyTextField.getText();	
                                  //  booksData[i] = "'%" + booksKeyTextField.getText() + "%'";
			}
			else
				return false;
		}
		return true;
	}

	/* A method to check the information from the text field */
	public boolean isVideosDataCorrect() {
		videosData = new String[2];
		videosData[0] = searchVideosTypes.getSelectedItem().toString();
		for (int i = 1; i < videosData.length; i++) {
			if (!videosKeyTextField.getText().equals("")) {
				if (searchVideosTypes.getSelectedItem().toString().equals("Item Number")) {
					videosData[i] = videosKeyTextField.getText();
				}
				else
					videosData[i] =  videosKeyTextField.getText() ;
			}
			else
				return false;
		}
		return true;
	}
        
        /* A method to display information of items */
        public int displayItem(String query){
             int row = 0;
             textArea.setText("");
                   try {
			connection = DBMSConnectivity.getConnection();     
                        statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
                        
                            textArea.append("::::::: SEARCH RESULTS ::::::\n\n");
                            textArea.append("Item No: " + "\tTitle: "+ "\t\tAuthor/Director: " + "\t\tType: "+ "\tShelf No:"+ "\tStatus: \n");
                            while (resultSet.next()) {
				textArea.append("\n"+resultSet.getString("ItemNumber") + 
                                        "\t" + resultSet.getString("Title")+
                                        "\t\t"+ resultSet.getString("Author")+ "\t\t" + 
                                        resultSet.getString("ItemType") + "\t" + 
                                        resultSet.getString("ShelfNumber")
                                        + "\t" + resultSet.getString("Status"));
                                row++;
                            }
                            textArea.append("\n\n :::::::: END :::::::: \n");
                        
                        resultSet.close();
			statement.close();
			connection.close();
                    }
                    catch (SQLException SQLe) {
                            System.out.println(SQLe.toString());
		}
                return row;
        
        }
        

	/* Constructor of SearchScreenView */
	public SearchScreenView() {
		/* setting the title for the internal frame */
		super("Search", false, true, false, true);
                // setSize(500, 500);
		/* setting the icon */
		setFrameIcon(new ImageIcon(ClassLoader.getSystemResource("libraryimages/Search12.png")));
		/* getting the content Pane */
		Container contentPane = getContentPane();

		/* setting the layout */
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                center.setLayout(new BorderLayout());
                centerBooksPanel.setLayout(new BorderLayout());
                searchBooksPanel.setLayout(new GridLayout(2, 2, 1, 1));
                
		image= new ImageIcon(ClassLoader.getSystemResource("libraryimages/Search.jpg"));
                northLabel = new JLabel(image);


                /* adding the label to the panel */
                northPanel.add(northLabel);
                /* adding the panel to the container */
                contentPane.add("North", northPanel);
                
                textAreaPanel.setBorder ( BorderFactory.createTitledBorder("Display Area" ) );

                /* to set textArea non Editable */
                textArea.setEditable(false);
                textAreaPanel.add(textArea);
                
                /* creating the middle panel components */
                JScrollPane scroll = new JScrollPane ( textArea );
                scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

                /* adding Textarea in to middle panel */
                textAreaPanel.add ( scroll );
              		
		/* adding the label */
		searchBooksPanel.add(searchBooksLabel);
		/* adding the JComboBos[] */
		searchBooksPanel.add(searchBooksTypes = new JComboBox(booksTypes));
		/* adding the label */
		searchBooksPanel.add(booksKey);
		/* adding the text field */
		searchBooksPanel.add(booksKeyTextField);
		/* adding the internal panel to the panel */
		centerBooksPanel.add("North", searchBooksPanel);

		/* setting the layout */
		searchBooksButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* adding the button */
		searchBooksButtonPanel.add(searchBooksButton);
		/* adding the internal panel to the center panel */
		centerBooksPanel.add("South", searchBooksButtonPanel);
                
                
		/* setting the border */
		centerBooksPanel.setBorder(BorderFactory.createTitledBorder("Search for a Book:"));
		
                /* adding panels to the center Panel */
		center.add("West", centerBooksPanel);
                center.add("South", textAreaPanel);
                
		/* setting the layout */
		centerVideosPanel.setLayout(new BorderLayout());
		searchVideosPanel.setLayout(new GridLayout(2, 2, 1, 1));
		
                /* adding the label */
		searchVideosPanel.add(searchVideosLabel);
		/* adding the JComboBos[] */
		searchVideosPanel.add(searchVideosTypes = new JComboBox(videosTypes));
		/* adding the label */
		searchVideosPanel.add(videosKey);
		/* adding the text field */
		searchVideosPanel.add(videosKeyTextField);
		/* adding the internal panel to the panel */
		centerVideosPanel.add("North", searchVideosPanel);

		/* setting the layout */
		searchVideosButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* adding the button */
		searchVideosButtonPanel.add(searchVideosButton);
		/* adding the internal panel to the center panel */
		centerVideosPanel.add("South", searchVideosButtonPanel);
		/* setting the border */
		centerVideosPanel.setBorder(BorderFactory.createTitledBorder("Search for a Video:"));
		/* adding center panel to the center */
		center.add("East", centerVideosPanel);

		/* adding the center to the container */
		contentPane.add("Center", center);

		/**
		 *for setting the font to the labels & buttons
		 */
		searchBooksLabel.setFont(new Font("Arial", Font.BOLD, 12));
		searchBooksTypes.setFont(new Font("Arial", Font.BOLD, 12));
		booksKey.setFont(new Font("Arial", Font.BOLD, 12));
		booksKeyTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		searchBooksButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		searchVideosLabel.setFont(new Font("Arial", Font.BOLD, 12));
		searchVideosTypes.setFont(new Font("Arial", Font.BOLD, 12));
		videosKey.setFont(new Font("Arial", Font.BOLD, 12));
		videosKeyTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		searchVideosButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));

		/* setting the layout */
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		/* adding the button */
		southPanel.add(cancelButton);
		/* setting the border */
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		/* adding the south panel to the container */
		contentPane.add("South", southPanel);
                
		/* adding the action listener to the button */
                searchBooksButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        /* checking if there is a missing information */
			if (isBooksDataCorrect()) {
					
                            Thread runner = new Thread() {
                                @Override
                                public void run() {
                                    book = new Item();
                                    index = searchBooksTypes.getSelectedIndex();
                                                                                                   
                                   /* checking the index of JCombobox */  
                                    if(index == 0){

                                        String bookQuery = "SELECT * FROM Items WHERE (ItemNumber LIKE '%" + booksData[1]+ "%' AND (itemType like 'Fiction' OR Itemtype like 'Non Fiction')) AND (Status NOT like 'Lost' )";
                                        int no = displayItem(bookQuery);
                                        if (no == 0)
                                                textArea.setText("\nSorry! No Match");
                                    }   

                                    else if(index == 1){

                                        String bookQuery = "SELECT * FROM Items WHERE (Title LIKE '%" + booksData[1]+ "%' AND (itemType like 'Fiction' OR Itemtype like 'Non Fiction')) AND (Status NOT like 'Lost' )";
                                        //displayItem = new DisplayItem(bookQuery);

                                        int no = displayItem(bookQuery);
                                        if (no == 0)
                                                textArea.setText("\nSorry! No Match");
                                           
                                    }
                                    else if(index == 2){

                                          String bookQuery = "SELECT * FROM Items WHERE (Author LIKE '%" + booksData[1]+ "%' AND (itemType like 'Fiction' OR Itemtype like 'Non Fiction')) AND (Status NOT like 'Lost' ) ";
                                          int no = displayItem(bookQuery);
                                          if (no == 0)
                                                textArea.setText("\nSorry! No Match");   
                                    }
                                    
                                }            
		
                            };
                            runner.start();
                        }
				
                        else
                            JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
		});
                
		/* adding the action listener for the button to serach */
                searchVideosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (isVideosDataCorrect()) {
					Thread runner = new Thread() {
                                    @Override
                                    public void run() {
                                    video = new Item();
                                    index = searchVideosTypes.getSelectedIndex();
                                    /* chceking the index of JCombobox */                                                                 
                                    
                                    if(index==0)
                                    {      
                                     String videoQuery = "SELECT * FROM Items WHERE (ItemNumber LIKE '%" + videosData[1]+ "%' AND (itemType like 'Video')) AND (Status NOT like 'Lost' ) ";
                                     int no = displayItem(videoQuery);
                                        if (no == 0)
                                            textArea.setText("\nSorry! No Match");   
                                     
                                    }
                                    else if(index==1)
                                    {
                                        String videoQuery = "SELECT * FROM Items WHERE (title LIKE '%" + videosData[1]+ "%' AND (itemType like 'Video')) AND (Status NOT like 'Lost' ) ";
                                        int no = displayItem(videoQuery);
                                        if (no == 0)
                                            textArea.setText("\nSorry! No Match");         
					
                                    }
                                    else if(index==2)
                                    {
                                      String videoQuery = "SELECT * FROM Items WHERE (Author LIKE '%" + videosData[1]+ "%' AND (itemType like 'Video')) AND (Status NOT like 'Lost' ) ";
                                      int no = displayItem(videoQuery);
                                        if (no == 0)
                                            textArea.setText("\nSorry! No Match");
                                     
                                    }
                                 
            
                                }            
		
                                };
				runner.start();
                            }
				
                            else
					JOptionPane.showMessageDialog(null, "Please, complete the information", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		/* adding the action listener for the button to dispose the frame */
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		/* setting the visible to true */
		setVisible(true);
		pack();
	}
}
