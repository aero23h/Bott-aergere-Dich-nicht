import java.io.IOException;
import java.util.ArrayList;

public class Game {
	private Board board;
	private Menu menu;
	private String scorePath;
	private String playerPath;
	
	public Game() throws IOException{
		this.board = new Board();
		this.menu = new Menu();
		this.scorePath = "./save/score";
		this.playerPath = "./save/player";
	}
	
	public void run(){
		// mainMenu
		int result;
		do {
			// header
			this.menu.plotHeader();
			result = this.menu.selectMenu(this.menu.mainMenu(), "Main menu");
			switch(result) {
			// new
			case 0:
				this.newGame();
				break;
			// resume
			case 1:
				this.resume();
				break;
			// save
			case 2:
				this.saveCurrentGame();
				break;
			// load
			case 3:
				this.loadExistingGame();
				break;
			// quit
			case 99:
				this.quit();
				break;
			default:
				System.err.println("Invalid key!");
				break;
			}
			
		} while(result != 99);
	}
	
	
	public void newGame() {
		// select number of players and select players
		//@
		// init a new board with players
		this.board.getScore().init(2);
		// start playing
		this.resume();
	}
	
	public void resume() {
		this.playGame();
	}
	
	public ArrayList<Integer> checkAvailableToken(Player p, int steps){
		ArrayList<Integer> tokenList = new ArrayList<>();
		int token = 0;
		for(int tokenNumber=1; tokenNumber<5;tokenNumber++) {
			token = tokenNumber + (p.getId()*10);
			if(this.board.getScore().checkMove(token, steps)){
				tokenList.add(tokenNumber);
				}
		}
		return tokenList;
	}
	
	public void playGame() {
		// while player did not win
		boolean quit = false;
		int result;
		do {
			// plot board
			this.board.plotScore2Console(this.menu.playerMenu(this.board.getActPlayer()));
			// print player functions
			result = this.menu.selectMenu(this.menu.playerMenu(this.board.getActPlayer()), "");
			switch(result) {
			// roll
			case 0:
				// play
				int roll = this.board.getScore().roll();
				int tokenNumber = -1;
				int token = 0;
				ArrayList<Integer> tokenList;
				do {
					tokenList = this.checkAvailableToken(this.board.getActPlayer(), roll);
					// @ plot board with token and dice
					// check if a token is move able
					if(tokenList.size() > 0) {
						this.board.plotScore2Console(this.menu.tokenMenu(tokenList, roll, this.board.getActPlayer()));
						//System.out.println("Only token: " + tokenList.toString() + "available");
						tokenNumber = this.menu.inputNumber(tokenList);
					} else {
						tokenNumber = 64;
						System.out.print("Your roll was: "+ roll + ". You got skipped.");
					}
					// pass
					if(tokenNumber == 64) {
						token = 0;
					// move token
					} else {
						token = tokenNumber + (this.board.getActPlayer().getId()*10);
					}
				// while !move was successful
				System.out.println(tokenNumber + " input");
				} while(!this.board.getScore().move(token, roll));
				
				System.out.print(new Color().getReset());
				this.board.nextPlayer();
				break;
			// back to menu
			case 99:
				this.board.clearConsole();
				quit = true;
				break;
			}
		} while(!quit & !this.board.didWin());
		// do sth player win
		//@
	}
	
	public void saveCurrentGame() {
		System.out.println("save");
		System.out.println(this.scorePath);
		System.out.println(this.playerPath);
	}
	public void loadExistingGame() {
		

	}
	public void quit() {
		System.out.println("quit");
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}	
}