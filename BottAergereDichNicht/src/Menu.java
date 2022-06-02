
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
	private Color color;
	private String[][] header;
	private BufferedReader keyReader;
	
	// constructor
	public Menu() {
		this.color = new Color();
		this.header = new String [][]{
				{"-----------------------------------------------------------------------------------"},
				{"  ___     _   _     _  _                        ___  _    _           _    _   _   "},
				{" | _ )___| |_| |_  (_)(_)_ _ __ _ ___ _ _ ___  |   \\(_)__| |_    _ _ (_)__| |_| |_ "},
				{" | _ / _ |  _|  _| / _` | '_/ _` / -_| '_/ -_) | |) | / _| ' \\  | ' \\| / _| ' |  _|"},
				{" |___\\___/\\__|\\__| \\__,_|_| \\__, \\___|_| \\___| |___/|_\\__|_||_| |_||_|_\\__|_||_\\__|"},
				{"                            |___/                                                  "},
				{"-----------------------------------------------------------------------------------"},
				};
		this.keyReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public char inputKey() {
		int result = 0;
		try {
			System.out.print("Please select key: ");
			result = keyReader.read();
			// remove overflow
			int dummy = -1;
			do {
				dummy = keyReader.read();
			} while(dummy  != 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (char) result;
	}
	
	public int inputToken(ArrayList<Integer> tokens) {
		int result = -1;
		do {
			try {
				System.out.print("Please select token: ");
				result = keyReader.read() - 48; // 48 is ascii 0 transfer acii to int
				int dummy = -1;
				do {
					dummy = keyReader.read();
				} while(dummy  != 10);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!tokens.contains(result)) {
				System.err.println("Invalid key!");
			}
		} while(!tokens.contains(result));
		return result;
	}
	
	public String inputString() {
		String result = "";
		System.out.print("Please enter String: ");
		try {
			result = keyReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int inputNumber() {
		int result = -1;
		do {
			try {
				System.out.print("Please enter number: ");
				result = Integer.parseInt(keyReader.readLine());
			} catch (Exception e) {
				System.err.println("Input is not a usable number");
			}
		} while(result == -1);
		return result;
	}

	// plot menu return integer by number
	public int selectMenuByNumber(ArrayList<MenuItem> menuItems, String header) {
		this.plotMenu(menuItems, header);
		return this.getNumber(menuItems);
	}
	
	// plot menu return integer by char
	public int selectMenuByChar(ArrayList<MenuItem> menuItems, String header) {
		this.plotMenu(menuItems, header);
		return this.getKey(menuItems);
	}
	
	public int getNumber(ArrayList<MenuItem> menuItems) {
		int key = this.inputNumber();
		for(int i=0; i<menuItems.size();i++) {
			if(key == menuItems.get(i).getIntKey()) {
				return menuItems.get(i).getResult();
			}
		}
		return -1;
	}
	
	public int getKey(ArrayList<MenuItem> menuItems) {
		// read key from keyboard
		char key = this.inputKey();
		for(int i=0; i<menuItems.size();i++) {
			if(key == menuItems.get(i).getKey()) {
				return menuItems.get(i).getResult();
			}
		}
		return -1;
	}
	
	// plot header
	public void plotHeader() {
		for(int i=0; i<this.header.length; i++){
			System.out.println(this.header[i][0]);
			}
	}
	
	// plot menu
	public void plotMenu(ArrayList<MenuItem> menuItems, String header) {
		// is header shown in console?
		// header == "" -> no header
		if(header != "") {
			// print menuHeader
			System.out.println("\t\t\t *** " + header + " ***" );
			// build menuLine and print it
			String menuLine = "";
			for(int i=0; i<menuItems.size();i++) {
				if(menuItems.get(i).getIntKey() == -1) {
					menuLine += "\t\t\t" + "("+ menuItems.get(i).getKey() +")  " +  menuItems.get(i).getText() + "\n";
				} else {
					menuLine += "\t\t\t" + "("+ menuItems.get(i).getIntKey() +")  " +  menuItems.get(i).getText() + "\n";
				}
			}
			System.out.println(menuLine);
		}
		
	}
	
	public ArrayList<File> getAllFiles(String path) {
	    File folder = new File(path);
	    File[] listOfFiles = folder.listFiles();
	    int count = 0;
	    ArrayList<File> files = new ArrayList<>();
	    for(File f: listOfFiles) {
	    	if(f.getName().endsWith(".json")) {
	    		files.add(f);
	    	}
	    }
		return files;
	}
	
	// ########################################################################################
	// build menu
	
	// new menuItem("text",'key', return value in integer)
	// return value of -1 == no function
	// key == '@' -> ignor the key
	
	public ArrayList<MenuItem> colorMenu(String[] colorList){
		ArrayList<MenuItem> colorMenu = new ArrayList<>();
		for(int c=0; c<colorList.length; c++) {
			if(colorList[c] != "") {
				switch(colorList[c]) {
				// blue
				case "\u001b[34m":
					colorMenu.add(new MenuItem(this.color.getColorName(colorList[c]), 'b', 1));
					// green
				case "\u001b[32m":
					colorMenu.add(new MenuItem(this.color.getColorName(colorList[c]), 'g', 2));
					// red
				case "\u001b[31m":
					colorMenu.add(new MenuItem(this.color.getColorName(colorList[c]), 'r', 3));
					// yellow
				case "\u001b[33m":
					colorMenu.add(new MenuItem(this.color.getColorName(colorList[c]), 'y', 1));
				}
			}
		}
		return colorMenu;
	}
	
	public ArrayList<MenuItem> playerList(ArrayList<File> files){
		ArrayList<MenuItem> playerList = new ArrayList<>();
		for(int i=1; i<files.size();i++) {
			playerList.add(new MenuItem(files.get(i).getName().replaceAll(".json", ""), i , i ));
		}
		playerList.add(new MenuItem("create one", 0 , 998));
		playerList.add(new MenuItem("back to menu", 999 , 999));
		return playerList;
	}
	
	public ArrayList<MenuItem> playerAmountMenu(){
		ArrayList<MenuItem> playerAmountMenu = new ArrayList<>();
		for(int i=2; i<=4;i++) {
			playerAmountMenu.add(new MenuItem(i + " Player", Character.forDigit(i, 10) , i ));
		}
		playerAmountMenu.add(new MenuItem("back to menu", 'b' , 99 ));
		return playerAmountMenu;
	}
	
	public ArrayList<MenuItem> mainMenu(){
		ArrayList<MenuItem> mainMenu = new ArrayList<>();
		mainMenu.add(new MenuItem("new", 'n' , 0 ));
		mainMenu.add(new MenuItem("resume", 'r' , 1 ));
		mainMenu.add(new MenuItem("save current game", 's' , 2 ));
		mainMenu.add(new MenuItem("load existing game", 'l' , 3 ));
		mainMenu.add(new MenuItem("edit saved users", 'u', 4));
		mainMenu.add(new MenuItem("quit", 'q' , 99 ));
		return mainMenu;
	}
	
	public ArrayList<MenuItem> playerMenu(Player p){
		ArrayList<MenuItem> playerMenu = new ArrayList<>();
		playerMenu.add(new MenuItem("*** " + p.getColor() + p.getName() + color.getReset() + " ***", '@', -1));
		playerMenu.add(new MenuItem("role the dice", 'r', 0));
		playerMenu.add(new MenuItem("back to menu", 'b', 99));
		return playerMenu;
	}
	
	public ArrayList<MenuItem> tokenMenu(ArrayList<Integer> tokens, int roll, Player p){
		ArrayList<MenuItem> tokenMenu = new ArrayList<>();
		tokenMenu.add(new MenuItem("*** " + p.getColor() + p.getName() + color.getReset() + " ***", '@', -1));
		for(int token: tokens) {
			tokenMenu.add(new MenuItem("Token: " + token, Character.forDigit(token, 10), -1));
		}
		tokenMenu.add(new MenuItem("", '@', -1));
		ArrayList<String> dice = this.getDice(roll);
		for(String diceString: dice) {
			tokenMenu.add(new MenuItem(diceString, '@', -1));
		}
		return tokenMenu;
	}
	
	//public ArrayList<MenuItem>
	
	public ArrayList<String> getDice(int roll){
		ArrayList<String> list = new ArrayList<>();
		switch(roll){
		case 1:
			list.add("+---------+");
			list.add("|         |");
			list.add("|    *    |");
			list.add("|         |");
			list.add("+---------+");
			break;
		case 2:
			list.add("+---------+");
			list.add("| *       |");
			list.add("|         |");
			list.add("|       * |");
			list.add("+---------+");
			break;
		case 3:
			list.add("+---------+");
			list.add("| *       |");
			list.add("|    *    |");
			list.add("|       * |");
			list.add("+---------+");
			break;
		case 4:
			list.add("+---------+");
			list.add("| *     * |");
			list.add("|         |");
			list.add("| *     * |");
			list.add("+---------+");
			break;
		case 5:
			list.add("+---------+");
			list.add("| *     * |");
			list.add("|    *    |");
			list.add("| *     * |");
			list.add("+---------+");
			break;
		case 6:
			list.add("+---------+");
			list.add("| *     * |");
			list.add("| *     * |");
			list.add("| *     * |");
			list.add("+---------+");
			break;
		}
		return list;
	}
}
