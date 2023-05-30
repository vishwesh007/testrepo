package consoles;

import java.io.*;
import java.net.*;

public class stockdata2 {
    public static void main(String[] args) {
        try {
            // Create a URL object for the server
            URL url = new URL("https://example.com/output.txt");

            // Create an HttpURLConnection object to connect to the server
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Set the method for the HTTP request
            con.setRequestMethod("GET");

            // Send the request and get the response
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer output = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine.replaceAll("hello", "world"));
            }
            in.close();

            // Write the output to a file
            FileWriter fw = new FileWriter("output.txt");
            fw.write(output.toString());
            fw.close();

            // Print the status code and message
            System.out.println("Response code: " + responseCode);
            System.out.println("Output written to output.txt");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}