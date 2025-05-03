package Directory_Walker;

/**
 * Represents metadata properties of a file.
 * Stores extension, read-only status, owner, and group information.
 */
public class FileProperties {
    // File extension (e.g., "txt")
    private String extension;
    // Indicates if the file is read-only
    private boolean isReadOnly;
    // File owner (e.g., username)
    private String owner;
    // File group (e.g., group name)
    private String group;

    /**
     * Constructs FileProperties with specified attributes.
     * @param extension file extension
     * @param isReadOnly read-only status
     * @param owner file owner
     * @param group file group
     */
    public FileProperties(String extension, boolean isReadOnly, String owner, String group) {
        this.extension = extension;
        this.isReadOnly = isReadOnly;
        this.owner = owner;
        this.group = group;
    }

    /**
     * Gets the file extension.
     * @return file extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Checks if the file is read-only.
     * @return true if read-only, false otherwise
     */
    public boolean isReadOnly() {
        return isReadOnly;
    }

    /**
     * Gets the file owner.
     * @return owner name
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the file group.
     * @return group name
     */
    public String getGroup() {
        return group;
    }
}