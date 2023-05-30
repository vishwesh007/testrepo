package consoles;

import java.io.*;

public class FileUtil {

    public static void createFile(String filePath, String content) {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            System.out.println("File created successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            handleException("Failed to create file", file.getAbsolutePath(), e);
        }
    }

    public static void appendToFile(String filePath, String content) {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
            System.out.println("File appended successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            handleException("Failed to append to file", file.getAbsolutePath(), e);
        }
    }

    public static void modifyFile(String filePath, String content) {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            System.out.println("File modified successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            handleException("Failed to modify file", file.getAbsolutePath(), e);
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        try {
            if (file.delete()) {
                System.out.println("File deleted successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("Failed to delete file: " + file.getAbsolutePath());
            }
        } catch (SecurityException e) {
            handleException("Failed to delete file", file.getAbsolutePath(), e);
        }
    }

    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            handleException("Failed to read file", filePath, e);
        }
        return sb.toString();
    }

    private static void handleException(String errorMessage, String filePath, Exception e) {
        System.out.println(errorMessage + ": " + filePath);
        e.printStackTrace();
    }
}
