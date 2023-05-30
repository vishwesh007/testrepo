package consoles;

import java.io.*;

public class FileOperations {
    public static void main(String[] args) {
        // Specify the file path
        String filePath = "example.txt";

        // Check if the file exists, and create it if it doesn't
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Write to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Hello, world!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Append to the file
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\nThis is a new line.");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read from the file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}