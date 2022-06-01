import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException, InterruptedException{
		// Create a game
		Game g = new Game();
		// start the game
		g.run();
	}

}
