import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException{
		Game m = new Game();
		//Player p = new Player("Alex", new Color().getGreen());
		//p.save2File("./save/player");
		m.run();
	}

}
