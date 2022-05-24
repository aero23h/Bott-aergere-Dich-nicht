import java.io.IOException;

import com.opencsv.exceptions.CsvException;

public class Menu {
	public static void main(String[] args) throws IOException, CsvException {
		Saves s = new Saves();
		//s.saveCSV();
		s.loadCSV();
		
		Game g = new Game();
		g.printBoard();
	}
}
