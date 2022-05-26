import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException{
		Board b = new Board();
		Color c = new Color();

		Player p = new Player("Kevin", c.getYellow());
		
		
		b.getScore().setPlayer(p);
		b.plotScore2Console();
		
		b.getScore().move(01, 6);
		b.getScore().move(12, 6);
		b.getScore().move(23, 6);
		b.getScore().move(34, 6);
		b.plotScore2Console();
	}

}
