package consoles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class tickertextsearch {
    public static void main(String[] args) {
        String inputFile = "/storage/emulated/0/#mods/equity3column.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tickertextsearch(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tickertextsearch(String search) {
        try {
            URL url = new URL("https://api.tickertape.in/search?text=" + search);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                FileOutputStream fos = new FileOutputStream("/storage/emulated/0/#mods/response.txt", true);
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                while (bytesRead > 0) {
                    fos.write(buffer, 0, bytesRead);
                    bytesRead = inputStream.read(buffer);
                }
                fos.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8)); // Write a newline
                fos.close();
                System.out.println(search + " Response content written to /storage/emulated/0/#mods/response.txt");
            } else {
                System.out.println("Failed to retrieve response from server for " + search);
                // Code for handling the failure is missing
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
