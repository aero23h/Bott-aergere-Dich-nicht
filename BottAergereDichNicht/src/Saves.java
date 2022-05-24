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
      
		CSVWriterBuilder builder = new CSVWriterBuilder(new FileWriter(new File(this.filePath)));
		builder.withSeparator(';');
		ICSVWriter csvWriter = builder.build();
		csvWriter.writeAll(data);
        csvWriter.close();
	}
	
	public void loadCSV() throws IOException, CsvException {
	     CSVReader reader = new CSVReader(new FileReader(this.filePath));
	     String[] heading = reader.readNext();
		     for(String[] line : reader) {
		    	 for(String e: line) {
		    		 System.out.println(e);
		    	 }
		     }
		reader.close();
	}

}
