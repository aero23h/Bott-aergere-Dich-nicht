package v2;
public class Main {

	public static void main(String[] args){
		// Create a game
		Game g = new Game();
		// start the game
		g.run();
		
		//g.getBoard().getScore().move(02, 6, 100);
		//g.getBoard().getScore().move(32, 6, 100);
		//g.getBoard().getScore().move(02, 3, 210);
		//g.getBoard().getScore().move(32, 12, 210);
		//g.getBoard().getScore().move(32, 1, 220);
		//g.getBoard().plotScore2Console(g.getMenu().playerMenu(g.getBoard().getScore().getActPlayer()));


	    // to do
			// add something while quitting
		
			// add multiple roll on 6
		
			// fix move kick
	}
	
	/* Rules 
	  	Done: 1. Vor Beginn des Spieles würfelt jede*r Spieler*in 1-mal. Der*die Spieler*in mit der höchsten Augenzahl eröffnet das Spiel.

		2. Jede*r Spieler*in darf 3-mal würfeln, solange sich keine seiner*ihrer Spielfiguren im Spielkreis (zum Spielkreis zählen auch die Zielfelder) befindet und dort noch vorrücken kann.
		
		3. Wird nach dreimaligem Würfeln keine 6 gewürfelt, ist der*die nächste Spieler*in an der Reihe.
		
		Done: 4. (Pflicht) Beim Würfeln einer 6 muss eine Figur aus den Eckkreisen auf das Startfeld der eigenen Spielfarbe, auf dem Spielfeld gesetzt werden (Ausnahme siehe 10 „Schlagpflicht“).
		
		Done: 5. (Pflicht) Das Startfeld muss immer frei gemacht werden, (Ausnahme siehe 10 „Schlagpflicht“) solange sich eine Spielfigur auf einem der Eckkreise befindet. Wer dieser Pflicht nicht nachkommt, muss seine Spielfigur zurück auf seinen eigenen Eckkreis stellen und darf erst wieder nach würfeln einer 6 auf das Spielfeld zurück
		
		Done: 6. Solange sich eine der eigenen Spielfiguren auf dem Spielfeld befindet und die Möglichkeit zum Vorrücken besteht, darf lediglich 1-mal gewürfelt werden. Dies gilt auch, wenn eine oder mehrere Figuren ihre Zielfelder erreicht haben, und dort noch vorrücken können. Wer mehrere Figuren auf dem Spielfeld hat, kann sich aussuchen, mit welcher Figur er weiterzieht.
		
		Done: 7. Wurde eine 6 gewürfelt muss der*die Spieler*in nochmals würfeln. Beim zweiten Wurf darf eine andere Figur benutzt werden (Ausnahme siehe 4. und 5.).
		
		Done: 8. (Pflicht) Jede gewürfelte Augenzahl muss mit der eigenen Spielfigur durch Vorrücken der gleichen Anzahl von Spielfeldern sofort ausgeführt werden. Es besteht Zugpflicht. Ausnahme von der Zugpflicht besteht dann, wenn mit keiner der eigenen Figuren die entsprechende Zahl vorgerückt werden kann.
		
		Done: 9. Über die im Wege stehenden gegnerischen und eigenen Figuren wird gesprungen, die übersprungen Felder werden mitgezählt. Ein Spielfeld darf jedoch auch nur von einer Figur besetzt werden.
		
		10. (Pflicht) Trifft eine Figur auf ein vom Gegner besetztes Feld, so muss die gegnerische Spielfigur geschlagen werden. >>>Schlagen ist oberste Pflicht<<< Die geschlagene Figur muss auf ihren eigenen Eckkreis zurückgesetzt werden und darf erst nach würfeln einer 6 wieder am Spiel teilnehmen. Gibt es mehrere Möglichkeiten zum Schlagen, kann der*die Spieler*Spielerin selbst eine davon auswählen (Beachte hierbei: doppelte Pflicht geht einfacher Pflicht vor – z.B. das eigene Startfeld ist von einer gegnerischen Figur besetzt = Pflicht 10 + 4 oder z.B. 10 + 5).
		
		11. Wer seiner Pflicht zum Schlagen der gegnerischen Spielfigur nicht nachkommt, muss seine Spielfigur zurück auf seinen eigenen Eckkreis stellen und darf erst wieder nach würfeln einer 6 auf das Spielfeld zurück.
		
		Done: 12. Es gilt der Grundsatz >>>Berührt = Geführt<<
		
		Done: 13. Bei nicht Durchführen eines Pflicht-Zuges (siehe 4; 5; 8, 10 und 14) wird der letzte Zug ungültig. Die berührte Figur verbleibt auf dem bisherigen Feld und es geht mit dem*der nächsten Spieler*in weiter.
		
		Done: 14. (Pflicht) Hat eine Spielfigur eine Runde vollständig zurückgelegt, so muss Sie in die Zielfelder einrücken. Das „Einrücken“ hat „Augengenau“ zu erfolgen. Innerhalb der vier Zielfelder dürfen die eigenen Figuren nicht übersprungen werden.
		
		Done: 15. Das mutwillige Umwerfen von Spielfiguren führt zum sofortigen Spielausschluss!
		
		Done: 16. Gewinner*in ist, wer als Erste*r alle seine Figuren auf seinen Zielfeldern platziert hat.
	 */
}
