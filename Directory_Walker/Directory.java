package Directory_Walker;

import java.util.ArrayList;

public class Directory extends Node {
    private int id;
    private ArrayList<Node> children;

    public Directory(String name, int id, int parent_id) {
        super(parent_id, name);
        this.id = id;
        this.children = new ArrayList<>();
    }

    public Iterator createIterator() {
        return new DirectoryIterator(this);
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return new ArrayList<>(children);
    }

    public int getId() {
        return id;
    }

    public void accept(Visitor v) {
        v.visit(this);

        for(Node child : children) {
            child.accept(v);
        }
    }
}
