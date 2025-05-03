package Directory_Walker;

import java.math.BigDecimal;
import java.util.HashMap;

public class FileSystem {
    // HashMap provides fast O(1) lookup by ID.
    // Hierarchy is maintained via parentId and subdirectories in Directory objects.
    // For large-scale systems (>10K directories) or disk-based storage, better to use B-tree.
    private HashMap<Integer, Directory> hashMap;

    private Directory root;

    FileSystem() {
        this.hashMap = new HashMap<>();
        this.root = new Directory(".", 0, 0);
        hashMap.put(0, root);
    }

    public boolean parseLine(String[] line) {
        try {
            int curs = 0;
            String command = line[curs++];

            if (command.equals("DIR")) {
                int id = Integer.parseInt(line[curs++]);
                int parent_id = 0;
                if (line[curs].matches("\\d+")) { parent_id = Integer.parseInt(line[curs++]);}
                String name = line[curs++];

                createDirectory(id, parent_id, name);
            } else if (command.equals("FILE")) {
                int id = Integer.parseInt(line[curs++]);
                boolean isReadOnly = line[curs++].equals("T");
                String owner = line[curs++];
                String group = line[curs++];
                BigDecimal size = new BigDecimal(line[curs++]);
                String file = line[curs++];
                String name = file.replaceFirst("[.][^.]+$", "");
                String extension = file.substring(file.lastIndexOf('.') + 1);

                createFile(id, isReadOnly, owner, group, size, name, extension);
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private Directory findDirectory(int id) {
        return hashMap.get(id);
    }

    public void createDirectory(int id, int parent_id, String name) {
        if (hashMap.containsKey(id)) {
            return;
        }

        Directory dir = new Directory(name, id, parent_id);
        hashMap.put(id, dir);

        Directory parent = findDirectory(parent_id);
        if (parent == null) {
            return;
        }
        parent.addChild(dir);
    }

    public void createFile(int parent_id, boolean isReadOnly, String owner, String group, BigDecimal size, String name, String extension) {
        if (!hashMap.containsKey(parent_id)) {
            return;
        }

        FileProperties fileProperties = FilePropertiesFactory.getFileProperties(extension, isReadOnly, owner, group);
        File file = new File(parent_id, name, size, fileProperties);

        Directory parent = findDirectory(parent_id);
        if (parent == null) {
            return;
        }
        parent.addChild(file);
    }

    public void printFileSystem() {
        TreePrinter treePrinter = new TreePrinter();
        treePrinter.printTree(root);
    }

    public Directory getRoot() {
        return root;
    }
}
