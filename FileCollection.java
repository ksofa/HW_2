import java.util.ArrayList;

/**
 * A class representing a collection of stacks of files that have dependencies.
 */
public class FileCollection {
    private final ArrayList<FileStack> fileStacks = new ArrayList<FileStack>();

    /**
     * Constructor
     */
    public FileCollection() {
    }

    /**
     * Constructor
     */
    public FileCollection(Coin fileCoin) {
        addFile(fileCoin);
    }

    /**
     * Constructor
     */
    public FileCollection(ArrayList<Coin> fileCoins) {
        for (Coin fileCoin : fileCoins) {
            addFile(fileCoin);
        }
    }

    /**
     * Method add
     *
     */
    public void addFile(Coin file) {
        for (FileStack fileStack : fileStacks) {
            if (file.getRequiredFiles().size() != 0 && fileStack.containsFiles(file.getRequiredFiles())) {
                fileStack.addFile(file);
                return;
            }
        }
        FileStack fileStack = new FileStack(file);
        fileStacks.add(fileStack);
    }

    /**
     * A method that, if possible, combines files with dependencies into collections.
     */
    public boolean normaliseStacks() {
        for (int i = 0; i < fileStacks.size(); ++i) {
            for (int j = i + 1; j < fileStacks.size(); ++j) {
                if (fileStacks.get(i).containsFiles(fileStacks.get(j).getTopRequests())) {
                    fileStacks.set(i, fileStacks.get(i).unite(fileStacks.get(j)));
                    fileStacks.remove(j);
                    --j;
                } else if (fileStacks.get(j).containsFiles(fileStacks.get(i).getTopRequests())) {
                    fileStacks.set(j, fileStacks.get(j).unite(fileStacks.get(i)));
                    fileStacks.remove(i);
                    --i;
                    break;
                }
            }
        }
        for (FileStack fileStack : fileStacks) {
            if (!fileStack.isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * A method that returns a set of full texts of all file folders with dependencies in the collection.
     */
    public ArrayList<String> getContents() {
        ArrayList<String> contents = new ArrayList<>();
        for (FileStack fileStack : fileStacks) {
            contents.add(fileStack.getFullText());
        }
        return contents;
    }

    /**
     * A method that returns all folders of files with dependencies in the collection.
     */
    public ArrayList<FileStack> getFileStacks() {
        return fileStacks;
    }
}