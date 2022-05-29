import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Game {
	private Board board;
	private Menu menu;
	private boolean isRunning;
	private BufferedReader br;
	
	public Game() throws StreamWriteException, DatabindException, IOException{
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.board = new Board();
		this.menu = new Menu();
		this.isRunning = true;
	}
	
	public int intInput() {
		try {
			return Integer.parseInt(this.br.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public String stringInput() {
		try {
			return br.readLine();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public void run() throws IOException {
		// dummy code

		
		// real code
		while(this.isRunning) {
			menu.plotMenu2Console(menu.buildStartMenu());
			switch(this.stringInput()) {
				case "s":
					System.out.println("S");
					break;
				default:
					System.out.println("wrong input!");
					break;
			}
			
			
			this.isRunning = false;
		}

	}
	
}