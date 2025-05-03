package Directory_Walker;

public abstract class Node {
    private int parent_id;
    private String name;
    

    public Node(int parent_id, String name) {
        this.parent_id = parent_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parent_id;
    }

    public abstract void accept(Visitor v);
}
