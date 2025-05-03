package Directory_Walker;

/**
 * Interface for iterating over nodes in a file system structure.
 */
public interface Iterator {
    /**
     * Checks if there is a next node in the iteration.
     * @return true if a next node exists, false otherwise
     */
    boolean hasNext();

    /**
     * Retrieves the next node in the iteration.
     * @return the next node
     */
    Node next();
}