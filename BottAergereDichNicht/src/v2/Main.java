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
	  	Done: 1. Vor Beginn des Spieles w�rfelt jede*r Spieler*in 1-mal. Der*die Spieler*in mit der h�chsten Augenzahl er�ffnet das Spiel.

		2. Jede*r Spieler*in darf 3-mal w�rfeln, solange sich keine seiner*ihrer Spielfiguren im Spielkreis (zum Spielkreis z�hlen auch die Zielfelder) befindet und dort noch vorr�cken kann.
		
		3. Wird nach dreimaligem W�rfeln keine 6 gew�rfelt, ist der*die n�chste Spieler*in an der Reihe.
		
		Done: 4. (Pflicht) Beim W�rfeln einer 6 muss eine Figur aus den Eckkreisen auf das Startfeld der eigenen Spielfarbe, auf dem Spielfeld gesetzt werden (Ausnahme siehe 10 �Schlagpflicht�).
		
		Done: 5. (Pflicht) Das Startfeld muss immer frei gemacht werden, (Ausnahme siehe 10 �Schlagpflicht�) solange sich eine Spielfigur auf einem der Eckkreise befindet. Wer dieser Pflicht nicht nachkommt, muss seine Spielfigur zur�ck auf seinen eigenen Eckkreis stellen und darf erst wieder nach w�rfeln einer 6 auf das Spielfeld zur�ck
		
		Done: 6. Solange sich eine der eigenen Spielfiguren auf dem Spielfeld befindet und die M�glichkeit zum Vorr�cken besteht, darf lediglich 1-mal gew�rfelt werden. Dies gilt auch, wenn eine oder mehrere Figuren ihre Zielfelder erreicht haben, und dort noch vorr�cken k�nnen. Wer mehrere Figuren auf dem Spielfeld hat, kann sich aussuchen, mit welcher Figur er weiterzieht.
		
		Done: 7. Wurde eine 6 gew�rfelt muss der*die Spieler*in nochmals w�rfeln. Beim zweiten Wurf darf eine andere Figur benutzt werden (Ausnahme siehe 4. und 5.).
		
		Done: 8. (Pflicht) Jede gew�rfelte Augenzahl muss mit der eigenen Spielfigur durch Vorr�cken der gleichen Anzahl von Spielfeldern sofort ausgef�hrt werden. Es besteht Zugpflicht. Ausnahme von der Zugpflicht besteht dann, wenn mit keiner der eigenen Figuren die entsprechende Zahl vorger�ckt werden kann.
		
		Done: 9. �ber die im Wege stehenden gegnerischen und eigenen Figuren wird gesprungen, die �bersprungen Felder werden mitgez�hlt. Ein Spielfeld darf jedoch auch nur von einer Figur besetzt werden.
		
		10. (Pflicht) Trifft eine Figur auf ein vom Gegner besetztes Feld, so muss die gegnerische Spielfigur geschlagen werden. >>>Schlagen ist oberste Pflicht<<< Die geschlagene Figur muss auf ihren eigenen Eckkreis zur�ckgesetzt werden und darf erst nach w�rfeln einer 6 wieder am Spiel teilnehmen. Gibt es mehrere M�glichkeiten zum Schlagen, kann der*die Spieler*Spielerin selbst eine davon ausw�hlen (Beachte hierbei: doppelte Pflicht geht einfacher Pflicht vor � z.B. das eigene Startfeld ist von einer gegnerischen Figur besetzt = Pflicht 10 + 4 oder z.B. 10 + 5).
		
		11. Wer seiner Pflicht zum Schlagen der gegnerischen Spielfigur nicht nachkommt, muss seine Spielfigur zur�ck auf seinen eigenen Eckkreis stellen und darf erst wieder nach w�rfeln einer 6 auf das Spielfeld zur�ck.
		
		Done: 12. Es gilt der Grundsatz >>>Ber�hrt = Gef�hrt<<
		
		Done: 13. Bei nicht Durchf�hren eines Pflicht-Zuges (siehe 4; 5; 8, 10 und 14) wird der letzte Zug ung�ltig. Die ber�hrte Figur verbleibt auf dem bisherigen Feld und es geht mit dem*der n�chsten Spieler*in weiter.
		
		Done: 14. (Pflicht) Hat eine Spielfigur eine Runde vollst�ndig zur�ckgelegt, so muss Sie in die Zielfelder einr�cken. Das �Einr�cken� hat �Augengenau� zu erfolgen. Innerhalb der vier Zielfelder d�rfen die eigenen Figuren nicht �bersprungen werden.
		
		Done: 15. Das mutwillige Umwerfen von Spielfiguren f�hrt zum sofortigen Spielausschluss!
		
		Done: 16. Gewinner*in ist, wer als Erste*r alle seine Figuren auf seinen Zielfeldern platziert hat.
	 */
}
