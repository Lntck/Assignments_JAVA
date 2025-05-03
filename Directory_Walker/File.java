package Directory_Walker;

import java.math.BigDecimal;

public class File extends Node{
    private BigDecimal size;    
    private FileProperties fileProperties;

    public File(int parent_id, String name, BigDecimal size, FileProperties fileProperties) {
        super(parent_id, name);
        this.size = size;
        this.fileProperties = fileProperties;
    }

    public BigDecimal getSize() {
        return size;
    }

    public FileProperties getFileProperties() {
        return fileProperties;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
