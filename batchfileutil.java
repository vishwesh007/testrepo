package consoles;

import java.io.*;

public class batchfileutil {

    public static void main(String[] args) {
        String folderPath = "path/to/folder";
        performFileActionInFolder(folderPath, FileAction.READ);
        performFileActionInFolder(folderPath, FileAction.APPEND, "Appended content");
        performFileActionInFolder(folderPath, FileAction.WRITE, "New content");
    }

    public static void performFileActionInFolder(String folderPath, FileAction action) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    performFileAction(file.getPath(), action, null);
                }
            }
        }
    }

    public static void performFileActionInFolder(String folderPath, FileAction action, String content) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    performFileAction(file.getPath(), action, content);
                }
            }
        }
    }

    public static void performFileAction(String filePath, FileAction action, String content) {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(file);
        }

        switch (action) {
            case READ:
                readFile(file);
                break;
            case APPEND:
                appendToFile(file, content);
                break;
            case WRITE:
                writeFile(file, content);
                break;
        }
    }

    public static void readFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            System.out.println("File content (" + file.getName() + "):");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(File file, String content) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(content);
            writer.close();
            System.out.println("Appended content to file: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File file, String content) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            System.out.println("Successfully wrote to file: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Created new file: " + file.getName());
            } else {
                System.out.println("File already exists: " + file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum FileAction {
        READ,
        APPEND,
        WRITE
    }
}
