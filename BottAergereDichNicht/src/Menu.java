
import java.util.ArrayList;

public class Menu {
	private Color c;
	private String line = "-----------------------------------------------------------------------------------";
	private String[][] top = {
			{"  ___     _   _     _  _                        ___  _    _           _    _   _   "},
			{" | _ )___| |_| |_  (_)(_)_ _ __ _ ___ _ _ ___  |   \\(_)__| |_    _ _ (_)__| |_| |_ "},
			{" | _ / _ |  _|  _| / _` | '_/ _` / -_| '_/ -_) | |) | / _| ' \\  | ' \\| / _| ' |  _|"},
			{" |___\\___/\\__|\\__| \\__,_|_| \\__, \\___|_| \\___| |___/|_\\__|_||_| |_||_|_\\__|_||_\\__|"},
			{"                            |___/                                                  "},
			};
	
	public Menu() {
		this.c = new Color();
	}
	
	public ArrayList<String> buildGameMenu(Player player) {	
		ArrayList<String> list = new ArrayList<>();
		list.add("*** " + player.getColor()  + player.getName() + c.getReset() +" ***");
		list.add("");
		list.add("roll a dice (r)");
		list.add("cancel game (c)");
		return list;
	}
	
	public ArrayList<String> buildGameMenu(Player player, int[] token) {
		ArrayList<String> list = new ArrayList<>();
				list.add("*** " + player.getColor() +  player.getName() + c.getReset() + " ***");
				list.add("");
				for(int t: token){
					list.add("\t Token " + (t%10) + " ("+ (t%10) +")");
				}
		return list;
	}
	
	public ArrayList<String> buildStartMenu(){
		ArrayList<String> list = new ArrayList<>();
		list.add("Start game (s)");
		list.add("Load game (l)");
		list.add("Show highscore (h)");
		list.add("Quit (q)");	
		return list;
	}
	
	public void plotMenu2Console(ArrayList<String> menu) {
		System.out.println(this.line);
		for(int i=0; i<this.top.length;i++) {
			System.out.println(this.top[i][0]);
		}
		System.out.println(this.line);
		for(int i=0; i<menu.size();i++) {
			System.out.println("\t\t\t\t"+ menu.get(i));
		}
		System.out.println(this.line);
	}
}
