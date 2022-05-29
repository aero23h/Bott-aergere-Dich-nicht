
import java.io.File;
import java.util.ArrayList;

public class Menu {
	private Color c;
	private String line;
	private String[][] top;
	
	public Menu() {
		this.c = new Color();
		this.line = "-----------------------------------------------------------------------------------";
		this.top = new String [][]{
				{"  ___     _   _     _  _                        ___  _    _           _    _   _   "},
				{" | _ )___| |_| |_  (_)(_)_ _ __ _ ___ _ _ ___  |   \\(_)__| |_    _ _ (_)__| |_| |_ "},
				{" | _ / _ |  _|  _| / _` | '_/ _` / -_| '_/ -_) | |) | / _| ' \\  | ' \\| / _| ' |  _|"},
				{" |___\\___/\\__|\\__| \\__,_|_| \\__, \\___|_| \\___| |___/|_\\__|_||_| |_||_|_\\__|_||_\\__|"},
				{"                            |___/                                                  "},
				};
		
	}
	public ArrayList<String> emptyMenu() {	
		ArrayList<String> list = new ArrayList<>();
		list.add("");
		return list;
	}
	
	public ArrayList<String> buildWonMenu(Player p) {	
		ArrayList<String> list = new ArrayList<>();
		list.add("\t*** " + p.getColor() + p.getName() + c.getReset() + " has won *** \n");
		list.add("");
		list.add("(e) \tExit");
		return list;
	}
	
	public ArrayList<String> buildGameMenu(Player p) {	
		ArrayList<String> list = new ArrayList<>();
		list.add("*** " + p.getColor()  + p.getName() + c.getReset() +" ***");
		list.add("");
		list.add("(r) roll a dice");
		list.add("(c) cancel game");
		return list;
	}
	
	public ArrayList<String> buildGameMenu(Player p, int[] token, int step) {
		ArrayList<String> list = new ArrayList<>();
		list.add("*** " + p.getColor() +  p.getName() + c.getReset() + " ***");
		list.add("");
		// add token to list
		for(int t: token){
			list.add("("+(t%10)+")" + " Token " + p.getColor() + (t%10) + c.getReset());
		}
		// add space
		list.add("");
		// add dice to list
		switch(step){
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
	
	public ArrayList<String> buildStartMenu(){
		ArrayList<String> list = new ArrayList<>();
		list.add("\t*** Menue *** \n");
		list.add("(s) \tStart game ");
		list.add("(l) \tLoad game ");
		list.add("(h) \tShow highscore ");
		list.add("(q) \tQuit ");	
		return list;
	}
	
	public ArrayList<String> buildAmountMenu(){
		ArrayList<String> list = new ArrayList<>();
		list.add("\t*** Select amount players *** \n");
		list.add("\t*** 2-4 ***");
		return list;
	}
	
	public ArrayList<String> buildCustomMenu(String[] s){
		ArrayList<String> list = new ArrayList<>();
		for(String line: s) {
			list.add(line);
		}
		return list;
	}
	
	public ArrayList<String> buildPlayerMenu(String path){
		ArrayList<String> list = new ArrayList<>();
		list.add("\t*** Saved players *** \n");
		if(!this.checkDirExist(path)) {
			new File(path).mkdirs();
		}
		File dir = new File(path);
		File[] files = dir.listFiles();
		int counter = 0;
		for(File e: files) {
			if(e.getName().contains(".json")) {
				counter +=1;
				list.add("(" + counter + ") \t" + e.getName().replace(".json", ""));
			}
		}
		// if no games are found
		if(counter == 0) {
			list.add("\tno players found!");
		}
		list.add("");
		list.add("(n)\tcreate new palyer");
		list.add("(c)\tback");
		return list;
	}
	
	public ArrayList<String> buildColorMenu(Player p, String[] colorList){
		Color c = new Color();
		ArrayList<String> list = new ArrayList<>();
		list.add("\t*** Select color ***");
		list.add("\tPlayer: "+ p.getName() + "\n");
		for(int i=0; i<colorList.length;i++) {
			list.add("\t("+i+") \t" + c.getColorName(colorList[i]) +c.getReset());
		}
		list.add("\t(c)\tback");
		return list;
	}
	
	public ArrayList<String> buildLoadMenu(String path){
		ArrayList<String> list = new ArrayList<>();
		list.add("\t*** Saved games *** \n");
		if(!this.checkDirExist(path)) {
			new File(path).mkdirs();
		}
		File dir = new File(path);
		File[] files = dir.listFiles();
		int counter = 0;
		for(File e: files) {
			if(e.getName().contains(".json")) {
				counter +=1;
				list.add("(" + counter + ") \t" + e.getName().replace(".json", ""));
			}
		}
		// if no games are found
		if(counter == 0) {
			list.add("\tno saved games found!");
		}
		list.add("");
		list.add("(c)\t\tback");
		
		return list;
	}
	
	public boolean checkDirExist(String path) {
		if(new File(path).exists()) {
			return true;
		}
		return false;
	}
	
	public void plotMenu2Console(ArrayList<String> menu) {
		System.out.println(this.line);
		for(int i=0; i<this.top.length;i++) {
			System.out.println(this.top[i][0]);
		}
		System.out.println(this.line);
		for(int i=0; i<menu.size();i++) {
			System.out.println("\t\t\t" + menu.get(i));
		}
		System.out.println(this.line);
	}
}
