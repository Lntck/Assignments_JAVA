package Directory_Walker;

import java.math.BigDecimal;

/**
 * Represents a file node in the file system.
 * Extends Node to include size and file properties, supporting the visitor pattern.
 */
public class File extends Node {
    // Size of the file in KB
    private BigDecimal size;
    // Additional file properties (e.g., metadata)
    private FileProperties fileProperties;

    /**
     * Constructs a File with parent ID, name, size, and properties.
     * @param parent_id ID of the parent node
     * @param name file name
     * @param size file size in KB
     * @param fileProperties additional file metadata
     */
    public File(int parent_id, String name, BigDecimal size, FileProperties fileProperties) {
        super(parent_id, name);
        this.size = size;
        this.fileProperties = fileProperties;
    }

    /**
     * Gets the file size.
     * @return file size in KB
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Gets the file properties.
     * @return file properties
     */
    public FileProperties getFileProperties() {
        return fileProperties;
    }

    /**
     * Accepts a visitor to process the file.
     * @param v visitor to process this file
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}