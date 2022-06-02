import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
	private Color color;
	private Board board;
	private Menu menu;
	private String scorePath;
	private String playerPath;
	
	// constructor 
	public Game() throws IOException{
		this.color = new Color();
		this.board = new Board();
		this.menu = new Menu();
		this.scorePath = "./save/score";
		this.playerPath = "./save/player";
	}
	
	// main run function
	public void run(){
		// mainMenu
		int result;
		do {
			// header
			this.menu.plotHeader();
			result = this.menu.selectMenuByChar(this.menu.mainMenu(), "Main menu");
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
			// user
			case 4:
				this.editUser();
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
		int playerAmount = -1;
		do {
			this.menu.plotHeader();
			playerAmount = this.menu.selectMenuByChar(this.menu.playerAmountMenu(), "How many player are playing?");
			switch(playerAmount) {
				case 2:
				case 3:
				case 4:
					this.board.getScore().init(playerAmount);
					this.resume();
					break;
				// back
				case 99:
					break;
				default:
					playerAmount = -1;
					System.err.println("Invalid key!");
					break;
			}
		} while(playerAmount == -1);
	}
	
	public void resume() {
		this.playGame();
	}
	
	// save function
	public void saveCurrentGame() {
		//@
		//
		System.out.println("save");
		System.out.println(this.scorePath);
		System.out.println(this.playerPath);
	}
	
	//load function
	public void loadExistingGame() {
		// @
		// list all available files
		// which file to load
		// load file by name / key
		// resume to loaded game
		this.resume();
	}
	// user menu
	public void editUser() {
		// @
		// list all available users
		// which user to load
		// edit user by input
		// save user
		System.out.println("edit user");	
	}
	
	// quit function
	public void quit() {
		System.out.println("quit");
	}
	
	public String playerID2Name(ArrayList<File> files, int id) {
		return files.get(id-1).getName();
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
	
	public void playGame(){
		// while player did not win
		boolean quit = false;
		int result;
		do {
			// plot board
			this.board.plotScore2Console(this.menu.playerMenu(this.board.getActPlayer()));
			// print player functions
			result = this.menu.getKey(this.menu.playerMenu(this.board.getActPlayer()));
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
					// check if a token is move able
					if(tokenList.size() > 0) {
						// plot board with token and dice
						this.board.plotScore2Console(this.menu.tokenMenu(tokenList, roll, this.board.getActPlayer()));
						//System.out.println("Only token: " + tokenList.toString() + "available");
						tokenNumber = this.menu.inputToken(tokenList);
					} else {
						tokenNumber = 64;
						System.out.print("Your rolled: "+ roll + ". No move is possible. Skipping...");
						// delay to read the not movable text better
						try {
							TimeUnit.MILLISECONDS.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// pass
					if(tokenNumber == 64) {
						token = 0;
					// no pass
					} else {
						// get token
						token = tokenNumber + (this.board.getActPlayer().getId()*10);
					}
				// while !move was successful
				} while(!this.board.getScore().move(token, roll));
				// after successful move set next player
				this.board.nextPlayer();
				break;
			// back to menu
			case 99:
				// clear console for a better readability
				this.board.clearConsole();
				quit = true;
				break;
			}
		// while no player win
		} while(!quit & !this.board.didWin());
		// do something when player win
		// fancy player display who win
		//@
	}
	
	// ########################################################################################
	// getter setter
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

	public String getScorePath() {
		return scorePath;
	}

	public void setScorePath(String scorePath) {
		this.scorePath = scorePath;
	}
	
	public String getPlayerPath() {
		return playerPath;
	}

	public void setPlayerPath(String playerPath) {
		this.playerPath = playerPath;
	}
}