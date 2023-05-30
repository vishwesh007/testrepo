package consoles;
import java.io.*;
import java.util.*;

public class RemoveDuplicateFromFile {

    public static void main(String[] args) {
        String filename = "example.txt"; // replace with your file name
        String text = readTextFromFile(filename); // read text from file
        String[] lines = text.split("\n"); // split text into lines
        Set<String> uniqueLines = new LinkedHashSet<>(); // set to store unique lines

        for (String line : lines) {
            // check if line already exists in the set
            if (!uniqueLines.contains(line)) {
                // if not, add it to the set
                uniqueLines.add(line);
            }
        }

        // write unique lines back to the file
        try (BufferedReader br = new BufferedReader(new FileReader(filename)); FileWriter fw = new FileWriter(filename)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!uniqueLines.contains(line)) {
                    fw.write(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readTextFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}