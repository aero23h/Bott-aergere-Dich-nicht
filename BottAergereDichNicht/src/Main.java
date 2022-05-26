import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException{
		Board b = new Board();
		Color c = new Color();
		
		b.plotScore2Console();
		b.getScore().move(24, 6);
		b.plotScore2Console();
	}

}
