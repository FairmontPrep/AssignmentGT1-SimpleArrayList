
public class SimpleArrayParser {

	private SimpleArrayList thelist;
	private Board theboard;
	private static final String ADD = "ADD";
	private static final String REMOVE = "REMOVE";
	private static final String CLEAR = "CLEAR";
	private static final String CONTAINS = "CONTAINS";
	private static final String GET = "GET";
	private static final String SET = "SET";
	
	private static final String OK = "OK";
	private static final String INVALID = "Invalid input for %s. See commands for help.";
	private static final String UNRECOGNIZED = "Unrecognized command. See commands for help.";
	
	// Parser for this project
	// Requires the SimpleArrayList (to send it requests) and the Board (to update the Gui)
	public SimpleArrayParser(SimpleArrayList sal, Board b)
	{
		thelist = sal;
		theboard = b;
	}
	
	// Take input from the user and parse it for known commands
	// @param s a String containing what the user typed (one or more words)
	// reports unrecognized commands to the Gui
	public void parse(String s)
	{
		// Tokenize
		String[] tokenz = s.split(" ");
		
		String cmd = tokenz[0].toUpperCase();
		
		if (cmd.equals(ADD))
		{
			add(tokenz);
		}
		else if (cmd.equals(REMOVE))
		{
			remove(tokenz);
		}
		else if (cmd.equals(CLEAR))
		{
			clear(tokenz);
		}
		else if (cmd.equals(CONTAINS))
		{
			contains(tokenz);
		}
		else if (cmd.equals(GET))
		{
			get(tokenz);
		}
		else if (cmd.equals(SET))
		{
			set(tokenz);
		}
		else
		{
			theboard.getGui().setStatusMessage(UNRECOGNIZED);
			theboard.getGui().repaint();			
		}
			

	}
	
	// The user typed ADD
	public void add(String[] tokenz)
	{
		// Number of tokens is 2 or 3 only
		// ADD <stuff>
		// or
		// ADD <index> <stuff>
		
		String feedback;
		
		// ADD <stuff>
		if (tokenz.length == 2)
		{
			feedback = thelist.add(tokenz[1]);
		}
		// ADD <index> <stuff>
		else if (tokenz.length == 3)
		{
			int index;
			// Attempt to convert the index to an integer. If it works, add it
			try
			{
				index = Integer.parseInt(tokenz[1]);
				feedback = thelist.add(index, tokenz[2]);
			}
			catch (Exception e)
			{
				feedback = "Remove: Error interpreting index as number.";
			}
			
		}
		else
		{
			feedback = String.format(INVALID, "add");
		}
		
		theboard.getGui().setStatusMessage(feedback);
		theboard.getGui().repaint();
	}
	
	
	// The user typed REMOVE
	public void remove(String[] tokenz)
	{
		// Number of tokens is 2 only
		// REMOVE <stuff>
		// or
		// REMOVE <index>
				
		String feedback = OK;
				
		if (tokenz.length == 2)
		{
			// Attempt to see if the second token is a number.
			try
			{
				int index = Integer.parseInt(tokenz[1]);
				feedback = thelist.remove(index);
			}
			catch (Exception e)
			{
				// Not an integer
				feedback = thelist.remove(tokenz[1]);		
			}
		}
		else
		{
			feedback = String.format(INVALID, "remove");
		}
				
		theboard.getGui().setStatusMessage(feedback);
		theboard.getGui().repaint();
	}
	
	// The user typed CLEAR
	public void clear(String[] tokenz)
	{
		// Number of tokens is ignored
		// CLEAR
		// followed by anything will clear the list
		
		String feedback = thelist.clear();
		
		theboard.getGui().setStatusMessage(feedback);
		theboard.getGui().repaint();
	}
	
	
	// The user typed CONTAINS
	public void contains(String[] tokenz)
	{
		// Number of tokens is 2 only
		// CONTAINS <item>
		
		String feedback;
		
		if (tokenz.length == 2)
		{
			feedback = thelist.contains(tokenz[1]);
		}
		else
		{
			feedback = String.format(INVALID, "contains");
		}
		
		theboard.getGui().setStatusMessage(feedback);
		theboard.getGui().repaint();
	}
	
	
	// The user typed GET
	public void get(String[] tokenz)
	{
		// Number of tokens is 2 
		// GET <index>
		
		String feedback;
		
		if (tokenz.length == 2)
		{
			int index;
			try
			{
				index = Integer.parseInt(tokenz[1]);
				feedback = thelist.get(index);
			}
			catch (Exception e)
			{
				feedback = "Remove: Error interpreting index as number.";
			}
		}
		else
		{
			feedback = String.format(INVALID, "get");
		}
		
		theboard.getGui().setStatusMessage(feedback);
		theboard.getGui().repaint();
	}
	
	
	// The user typed SET
	public void set(String[] tokenz)
	{
		// Number of tokens is 3 
		// SET <index> <item>
			
		String feedback;
			
		if (tokenz.length == 3)
		{
			int index;
			try
			{
				index = Integer.parseInt(tokenz[1]);
				feedback = thelist.set(index, tokenz[2]);
			}
			catch (Exception e)
			{
				feedback = "Remove: Error interpreting index as number.";
			}
		}
		else
		{
			feedback = String.format(INVALID, "set");
		}
			
		theboard.getGui().setStatusMessage(feedback);
		theboard.getGui().repaint();
	}
}
