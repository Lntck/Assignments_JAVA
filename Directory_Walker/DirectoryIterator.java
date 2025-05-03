package Directory_Walker;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Iterator for traversing a directory's nodes in dfs.
 * Uses a stack to manage node traversal.
 */
public class DirectoryIterator implements Iterator {
    // Stack for depth-first traversal of nodes
    private Stack<Node> stack;

    /**
     * Constructs an iterator for the given directory.
     * @param directory the directory to iterate over
     */
    public DirectoryIterator(Directory directory) {
        this.stack = new Stack<>();
        // Initialize with the root directory
        this.stack.push(directory);
    }

    /**
     * Checks if there are more nodes to iterate.
     * @return true if nodes remain, false otherwise
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Retrieves the next node in the depth-first traversal.
     * @return the next node, or null if no nodes remain
     */
    @Override
    public Node next() {
        // Return null if no more nodes
        if (!hasNext()) {
            return null;
        }

        // Pop the current node
        Node currentNode = stack.pop();
        // If the node is a directory, push its children in reverse order
        if (currentNode instanceof Directory) {
            ArrayList<Node> Childrens = ((Directory) currentNode).getChildren();
            // Add children to stack in reverse to maintain order
            for (int i = Childrens.size() - 1; i >= 0; i--) {
                stack.add(Childrens.get(i));
            }
        }
        return currentNode;
    }
}