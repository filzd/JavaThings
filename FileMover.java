import java.io.File;
import java.util.Scanner;

public class FileMover {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.print(ANSI_BLUE + "Enter your choice (mkdir, rmdir, mktxt, rmtxt): " + ANSI_RESET);
            String choice = scanner.nextLine();
            switch (choice) {
                case "mkdir":
                    createDirectory(scanner);
                    break;
                case "rmdir":
                    removeDirectory(scanner);
                    break;
                case "mktxt":
                    createTextFile(scanner);
                    break;
                case "rmtxt":
                    removeTextFile(scanner);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please choose from 'mkdir', 'rmdir', or 'txt'." + ANSI_RESET);
                    break;
            }
        }
    }

    public static void createDirectory(Scanner scanner) {
        System.out.print(ANSI_BLUE + "Enter the location (path) where you want to create the directory: " + ANSI_RESET);
        String location = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter the name of the directory you want to create: " + ANSI_RESET);
        String directoryName = scanner.nextLine();

        String fullPath = location + File.separator + directoryName;
        File directory = new File(fullPath);

        if (directory.mkdir()) {
            System.out.println(ANSI_GREEN + "Directory '" + fullPath + "' created successfully." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Failed to create directory '" + fullPath + "'." + ANSI_RESET);
        }
    }

    public static void removeDirectory(Scanner scanner) {
        System.out.print(ANSI_BLUE + "Enter the location (path) where you want to create the directory: " + ANSI_RESET);
        String location = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter the name of the directory you want to remove: " + ANSI_RESET);
        String directoryName = scanner.nextLine();

        String fullPath = location + File.separator + directoryName;
        File directory = new File(fullPath);

        if (directory.exists() && directory.isDirectory()) {
            if (directory.delete()) {
                System.out.println(ANSI_GREEN + "Directory '" + directoryName + "' removed successfully." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Failed to remove directory '" + directoryName + "'." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "Directory '" + directoryName + "' not found." + ANSI_RESET);
        }
    }

    public static void createTextFile(Scanner scanner) {
        System.out.print(ANSI_BLUE + "Enter the location (path) where you want to create the text file: " + ANSI_RESET);
        String location = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter the name of the text file you want to create (without extension): " + ANSI_RESET);
        String fileName = scanner.nextLine();

        // Add .txt extension to the file name
        fileName += ".txt";

        // Combine the location and file name to create the full path
        String fullPath = location + File.separator + fileName;

        System.out.print("Enter the content of the text file: ");
        String content = scanner.nextLine();

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fullPath);
            fileWriter.write(content);
            fileWriter.close();
            System.out.println(ANSI_GREEN + "Text file '" + fullPath + "' created successfully." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "An error occurred: " + e.getMessage() + ANSI_RESET);
        }
    }

    public static void removeTextFile(Scanner scanner) {
        System.out.print(ANSI_BLUE + "Enter the location (path) of the text file you want to remove: " + ANSI_RESET);
        String location = scanner.nextLine();

        System.out.print(ANSI_BLUE + "Enter the name of the text file you want to remove: " + ANSI_RESET);
        String fileName = scanner.nextLine();

        // Combine the location and file name to create the full path
        String fullPath = location + File.separator + fileName;

        File fileToDelete = new File(fullPath);

        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println(ANSI_GREEN + "Text file '" + fullPath + "' removed successfully." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Failed to remove text file '" + fullPath + "'." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_GREEN + "Text file '" + fullPath + "' does not exist." + ANSI_RESET);
        }
    }

}
