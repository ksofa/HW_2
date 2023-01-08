import java.util.AbstractList;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A class representing a stack of files that have dependencies.
 */
public class FileStack {
    private final SortedSet<Coin> files = new TreeSet<>();
    private final ArrayList<String> fileNames = new ArrayList<>();

    /**
     * Constructor
     */
    public FileStack() {
    }

    /**
     * Constructor
     */
    public FileStack(Coin file) {
        addFile(file);
    }

    /**
     * Method add
     */
    public void addFile(Coin file) {
        files.add(file);
        fileNames.add(file.getName());
    }

    /**
     * A method that returns information about whether these files are contained in a stack.
     */
    public boolean containsFiles(AbstractList<String> fileNames) {
        for (String name : fileNames) {
            if (!this.fileNames.contains(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method that combines two stacks of files that have dependencies.
     */
    public FileStack unite(FileStack fileStack) {
        FileStack newFileStack = new FileStack();
        for (Coin file : files) {
            newFileStack.addFile(file);
        }
        for (Coin file : fileStack.files) {
            newFileStack.addFile(file);
        }
        return newFileStack;
    }

    /**
     * Method  all file dependencies in the stack are ok.
     */
    public boolean isFull() {
        ArrayList<String> requests = new ArrayList<>();
        for (Coin file : files) {
            requests.addAll(file.getRequiredFiles());
            if (requests.contains(file.getName())) {
                return false;
            }
        }
        return containsFiles(requests);
    }

    /**
     * Method returns a list of dependencies of the top file in the stack.
     */
    public ArrayList<String> getTopRequests() {
        return files.first().getRequiredFiles();
    }

    /**
     * Method return full text
     */
    public String getFullText() {
        StringBuilder fullText = new StringBuilder();
        for (Coin file : files) {
            fullText.append(file.getText());
            fullText.append("\n");
        }
        return fullText.toString();
    }
}
