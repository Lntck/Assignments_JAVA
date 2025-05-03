package Directory_Walker;


public class FileProperties {
    private String extension;
    private boolean isReadOnly;
    private String owner;
    private String group;

    public FileProperties(String extension, boolean isReadOnly, String owner, String group) {
        this.extension = extension;
        this.isReadOnly = isReadOnly;
        this.owner = owner;
        this.group = group;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public String getOwner() {
        return owner;
    }

    public String getGroup() {
        return group;
    }
}
