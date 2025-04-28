package Directory_Walker;

import java.math.BigDecimal;

public class File extends Node{
    private BigDecimal size;    
    private FileProperties fileProperties;

    public File(String name, BigDecimal size, FileProperties fileProperties) {
        super(name);
        this.size = size;
        this.fileProperties = fileProperties;
    }
}
