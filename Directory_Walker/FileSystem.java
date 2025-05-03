package Directory_Walker;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Manages the file system structure, parsing input to create directories and files.
 * Maintains a hierarchy using a HashMap for fast ID-based lookups.
 */
public class FileSystem {
    // Stores directories by ID for O(1) lookup
    // For large-scale systems or disk-based storage, better to use B-tree.
    private HashMap<Integer, Directory> hashMap;

    // Root directory of the file system
    private Directory root;

    /**
     * Initializes the file system with a root directory (ID 0, name ".").
     */
    FileSystem() {
        this.hashMap = new HashMap<>();
        this.root = new Directory(".", 0, 0);
        // Store root in HashMap
        hashMap.put(0, root);
    }

    /**
     * Parses a line of input to create a directory or file.
     * @param line array of tokens representing a command
     * @return true if parsing succeeds, false otherwise
     */
    public boolean parseLine(String[] line) {
        try {
            int curs = 0;
            String command = line[curs++];

            if (command.equals("DIR")) {
                int id = Integer.parseInt(line[curs++]);
                int parent_id = 0;
                // Optional parent_id; defaults to 0 if not a number
                if (line[curs].matches("\\d+")) {
                    parent_id = Integer.parseInt(line[curs++]);
                }
                String name = line[curs++];

                // Create directory with parsed values
                createDirectory(id, parent_id, name);
            } else if (command.equals("FILE")) {
                int id = Integer.parseInt(line[curs++]);
                boolean isReadOnly = line[curs++].equals("T");
                String owner = line[curs++];
                String group = line[curs++];
                BigDecimal size = new BigDecimal(line[curs++]);
                String file = line[curs++];
                // Extract name and extension from file
                String name = file.replaceFirst("[.][^.]+$", "");
                String extension = file.substring(file.lastIndexOf('.') + 1);

                // Create file with parsed values
                createFile(id, isReadOnly, owner, group, size, name, extension);
            } else {
                // Invalid command
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            // Insufficient tokens in input
            return false;
        }
        return true;
    }

    /**
     * Finds a directory by its ID.
     * @param id directory ID
     * @return Directory object or null if not found
     */
    private Directory findDirectory(int id) {
        return hashMap.get(id);
    }

    /**
     * Creates a directory and adds it to the parent and HashMap.
     * @param id unique directory ID
     * @param parent_id ID of parent directory
     * @param name directory name
     */
    public void createDirectory(int id, int parent_id, String name) {
        // Skip if ID already exists
        if (hashMap.containsKey(id)) {
            return;
        }

        // Create new directory
        Directory dir = new Directory(name, id, parent_id);
        hashMap.put(id, dir);

        // Add to parent directory
        Directory parent = findDirectory(parent_id);
        if (parent == null) {
            return;
        }
        parent.addChild(dir);
    }

    /**
     * Creates a file and adds it to the parent directory.
     * @param parent_id ID of parent directory
     * @param isReadOnly read-only status
     * @param owner file owner
     * @param group file group
     * @param size file size in KB
     * @param name file name (without extension)
     * @param extension file extension
     */
    public void createFile(int parent_id, boolean isReadOnly, String owner, String group, BigDecimal size, String name, String extension) {
        // Skip if parent does not exist
        if (!hashMap.containsKey(parent_id)) {
            return;
        }

        // Get or create file properties using Flyweight pattern
        FileProperties fileProperties = FilePropertiesFactory.getFileProperties(extension, isReadOnly, owner, group);
        // Create new file
        File file = new File(parent_id, name, size, fileProperties);

        // Add to parent directory
        Directory parent = findDirectory(parent_id);
        if (parent == null) {
            return;
        }
        parent.addChild(file);
    }

    /**
     * Prints the file system tree starting from the root.
     */
    public void printFileSystem() {
        TreePrinter treePrinter = new TreePrinter();
        treePrinter.printTree(root);
    }

    /**
     * Gets the root directory.
     * @return root Directory object
     */
    public Directory getRoot() {
        return root;
    }
}