import java.awt.Point;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.net.URL;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
/**
 * This class provides a GUI for grid-based games.
 * This class started life as the GUI class for ElevensBoard but has been significantly modified.
 */
@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener {

	
		
	private static final int MARGIN_TOP = 10;
	private static final int MARGIN_SIDE = 10;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 700;
	private int width = WIDTH;
	private int height = HEIGHT;
	

	/** The board (<code>Board</code> subclass). */
	private Board board;

	/** The main panel containing the game components. */
	private JPanel panel;
	/** The text field - console field for entering commands */
	private JTextField textField;
	/** The text window - console window under the game board tiles */
	private JTextArea textWindow;
	/** The text window - console window under the game board tiles */
	private JTextArea helpWindow;
	/** The status message - message on the bottom of the window*/
	private JLabel statusMsg;
	
	/** The text in the status message */
	private String statusMsgText = "Status: OK";
	/** Whether or not to  print to console */
	private boolean suppress = false;
	/** Whether or not we are currently running a test */
	private boolean inTest = false;
	
	/**
	 * Creates a new <code>Gui</code> object to control all the drawing.
	 * @param gameBoard The <code>Board</code> derived class containing game/program logic
	 * @param rows The number of rows of tiles
	 * @param columns The number of columns of tiles
	 * @param applicationName The string to appear in the title of the application window
	 */
	public Gui(Board gameBoard, int width, int height, String applicationName) 
	{
		this(gameBoard, width, height, applicationName, false);
	}


	/**
	 * Tertiary constructor to create a new <code>Gui</code> object for testing
	 * @param gameBoard The <code>Board</code> derived class containing game/program logic
	 * @param rows The number of rows of tiles
	 * @param columns The number of columns of tiles
	 */
	public Gui(Board gameBoard, int width, int height, String applicationName, boolean inTest)
	{
		board = gameBoard;
		gameBoard.setGui(this);
		this.inTest = inTest;
		this.height = height;
		this.width = width;
		
		initDisplay(applicationName);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (!inTest)
		{
			repaint();
		}
		
		// Create a KeyEvent manager to capture key presses
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new
		           KeyEventDispatcher() 
		{
			public boolean dispatchKeyEvent(KeyEvent event)
		    {
				if (getFocusOwner() == null) return false;
		        String text = KeyStroke.getKeyStrokeForEvent(event).toString();
		        final String PRESSED = "pressed ";                  
		        int n = text.indexOf(PRESSED);
		        if (n < 0) return false;
		        // filter out modifier keys; they are neither characters or actions
		        if (event.getKeyChar() == KeyEvent.CHAR_UNDEFINED && !event.isActionKey()) 
		        	return false;
		        text = text.substring(0, n)  + text.substring(n + PRESSED.length());
		        boolean consumed = board.keyPressed(text);
		        if (consumed) repaint();
		        return consumed;
		     }
		 });
	}
	
	
	
	
	/**
	 * Run the game.
	 */
	public void displayGame() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}

	public void resize()
	{
	}
	
	/**
	 * Draw the display (tiles and messages).
	 */
	public void repaint() {		
		// Set the status message
		statusMsg.setText(statusMsgText);
		statusMsg.setVisible(true);
		pack();
		panel.repaint();
	}

	/**
	 * Initialize the display.
	 */
	
    
	private void initDisplay(String applicationName)	
	{
		int y_realestate = 0;
		panel = new JPanel(null); 

		setTitle(applicationName);
		setSize();
		
		// Create a text field for entering commands
		textField = new JTextField();
		textField.addActionListener(this);
		int space = 25;
		textField.setBounds(MARGIN_SIDE, MARGIN_TOP, width - 2*MARGIN_SIDE, space);
		y_realestate += space;
		
		// Create the text console window
		textWindow = new JTextArea();
		textWindow.setEditable(false);
		space = height - 100;
		textWindow.setBounds(MARGIN_SIDE, MARGIN_TOP + y_realestate, (width/2)-MARGIN_SIDE, height - 100);
		textWindow.setVisible(true);
		
		// Create the help console window
		helpWindow = new JTextArea();
		helpWindow.setEditable(false);
		helpWindow.setBounds(MARGIN_SIDE + width/2, MARGIN_TOP + y_realestate, (width/2)-MARGIN_SIDE, height - 100);
		textWindow.setVisible(true);
		y_realestate += space;
		
		// Add a scrollbar to the text console window
		JScrollPane scroll = new JScrollPane(textWindow);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    //scroll.setBounds(MARGIN_SIDE, MARGIN_TOP, 600, 600);
	    new SmartScroller(scroll);
	    
	    panel.add(textField);
	    panel.add(textWindow);
	    panel.add(helpWindow);
	    panel.add(scroll);
	    	    
		// Create the totals message
		statusMsg = new JLabel("OK");
		space = 50;
		statusMsg.setBounds(MARGIN_SIDE, MARGIN_TOP + y_realestate, width-2*MARGIN_SIDE, space);
		panel.add(statusMsg);
    
		helpWindow.setText(board.getInitialText());
		
		pack();
		getContentPane().add(panel);
		panel.setVisible(true);
	}

	/**
	 * Set the size of the application based upon the tile size
	 */
	private void setSize()
	{
		this.setSize(new Dimension(width, height));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(width, height));
	}
	

		
	/**
	 * Respond to a button click (on any of the four buttons).
	 * @param e the button click action event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(textField))
		{
			String text = textField.getText();
			board.textInput(text,  false);
			textField.setText("");
		}
		else {
			return;
		}
	}

	
	/**
	 * Call the <code>Board</code>'s step() method exactly once.
	 */
	public void step()
	{
		board.step();
	}
	
	
	/**
	 * Set the status message (first of the four text labels on the right of the application window)
	 * @param s The <code>String</code> to set the label
	 */
	public void setStatusMessage(String s)
	{
		statusMsgText = s;
	}

	
	/**
	 * Add a string to the console window below the game tiles
	 * @param s The <code>String</code> to add to the console window
	 */
	public void appendTextWindow(String s)
	{
		textWindow.append(s + "\n");
		if (!suppress)
		{
			System.out.println(s);
		}
	}
	
	/**
	 * Add a string to the console window below the game tiles
	 * @param s The <code>String</code> to add to the console window
	 */
	public String getTextWindow()
	{
		String s = textWindow.getText();
		return s;
	}
	
	
	/**
	 * Replace the contents of the console window below the game tiles
	 * @param s The <code>String</code> to show in the console window
	 */
	public void setTextWindow(String s)
	{
		textWindow.setText(s + "\n");
		if (!suppress)
		{
			System.out.println(s);
		}
	}
	
	public void setHelpText(String s)
	{
		helpWindow.setText(s + "\n");
		if (!suppress)
		{
			System.out.println(s);
		}
	}
	
	public void setSuppress(boolean noText)
	{
		suppress = noText;
	}
	
	public void setInTest(boolean inTest)
	{
		this.inTest = inTest;
	}
	
	public boolean getInTest()
	{
		return inTest;
	}
	
	
}
