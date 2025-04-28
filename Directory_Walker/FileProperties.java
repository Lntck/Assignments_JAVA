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
}
