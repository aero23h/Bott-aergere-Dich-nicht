package v2;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
	private Color color;
	private Board board;
	private Menu menu;
	private String scorePath;
	private String playerPath;
	private PlayerList playerList;
	
	// constructor 
	public Game(){
		this.color = new Color();
		this.board = new Board();
		this.menu = new Menu();
		this.scorePath = "./save/score";
		this.playerPath = "./save/player";
		
		// check if directories exist
		if(!this.board.getScore().checkDirExist(this.playerPath)) {
			new File(this.playerPath).mkdirs();
		}
		if(!this.board.getScore().checkDirExist(this.scorePath)) {
			new File(this.scorePath).mkdirs();
		}
		// collect all files
		this.playerList = new PlayerList();
		if(!this.playerList.loadPlayerList(this.playerPath)) {
			// @ AI import
			this.playerList.addNewPlayer(new Player("Minion-Bob (AI)"));
			this.playerList.addNewPlayer(new Player("Minion-Stuart (AI)"));
			this.playerList.addNewPlayer(new Player("Minion-Kevin (AI)"));
			this.playerList.addNewPlayer(new Player("Minion-Alex (AI)"));
		}
	}
	
	// ########################################################################################################################
	
	public void run(){
		// mainMenu
		int result;
		do {
			// header
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
			// user
			case 4:
				this.user();
				break;
			// highScore
			case 5:
				this.highScore();
				break;
			// remove file
			case 6:
				this.removeFile();
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
	
	public void highScore() {
		int result = -1;
		ArrayList<Player> players = this.playerList.getPlayerList();
		do {
			result = this.menu.selectMenu(this.menu.highScoreMenu(players), "Scoreboard");
			switch(result){
			// back
			case 99:
				break;
			}
		} while(result != 99);
	}
	
	public void newGame() {
		// select number of players and select players
		int result = -1;
		do {
			result = this.menu.selectMenu(this.menu.playerAmountMenu(), "How many player are playing?");
			switch(result) {
				case 2:
				case 3:
				case 4:
					// initialize board by number of players
					this.board.getScore().init(result);
					// loop over number of playing players
					ArrayList<Integer> startRolls = new ArrayList<>();
					for(int i=0; i<result; i++) {
						// select
						Player p = this.selectPlayerFromVisibleList();
						if(p == null) {
							this.board.getScore().init(4);
							result = 99;
							break;
						}
						// set player color and give to score
						p.setColor(this.getColor(p, result));
						this.board.getScore().setPlayer(p);
						int roll = 0;
						do {
							roll = this.board.getScore().roll();
							if(!startRolls.contains(roll)) {
								startRolls.add(roll);
								break;
							}
						} while(true);				
						// player not selectAble anymore
						p.setVisible(false);
					}
					int max = 0;
					int index = 0;
					for(int j=0; j<startRolls.size(); j++) {
						if(startRolls.get(j) > max) {
							max = startRolls.get(j);
							index = j;
						}
					}
					// plot startroll list
					// @
					System.out.println(startRolls.toString());
					// start player set
					this.board.getScore().setActPlayer(this.board.getScore().getPlayers()[index]);
					System.out.println(this.board.getScore().getActPlayer().getName() + " will start.");
					this.sleep(1500);
					// start game
					if(result != 99) {
						this.resume();
					}
					result = 99;
					break;
				// back
				case 99:
					break;
				default:
					System.err.println("Invalid key!");
					break;
			}
		} while(result != 99);
	}
	
	public ColorItem getColor(Player p, int amountPlayers) {
		int result = -1;
		ColorItem c = null;
		ArrayList<ColorItem> colorList = this.checkAvailableColor(this.board.getScore().getPlayers(), amountPlayers);
		do {
			result = this.menu.selectMenu(this.menu.colorMenu(colorList), "Select color for " + p.getName());
			// back to menu
			switch(result) {
			default:
				// if color not exist
				if(result == -1) {
					System.err.println("Invalid key!");
					this.sleep(1000);
					break;
				}
				// color exist
				c = colorList.get(result);
				break;
			}
		} while(c == null);
		return c;
		
	}
	
	public Player selectPlayerFromVisibleList() {
		int result = -1;
		do {
			result = this.menu.selectMenu(this.menu.listPlayer(this.playerList.getPlayerList()), "Select player");
			// back to menu
			if(result == 99) {
				return null;
			}
			if(result == -1) {
				System.err.println("Invalid key!");
				this.sleep(1000);
			}
		} while(result == -1);
		return this.playerList.getPlayerList().get(result);
	}

	public void resume() {
		if(this.playerList.findPlayer(this.board.getScore().getActPlayer().getName()) != null) {
			this.playGame();
		} else {
			System.err.println("No game to resume!");
			this.sleep(1500);
		}		
	}
	
	// save menu
	public void saveCurrentGame() {
		if(this.playerList.findPlayer(this.board.getScore().getActPlayer().getName()) != null) {
			this.playerList.savePlayerList(this.playerPath);
			this.board.getScore().save2File(this.scorePath);
			System.out.println("Game saved");
			this.sleep(1000);
		} else {
			System.err.println("No game to save!");
			this.sleep(1500);
		}

	}
	
	//load menu
	public void loadExistingGame() {
		int result = -1;
		ArrayList<Score> scoreFiles = this.menu.getAllScoresFromDir(this.scorePath);
		do {
			result = this.menu.selectMenu(this.menu.fileListMenu(scoreFiles), "File menu to load");
			// back to menu
			if(result == 99) {
				break;
			}
			if(result != -1) {
				// set score from file
				this.board.setScore(scoreFiles.get(result));
				// set players
				for(int i=0; i<this.board.getScore().getPlayers().length; i++) {
					if(!this.board.getScore().getPlayers()[i].getName().equals("")) {
						Player p = this.playerList.findPlayer(this.board.getScore().getPlayers()[i].getName());
						if(p == null) {
							this.playerList.addNewPlayer(this.board.getScore().getPlayers()[i]);
						} else {
							this.board.getScore().getPlayers()[i] = p;
						}
					}
				}
				Player p = this.playerList.findPlayer(this.board.getScore().getActPlayer().getName());
				if(p != null) {
					this.board.getScore().setActPlayer(p);
					this.resume();
					break;
				}				
				break;
			}
			System.err.println("Invalid key!");
			this.sleep(500);
		} while(result != 99);
	}
	
	public void removeFile() {
		int result = -1;
		ArrayList<Score> scoreFiles = this.menu.getAllScoresFromDir(this.scorePath);
		do {
			result = this.menu.selectMenu(this.menu.fileListMenu(scoreFiles), "File menu to remove");
			// back to menu
			if(result == 99) {
				break;
			}
			if(result != -1) {
				String fileName = scoreFiles.get(result).getCreateTime();
				File f = new File(this.scorePath + "/" + fileName + ".json");
				f.delete();
				System.out.println("File: " + fileName + " is deleted.");
				this.sleep(1500);
				break;
			}
			System.err.println("Invalid key!");
			this.sleep(500);
		} while(result != 99);
	}
	
	// user menu
	public void user() {
		int result = -1;
		String name = "";
		do {
			this.playerList.allPlayersVisible();
			result = this.menu.selectMenu(this.menu.userMenu(this.playerList.getPlayerList()), "Player menu");
			switch(result) {
				// create new user
				case 0:
					do {
						this.menu.plotHeader();
						System.out.println("\t\t\t*** Enter new name ***");
						System.out.println("\t\t*** No special characters allowed! ***");
						// get new name
						name = this.menu.inputString("name");
						// replace special characters
						name = name.replaceAll("[^a-zA-Z0-9]+","");
						// check if name already exist
						if(this.playerList.findPlayer(name) != null) {
							System.err.println("User with name: " + name + " already exist!");
							this.sleep(1500);
						}
					} while(!this.playerList.addNewPlayer(new Player(name)));
					System.out.println("User " + name + " created and saved.");
					this.sleep(1000);
					break;
				// edit user
				case 1:
					this.playerList.setVisibility("Minion-Bob (AI)", false);
					this.playerList.setVisibility("Minion-Stuart (AI)", false);
					this.playerList.setVisibility("Minion-Kevin (AI)", false);
					this.playerList.setVisibility("Minion-Alex (AI)", false);
					Player p = new Player();
					do {
						// select player
						p = this.selectPlayerFromVisibleList();
						if(p != null) {
							String newName = "";
							do {
								this.menu.plotHeader();
								System.out.println("\t\t\t*** Enter new name ***");
								// get new name
								newName = this.menu.inputString("name");
								newName = newName.replaceAll("[^a-zA-Z0-9]+","");
								// check if name already exist
								if(this.playerList.findPlayer(newName) != null) {
									System.err.println("User with name: " + newName + " already exist!");
									this.sleep(1500);
								}
							} while(!this.playerList.changePlayerName(p.getName(), newName));
						} else {
							break;
						}
					} while(p == null);
					this.playerList.allPlayersVisible();
					break;
				// back
				case 99:
					break;
				default:
					System.err.println("Invalid key!");
					this.sleep(500);
					break;
			}
		} while(result != 99);
		// set all players visible to true
		this.playerList.allPlayersVisible();
	}
	
	// quit function
	public void quit() {
		this.playerList.savePlayerList(this.playerPath);
		System.out.println("Quitted");
	}
	
	
	public void playGame(){
		this.playerList.allPlayersVisible();
		// while player did not win
		boolean quit = false;
		boolean ai = false;
		int result;
		do {
			// plot board
			this.board.plotScore2Console(this.menu.playerMenu(this.board.getScore().getActPlayer()));
			if(!this.board.getScore().getActPlayer().getName().contains("(AI)")) {
				// player move
				ai = false;
				result = this.menu.selectMenu(this.menu.playerMenu(this.board.getScore().getActPlayer()), "");
			} else {
				// AI move
				ai = true;
				// AI will roll the dice;
				result = 0;
			}
			// check if no token is lost
			// @
			this.checkTokenAmount();
			
			switch(result) {
			// roll
			case 0:
				// play
				int roll = this.board.getScore().roll();
				// set attribute timesRolled6 +1
				if(roll == 6) {
					this.board.getScore().getActPlayer().setTimesRolled6(this.board.getScore().getActPlayer().getTimesRolled6() +1);
				}
				int[] tokenIDCode = new int[2];
				int token = 0;
				ArrayList<int[]> tokenList;
				do {
					tokenList = this.checkAvailableToken(this.board.getScore().getActPlayer(), roll);
					// check if a token is move able
					if(tokenList.size() > 0) {
						// plot board with token and dice
						this.board.plotScore2Console(this.menu.tokenMenu(tokenList, roll, this.board.getScore().getActPlayer()));
						if(!ai) {
							tokenIDCode = this.menu.inputToken(tokenList);
						} else {
							tokenIDCode = tokenList.get(this.board.getScore().getRandomNumberInRange(0, tokenList.size()-1));
							this.sleep(0);
						}
					} else {
						tokenIDCode[0] = 64;
						System.out.print("Your rolled: "+ roll + ". No move is possible. Skipping...");
						// delay to read the not movable text better
						this.sleep(100);
					}
					// pass
					if(tokenIDCode[0] == 64) {
						token = 0;
					// no pass
					} else {
						token = tokenIDCode[0];
					}
				// while !move was successful
				} while(!this.board.getScore().move(token, roll, tokenIDCode[1]));
				
				// after successful move set next player
				if(this.board.didWin()) {
					break;
				}
				// roll again on 6
				if(roll != 6) {
					this.board.nextPlayer();
				}
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
		
		if(this.board.didWin()) {
			// set wins attribute +1
			this.board.getScore().getActPlayer().setWins(this.board.getScore().getActPlayer().getWins() + 1);
			// set total played +1 for every player who played
			for(Player p: this.board.getScore().getPlayers()) {
				p.setTotalPlayed(p.getTotalPlayed() +1);
			}
			
			do {
				// display fancy win menu
				this.board.plotScore2Console(this.menu.fancyWinMenu(this.board.getScore().getActPlayer()));
				result = this.menu.selectMenu(this.menu.fancyWinMenu(this.board.getScore().getActPlayer()), "");
			} while(result != 99);
		}
	}
	
	public void checkTokenAmount() {
		int count = 0;
		for(int e: this.board.getScore().getOnBoard()) {
			count += e;
		}
		for(int e: this.board.getScore().getGoalBoard()) {
			count += e;
		}
		for(int e: this.board.getScore().getStartBoard()) {
			count += e;
		}
		if(count != 280) {
			int missingToken = 280 - count;
			System.out.println(missingToken + " is missing!");
			this.sleep(5000);
		}
	}
	
	public ArrayList<int[]> checkAvailableToken(Player p, int roll){
		ArrayList<int[]> tokenList = new ArrayList<>();
		int token = 0;
		int moveCode = -1;
		for(int tokenNumber=1; tokenNumber<5;tokenNumber++) {
			token = tokenNumber + (p.getId()*10);
			moveCode = this.board.getScore().checkMove(token, roll);
			if(moveCode != -1) {
				tokenList.add(new int[] {token, moveCode});
			}
		}
		return tokenList;
	}
	
	public ArrayList<ColorItem> checkAvailableColor(Player[] players, int amountPlayers){
		ArrayList<ColorItem> colorList = new ArrayList<>();
		for(int i=0; i<amountPlayers; i++) {
			if(players[i].getName().equals("")) {
				colorList.add(players[i].getColor());
			}
		}
		return colorList;
	}
	
	public void sleep(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// ########################################################################################
	// getter setter
	public Board getBoard() {
		return this.board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Menu getMenu() {
		return this.menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getScorePath() {
		return this.scorePath;
	}

	public void setScorePath(String scorePath) {
		this.scorePath = scorePath;
	}
	
	public String getPlayerPath() {
		return this.playerPath;
	}

	public void setPlayerPath(String playerPath) {
		this.playerPath = playerPath;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}