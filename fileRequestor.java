package consoles;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class fileRequestor {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://analyze.api.tickertape.in/stocks/scorecard/VDAN");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            FileOutputStream fos = new FileOutputStream("/storage/emulated/0/#mods/response.txt");
            byte[] buffer = new byte[1024];
            int bytesRead = connection.getInputStream().read(buffer);
            while (bytesRead > 0) {
                fos.write(buffer, 0, bytesRead);
                bytesRead = connection.getInputStream().read(buffer);
            }
            fos.close();
            System.out.println("Response content written to response.txt");
        } else {
            System.out.println("Failed to retrieve response from server");
        }
    }
}