package Directory_Walker;

import java.math.BigDecimal;

/**
 * Visitor implementation to calculate the total size of files in the file system.
 * Accumulates file sizes while ignoring directories.
 */
public class SizeVisitor implements Visitor {
    // Accumulated total size of all files visited
    private BigDecimal totalSize;

    /**
     * Constructs a SizeVisitor with an initial size of zero.
     */
    public SizeVisitor() {
        this.totalSize = BigDecimal.ZERO;
    }

    /**
     * Adds the size of a file to the total.
     * @param file the file to process
     */
    @Override
    public void visit(File file) {
        // Accumulate file size
        totalSize = totalSize.add(file.getSize());
    }

    /**
     * Processes a directory (no action taken for directories).
     * @param dir the directory to process
     */
    @Override
    public void visit(Directory dir) {
        // Directories contribute no size directly
    }

    /**
     * Gets the total size of all visited files.
     * @return total size in KB
     */
    public BigDecimal getTotalSize() {
        return totalSize;
    }
}