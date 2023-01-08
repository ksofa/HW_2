import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing the directory scanner.
 */
public class ScannerDirectory {
    private final File directory;

    /**
     * Constructor
     */
    public ScannerDirectory(String path) throws IllegalArgumentException {
        directory = new File(path);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("По указанному пути не найдена директория");
        }
    }

    /**
     * A method that returns a list of files in a given directory and its subdirectories.
     */
    public ArrayList<Coin> getFileCoins() {
        ArrayList<Coin> fileCoins = new ArrayList<>();
        try {
            scanDirectory(directory, fileCoins);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileCoins;
    }

    /**
     * Method returns path
     */
    public String getDirectoryPath() {
        return directory.getAbsolutePath();
    }

    private void scanDirectory(File directory, ArrayList<Coin> fileCoins) throws FileNotFoundException, SecurityException {
        try {
            for (File item : Objects.requireNonNull(directory.listFiles())) {
                if (item.isDirectory()) {
                    scanDirectory(item, fileCoins);
                } else {
                    if (!item.getName().endsWith(".txt")) {
                        continue;
                    }
                    try (FileReader fileReader = new FileReader(item)) {
                        StringBuilder fileText = new StringBuilder();
                        StringBuilder fileName = new StringBuilder();
                        int ch;
                        while ((ch = fileReader.read()) != -1) {
                            fileText.append((char) ch);
                        }
                        fileName.append(item.getAbsolutePath());
                        fileName.delete(0, getDirectoryPath().length() + 1);
                        fileName.delete(fileName.length() - 4, fileName.length());
                        fileCoins.add(new Coin(fileName.toString(), fileText.toString()));
                    } catch (IOException e) {
                        throw new FileNotFoundException("Erroe");
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new FileNotFoundException("An error occurred while reading the file");
        } catch (SecurityException e) {
            throw new SecurityException("A file access error has occurred");
        }
    }
}