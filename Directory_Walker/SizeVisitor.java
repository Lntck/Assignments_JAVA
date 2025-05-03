package Directory_Walker;

import java.math.BigDecimal;

public class SizeVisitor implements Visitor {
    private BigDecimal totalSize;

    public SizeVisitor() {
        this.totalSize = BigDecimal.ZERO;
    }
    
    @Override
    public void visit(File file) {
        totalSize = totalSize.add(file.getSize());
    }

    @Override
    public void visit(Directory dir) {
    }

    public BigDecimal getTotalSize() {
        return totalSize;
    }
}
