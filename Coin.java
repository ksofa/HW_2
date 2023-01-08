import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing a file that has dependencies on other files.
 */
public class Coin implements Comparable<Coin> {
    private final String name;
    private final String text;
    private final ArrayList<String> requiredFiles;

    /**
     * Constuctor
     */
    public Coin(String name, String text) {
        this.name = name;
        this.text = text;
        this.requiredFiles = new ArrayList<>();
        String regex = "require\\s['‘’][^'‘’]*['‘’]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String requiredFile = matcher.group();
            requiredFile = requiredFile.substring(9, requiredFile.length() - 1);
            requiredFiles.add(requiredFile);
        }
    }

    /**
     * Method return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method return text
     */
    public String getText() {
        return text;
    }

    /**
     * Method returns a list of file dependencies.
     */
    public ArrayList<String> getRequiredFiles() {
        return requiredFiles;
    }

    /**
     * Method compare
     */
    @Override
    public int compareTo(Coin o) {
        if (this.name.equals(o.name)) {
            return 0;
        }
        if (this.getRequiredFiles().contains(o.getName())) {
            return 1;
        } else {
            return -1;
        }
    }
}
