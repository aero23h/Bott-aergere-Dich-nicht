
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
	private Color c;
	private String[][] header;
	private BufferedReader keyReader;
	
	// constructor
	public Menu() {
		this.keyReader = new BufferedReader(new InputStreamReader(System.in));
		this.c = new Color();
		this.header = new String [][]{
				{"-----------------------------------------------------------------------------------"},
				{"  ___     _   _     _  _                        ___  _    _           _    _   _   "},
				{" | _ )___| |_| |_  (_)(_)_ _ __ _ ___ _ _ ___  |   \\(_)__| |_    _ _ (_)__| |_| |_ "},
				{" | _ / _ |  _|  _| / _` | '_/ _` / -_| '_/ -_) | |) | / _| ' \\  | ' \\| / _| ' |  _|"},
				{" |___\\___/\\__|\\__| \\__,_|_| \\__, \\___|_| \\___| |___/|_\\__|_||_| |_||_|_\\__|_||_\\__|"},
				{"                            |___/                                                  "},
				{"-----------------------------------------------------------------------------------"},
				};
		
	}
	
	// input functions
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (char) result;
	}
	
	public int inputToken(ArrayList<Integer> tokens) {
		int result = -1;
		do {
			try {
				System.out.print("Please select number: ");
				result = keyReader.read() - 48; // 48 is ascii 0 transfer acii to int
				int dummy = -1;
				do {
					dummy = keyReader.read();
				} while(dummy  != 10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!tokens.contains(result)) {
				System.err.println("Invalid key!");
			}
		} while(!tokens.contains(result));
		return result;
	}
	
	// plot menu
	public int selectMenu(ArrayList<menuItem> menuItems, String header) {
		// is header shown in console?
		// header == "" -> no header
		if(header != "") {
			// print menuHeader
			System.out.println("\t\t\t *** " + header + " ***" );
			// build menuLine and print it
			String menuLine = "";
			for(int i=0; i<menuItems.size();i++) {
				menuLine += "\t\t\t" + "("+ menuItems.get(i).getKey() +")  " +  menuItems.get(i).getText() + "\n";
			}
			System.out.println(menuLine);
		}
		// read key from keyboard
		char key = this.inputKey();
		for(int i=0; i<menuItems.size();i++) {
			if(key == menuItems.get(i).getKey()) {
				return menuItems.get(i).getResult();
			}
		}
		// wrong key selected
		return -1;
	}
	
	// plot header
	public void plotHeader() {
		for(int i=0; i<this.header.length; i++){
			System.out.println(this.header[i][0]);
			}
	}
	
	// ########################################################################################
	// build menu
	
	// new menuItem("text",'key', return value in integer)
	// return value of -1 == no function
	// key == '@' -> ignor the key
	
	public ArrayList<menuItem> mainMenu(){
		ArrayList<menuItem> mainMenu = new ArrayList<>();
		mainMenu.add(new menuItem("new", 'n' , 0 ));
		mainMenu.add(new menuItem("resume", 'r' , 1 ));
		mainMenu.add(new menuItem("save current game", 's' , 2 ));
		mainMenu.add(new menuItem("load existing game", 'l' , 3 ));
		mainMenu.add(new menuItem("edit saved users", 'u', 4));
		mainMenu.add(new menuItem("quit", 'q' , 99 ));
		return mainMenu;
	}
	
	public ArrayList<menuItem> playerMenu(Player p){
		ArrayList<menuItem> playerMenu = new ArrayList<>();
		playerMenu.add(new menuItem("*** " + p.getColor() + p.getName() + c.getReset() + " ***", '@', -1));
		playerMenu.add(new menuItem("role the dice", 'r', 0));
		playerMenu.add(new menuItem("back to menu", 'b', 99));
		return playerMenu;
	}
	
	public ArrayList<menuItem> tokenMenu(ArrayList<Integer> tokens, int roll, Player p){
		ArrayList<menuItem> tokenMenu = new ArrayList<>();
		tokenMenu.add(new menuItem("*** " + p.getColor() + p.getName() + c.getReset() + " ***", '@', -1));
		for(int token: tokens) {
			tokenMenu.add(new menuItem("Token: " + token, Character.forDigit(token, 10), -1));
		}
		tokenMenu.add(new menuItem("", '@', -1));
		ArrayList<String> dice = this.getDice(roll);
		for(String diceString: dice) {
			tokenMenu.add(new menuItem(diceString, '@', -1));
		}
		return tokenMenu;
	}
	
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
