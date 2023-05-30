package sasta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class requestwriterv8 {
    String path = "/storage/emulated/0/HttpCanary/download/ticker/request.json";
    String bpath = "/storage/emulated/0/HttpCanary/download/ticker/request_body.json";

    public requestwriterv8() {
        path = this.path;
        int zaa = path.indexOf("request.json");
        bpath = path.substring(0, zaa) + "request_body.json";
    }

    public void main(String[] args) {
        requestwriterv8 s = new requestwriterv8();
        s.generateMainMethod();
    }

    String temp = "";

    public void setTexter(String se) {
        temp += se;
        System.out.println(temp);
    }

    public void generateMainMethod() {
        String url = url();
        importer();
        setTexter("public void main(String[] args) throws Exception {\n\tString url = \"" + url + "\";\n\n\tHttpsURLConnection pasta = (HttpsURLConnection) new URL(url).openConnection();\n\n\tpasta.setDoOutput(true);");

        mpain();
        System.out.print("String urlParameters = \"");
        body();
        setTexter("\n");
        last();
    }

    public void importer() {
        String p = "\nimport java.io.BufferedReader;\nimport java.io.BufferedWriter;\nimport java.io.DataOutputStream;\nimport java.io.File;\nimport java.io.FileNotFoundException;\nimport java.io.InputStreamReader;\nimport java.net.URL;\nimport java.net.URL;\nimport java.util.*;\nimport java.util.HashMap;\nimport java.util.Map.*;\nimport java.util.Scanner;\nimport javax.net.ssl.HttpsURLConnection;\nimport javax.net.ssl.HttpsURLConnection;\n\n";
        setTexter(p);
    }

    public void last() {
        String last = "\n\t\ttry (DataOutputStream wr = new DataOutputStream(pasta.getOutputStream())) {\n\t\t\twr.writeBytes(urlParameters);\n\t\t\twr.flush();\n\t\t}\n\n\t\tint responseCode = pasta.getResponseCode();\n\t\tsetTexter(\"Sending 'POST' request to URL: \" + url);\n\t\tsetTexter(\"Post parameters: \" + urlParameters);\n\t\tsetTexter(\"Response Code: \" + responseCode);\n\n\t\ttry (BufferedReader in = new BufferedReader(\n\t\t\tnew InputStreamReader(pasta.getInputStream()))) {\n\n\t\t\tString line;\n\t\t\tStringBuilder response = new StringBuilder();\n\n\t\t\twhile ((line = in.readLine()) != null) {\n\t\t\t\tresponse.append(line);\n\t\t\t}\n\n\t\t\t// print result\n\t\t\tsetTexter(response.toString());\n\t\t}";
        setTexter(last);
    }

    public String body() {
        String msg1 = "";
        try {
            File f = new File(bpath);
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                msg1 = msg1 + data + "\\n";
            }
            read.close();
        } catch (FileNotFoundException e) {
            setTexter("\";");
        }

        if (msg1.length() > 2) {
            int n = 0, o = 0, p = 0, ze = 0;
            String msg2 = "";
            n = msg1.indexOf("\"", o);

            if (n != -1) {
                msg2 = msg2 + msg1.substring(0, n);
            } else {
                msg2 = msg2 + msg1;
            }

            while ((n | o) != (-1 | msg1.length())) {
                n = msg1.indexOf("\"", o);
                o = msg1.indexOf("\"", n + 1);

                if ((n | o) != -1) {
                    msg2 = msg2 +("\\");
                    msg2 = msg2 + msg1.substring(n, o);
                }
                if ((n | o) == -1) {
                    if (ze < 1) {
                        msg2 = msg2 +("\\");
                        int q = msg1.lastIndexOf("\"");
                        msg2 = msg2 + msg1.substring(q);
                        ze++;
                    }
                }

                o = n + 1;
                p = o;
            }

            System.out.print(msg2 + "\";");
        }
        return msg1;
    }

    public String url() {
        String message = "";
        try {
            File f = new File(path);
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                message = message + data;
            }
            read.close();
        } catch (FileNotFoundException e) {
            setTexter("file not found exception");
            e.printStackTrace();
        }
        return message;
    }

    public HashMap<String, String> mpain() {
        HashMap<String, String> property = new HashMap<String, String>();
        String message = "";

        try {
            File f = new File(path);
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String data = read.nextLine();
                message = message + data;
            }
            read.close();
        } catch (FileNotFoundException e) {
            setTexter("file not found exception");
            e.printStackTrace();
        }

        return property;
    }
}
