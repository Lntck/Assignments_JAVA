package Directory_Walker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class FileSystem {
    // HashMap provides fast O(1) lookup by ID.
    // Hierarchy is maintained via parentId and subdirectories in Directory objects.
    // For large-scale systems (>10K directories) or disk-based storage, better to use B-tree.
    private HashMap<Integer, Directory> hashMap;

    private ArrayList<Directory> directories;
    private ArrayList<File> files;

    FileSystem() {
        this.hashMap = new HashMap<>();
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
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

                System.out.println(id + " " + parent_id + " " + name);
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

                System.out.println(id + " " + isReadOnly + " " + size + " " + name + " " + extension);
                createFile(id, isReadOnly, owner, group, size, name, extension);
            } else {
                System.out.println("Invalid command");
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid number of elements in command");
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

        if (parent_id == 0) {
            directories.add(dir);
        } else {
            Directory parent = findDirectory(parent_id);
            if (parent == null) {
                return;
            }
            parent.getDirectories().add(dir);
        }
    }

    public void createFile(int id, boolean isReadOnly, String owner, String group, BigDecimal size, String name, String extension) {
        if (id != 0 && !hashMap.containsKey(id)) {
            return;
        }

        FileProperties fileProperties = FilePropertiesFactory.getFileProperties(extension, isReadOnly, owner, group);
        File file = new File(name, size, fileProperties);

        if (id == 0) {
            files.add(file);
        } else {
            Directory parent = findDirectory(id);
            if (parent == null) {
                return;
            }
            parent.getFiles().add(file);
        }
    }

    public void printFileSystem() {
        for (Directory dir : directories) {
            System.out.println("Directory: " + dir.getName());
            for (File file : dir.getFiles()) {
                System.out.println("  File: " + file.getName());
            }
            for (Directory subDir : dir.getDirectories()) {
                System.out.println("  Subdirectory: " + subDir.getName());
            }
        }
        for (File file : files) {
            System.out.println("File: " + file.getName());
        }
    }
}
