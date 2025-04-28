package Directory_Walker;

public class Directory extends Node {
    private int id;
    private Directory[] directories;
    private File[] files;

    public Directory(String name, int id) {
        super(name);
        this.id = id;

    }
}
