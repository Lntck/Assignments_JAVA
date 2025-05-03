package Directory_Walker;

import java.util.ArrayList;

/**
 * Represents a directory node in the file system.
 * Extends Node to include a unique ID and a list of child nodes, supporting the visitor and iterator patterns.
 */
public class Directory extends Node {
    // Unique identifier for the directory
    private int id;
    // List of child nodes (files or directories)
    private ArrayList<Node> children;

    /**
     * Constructs a Directory with a name, unique ID, and parent ID.
     * @param name directory name
     * @param id unique directory ID
     * @param parent_id ID of the parent node
     */
    public Directory(String name, int id, int parent_id) {
        super(parent_id, name);
        this.id = id;
        this.children = new ArrayList<>();
    }

    /**
     * Creates an iterator for traversing child nodes.
     * @return iterator for children
     */
    public Iterator createIterator() {
        return new DirectoryIterator(this);
    }

    /**
     * Adds a child node to the directory.
     * @param node child node to add
     */
    public void addChild(Node node) {
        children.add(node);
    }

    /**
     * Gets a copy of the child nodes list.
     * @return new ArrayList containing children
     */
    public ArrayList<Node> getChildren() {
        return new ArrayList<>(children);
    }

    /**
     * Gets the directory's unique ID.
     * @return directory ID
     */
    public int getId() {
        return id;
    }

    /**
     * Accepts a visitor to process the directory and its children.
     * @param v visitor to process this directory and its children
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
        // Recursively visit all child nodes
        for (Node child : children) {
            child.accept(v);
        }
    }
}