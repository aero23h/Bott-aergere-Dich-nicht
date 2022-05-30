import java.io.IOException;

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
		int result;
		do {
			result = this.menu.selectMenu(this.menu.getMainMenu());
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
	
	public void playGame() {
		// while player did not win
		boolean quit = false;
		int result;
		do {
			// plot board
			this.board.plotScore2Console();
			// print player functions
			System.out.println(this.board.getActPlayer().getColor() + "Actual player is: "+ this.board.getActPlayer().getName());
			result = this.menu.selectMenu(this.menu.getPlayerMenu());
			switch(result) {
			// roll
			case 0:
				// play
				int roll = this.board.getScore().roll();
				int tokenNumber = -1;
				int token = 0;
				do {
					System.out.print("Your roll is: "+ roll + ". Which token do you want to move or (p)ass?");
					tokenNumber = this.menu.inputNumber(4, true);
					// pass
					if(tokenNumber == 64) {
						token = 0;
					// move token
					} else {
						token = tokenNumber + (this.board.getActPlayer().getId()*10);
					}
				// while move was successful
				} while(!this.board.getScore().move(token, roll));
				System.out.print(new Color().getReset());
				this.board.nextPlayer();
				break;
			// back to menu
			case 99:
				quit = true;
				break;
			}
		} while(!quit & !this.board.didWin(this.board.getActPlayer()));
		// do sth player win
		//@
	}
	
	public void saveCurrentGame() {
		System.out.println("save");
		System.out.println(this.scorePath);
		System.out.println(this.playerPath);
	}
	public void loadExistingGame() {
		System.out.println("load");
		System.out.println(this.scorePath);
		System.out.println(this.playerPath);
	}
	public void quit() {
		System.out.println("quit");
	}	
}