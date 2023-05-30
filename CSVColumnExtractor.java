package consoles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVColumnExtractor {
    public static void extractThirdColumn(String inputFileName, String outputFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 4) {
                    String thirdColumnValue = columns[2].replaceAll("\"", columns[3]);  // Remove double quotes if present
                    writer.write(thirdColumnValue);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFileName = "/storage/emulated/0/Download/Equity.csv";
        String outputFileName = "/storage/emulated/0/#mods/equity3column.txt";
        extractThirdColumn(inputFileName, outputFileName);
    }
}
