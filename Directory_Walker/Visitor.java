package Directory_Walker;

public interface Visitor {
    void visit(Directory dir);
    void visit(File file);
}
