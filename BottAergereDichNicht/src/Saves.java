import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saves {
	private String filePath;
	
	public Saves() {
		this.filePath = "example.csv";
	}
	
	
	public void saveCSV() throws IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();
        data.add(new String[] { "Name", "Class", "Marks" });
        data.add(new String[] { "Aman", "10", "620" });
        data.add(new String[] { "Suraj", "10", "630" });
      
		CSVWriter writer = new CSVWriter(new FileWriter(new File(this.filePath)));
        writer.writeAll(data);
		writer.close(); 
	}
	
	public void loadCSV() throws IOException, CsvException {
	     CSVReader reader = new CSVReader(new FileReader(this.filePath));
	     String[] heading = reader.readNext();
		     for(String[] line : reader) {
		    	 System.out.println(line[0] +" "+ line[1] +" "+ line[2]);
		     }
		reader.close();
	}

}
