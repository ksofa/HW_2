import java.util.Scanner;

/**
 * Class of user interface
 */
public class UserInterface {
    private final Scanner scanner;

    /**
     * Constructor
     */
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to concatenate
     */
    public void concatenate() {
        ScannerDirectory directoryScanner;
        FileCollection fileStacksCollection;
        while (true) {
            System.out.println("Enter the path to the directory where the files are stored");
            try {
                directoryScanner = new ScannerDirectory(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        fileStacksCollection = new FileCollection(directoryScanner.getFileCoins());
        if (fileStacksCollection.normaliseStacks()) {
            System.out.println("The files are successfully connected, the resulting text:");
            for (String line : fileStacksCollection.getContents()) {
                System.out.println(line);
            }
            while (true) {
                System.out.println("Press 1 / 2 (1 - save result , 2 - dont save)");
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    System.out.println("Enter the path to the file where you want to save the result");
                    try {
                        Writer simpleFileWriter = new Writer(scanner.nextLine());
                        simpleFileWriter.write(fileStacksCollection.getContents());
                        break;
                    } catch (Exception e) {
                        System.out.println("Incorrect input, error: " + e.getMessage());
                    }
                } else if (input.equals("2")) {
                    break;
                } else {
                    System.out.println("Incorrect input");
                }
            }
        } else {
            System.out.println("It is impossible to glue files due to an error in their dependencies");
        }
    }
}