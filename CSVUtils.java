package consoles;

import java.io.*;
import java.util.*;

public class CSVUtils {
    public static String[] extractSecondColumn(String filePath, String newFileName) throws IOException {
        File file = new File(filePath);
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 1) {
                    rows.add(values);
                }
            }
        }

        String[] secondColumn = new String[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            secondColumn[i] = rows.get(i)[2];
        }

        FileWriter writer = new FileWriter(newFileName);
        for (String value : secondColumn) {
            writer.write(value + "\n");
        }
        writer.close();
        
        return  secondColumn;
    }
}

//CSVUtils.extractSecondColumn("input.csv", "output.txt");