import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StockdataExtractor2 {
    public static void main(String[] args) {
        String stockSlugFilePath = "/storage/emulated/0/#mods/slug.txt"; // Specify the file path of the stock slugs
        String filePath = "/storage/emulated/0/#mods/stockdatapredictorvalue.txt"; // Specify the file path where you want to append the data
        String failedSlugsFilePath = "/storage/emulated/0/#mods/slugfailed.txt"; // Specify the file path for failed slugs

        try {
            List<String> stockSlugs = Files.readAllLines(Paths.get(stockSlugFilePath));

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            BufferedWriter failedSlugsWriter = new BufferedWriter(new FileWriter(failedSlugsFilePath, true));

            for (String stockSlug : stockSlugs) {
                String url = "https://www.tickertape.in" + stockSlug;

                try {
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    // Check if the response code is 200 (HTTP_OK)
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read the response data
                        try (InputStream inputStream = connection.getInputStream();
                             Scanner scanner = new Scanner(inputStream)) {
                            scanner.useDelimiter("\\A"); // Use the entire content as a delimiter
                            String response = scanner.hasNext() ? scanner.next() : "";

                            // Extract and write matched parts to file and print to screen
                            extractAndWriteMatches(response, writer);
                        }
                    } else {
                        failedSlugsWriter.write(stockSlug);
                        failedSlugsWriter.newLine();
                    }

                    connection.disconnect();
                } catch (UnknownHostException e) {
                    System.out.println("Unknown host: " + e.getMessage());
                    failedSlugsWriter.write(stockSlug);
                    failedSlugsWriter.newLine();
                }
            }

            writer.close();
            failedSlugsWriter.close();

            System.out.println("Data extraction completed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractAndWriteMatches(String content, BufferedWriter writer) throws IOException {
        String pattern1 = "<title>.*</title>";
        String pattern2 = ">\\d{2,3}<span";

        Pattern regexPattern1 = Pattern.compile(pattern1);
        Matcher matcher1 = regexPattern1.matcher(content);
        while (matcher1.find()) {
            String match = matcher1.group();
            writer.write(match);
            writer.newLine();
            System.out.println(match); // Print the match to the screen
        }

        Pattern regexPattern2 = Pattern.compile(pattern2);
        Matcher matcher2 = regexPattern2.matcher(content);
        while (matcher2.find()) {
            String match = matcher2.group();
            writer.write(match);
            writer.newLine();
            System.out.println(match); // Print the match to the screen
        }
    }
}
