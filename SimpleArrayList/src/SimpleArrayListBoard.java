
import java.awt.event.MouseEvent;

public class SimpleArrayListBoard extends Board {

	/**
	 * Creates a new <code>PokeBoard</code> with the specified number of rows and columns.
	 */
	
	private SimpleArrayList thelist;
	private SimpleArrayParser theparser;
	
	public SimpleArrayListBoard(int rows, int columns) 
	{
		// Call the Board constructor to make a 6x6 Board
	 	super(rows, columns);
	 	newGame(false);
	 	setTitle("SimpleArrayList");
	 	setInitialText("Type commands in the box above.\n"
	 			+ "The left box will show your list.\n"
	 			+ "ADD <item>\n"
	 			+ "ADD <index> <item>\n"
	 			+ "REMOVE <item>\n"
	 			+ "REMOVE <index>\n"
	 			+ "CLEAR\n"
	 			+ "CONTAINS <item>\n"
	 			+ "GET <index>\n"
	 			+ "SET <index> <item>");
	 	thelist = new SimpleArrayList();
	 	theparser = new SimpleArrayParser(thelist, this);
	}
	
	public SimpleArrayListBoard() 
	{
		this(1, 6);
	}
	
	public void setHelpText(String s)
	{
		Gui g = getGui();
		g.setHelpText(s);
	}
	
	

	/**
	 * Initial placement of all PokeThings in the PokeBoard
	 */
	@Override
	public void newGame(boolean repaint)
	{
		super.newGame(repaint);
	}
	
	
	@Override
	/**
	 * Directs any text input to the PikachuThing in the board
	 */
	public void textInput(String s, boolean fromConsole)
	{
		theparser.parse(s);
		getGui().setTextWindow(thelist.toString());
	}
	
	

	
}
