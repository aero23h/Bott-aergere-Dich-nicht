import com.opencsv.*;
import java.io.*;
import java.util.ArrayList;

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
		System.out.println("Saved");
	}

}
