package bottAergereDichNicht;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args){
		// Create a game
		Game g = new Game();
		// start the game
		g.run();
		
//		ArrayList<Integer> n = new ArrayList<>();
//		n.add(1);
//		n.add(5);
//		n.add(3);
//		n.add(2);
//		
//		g.menu.plotStartRolls(g.board.getScore().getPlayers(), g.board.getScore().getActPlayer(), n, 3);
	}
}

/*
	todo:
		248
	
	
	To get the full experience of the game you need to install the "ANSI Escape in Console" addOn over the Eclipse Marketplace.
	In addition you need to activate the Carriage Return (\r) as control character over the console properties.
	Also you probably need to add 3 additional libraries. 
	The libraries are:
						jackson-core-2.13.3.jar
						jackson-databind-2.13.3.jar
						jackson-annotations-2.13.3.jar

*/