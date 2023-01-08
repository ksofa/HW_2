import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractList;

/**
 * A class representing an entry in a file.
 */
public class Writer {
    private final String path;

    /**
     * Constructor of a class representing an entry in a file.
     */
    public Writer(String path) {
        this.path = path;
    }

    public void write(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }

    public void write(AbstractList<String> text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        for (String line : text) {
            fileWriter.write(line);
        }
        fileWriter.close();
    }
}