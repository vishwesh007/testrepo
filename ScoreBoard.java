package consoles;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScoreBoard {
    public static void main(String[] args) {
        String stocksidFilePath = "/storage/emulated/0/#mods/sid.txt";
        String responseFilePath = "/storage/emulated/0/#mods/responsesid.txt";
        String errorFilePath = "/storage/emulated/0/#mods/sidvalueerror.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(stocksidFilePath));
             FileWriter errorWriter = new FileWriter(errorFilePath, true)) {

            String stocksid;
            while ((stocksid = reader.readLine()) != null) {
                URL url = new URL("https://analyze.api.tickertape.in/stocks/scorecard/" + stocksid);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    FileOutputStream fos = new FileOutputStream(responseFilePath, true);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = connection.getInputStream().read(buffer)) > 0) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    fos.close();
                    System.out.println("Response content appended to response.txt");
                } else {
                    String errorMessage = "Failed to retrieve response for stocksid: " + stocksid +
                            ". Response code: " + responseCode + "\n";
                    errorWriter.write(errorMessage);
                    System.out.println(errorMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
