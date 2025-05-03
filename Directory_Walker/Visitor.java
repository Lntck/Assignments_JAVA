package Directory_Walker;

/**
 * Interface for the Visitor pattern to process file system nodes.
 * Defines methods to handle Directory and File nodes.
 */
public interface Visitor {
    /**
     * Processes a Directory node.
     * @param dir the directory to process
     */
    void visit(Directory dir);

    /**
     * Processes a File node.
     * @param file the file to process
     */
    void visit(File file);
}