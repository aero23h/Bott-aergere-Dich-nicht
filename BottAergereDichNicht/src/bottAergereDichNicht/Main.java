package bottAergereDichNicht;

public class Main {

	public static void main(String[] args){
		// Create a game
		Game g = new Game();
		// start the game
		g.run();
	}
}

/*
	To get the full experience of the game you need to install the "ANSI Escape in Console" addOn over the Eclipse Marketplace.
	In addition you need to activate the Carriage Return (\r) as control character over the console properties.
	Also you probably need to add 3 additional libraries. 
	The libraries are:
						jackson-core-2.13.3.jar
						jackson-databind-2.13.3.jar
						jackson-annotations-2.13.3.jar
*/