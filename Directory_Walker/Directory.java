package Directory_Walker;

import java.util.ArrayList;

public class Directory extends Node {
    private int id;
    private int parent_id;
    private ArrayList<Directory> directories;
    private ArrayList<File> files;

    public Directory(String name, int id, int parent_id) {
        super(name);
        this.id = id;
        this.parent_id = parent_id;
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public ArrayList<Directory> getDirectories() {
        return directories;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public Iterator createIterator() {
        return new DirectoryIterator(this);
    }
}
