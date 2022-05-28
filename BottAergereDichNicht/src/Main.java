import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		Board b = new Board();
		Color c = new Color();
		
		//b.getScore().loadFromFile("score.json");
		//b.plotScore2Console();
		
		//b.getScore().init(2);
		
		b.getScore().move(13, 6);
		b.plotScore2Console();
		b.getScore().move(13, 38);
		b.plotScore2Console();
		b.getScore().move(13, 3);
		b.plotScore2Console();
		b.getScore().move(13, 1);
		b.plotScore2Console();
		
		b.getScore().save2File("score.json");
	}

}
