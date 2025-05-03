package Directory_Walker;

/**
 * Abstract base class for file system nodes.
 * Represents a node with a parent ID and name, supporting the visitor pattern.
 */
public abstract class Node {
    // Unique ID of the parent node
    private int parent_id;
    // Name of the node (file or directory)
    private String name;

    /**
     * Constructs a Node with a parent ID and name.
     * @param parent_id ID of the parent node
     * @param name name of the node
     */
    public Node(int parent_id, String name) {
        this.parent_id = parent_id;
        this.name = name;
    }

    /**
     * Gets the name of the node.
     * @return node name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parent node's ID.
     * @return parent ID
     */
    public int getParentId() {
        return parent_id;
    }

    /**
     * Accepts a visitor for processing the node.
     * @param v visitor to process the node
     */
    public abstract void accept(Visitor v);
}