package bottAergereDichNicht;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
	private Color color;
	private String[][] header;
	private BufferedReader keyReader;
	
	public Menu() {
		this.color = new Color();
		this.header = new String [][]{
				{"------------------------------------------------------------------------------------"},
				{"  ___     _   _     _  _                        ___  _    _           _    _   _   "},
				{" | _ )___| |_| |_  (_)(_)_ _ __ _ ___ _ _ ___  |   \\(_)__| |_    _ _ (_)__| |_| |_ "},
				{" | _ / _ |  _|  _| / _` | '_/ _` / -_| '_/ -_) | |) | / _| ' \\  | ' \\| / _| ' |  _|"},
				{" |___\\___/\\__|\\__| \\__,_|_| \\__, \\___|_| \\___| |___/|_\\__|_||_| |_||_|_\\__|_||_\\__|"},
				{"                            |___/                                                  "},
				{"------------------------------------------------------------------------------------"},
				};
		this.keyReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// ################################################################################################################

	public void plotHeader() {
		for(int i=0; i<this.header.length; i++){
			System.out.println(this.header[i][0]);
			}
	}
	
	public void plotMenu(ArrayList<MenuItem> menuItems, String header) {
		// print menuHeader
		System.out.println("\t\t\t *** " + header + " ***" );
		// build menuLine and print it
		String menuLine = "";
		for(int i=0; i<menuItems.size();i++) {
			if(menuItems.get(i).getKey().equals("")) {
				menuLine += "\t\t\t     " +  menuItems.get(i).getText() + "\n";
			} else {
				menuLine += "\t\t\t" + "("+ menuItems.get(i).getKey() +")  " +  menuItems.get(i).getText() + "\n";
			}
		}
		System.out.println(menuLine);
	}
	
	public int selectMenu(ArrayList<MenuItem> menuItems, String header) {
		// header == "" -> no header
		if(header != "") {
			this.plotHeader();
			this.plotMenu(menuItems, header);
		}
		int result = -1;
		String key = this.inputString();
		for(int i=0; i<menuItems.size(); i++) {
			if(menuItems.get(i).getKey().equals(key)) {
				result = menuItems.get(i).getResult();
				break;
			}
		}
		return result;
	}
	
	public int[] inputToken(ArrayList<int[]> tokenList) {
		int[] result = new int[] {-1,-1};
		do {
			try {
				System.out.print("Please select token: ");
				result[0] = keyReader.read() - 48; // 48 is ascii 0 transfer acii to int
				int dummy = -1;
				do {
					dummy = keyReader.read();
				} while(dummy  != 10);
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = this.checkTokenInList(tokenList, result[0]);
			if(result[0] == -1) {
				System.err.println("Invalid token!");
			}
		} while(result[0] == -1);
		return result;
	}
	
	public String inputString() {
		String result = "";
		try {
			System.out.print("Please enter key(value): ");
			result = keyReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// cleanup result
		// sometimes the plot format will be changed without cleanup
		result = result.replaceAll("\n", "").replaceAll("\r", "");
		return result;
	}
	
	public String inputString(String askString) {
		String result = "";
		try {
			System.out.print("Please enter "+ askString +": ");
			result = keyReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// cleanup result
		// sometimes the plot format will be changed without cleanup
		result = result.replaceAll("\n", "").replaceAll("\r", "");
		return result;
	}

	// ################################################################################################################
	
	public int[] checkTokenInList(ArrayList<int[]> tokenList, int result) {
		for(int[] a: tokenList) {
			if(a[0] == result) {
				return new int[] {a[0], a[1]};
			}
		}
		return new int[] {-1, -1};
	}

	public ArrayList<Score> getAllScoresFromDir(String path){
		ArrayList<Score> scores = new ArrayList<>();
		ArrayList<File> files = this.getAllFiles(path);
		for(File f: files) {
			scores.add(new Score().loadFromFile(path, f.getName().replaceAll(".json", "")));
		}
		return scores;
	}
	
	public ArrayList<File> getAllFiles(String path) {
	    File folder = new File(path);
	    File[] listOfFiles = folder.listFiles();
	    ArrayList<File> files = new ArrayList<>();
	    for(File f: listOfFiles) {
	    	if(f.getName().endsWith(".json")) {
	    		files.add(f);
	    	}
	    }
		return files;
	}
	
	// ################################################################################################################
	
	public ArrayList<MenuItem> playerMenu(Player p){
		ArrayList<MenuItem> playerMenu = new ArrayList<>();
		playerMenu.add(new MenuItem("*** " + p.getColor().getCode() + p.getName() + color.reset() + " ***", "@", -1));
		playerMenu.add(new MenuItem("role the dice", "r", 0));
		playerMenu.add(new MenuItem("back to menu", "b", 99));
		return playerMenu;
	}
	
	public ArrayList<MenuItem> tokenMenu(ArrayList<int[]> tokens, int roll, Player p){
		ArrayList<MenuItem> tokenMenu = new ArrayList<>();
		tokenMenu.add(new MenuItem("*** " + p.getColor().getCode() + p.getName() + color.reset() + " ***", "@", -1));
		for(int[] token: tokens) {
			tokenMenu.add(new MenuItem("Token: "+(token[0]%10), ""+(token[0]%10), -1));
		}
		tokenMenu.add(new MenuItem("", "@", -1));
		ArrayList<String> dice = this.getDice(roll);
		for(String diceString: dice) {
			tokenMenu.add(new MenuItem(diceString, "@", -1));
		}
		return tokenMenu;
	}
	
	public ArrayList<String> getDice(int roll){
		ArrayList<String> list = new ArrayList<>();
		list.add("+---------+");
		switch(roll){
		case 1:
			list.add("|         |");
			list.add("|    *    |");
			list.add("|         |");
			break;
		case 2:
			list.add("| *       |");
			list.add("|         |");
			list.add("|       * |");
			break;
		case 3:
			list.add("| *       |");
			list.add("|    *    |");
			list.add("|       * |");
			break;
		case 4:
			list.add("| *     * |");
			list.add("|         |");
			list.add("| *     * |");
			break;
		case 5:
			list.add("| *     * |");
			list.add("|    *    |");
			list.add("| *     * |");
			break;
		case 6:
			list.add("| *     * |");
			list.add("| *     * |");
			list.add("| *     * |");
			break;
		}
		list.add("+---------+");
		return list;
	}
	
	public ArrayList<MenuItem> fancyWinMenu(Player p){
		ArrayList<MenuItem> playerMenu = new ArrayList<>();
		playerMenu.add(new MenuItem("*** " + p.getColor().getCode() + p.getName() + color.reset() + " won the game! ***", "@", -1));
		playerMenu.add(new MenuItem("back to menu", "b", 99));
		return playerMenu;
	}
	
	// ################################################################################################################
	
	public ArrayList<MenuItem> mainMenu(){
		ArrayList<MenuItem> mainMenu = new ArrayList<>();
		mainMenu.add(new MenuItem("new", "n" , 0 ));
		mainMenu.add(new MenuItem("resume", "r" , 1 ));
		mainMenu.add(new MenuItem("load existing game", "l" , 3 ));
		mainMenu.add(new MenuItem("save current game", "s" , 2 ));
		mainMenu.add(new MenuItem("delete existing game", "d" , 6 ));
		mainMenu.add(new MenuItem("user menu", "u", 4));
		mainMenu.add(new MenuItem("highscore", "h", 5));
		mainMenu.add(new MenuItem("quit", "q" , 99 ));
		return mainMenu;
	}
	
	public ArrayList<MenuItem> playerAmountMenu(){
		ArrayList<MenuItem> playerAmountMenu = new ArrayList<>();
		for(int i=2; i<=4;i++) {
			playerAmountMenu.add(new MenuItem(i + " Player", String.valueOf(i) , i ));
		}
		playerAmountMenu.add(new MenuItem("back to menu", "b" , 99 ));
		return playerAmountMenu;
	}
	
	public ArrayList<MenuItem> userMenu(ArrayList<Player> p){
		ArrayList<MenuItem> editMenu = new ArrayList<>();
		editMenu.add(new MenuItem("Create new", "n" , 0 ));
		editMenu.add(new MenuItem("edit", "e", 1));
		editMenu.add(new MenuItem("back to menu", "b" , 99 ));
		editMenu.add(new MenuItem("", "" , -1 ));
		editMenu.add(new MenuItem("*** Player list ***", "" , -1 ));
		for(int i=0; i<p.size(); i++) {
			if(p.get(i).isVisible()) {
				editMenu.add(new MenuItem(p.get(i).getName(), "" , -1 ));
			}
		}
		return editMenu;
	}
	
	public ArrayList<MenuItem> fileListMenu(ArrayList<Score> files){
		ArrayList<MenuItem> loadMenu = new ArrayList<>();
		loadMenu.add(new MenuItem("back to menu", "b" , 99 ));
		loadMenu.add(new MenuItem("", "" , -1 ));
		loadMenu.add(new MenuItem("*** Game list ***", "" , -1 ));
		if(files.size() == 0) {
			loadMenu.add(new MenuItem("no game available!", "" , -1 ));
		}
		for(int i=0; i<files.size(); i++) {
			if(files.get(i).isFinished()) {
				loadMenu.add(new MenuItem(files.get(i).getCreateTime() + "\t('"+files.get(i).getActPlayer().getName() + "' won the game)" , ""+(i+1) , i ));
			} else {
				loadMenu.add(new MenuItem(files.get(i).getCreateTime() + "\t(still running)", ""+(i+1) , i ));
			}
		}
		return loadMenu;
	}
	
	public ArrayList<MenuItem> selectPlayerMenu(ArrayList<Player> players){
		ArrayList<MenuItem> fileList = new ArrayList<>();
		fileList.add(new MenuItem("back to menu", "b" , 99 ));
		int counter = 1;
		for(int i=0; i<players.size(); i++) {
			if(players.get(i).isVisible()) {
				fileList.add(new MenuItem(players.get(i).getName(), ""+(counter) , i ));
				counter +=1;
			}
		}
		return fileList;
	}
	
	public ArrayList<MenuItem> colorMenu(ArrayList<ColorItem> color){
		ArrayList<MenuItem> fileList = new ArrayList<>();
		for(int i=0; i<color.size(); i++) {
			fileList.add(new MenuItem(new Color().getColoredName(color.get(i)), ""+(i+1) , i ));
		}
		return fileList;
	}
	
	public ArrayList<MenuItem> highScoreMenu(ArrayList<Player> players){
		ArrayList<MenuItem> highScoreMenu = new ArrayList<>();
		highScoreMenu.add(new MenuItem("back to menu", "b", 99));
		highScoreMenu.add(new MenuItem("\rName\t\t\t\twins\t\trolled 6\tplayed", "", -1));
		for(int i=0; i<players.size(); i++) {
			String name = players.get(i).getName();
			String wins = ""+players.get(i).getWins();
			String timesRolled6 = ""+players.get(i).getTimesRolled6();
			String timesPlayed = ""+players.get(i).getTotalPlayed();
			int newTab = 4 - (name.length()/8);
			String tabs = "";
			for(int j=0; j<newTab; j++){
				tabs += "\t";
			}
			String text = "\r"+ name +tabs+ wins +"\t\t"+ timesRolled6 +"\t\t"+ timesPlayed;
			highScoreMenu.add(new MenuItem("\r--------------------------------------------------------------------------", "", -1));
			highScoreMenu.add(new MenuItem(text, "", -1));
		}
		return highScoreMenu;
	}

}
