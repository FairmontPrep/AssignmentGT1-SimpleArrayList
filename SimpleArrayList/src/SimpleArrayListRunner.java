public class SimpleArrayListRunner {

	/**
	 * Runner for a PokemonCode version of SimpleArrayList
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		// Create the board 
		Board board = new SimpleArrayListBoard();
				
		// Create the gui and use the board's info to run the game.
		int width = 400;
		int height = 400;
		Gui gui = new Gui(board, width, height, board.getTitle());
		gui.setTextWindow("");
		gui.displayGame();
	}
	
}