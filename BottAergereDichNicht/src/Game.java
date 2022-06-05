import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
	private Color color;
	private Board board;
	private Menu menu;
	private String scorePath;
	private String playerPath;
	private ArrayList<Player> playerList;
	private ArrayList<Score> scoreList;
	
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
		this.playerList = this.menu.getAllPlayersFromDir(this.playerPath);
		this.scoreList = this.menu.getAllScoresFromDir(this.scorePath);
		
		
		for(Score score: this.scoreList) {
			for(Player ps: score.getPlayers()) {
				for(Player p: this.playerList) {
					
				}
			}
		}
		
		for(Player p: this.board.getScore().getPlayers()) {
			for(Player p2: this.playerList) {
				if(p.getName().equals(p2.getName())) {
					this.board.getScore().getPlayers()[p.getId()] = p2;
					break;
				}
			}
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
				this.editUserMenu();
				break;
			// highScore
			case 5:
				this.highScore();
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
		ArrayList<Player> players = this.playerList;
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
					// get all players
					ArrayList<Player> availablePlayers = this.menu.clonePlayerList(this.playerList);
					// loop over number of playing players
					for(int i=0; i<result; i++) {
						// select
						Player p = this.getPlayer(availablePlayers);
						if(p == null) {
							this.board.getScore().init(4);
							result = 99;
							break;
						}
						// remove player from availablePlayers
						for(int j=0; j<availablePlayers.size(); j++) {
							if(availablePlayers.get(j).getName().replaceAll(".json", "").equals(p.getName())) {
								availablePlayers.remove(j);
							}
						}
						p.setColor(this.getColor(p, result));
						this.board.getScore().setPlayer(p);
					}
					this.board.getScore().setActPlayer(this.board.getScore().getPlayers()[0]);
					// start game
					if(result != 99) {
						this.scoreList.add(this.board.getScore());
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
	
	public Player getPlayer(ArrayList<Player> availablePlayers) {
		int result = -1;
		Player p = null;
		do {
			result = this.menu.selectMenu(this.menu.fileListPlayer(availablePlayers), "Select player");
			// back to menu
			switch(result) {
			// back
			case 99:
				break;
			default:
				// if player not exist
				if(result == -1) {
					System.err.println("Invalid key!");
					this.sleep(1000);
					break;
				}
				// player exist
				for(Player originalPlayer: this.playerList) {
					if(originalPlayer.getName() == availablePlayers.get(result).getName()) {
						p = originalPlayer;
					}
				}
				break;
			}
		} while((p == null) && !(result == 99) );
		return p;
	}	
	
	public void resume() {
		this.playGame();
	}
	
	// save menu
	public void saveCurrentGame() {
		for(Player p: this.playerList) {
			p.save2File(this.playerPath);
		}
		for(Score s: this.scoreList) {
			s.save2File(this.scorePath);
		}
		System.out.println("Game saved");
		this.sleep(1000);
	}
	
	//load menu
	public void loadExistingGame() {
		int result = -1;
		do {
			result = this.menu.selectMenu(this.menu.loadMenu(this.scoreList), "File menu");
			switch(result) {
				// remove
				case 0:
					this.removeFile();
					break;
				// load
				case 1:
					if(this.loadFile()) {
						this.resume();
						result = 99;
					}
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
	}
	
	public boolean loadFile() {
		String name = "";
		int result = -1;
		int result2 = -1;
		do {
			result = this.menu.selectMenu(this.menu.fileListScore(this.scoreList), "Which Game do you want to load?");
			// back to menu
			switch(result) {
			// back
			case 99:
				name = " ";
				break;
			default:
				// if game does not exist
				if(result == -1) {
					System.err.println("Invalid key!");
					this.sleep(1000);
					break;
				}
				name = this.scoreList.get(result).getCreateTime();
				result2 = -1;
				do {
					result2 = this.menu.selectMenu(this.menu.yesNo(), "Load game with name: " + name);
					switch(result2) {
					// no
					case 0:
						break;
					// yes
					case 1:
						this.board.setScore(this.scoreList.get(result));
						System.out.println("Game: " + name + " is loading.");
						this.sleep(1500);
						return true;
					default:
						result = -1;
						System.err.println("Invalid key!");
						this.sleep(1000);
						break;
					}
				} while(result == -1);
				break;
			}
		} while("".equals(name));
		return false;
	}
	
	public void removeFile() {
		String name = "";
		int result = -1;
		do {
			result = this.menu.selectMenu(this.menu.fileListScore(this.scoreList), "Which Game do you want to remove?");
			// back to menu
			switch(result) {
			// back
			case 99:
				name = " ";
				break;
			default:
				// if game does not exist
				if(result == -1) {
					System.err.println("Invalid key!");
					this.sleep(1000);
					break;
				}
				name = this.scoreList.get(result).getCreateTime();
				result = -1;
				do {
					result = this.menu.selectMenu(this.menu.yesNo(), "Remove game with name: " + name);
					switch(result) {
					// no
					case 0:
						break;
					// yes
					case 1:
						File f = new File(this.scorePath + "/" + name + ".json");
						f.delete();
						System.out.println("Game " + name + " is removed.");
						this.sleep(1500);
						break;
					default:
						result = -1;
						System.err.println("Invalid key!");
						this.sleep(1000);
						break;
					}
				} while(result == -1);
				break;
			}
		} while("".equals(name));
	}
	
	// user menu
	public void editUserMenu() {
		int result = -1;
		do {
			result = this.menu.selectMenu(this.menu.editMenu(this.playerList), "Player menu");
			switch(result) {
				// create new user
				case 0:
					this.createUser();
					break;
				// edit
				case 1:
					this.editUser();
					break;
				// remove
				case 2:
					this.removeUser();
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
	}
	
	public void createUser() {
		String name = "";
		do {
			this.menu.plotHeader();
			System.out.println("\t\t\t*** Enter new name ***");
			System.out.println("\t\t*** No special characters allowed! ***");
			// get new name
			name = this.menu.inputString("name");
			// replace special characters
			name = name.replaceAll("[^a-zA-Z0-9]+","");
			// check if name already exist
			for(Player p: this.playerList) {
				if(p.getName().equals(name)) {
					System.err.println("User with name: " + name + " already exist!");
					name = "";
					break;
				}
			}
			if(!"".equals(name)) {
				this.playerList.add(new Player(name, color.getReset()));
				System.out.println("User " + name + " created and saved.");
				this.sleep(1500);
			}
		} while("".equals(name));
	}
	
	public void editUser() {
		String name = "";
		int result = -1;
		do {
			result = this.menu.selectMenu(this.menu.fileListPlayer(this.playerList), "Which player do you want to edit?");
			// back to menu
			switch(result) {
			// back
			case 99:
				name = " ";
				break;
			default:
				// if player not exist
				if(result == -1) {
					System.err.println("Invalid key!");
					this.sleep(1000);
					break;
				}
				// player exist
				String newName = "";
				// get name
				name = this.playerList.get(result).getName();
				// get player by name
				Player p = this.playerList.get(result);
				do {
					this.menu.plotHeader();
					System.out.println("\t\t\t*** Enter new name ***");
					// get new name
					newName = this.menu.inputString("name");
					newName = newName.replaceAll("[^a-zA-Z0-9]+","");
					// check if name already exist
					for(Player player: this.playerList) {
						if(player.getName().equals(newName)) {
							System.err.println("User with name: " + newName + " already exist!");
							newName = "";
							break;
						}
					}
					if(!"".equals(newName)) {
						p.setName(newName);
						System.out.println("User name changed from: " + name + " into " + newName +".");
						this.sleep(1500);
					}
				} while("".equals(newName));
				break;
			}
		} while("".equals(name));
	}
	
	public void removeUser() {
		String name = "";
		int result = -1;
		int result2 = -1;
		do {
			result = this.menu.selectMenu(this.menu.fileListPlayer(this.playerList), "Which player do you want to remove?");
			// back to menu
			switch(result) {
			// back
			case 99:
				name = " ";
				break;
			default:
				// if player not exist
				if(result == -1) {
					System.err.println("Invalid key!");
					this.sleep(1000);
					break;
				}
				name = this.playerList.get(result).getName();
				do {
					result2 = this.menu.selectMenu(this.menu.yesNo(), "Remove user with name: " + name);
					switch(result2) {
					// no
					case 0:
						break;
					// yes
					case 1:
						this.playerList.remove(result);
						System.out.println("User " + name + " is removed.");
						this.sleep(1500);
						break;
					default:
						result = -1;
						System.err.println("Invalid key!");
						this.sleep(1000);
						break;
					}
				} while(result2 == -1);
				break;
			}
		} while("".equals(name));
	}
	
	// quit function
	public void quit() {
		for(File f: this.menu.getAllFiles(this.playerPath)){
			f.delete();
		}
		for(Player p: this.playerList) {
			p.save2File(this.playerPath);
		}
		System.out.println("Quitted");
	}
	
	public void playGame(){
		// while player did not win
		boolean quit = false;
		int result;
		do {
			// plot board
			this.board.plotScore2Console(this.menu.playerMenu(this.board.getScore().getActPlayer()));
			result = this.menu.selectMenu(this.menu.playerMenu(this.board.getScore().getActPlayer()), "");
			//result = this.menu.getKey(this.menu.playerMenu(this.board.getActPlayer()));
			switch(result) {
			// roll
			case 0:
				// play
				int roll = this.board.getScore().roll();
				roll = 6;
				// set attribute timesRolled6 +1
				if(roll == 6) {
					this.board.getScore().getActPlayer().setTimesRolled6(this.board.getScore().getActPlayer().getTimesRolled6() +1);
				}
				int tokenNumber = -1;
				int token = 0;
				ArrayList<Integer> tokenList;
				do {
					tokenList = this.checkAvailableToken(this.board.getScore().getActPlayer(), roll);
					// check if a token is move able
					if(tokenList.size() > 0) {
						// plot board with token and dice
						this.board.plotScore2Console(this.menu.tokenMenu(tokenList, roll, this.board.getScore().getActPlayer()));
						//System.out.println("Only token: " + tokenList.toString() + "available");
						tokenNumber = this.menu.inputToken(tokenList);
					} else {
						tokenNumber = 64;
						System.out.print("Your rolled: "+ roll + ". No move is possible. Skipping...");
						// delay to read the not movable text better
						this.sleep(1000);
					}
					// pass
					if(tokenNumber == 64) {
						token = 0;
					// no pass
					} else {
						// get token
						token = tokenNumber + (this.board.getScore().getActPlayer().getId()*10);
					}
				// while !move was successful
				} while(!this.board.getScore().move(token, roll));
				// after successful move set next player
				if(this.board.didWin()) {
					break;
				}
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
			// @		
			// remove score
			this.board.setScore(new Score());
			
			
		}
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

	public ArrayList<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(ArrayList<Score> scoreList) {
		this.scoreList = scoreList;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
}