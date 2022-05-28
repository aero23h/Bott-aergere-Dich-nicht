import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		Board b = new Board();
		Color c = new Color();
		Menu m = new Menu();	
		m.plotMenu2Console(m.buildStartMenu());
		//int[] tokenL = {31,33,32};  
		//b.plotScore2Console(m.buildGameMenu(b.getScore().getPlayers()[3], tokenL));
		
	}

}
