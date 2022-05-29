import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Game {
	private Board board;
	private Menu menu;
	private boolean isRunning;
	private int gameMode;
	private BufferedReader br;
	private String scorePath;
	private String playerPath;
	
	public Game() throws StreamWriteException, DatabindException, IOException{
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.board = new Board();
		this.menu = new Menu();
		this.isRunning = true;
		this.gameMode = 0;
		this.scorePath = "./save/score";
		this.playerPath = "./save/player";
		
		
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
		
	}
		
}