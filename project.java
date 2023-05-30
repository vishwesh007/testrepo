package consoles;
import java.io.*;
import java.net.*;

public class project {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.tickertape.in");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
               System.out.println(inputLine);
               
                }

                in.close();
                FileOutputStream fos = new FileOutputStream("/storage/emulated/0/#mods/response.txt");
                //response.getBytes(StandardCharsets.UTF_8);
                System.out.println();
                fos.close();
                System.out.println("Response written to file: response.txt");
            } else {
                System.out.println("Response code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}