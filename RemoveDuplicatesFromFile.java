package consoles;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromFile {
    public static void removeDuplicates(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            Set<String> lines = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            for (String uniqueLine : lines) {
                writer.write(uniqueLine);
                writer.newLine();
            }

            System.out.println("Duplicates removed. Unique lines written to file: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "/storage/emulated/0/#mods/slug.txt";
        String outputFilePath = "/storage/emulated/0/#mods/slugunique.txt";
        removeDuplicates(inputFilePath, outputFilePath);
    }
}
