package Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.out.printf(e.toString());
        }
        return data;
    }

    public void write(String filename) {

    }

    public void changeLine(String filename, int lineNumber, String data) throws IOException {
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber + 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
