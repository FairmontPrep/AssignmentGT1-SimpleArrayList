import java.util.ArrayList;

public class SimpleArrayList {
	
	private ArrayList<String> thelist;
	
	private static final String OK = "OK";
	private static final String INVALID_INDEX_NEGATIVE = "Invalid input - index cannot be negative.";
	private static final String INVALID_INDEX_SIZE = "Invalid input - index cannot be greater than the size of the list.";
	private static final String ITEM_NOT_PRESENT = "Item not in list.";
	private static final String LIST_CONTAINS = "List contains ";
	private static final String GET = "GET";
	private static final String RETURNED = " returned ";
	
	// FIXME
	public SimpleArrayList()
	{
	
	}
	
	// FIXME - add to the end of the list
	// Should always succeed
	// @param s the String to add to the list
	// @return status message (this one should always be OK
	public String add(String s)
	{
	}
	
	// FIXME - add to the index specified
	// @param i the index at which to add String s\
	// @param s the String to add to the list
	// @returns INVALID_INDEX_NEGATIVE if i < 0, INVALID_INDEX_SIZE if i >= current size of the list, or OK
	public String add(int i, String s)
	{
	}
	
	// FIXME - clear the list of all items
	// @return OK because it should always succeed
	public String clear()
	{
	}
		
	// FIXME - does the list contain a specific item?
	// @param s a String that may or may not be in the list
	// @return a String of the format: "List contains <s>: <true/false>"
	// for example:
	// List contains itemOne: true
	// List contains banana: false
	// use the string constants LIST_CONTAINS
	public String contains(String s)
	{
	}
	
	// FIXME - remove the item at index i from the list
	// @param i the index to be removed
	// @returns INVALID_INDEX_NEGATIVE if i < 0, INVALID_INDEX_SIZE if i >= current size of the list, or OK
	public String remove(int i)
	{
	}
		
	// FIXME - remove the item matching String s from the list
	// @param s a String to remove from the list
	// @return ITEM_NOT_PRESENT if s is not in the list, OK otherwise
	public String remove(String s)
	{
	}
	
	
	// FIXME - get the item at the specified index from the list
	// @param i the index to get from
	// @returns INVALID_INDEX_NEGATIVE if i < 0, INVALID_INDEX_SIZE if i >= current size of the list, or 
	// use the string constants GET and RETURNED to create the format:
	// GET <index> returned <item>
	// such as
	// GET 1 returned donut
	public String get(int i)
	{
	}
	
	
	// FIXME - set the item at the specified index in the list
	// @param i the index to set
	// @returns INVALID_INDEX_NEGATIVE if i < 0, INVALID_INDEX_SIZE if i >= current size of the list, or OK
	public String set(int i, String s)
	{
	}
		
	// FIXME - return a string representation of this list
	// @return a String containing all items in the list, separated by new lines
	// For example, if the list contains ["one", "two", "three"]
	// then toString() should return "one\ntwo\nthree\n"
	public String toString()
	{
	}
}

