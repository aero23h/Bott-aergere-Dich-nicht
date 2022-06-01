import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException, InterruptedException{
		// Create a game
		Game g = new Game();
		// start the game
		g.run();
		

		g.getBoard().getScore().move(01, 6);
		g.getBoard().getScore().move(11, 6);
		g.getBoard().getScore().move(11, 2);
		g.getBoard().getScore().move(01, 10);
		g.getBoard().getScore().move(01, 2);
		g.getBoard().plotScore2Console(g.getMenu().playerMenu(g.getBoard().getActPlayer()));
	}

}
