import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		Board b = new Board();
		Color c = new Color();
		Menu m = new Menu();
		
		b.getScore().loadFromFile("./saves/score", "score");
		b.plotScore2Console(m.buildWonMenu(b.getScore().getPlayers()[0]));

		
	}

}
