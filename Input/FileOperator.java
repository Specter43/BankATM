package Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileOperator {

    public FileOperator() {
    }

    public HashMap<String, List<String>> readFile(String fileName) {
        HashMap<String, List<String>> data = new HashMap<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader;
            String[] keys = null;
            try {
                myReader = new Scanner(myObj);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            // Read First line as keys
            if (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                keys = line.split("/");
                for (String key : keys){
                    data.put(key, new ArrayList<>());
                }
            }
            // Read the rest lines as values
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] values = line.split("\\s+");
                for (int i = 0; i < keys.length; i++) {
                    data.get(keys[i]).add(values[i]);
                }
            }
            myReader.close();
            return data;
        } catch (Exception e) {
            System.out.printf("%s not found.\n", fileName);
        }
        return data;
    }

    public void write(String filename) {

    }
}
