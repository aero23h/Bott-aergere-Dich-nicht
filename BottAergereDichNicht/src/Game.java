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
		while(this.isRunning) {
			for(Player p: this.board.getScore().getPlayers()) {
				int steps = this.board.getScore().roll();
				this.board.plotScore2Console();
				System.out.println("Gewürfelt wurde eine: " + steps);
				System.out.println(p.getColor() + p.getName() + " Ist am zuge" + new Color().getReset());
				int token = this.intInput() + p.getId()*10;
				this.board.getScore().move(token, steps);
				if(this.board.hasWon(p)) {
					this.isRunning = false;
					System.out.println("Gewonnen!");
					break;
				}
			}
		}
		
	}
		
}