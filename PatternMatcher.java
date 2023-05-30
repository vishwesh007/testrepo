package consoles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    private static final int NUM_THREADS = 4; // Number of threads to use

    public static void main(String[] args) {
        String inputFilePath = "/storage/emulated/0/#mods/stockdatapredictor.txt";
        String outputFilePath = "/storage/emulated/0/#mods/stockdataanalysis.txt";
        String pattern1 = "<title>.*</title>";
        String pattern2 = ">\\d{2,3}<span";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true))) {

            ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

            String line;
            while ((line = reader.readLine()) != null) {
                Runnable worker = new PatternMatcherWorker(line, pattern1, pattern2, writer);
                executor.execute(worker);
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (IOException e) {
            System.out.println("Error occurred while reading or writing the file.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
            e.printStackTrace();
        }
    }

    private static class PatternMatcherWorker implements Runnable {
        private final String line;
        private final String pattern1;
        private final String pattern2;
        private final PrintWriter writer;

        public PatternMatcherWorker(String line, String pattern1, String pattern2, PrintWriter writer) {
            this.line = line;
            this.pattern1 = pattern1;
            this.pattern2 = pattern2;
            this.writer = writer;
        }

        @Override
        public void run() {
            boolean pattern1Found = matchPattern(line, pattern1, writer);
            boolean pattern2Found = matchPattern(line, pattern2, writer);

            if (pattern1Found || pattern2Found) {
                System.out.println("Pattern found in the data: " + line);
            }
        }

        private boolean matchPattern(String input, String pattern, PrintWriter writer) {
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(input);
            boolean found = false;

            while (matcher.find()) {
                String match = matcher.group();
                writer.println(match);
                found = true;
            }

            return found;
        }
    }
}
